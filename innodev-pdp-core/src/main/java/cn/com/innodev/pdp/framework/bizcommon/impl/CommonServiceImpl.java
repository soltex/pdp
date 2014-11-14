/**
 * 
 */
package cn.com.innodev.pdp.framework.bizcommon.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import cn.com.innodev.pdp.framework.Constants;
import cn.com.innodev.pdp.framework.bizcommon.CommonService;
import cn.com.innodev.pdp.framework.bizcommon.persistence.SysCityDOMapper;
import cn.com.innodev.pdp.framework.bizcommon.persistence.SysProvinceDOMapper;
import cn.com.innodev.pdp.framework.bizcommon.persistence.object.QuerySysProvinceResultMap;
import cn.com.innodev.pdp.framework.bizcommon.persistence.object.SysCityDO;
import cn.com.innodev.pdp.framework.bizcommon.persistence.object.SysProvinceDO;
import cn.com.innodev.pdp.framework.bizcommon.region.City;
import cn.com.innodev.pdp.framework.bizcommon.region.Province;
import cn.com.innodev.pdp.framework.bizcommon.region.event.AbstractRelCityDataEventListener;
import cn.com.innodev.pdp.framework.bizcommon.region.event.RelCityDataEvent;
import cn.com.innodev.pdp.framework.cache.PlatformCacheRef;
import cn.com.innodev.pdp.framework.repo.BusinessEventListener;
import cn.com.innodev.pdp.framework.repo.GlobalRepo;
import cn.com.innodev.pdp.framework.services.AbstractPlatformServiceMgr;
import cn.com.innodev.pdp.framework.zk.NodeUtil;
import cn.com.innodev.pdp.framework.zk.PlatformZKManager;

import com.vanstone.business.ObjectDuplicateException;
import com.vanstone.business.ObjectHasSubObjectException;
import com.vanstone.business.def.BusinessObjectKeyBuilder;
import com.vanstone.common.MyAssert;
import com.vanstone.common.util.PinyinUtil;
import com.vanstone.framework.business.services.ServiceUtil;
import com.vanstone.redis.RedisTemplate;

/**
 * @author shipeng
 */
@Service("commonService")
public class CommonServiceImpl extends AbstractPlatformServiceMgr implements CommonService {
	
	/** */
	private static final long serialVersionUID = 4680773706189038958L;
	
	private static final Logger LOG = LoggerFactory.getLogger(CommonServiceImpl.class);
	
	@Autowired
	private SysProvinceDOMapper sysProvinceDOMapper;
	@Autowired
	private SysCityDOMapper sysCityDOMapper;
	@Autowired
	private RedisTemplate redisTemplate;
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.framework.common.CommonService#addProvince(cn.com.innodev.pdp.framework.common.Province)
	 */
	@Override
	public Province addProvince(final Province province)  throws ObjectDuplicateException {
		MyAssert.notNull(province);
		SysProvinceDO tempModel = this.sysProvinceDOMapper.selectByTitle(province.getTitle());
		if (tempModel != null) {
			throw new ObjectDuplicateException();
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				char firstLetter = PinyinUtil.firstLetterOfString(province.getTitle()).charAt(0);
				province.setFirstLetter(firstLetter);
				SysProvinceDO sysProvinceDO = BeanUtil.toSysProvinceDO(province);
				sysProvinceDOMapper.insert(sysProvinceDO);
				province.setId(sysProvinceDO.getId());
			}
		});
		return province;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.framework.common.CommonService#updateProvince(cn.com.innodev.pdp.framework.common.Province)
	 */
	@Override
	public Province updateProvince(final Province province) throws ObjectDuplicateException{
		Province loadProvince = this.getProvince(province.getId());
		if (loadProvince == null) {
			throw new IllegalArgumentException();
		}
		
		SysProvinceDO tempModel = this.sysProvinceDOMapper.selectByTitle_NotSelf(province.getId(), province.getTitle());
		if (tempModel != null) {
			throw new ObjectDuplicateException();
		}
		
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				char firstLetter = PinyinUtil.firstLetterOfString(province.getTitle()).charAt(0);
				province.setFirstLetter(firstLetter);
				SysProvinceDO sysProvinceDO = BeanUtil.toSysProvinceDO(province);
				sysProvinceDOMapper.updateByPrimaryKey(sysProvinceDO);
			}
		});
		
		ServiceUtil<Province, Integer> provinceServiceUtil = new ServiceUtil<Province, Integer>();
		provinceServiceUtil.deleteFromRedis(redisTemplate, PlatformCacheRef.REF_CORE, province);
		Collection<City> cities = this.getCities(province.getId());
		ServiceUtil<City, Integer> cityServiceUtil = new ServiceUtil<City, Integer>();
		if (cities != null && cities.size() >0) {
			for (City city : cities) {
				cityServiceUtil.deleteFromRedis(redisTemplate, PlatformCacheRef.REF_CORE, city);
			}
		}
		return province;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.framework.common.CommonService#getProvince(int)
	 */
	@Override
	public Province getProvince(int provinceId) {
		ServiceUtil<Province, Integer> serviceUtil = new ServiceUtil<Province, Integer>();
		Province loadProvince = serviceUtil.getObjectFromRedis(this.redisTemplate, PlatformCacheRef.REF_CORE, Province.class, provinceId);
		if (loadProvince != null) {
			return loadProvince;
		}
		
		CuratorFramework curatorFramework = PlatformZKManager.getInstance().getCuratorFramework();
		InterProcessMutex mutex = new InterProcessMutex(curatorFramework, NodeUtil.buildPlatformLockPath(BusinessObjectKeyBuilder.class2key(Province.class, provinceId)));
		try {
			boolean isok = mutex.acquire(0, TimeUnit.SECONDS);
			if (!isok) {
				TimeUnit.SECONDS.sleep(Constants.DEFAULT_LOCK_WAIT_TIME);
				return getProvince(provinceId);
			}
			SysProvinceDO sysProvinceDO = this.sysProvinceDOMapper.selectByPrimaryKey(provinceId);
			if (sysProvinceDO == null) {
				return null;
			}
			loadProvince = BeanUtil.toProvince(sysProvinceDO);
			serviceUtil.setObjectToRedis(this.redisTemplate, PlatformCacheRef.REF_CORE, loadProvince);
			return loadProvince;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (mutex != null && mutex.isAcquiredInThisProcess()) {
					mutex.release();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public Province getProvinceByTitle(String title) {
		MyAssert.hasText(title);
		SysProvinceDO model = this.sysProvinceDOMapper.selectByTitle(title);
		if (model == null) {
			return null;
		}
		return BeanUtil.toProvince(model);
	}

	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.framework.common.CommonService#deleteProvince(int)
	 */
	@Override
	public void deleteProvince(final int provinceId) throws ObjectHasSubObjectException {
		Province loadProvince = this.getProvince(provinceId);
		if (loadProvince == null) {
			throw new IllegalArgumentException();
		}
		Collection<City> cities = this.getCities(provinceId);
		if (cities != null && cities.size() >0) {
			throw new ObjectHasSubObjectException();
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				sysProvinceDOMapper.deleteByPrimaryKey(provinceId);
			}
		});
		ServiceUtil<Province, Integer> serviceUtil = new ServiceUtil<Province, Integer>();
		serviceUtil.deleteFromRedis(this.redisTemplate, PlatformCacheRef.REF_CORE, Province.class, provinceId);
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.framework.common.CommonService#getProvinces()
	 */
	@Override
	public Collection<Province> getProvinces() {
		List<SysProvinceDO> sysProvinceDOs = this.sysProvinceDOMapper.selectAll();
		if (sysProvinceDOs == null || sysProvinceDOs.size() <=0) {
			return null;
		}
		Collection<Province> provinces = new ArrayList<Province>();
		for (SysProvinceDO model : sysProvinceDOs) {
			provinces.add(BeanUtil.toProvince(model));
		}
		return provinces;
	}
	
	@Override
	public Collection<Province> getProvincesWithCityCount() {
		List<QuerySysProvinceResultMap> rms = this.sysProvinceDOMapper.selectAllWithCityCount();
		if (rms == null || rms.size() <=0) {
			return null;
		}
		Collection<Province> provinces = new ArrayList<Province>();
		for (QuerySysProvinceResultMap rm : rms) {
			Province province = new Province(rm.getCityCount());
			province.setFirstLetter(rm.getFirstLetter().charAt(0));
			province.setId(rm.getId());
			province.setTitle(rm.getTitle());
			provinces.add(province);
		}
		return provinces;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.framework.common.CommonService#addCity(cn.com.innodev.pdp.framework.common.City)
	 */
	@Override
	public City addCity(final City city) throws ObjectDuplicateException {
		MyAssert.notNull(city);
		if (city.getProvince() == null) {
			throw new IllegalArgumentException();
		}
		SysCityDO tempModel = this.sysCityDOMapper.selectByProvinceId_Title(city.getProvince().getId(), city.getTitle());
		if (tempModel != null) {
			throw new ObjectDuplicateException();
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				city.setFirstLetter(PinyinUtil.firstLetterOfString(city.getTitle()).charAt(0));
				SysCityDO sysCityDO = BeanUtil.toSysCityDO(city);
				sysCityDOMapper.insert(sysCityDO);
				city.setId(sysCityDO.getId());
			}
		});
		return city;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.framework.common.CommonService#updateCity(cn.com.innodev.pdp.framework.common.City)
	 */
	@Override
	public City updateCity(final City city) throws ObjectDuplicateException {
		MyAssert.notNull(city);
		if (city.getProvince() == null) {
			throw new IllegalArgumentException();
		}
		City loadCity = this.getCity(city.getId());
		if (loadCity == null) {
			throw new IllegalArgumentException();
		}
		SysCityDO tempModel = this.sysCityDOMapper.selectByProvinceId_Title_NotSelf(city.getProvince().getId(), city.getTitle(), city.getId());
		if (tempModel != null) {
			throw new ObjectDuplicateException();
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				city.setFirstLetter(PinyinUtil.firstLetterOfString(city.getTitle()).charAt(0));
				SysCityDO model = BeanUtil.toSysCityDO(city);
				sysCityDOMapper.updateByPrimaryKey(model);
			}
		});
		ServiceUtil<City, Integer> serviceUtil = new ServiceUtil<City, Integer>();
		serviceUtil.deleteFromRedis(this.redisTemplate, PlatformCacheRef.REF_CORE, City.class, city.getId());
		return this.getCity(city.getId());
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.framework.common.CommonService#getCity(int)
	 */
	@Override
	public City getCity(int cityId) {
		ServiceUtil<City, Integer> serviceUtil = new ServiceUtil<City, Integer>();
		City loadCity = serviceUtil.getObjectFromRedis(redisTemplate, PlatformCacheRef.REF_CORE, City.class, cityId);
		if (loadCity != null) {
			LOG.debug("Load city from redis cache : {}", cityId);
			return loadCity;
		}
		CuratorFramework curatorFramework = PlatformZKManager.getInstance().getCuratorFramework();
		InterProcessMutex mutex = new InterProcessMutex(curatorFramework, NodeUtil.buildPlatformLockPath(BusinessObjectKeyBuilder.class2key(City.class, cityId)));
		try {
			if (mutex.acquire(0, TimeUnit.SECONDS)) {
				SysCityDO model = this.sysCityDOMapper.selectByPrimaryKey(cityId);
				if (model == null) {
					return null;
				}
				loadCity = BeanUtil.toCity(model, this.getProvince(model.getProvinceId()));
				serviceUtil.setObjectToRedis(redisTemplate, PlatformCacheRef.REF_CORE, loadCity);
				return loadCity;
			}
			TimeUnit.SECONDS.sleep(Constants.DEFAULT_LOCK_WAIT_TIME);
			return this.getCity(cityId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (mutex != null && mutex.isAcquiredInThisProcess()) {
				try {
					mutex.release();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.framework.common.CommonService#deleteCity(int)
	 */
	@Override
	public void deleteCity(final int cityId) throws ObjectHasSubObjectException{
		City loadCity = this.getCity(cityId);
		if (loadCity == null) {
			throw new IllegalArgumentException();
		}
		Iterator<BusinessEventListener> listenerIterator = GlobalRepo.getInstance().getBusinessEventListenersIteratorByGroup(AbstractRelCityDataEventListener.GROUP);
		if (listenerIterator != null) {
			RelCityDataEvent event = new RelCityDataEvent(this, cityId);
			while(listenerIterator.hasNext()) {
				AbstractRelCityDataEventListener listener = (AbstractRelCityDataEventListener)listenerIterator.next();
				int count = listener.onCommunityCount(event);
				if (count >0) {
					throw new ObjectHasSubObjectException();
				}
			}
		}
		
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				CommonServiceImpl.this.sysCityDOMapper.deleteByPrimaryKey(cityId);
			}
		});
		
		ServiceUtil<City, Integer> serviceUtil = new ServiceUtil<City, Integer>();
		serviceUtil.deleteFromRedis(redisTemplate, PlatformCacheRef.REF_CORE, City.class, cityId);
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.framework.common.CommonService#getCities(int)
	 */
	@Override
	public Collection<City> getCities(int provinceId) {
		Province province = this.getProvince(provinceId);
		if (province == null) {
			return null;
		}
		List<SysCityDO> sysCityDOs = this.sysCityDOMapper.selectByProvinceId(provinceId);
		if (sysCityDOs == null || sysCityDOs.size() <0) {
			return null;
		}
		Collection<City> cities = new ArrayList<City>();
		for (SysCityDO model : sysCityDOs) {
			cities.add(BeanUtil.toCity(model, province));
		}
		return cities;
	}
	
	@Override
	public Collection<City> getCities() {
		List<SysCityDO> sysCityDOs = this.sysCityDOMapper.selectAll();
		if (sysCityDOs == null || sysCityDOs.size() <=0) {
			return null;
		}
		Collection<City> cities = new ArrayList<City>();
		for (SysCityDO sysCityDO : sysCityDOs) {
			Province province = this.getProvince(sysCityDO.getProvinceId());
			if (province == null) {
				throw new IllegalArgumentException("ProvinceID IS Null ," + sysCityDO.getProvinceId());
			}
			City city = BeanUtil.toCity(sysCityDO, province);
			cities.add(city);
		}
		return cities;
	}
	
	@Override
	public Collection<City> getCitiesWithBusinessObjectCount(int provinceId) {
		Province province = this.getProvince(provinceId);
		if (province == null) {
			return null;
		}
		List<SysCityDO> sysCityDOs = this.sysCityDOMapper.selectByProvinceId(provinceId);
		if (sysCityDOs == null || sysCityDOs.size() <0) {
			return null;
		}
		Collection<City> cities = new ArrayList<City>();
		for (SysCityDO model : sysCityDOs) {
			Iterator<BusinessEventListener> listenerIterator = GlobalRepo.getInstance().getBusinessEventListenersIteratorByGroup(AbstractRelCityDataEventListener.GROUP);
			int communityCount = 0;
			if (listenerIterator != null) {
				RelCityDataEvent event = new RelCityDataEvent(this, model.getId());
				while (listenerIterator.hasNext()) {
					AbstractRelCityDataEventListener listener = (AbstractRelCityDataEventListener)listenerIterator.next();
					communityCount = communityCount + listener.onCommunityCount(event);
				}
			}
			cities.add(BeanUtil.toCity(model, province, communityCount));
		}
		return cities;
	}
	
	@Override
	public Collection<City> getCitiesWithBusinessObjectCount() {
		//TODO 暂未实现
		return null;
	}
	
}
