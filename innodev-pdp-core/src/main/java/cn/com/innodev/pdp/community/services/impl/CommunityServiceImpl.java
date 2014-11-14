/**
 * 
 */
package cn.com.innodev.pdp.community.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import redis.clients.jedis.Jedis;
import cn.com.innodev.pdp.community.AlbumType;
import cn.com.innodev.pdp.community.Building;
import cn.com.innodev.pdp.community.Community;
import cn.com.innodev.pdp.community.CommunityImage;
import cn.com.innodev.pdp.community.InfoState;
import cn.com.innodev.pdp.community.ProjectCompany;
import cn.com.innodev.pdp.community.Room;
import cn.com.innodev.pdp.community.persistence.ComBuildingDOMapper;
import cn.com.innodev.pdp.community.persistence.ComCommunityDOMapper;
import cn.com.innodev.pdp.community.persistence.ComCommunityImageDOMapper;
import cn.com.innodev.pdp.community.persistence.ComProjectCompanyDOMapper;
import cn.com.innodev.pdp.community.persistence.ComRoomDOMapper;
import cn.com.innodev.pdp.community.persistence.object.ComBuildingDO;
import cn.com.innodev.pdp.community.persistence.object.ComCommunityDO;
import cn.com.innodev.pdp.community.persistence.object.ComCommunityImageDO;
import cn.com.innodev.pdp.community.persistence.object.ComProjectCompanyDO;
import cn.com.innodev.pdp.community.persistence.object.ComRoomDO;
import cn.com.innodev.pdp.community.services.CommunityService;
import cn.com.innodev.pdp.framework.Constants;
import cn.com.innodev.pdp.framework.bizcommon.CommonService;
import cn.com.innodev.pdp.framework.bizcommon.ImageBean;
import cn.com.innodev.pdp.framework.bizcommon.region.City;
import cn.com.innodev.pdp.framework.cache.PlatformCacheRef;
import cn.com.innodev.pdp.framework.services.AbstractPlatformServiceMgr;
import cn.com.innodev.pdp.framework.zk.NodeUtil;
import cn.com.innodev.pdp.framework.zk.PlatformZKManager;

import com.google.gson.Gson;
import com.vanstone.business.MyAssert4Business;
import com.vanstone.business.ObjectDuplicateException;
import com.vanstone.business.ObjectHasSubObjectException;
import com.vanstone.business.def.BusinessObjectKeyBuilder;
import com.vanstone.business.serialize.GsonCreator;
import com.vanstone.centralserver.common.MyAssert;
import com.vanstone.dal.id.IDBuilder;
import com.vanstone.framework.business.services.ServiceUtil;
import com.vanstone.redis.RedisCallback;
import com.vanstone.redis.RedisTemplate;
import com.vanstone.weedfs.client.RequestResult;
import com.vanstone.weedfs.client.impl.WeedFSClient;

/**
 * @author shipeng
 */
@Service("communityService")
public class CommunityServiceImpl extends AbstractPlatformServiceMgr implements CommunityService {
	
	/** */
	private static final long serialVersionUID = -7947397971808136029L;
	
	private static Logger LOG = LoggerFactory.getLogger(CommunityServiceImpl.class);
	
	@Autowired
	private ComCommunityDOMapper comCommunityDOMapper;
	@Autowired
	private ComProjectCompanyDOMapper comProjectCompanyDOMapper;
	@Autowired
	private ComBuildingDOMapper comBuildingDOMapper;
	@Autowired
	private ComRoomDOMapper comRoomDOMapper;
	@Autowired
	private ComCommunityImageDOMapper comCommunityImageDOMapper;
	@Autowired
	private CommonService commonService;
	@Autowired
	private RedisTemplate redisTemplate;
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.community.services.CommunityService#addCommunity(cn.com.innodev.pdp.community.Community, cn.com.innodev.pdp.community.ProjectCompany)
	 */
	@Override
	public Community addCommunity(final Community community, final ProjectCompany projectCompany) throws ObjectDuplicateException {
		Community loadCommunity = this.getCommunity(community.getId());
		if (loadCommunity != null) {
			throw new ObjectDuplicateException();
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				community.setSysInsertTime(new Date());
				community.setSysUpdateTime(new Date());
				if (projectCompany.getLogoImageBean() != null) {
					community.setInfoState(InfoState.Completed);
				}else{
					community.setInfoState(InfoState.New);
				}
				ComCommunityDO comCommunityDO = BeanUtil.toComCommunityDO(community);
				comCommunityDOMapper.insert(comCommunityDO);
				
				ComProjectCompanyDO comProjectCompanyDO = BeanUtil.toComProjectCompanyDO(projectCompany);
				comProjectCompanyDO.setCommunityId(community.getId());
				comProjectCompanyDOMapper.insert(comProjectCompanyDO);
				
//				创建默认账号
//				AccountObject defaultAccountObject = staffAccountGenerator.generate(community);
//				Staff staff = new Staff(community);
//				staff.setAccountName(defaultAccountObject.getAccountName());
//				staff.setAccountPassword(defaultAccountObject.getPassword());
//				staff.setStaffRole(StaffRole.Project_Company_Admin);
//				staff.setStaffState(StaffState.Active);
//				try {
//					addStaff(staff);
//				} catch (ObjectDuplicateException e) {
//					e.printStackTrace();
//					LOG.error("Staff Default Account name Duplicate [{}]", defaultAccountObject.getAccountName());
//				}
			}
		});
		return this.getCommunity(community.getId());
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.community.services.CommunityService#deleteCommunity(java.lang.String)
	 */
	@Override
	public void deleteCommunity(String id) throws ObjectHasSubObjectException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.community.services.CommunityService#updateCommunityBaseInfo(cn.com.innodev.pdp.community.Community)
	 */
	@Override
	public Community updateCommunityBaseInfo(final Community baseInfoCommunity) {
		final Community loadCommunity = this.getCommunity(baseInfoCommunity.getId());
		if (loadCommunity == null) {
			throw new IllegalArgumentException();
		}
		loadCommunity.setCommunityName(baseInfoCommunity.getCommunityName());
		loadCommunity.setLongitude(baseInfoCommunity.getLongitude());
		loadCommunity.setLatitude(baseInfoCommunity.getLatitude());
		loadCommunity.setContent(baseInfoCommunity.getContent());
		loadCommunity.setSysUpdateTime(new Date());
		loadCommunity.setAvgPrice(baseInfoCommunity.getAvgPrice());
		loadCommunity.setAddress(baseInfoCommunity.getAddress());
		loadCommunity.setCity(baseInfoCommunity.getCity());
		
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				ComCommunityDO model = BeanUtil.toComCommunityDO(loadCommunity);
				comCommunityDOMapper.updateByPrimaryKeyWithBLOBs(model);
			}
		});
		this.expireCommunityCache(loadCommunity.getId());
		return loadCommunity;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.community.services.CommunityService#deleteQRImage(java.lang.String)
	 */
	@Override
	public Community deleteQRImage(final String communityId) {
		MyAssert.hasText(communityId);
		Community loadCommunity = this.getCommunity(communityId);
		if (loadCommunity == null) {
			throw new IllegalArgumentException();
		}
		if (loadCommunity.getQrImageBean() == null) {
			LOG.warn("The community do'es has qr image bean [{}]", communityId);
			return loadCommunity;
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				comCommunityDOMapper.updateQR(communityId, new Date(), null, null, null, null);
			}
		});
		ImageBean qrImageBean = loadCommunity.getQrImageBean();
		WeedFSClient weedFSClient = new WeedFSClient();
		RequestResult result = weedFSClient.delete(qrImageBean.getWeedFile().getFileid());
		if (!result.isSuccess()) {
			LOG.error("DELETE WeedFile ,FILEID : [{}]", qrImageBean.getWeedFile().getFileid());
		}
		this.expireCommunityCache(loadCommunity.getId());
		return this.getCommunity(communityId);
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.community.services.CommunityService#updateQRImage(java.lang.String, cn.com.innodev.pdp.framework.bizcommon.ImageBean)
	 */
	@Override
	public void updateQRImage(final String communityId, final ImageBean imageBean) {
		MyAssert.hasText(communityId);
		MyAssert.notNull(imageBean);
		
		Community loadCommunity = this.getCommunity(communityId);
		if (loadCommunity == null) {
			throw new IllegalArgumentException();
		}
		if (loadCommunity.getQrImageBean() == null) {
			LOG.warn("The community do'es has qr image bean [{}]", communityId);
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				comCommunityDOMapper.updateQR(communityId, new Date(), imageBean.getWeedFile().getFileid(), imageBean.getWeedFile().getExtName(), imageBean.getWidth(), imageBean.getHeight());
			}
		});
		this.expireCommunityCache(loadCommunity.getId());
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.community.services.CommunityService#getCommunities(java.lang.String, int, int)
	 */
	@Override
	public Collection<Community> getCommunities(String key, int offset, int limit) {
		List<String> ids = this.comCommunityDOMapper.selectByKey(key, new RowBounds(offset, limit));
		if (ids == null || ids.size() <= 0) {
			return null;
		}
		Collection<Community> communities = new ArrayList<Community>();
		for (String id : ids) {
			communities.add(this.getCommunity(id));
		}
		return communities;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.community.services.CommunityService#getTotalCommunities(java.lang.String)
	 */
	@Override
	public int getTotalCommunities(String key) {
		return this.comCommunityDOMapper.selectCountByKey(key);
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.community.services.CommunityService#getCommunity(java.lang.String)
	 */
	@Override
	public Community getCommunity(String communityId) {
		MyAssert.hasText(communityId);
		ServiceUtil<Community, String> serviceUtil = new ServiceUtil<Community, String>();
		Community loadCommunity = serviceUtil.getObjectFromRedis(this.redisTemplate, PlatformCacheRef.REF_CORE, Community.class, communityId);
		if (loadCommunity != null) {
			LOG.debug("Load Community from Redis Cache. {}", communityId);
			return loadCommunity;
		}
		CuratorFramework curatorFramework = PlatformZKManager.getInstance().getCuratorFramework();
		InterProcessMutex mutex = new InterProcessMutex(curatorFramework, NodeUtil.buildPlatformLockPath(BusinessObjectKeyBuilder.class2key(Community.class, communityId)));
		try {
			if (mutex.acquire(0, TimeUnit.SECONDS)) {
				//重新load from db
				ComCommunityDO model = this.comCommunityDOMapper.selectByPrimaryKey(communityId);
				if (model == null) {
					return null;
				}
				ComProjectCompanyDO comProjectCompanyDO = this.comProjectCompanyDOMapper.selectByPrimaryKey(communityId);
				ProjectCompany projectCompany = null;
				if (comProjectCompanyDO != null) {
					projectCompany = BeanUtil.toProjectCompany(comProjectCompanyDO);
				}
				City city = commonService.getCity(model.getCityId());
				//communityimages
				Map<AlbumType, Collection<CommunityImage>> imageMap = this.loadCommunityImagesFromDB(communityId);
				//building rooms data map
				Map<Building, Collection<Room>> buildingRoomDataMap = this.loadBuildingRoomsFromDB(communityId);
				
				Community community = BeanUtil.toCommunity(model, city, projectCompany, imageMap, buildingRoomDataMap);
				this.refreshCommunityCache(community, false);
				return community;
			}
			TimeUnit.SECONDS.sleep(Constants.DEFAULT_LOCK_WAIT_TIME);
			return this.getCommunity(communityId);
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
	
	/**
	 * 获取楼号房间号映射对象,从DB中直接获取
	 * @param communityId
	 * @return
	 */
	private Map<Building, Collection<Room>> loadBuildingRoomsFromDB(String communityId) {
		MyAssert.hasText(communityId);
		List<ComBuildingDO> comBuildingDOs = this.comBuildingDOMapper.selectByCommunityId(communityId);
		if (comBuildingDOs == null || comBuildingDOs.size() <=0) {
			return null;
		}
		Map<Building, Collection<Room>> dataMap = new LinkedHashMap<Building, Collection<Room>>();
		for (ComBuildingDO comBuildingDO : comBuildingDOs) {
			Building building = BeanUtil.toBuilding(comBuildingDO);
			Collection<Room> rooms = new ArrayList<Room>();
			dataMap.put(building, rooms);
			List<ComRoomDO> comRoomDOs = this.comRoomDOMapper.selectByBuildingId(comBuildingDO.getId());
			if (comRoomDOs == null || comRoomDOs.size() <=0) {
				continue;
			}
			for (ComRoomDO comRoomDO : comRoomDOs) {
				Room room = BeanUtil.toRoom(comRoomDO, building);
				rooms.add(room);
			}
		}
		return dataMap;
	}
	
	/**
	 * Load community images from DB
	 * @param communityId
	 * @return
	 */
	private Map<AlbumType, Collection<CommunityImage>> loadCommunityImagesFromDB(String communityId) {
		List<ComCommunityImageDO> models = this.comCommunityImageDOMapper.selectByCommunityId(communityId);
		if (models == null || models.size() <=0) {
			return null;
		}
		Map<AlbumType, Collection<CommunityImage>> dataMap = new LinkedHashMap<AlbumType, Collection<CommunityImage>>();
		for (ComCommunityImageDO model : models) {
			//Floor_Plan_Type("户型图",0),Around_Environment("周边环境",1),Traffic_Environment("交通环境",2)
			if (model.getAlbumType().equals(AlbumType.Floor_Plan_Type.getCode())) {
				Collection<CommunityImage> images = dataMap.get(AlbumType.Floor_Plan_Type);
				if (images == null) {
					dataMap.put(AlbumType.Floor_Plan_Type, new ArrayList<CommunityImage>());
				}
				dataMap.get(AlbumType.Floor_Plan_Type).add(BeanUtil.toCommunityImage(model));
			}else if (model.getAlbumType().equals(AlbumType.Around_Environment.getCode())) {
				Collection<CommunityImage> images = dataMap.get(AlbumType.Around_Environment);
				if (images == null) {
					dataMap.put(AlbumType.Around_Environment, new ArrayList<CommunityImage>());
				}
				dataMap.get(AlbumType.Around_Environment).add(BeanUtil.toCommunityImage(model));
			}else if (model.getAlbumType().equals(AlbumType.Traffic_Environment.getCode())) {
				Collection<CommunityImage> images = dataMap.get(AlbumType.Traffic_Environment);
				if (images == null) {
					dataMap.put(AlbumType.Traffic_Environment, new ArrayList<CommunityImage>());
				}
				dataMap.get(AlbumType.Traffic_Environment).add(BeanUtil.toCommunityImage(model));
			}else {
				LOG.error("AlbumType not match [{}]", model.getAlbumType());
			}
		}
		return dataMap;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.community.services.CommunityService#getProjectCompany(int)
	 */
	@Override
	public ProjectCompany getProjectCompany(String communityId) {
		MyAssert.hasText(communityId);
		Community community = this.getCommunity(communityId);
		if (community == null) {
			throw new IllegalArgumentException();
		}
		return community.getProjectCompany();
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.community.services.CommunityService#updateProjectCompany(java.lang.String, java.lang.String)
	 */
	@Override
	public void updateProjectCompany(final String communityId, final String name, final String email) {
		MyAssert.notNull(communityId);
		MyAssert.hasText(name);
		MyAssert.hasText(email);
		
		ProjectCompany projectCompany = this.getProjectCompany(communityId);
		if (projectCompany == null) {
			throw new IllegalArgumentException();
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				ComProjectCompanyDO model = new ComProjectCompanyDO();
				model.setCommunityId(communityId);
				model.setCompanyName(name);
				model.setCompanyEmail(email);
				comProjectCompanyDOMapper.updateByPrimaryKeySelective(model);
			}
		});
		expireCommunityCache(communityId);
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.community.services.CommunityService#deleteProjectCompanyLogoInfo(java.lang.String)
	 */
	@Override
	public void deleteProjectCompanyLogoInfo(final String communityId) {
		MyAssert.notNull(communityId);
		ProjectCompany projectCompany = this.getProjectCompany(communityId);
		if (projectCompany == null) {
			throw new IllegalArgumentException();
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				comProjectCompanyDOMapper.updateLogoFileInfo(communityId, null, null, null, null);
			}
		});
		expireCommunityCache(communityId);
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.community.services.CommunityService#updateProjectCompanyLogoInfo(java.lang.String, cn.com.innodev.pdp.framework.bizcommon.ImageBean)
	 */
	@Override
	public void updateProjectCompanyLogoInfo(final String communityId, final ImageBean imageBean) {
		MyAssert.hasText(communityId);
		MyAssert.notNull(imageBean);
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				comProjectCompanyDOMapper.updateLogoFileInfo(communityId, imageBean.getWeedFile().getFileid(), imageBean.getWeedFile().getExtName(), imageBean.getWidth(), imageBean.getHeight());
			}
		});
		expireCommunityCache(communityId);
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.community.services.CommunityService#addCommunityAlbum(cn.com.innodev.pdp.community.CommunityImage)
	 */
	@Override
	public CommunityImage addCommunityImage(final Community community, final CommunityImage communityImage) {
		MyAssert.notNull(communityImage);
		MyAssert4Business.objectInitialized(community);
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				communityImage.setSysInsertTime(new Date());
				communityImage.setSysUpdateTime(new Date());
				communityImage.setId(IDBuilder.base58Uuid());
				ComCommunityImageDO model = BeanUtil.toComCommunityImageDO(communityImage, community.getId());
				comCommunityImageDOMapper.insert(model);
			}
		});
		expireCommunityCache(community.getId());
		return communityImage;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.community.services.CommunityService#updateCommunityImage(cn.com.innodev.pdp.community.CommunityImage)
	 */
	@Override
	public void updateCommunityImage(final CommunityImage communityImage) {
		MyAssert.notNull(communityImage);
		final ComCommunityImageDO model = this.comCommunityImageDOMapper.selectByPrimaryKey(communityImage.getId());
		if (model == null) {
			throw new IllegalArgumentException();
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				model.setSysUpdateTime(new Date());
				model.setTitle(communityImage.getTitle());
				model.setAlbumType(communityImage.getAlbumType().getCode());
				model.setFloorPlanType(communityImage.getFloorPlanType() != null ? communityImage.getFloorPlanType().getCode() : null);
				model.setImageFileId(communityImage.getImageBean() != null ? communityImage.getImageBean().getWeedFile().getFileid() : null);
				model.setImageFileExt(communityImage.getImageBean() != null ? communityImage.getImageBean().getWeedFile().getExtName() : null);
				model.setImageFileWidth(communityImage.getImageBean() != null ? communityImage.getImageBean().getWidth() : null);
				model.setImageFileHeight(communityImage.getImageBean() != null ? communityImage.getImageBean().getHeight() : null);
				comCommunityImageDOMapper.updateByPrimaryKey(model);
			}
		});
		expireCommunityCache(model.getCommunityId());
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.community.services.CommunityService#deleteCommunityImage(java.lang.String)
	 */
	@Override
	public void deleteCommunityImage(final String imageId) {
		final ComCommunityImageDO model =this.comCommunityImageDOMapper.selectByPrimaryKey(imageId);
		if (model == null) {
			throw new IllegalArgumentException();
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				comCommunityImageDOMapper.deleteByPrimaryKey(imageId);
			}
		});
		CommunityImage loadImage = BeanUtil.toCommunityImage(model);
		WeedFSClient weedFSClient = new WeedFSClient();
		RequestResult requestResult = weedFSClient.delete(loadImage.getImageBean().getWeedFile().getFileid());
		if (requestResult != null && !requestResult.isSuccess()) {
			LOG.error("DELETE Image Failure - WeedFS ID : [{},{}]", loadImage.getImageBean().getWeedFile().getFileid(), loadImage.getImageBean().getWeedFile().getExtName());
		}
		expireCommunityCache(model.getCommunityId());
	}
	
	@Override
	public CommunityImage getCommunityImage(String imageId) {
		MyAssert.hasText(imageId);
		ComCommunityImageDO model = this.comCommunityImageDOMapper.selectByPrimaryKey(imageId);
		if (model == null) {
			return null;
		}
		CommunityImage communityImage = BeanUtil.toCommunityImage(model);
		return communityImage;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.community.services.CommunityService#getCommunityImages(java.lang.String)
	 */
	@Override
	public Map<AlbumType, Collection<CommunityImage>> getCommunityImages(String communityId) {
		Community loadCommunity = this.getCommunity(communityId);
		if (loadCommunity == null) {
			throw new IllegalArgumentException();
		}
		return loadCommunity.getCommunityImages();
	}
	
	@Override
	public Building addBuilding(final Community community, final String buildNo, final String content, final Set<String> roomNos) throws ObjectDuplicateException {
		MyAssert4Business.objectInitialized(community);
		MyAssert.hasText(buildNo);
		MyAssert.notEmpty(roomNos);
		ComBuildingDO comBuildingDO = this.comBuildingDOMapper.selectByRoomNo(community.getId(), buildNo);
		if (comBuildingDO != null) {
			throw new ObjectDuplicateException();
		}
		Integer buildId = this.execute(new TransactionCallback<Integer>() {
			@Override
			public Integer doInTransaction(TransactionStatus arg0) {
				ComBuildingDO comBuildingDO = new ComBuildingDO();
				comBuildingDO.setBuildingNo(buildNo);
				comBuildingDO.setContent(content);
				comBuildingDO.setCommunityId(community.getId());
				comBuildingDOMapper.insert(comBuildingDO);
				Integer buildId = comBuildingDO.getId();
				for (String roomNo : roomNos) {
					ComRoomDO comRoomDO = new ComRoomDO();
					comRoomDO.setBuildingId(buildId);
					comRoomDO.setRoomNo(roomNo);
					comRoomDOMapper.insert(comRoomDO);
				}
				return buildId;
			}
		});
		this.expireCommunityCache(community.getId());
		return this.getBuilding(buildId);
	}
	
	@Override
	public Building updateBaseBuilding(final int buildingId, final String buildNo, final String content) throws ObjectDuplicateException {
		ComBuildingDO comBuildingDO = this.comBuildingDOMapper.selectByRoomNo_NotSelf(buildingId, buildNo);
		if (comBuildingDO != null) {
			throw new ObjectDuplicateException();
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				ComBuildingDO comBuildingDO = new ComBuildingDO();
				comBuildingDO.setId(buildingId);
				comBuildingDO.setBuildingNo(buildNo);
				comBuildingDO.setContent(content);
				comBuildingDOMapper.updateByPrimaryKeySelective(comBuildingDO);
			}
		});
		ComBuildingDO tempComBuildingDO = this.comBuildingDOMapper.selectByPrimaryKey(buildingId);
		expireCommunityCache(tempComBuildingDO.getCommunityId());
		List<ComRoomDO> comRoomDOs = this.comRoomDOMapper.selectByBuildingId(buildingId);
		if (comRoomDOs != null && comRoomDOs.size() >0) {
			final Collection<String> keies = new ArrayList<String>();
			for (ComRoomDO comRoomDO : comRoomDOs) {
				keies.add(BusinessObjectKeyBuilder.class2key(Room.class, comRoomDO.getId()));
			}
			this.redisTemplate.executeInRedis(PlatformCacheRef.REF_CORE, new RedisCallback<String>() {
				@Override
				public String doInRedis(Jedis jedis) {
					jedis.del(keies.toArray(new String[keies.size()]));
					LOG.debug("Batch expire room by building ID [{}]", buildingId);
					return null;
				}
			});
		}
		return BeanUtil.toBuilding(tempComBuildingDO);
	}
	
	@Override
	public void deleteBuilding(final int buildingId) throws ObjectHasSubObjectException {
		ComBuildingDO comBuildingDO = this.comBuildingDOMapper.selectByPrimaryKey(buildingId);
		if (comBuildingDO == null) {
			throw new IllegalArgumentException();
		}
		List<ComRoomDO> comRoomDOs = this.comRoomDOMapper.selectByBuildingId(buildingId);
		if (comRoomDOs != null && comRoomDOs.size() >0) {
			throw new ObjectHasSubObjectException();
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				comBuildingDOMapper.deleteByPrimaryKey(buildingId);
			}
		});
		expireCommunityCache(comBuildingDO.getCommunityId());
	}
	
	@Override
	public Building getBuilding(int buildingId) {
		ComBuildingDO comBuildingDO = this.comBuildingDOMapper.selectByPrimaryKey(buildingId);
		if (comBuildingDO == null) {
			return null;
		}
		Building building = BeanUtil.toBuilding(comBuildingDO);
		return building;
	}
	
	@Override
	public Room addRoom(final int buildingId, final String roomNo) throws ObjectDuplicateException {
		ComBuildingDO comBuildingDO = this.comBuildingDOMapper.selectByPrimaryKey(buildingId);
		if (comBuildingDO == null) {
			throw new IllegalArgumentException();
		}
		ComRoomDO model = this.comRoomDOMapper.selectByBuildingId_RoomNo(buildingId, roomNo);
		if (model != null) {
			throw new ObjectDuplicateException();
		}
		this.execute(new TransactionCallback<Integer>() {
			@Override
			public Integer doInTransaction(TransactionStatus arg0) {
				ComRoomDO comRoomDO = new ComRoomDO();
				comRoomDO.setBuildingId(buildingId);
				comRoomDO.setRoomNo(roomNo);
				comRoomDOMapper.insert(comRoomDO);
				return comRoomDO.getId();
			}
		});
		Room room = new Room(this.getBuilding(buildingId));
		room.setRoomNo(roomNo);
		expireCommunityCache(comBuildingDO.getCommunityId());
		return room;
	}
	
	@Override
	public void updateRoom(final int roomId, final String roomNo) throws ObjectDuplicateException {
		ComRoomDO tempComRoomDO = this.comRoomDOMapper.selectByPrimaryKey(roomId);
		if (tempComRoomDO == null) {
			throw new IllegalArgumentException();
		}
		ComRoomDO comRoomDO = this.comRoomDOMapper.selectByBuildingId_RoomNo_NotSelf(roomNo, roomId);
		if (comRoomDO != null) {
			throw new ObjectDuplicateException();
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				ComRoomDO comRoomDO = new ComRoomDO();
				comRoomDO.setId(roomId);
				comRoomDO.setRoomNo(roomNo);
				comRoomDOMapper.updateByPrimaryKeySelective(comRoomDO);
			}
		});
		ComBuildingDO comBuildingDO = this.comBuildingDOMapper.selectByPrimaryKey(tempComRoomDO.getBuildingId());
		expireRoomCache(roomId);
		expireCommunityCache(comBuildingDO.getCommunityId());
	}
	
	@Override
	public void deleteRoom(final int roomid) throws ObjectHasSubObjectException {
		ComRoomDO comRoomDO = this.comRoomDOMapper.selectByPrimaryKey(roomid);
		if (comRoomDO == null) {
			throw new IllegalArgumentException();
		}
		ComBuildingDO comBuildingDO = this.comBuildingDOMapper.selectByPrimaryKey(comRoomDO.getBuildingId());
		if (comRoomDO.getProprietorCount() > 0) {
			throw new ObjectHasSubObjectException();
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				comRoomDOMapper.deleteByPrimaryKey(roomid);
			}
		});
		expireCommunityCache(comBuildingDO.getCommunityId());
		expireRoomCache(roomid);
	}
	
	@Override
	public Room getRoom(int roomid) {
		ServiceUtil<Room, Integer> serviceUtil = new ServiceUtil<Room, Integer>();
		Room loadRoom = serviceUtil.getObjectFromRedis(redisTemplate, PlatformCacheRef.REF_CORE, Room.class, roomid);
		if (loadRoom != null) {
			return loadRoom;
		}
		CuratorFramework curatorFramework = PlatformZKManager.getInstance().getCuratorFramework();
		InterProcessMutex mutex = new InterProcessMutex(curatorFramework, NodeUtil.buildPlatformLockPath(BusinessObjectKeyBuilder.class2key(Room.class, roomid)));
		try {
			if (mutex.acquire(0, TimeUnit.SECONDS)) {
				ComRoomDO comRoomDO = this.comRoomDOMapper.selectByPrimaryKey(roomid);
				if (comRoomDO == null) {
					return null;
				}
				Building building = this.getBuilding(comRoomDO.getBuildingId());
				if (building == null) {
					throw new IllegalArgumentException();
				}
				Room room = new Room(building);
				room.setRoomNo(comRoomDO.getRoomNo());
				room.setId(roomid);
				serviceUtil.setObjectToRedis(redisTemplate, PlatformCacheRef.REF_CORE, room);
				return room;
			}
			TimeUnit.SECONDS.sleep(Constants.DEFAULT_LOCK_WAIT_TIME);
			return this.getRoom(roomid);
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
	
	@Override
	public Map<Integer, Room> getRooms(final Collection<Integer> ids) {
		if (ids == null || ids.size() <=0) {
			return null;
		}
		List<String> values = this.redisTemplate.executeInRedis(PlatformCacheRef.REF_CORE, new RedisCallback<List<String>>() {
			@Override
			public List<String> doInRedis(Jedis jedis) {
				List<String> keies = new ArrayList<String>();
				for (Integer id : ids) {
					keies.add(BusinessObjectKeyBuilder.class2key(Room.class, id));
				}
				List<String> values = jedis.mget(keies.toArray(new String[keies.size()]));
				return values;
			}
		});
		
		Map<Integer, Room> roomMap = new LinkedHashMap<Integer, Room>();
		Iterator<Integer> iterator = ids.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			Integer id = iterator.next();
			String value = values.get(i);
			Room room = null;
			if (value == null || values.equals("")) {
				room = this.getRoom(id);
				if (room == null) {
					throw new IllegalArgumentException("room id not found " + id);
				}
			}else{
				Gson gson = GsonCreator.create();
				room = gson.fromJson(value, Room.class);
			}
			roomMap.put(id, room);
			i++;
		}
		return roomMap;
	}
	
	@Override
	public void updateRoomProprietorCount(final int roomid, final int count) {
		Room loadRoom = this.getRoom(roomid);
		if (loadRoom == null) {
			throw new IllegalArgumentException();
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				ComRoomDO model = new ComRoomDO();
				model.setId(roomid);
				model.setProprietorCount(count);
				comRoomDOMapper.updateByPrimaryKeySelective(model);
			}
		});
		ComBuildingDO comBuildingDO = this.comBuildingDOMapper.selectByPrimaryKey(loadRoom.getBuilding().getId());
		expireCommunityCache(comBuildingDO.getCommunityId());
		expireRoomCache(roomid);
	}
	
	@Override
	public Map<Building, Collection<Room>> getBuildingDataMap(String communityId) {
		Community loadCommunity = this.getCommunity(communityId);
		if (loadCommunity == null) {
			throw new IllegalArgumentException();
		}
		return loadCommunity.getBuildingRoomsMap();
	}

	@Override
	public void expireCommunityCache(String communityId) {
		ServiceUtil<Community, String> serviceUtil = new ServiceUtil<Community, String>();
		serviceUtil.deleteFromRedis(this.redisTemplate, PlatformCacheRef.REF_CORE, Community.class, communityId);
		LOG.debug("Expire Community In Redis Cache, {}", communityId);
	}
	
	@Override
	public void expireRoomCache(int roomId) {
		ServiceUtil<Room, Integer> serviceUtil = new ServiceUtil<Room, Integer>();
		serviceUtil.deleteFromRedis(this.redisTemplate, PlatformCacheRef.REF_CORE, Room.class, roomId);
		LOG.debug("Expire Room In Redis Cache, {}", roomId);
	}
	
	/**
	 * 刷新Cache中的Community
	 * @param newCommunity
	 */
	private void refreshCommunityCache(final Community newCommunity, boolean expirefirst) {
		MyAssert.notNull(newCommunity);
		ServiceUtil<Community, String> serviceUtil = new ServiceUtil<Community, String>();
		if (expirefirst) {
			serviceUtil.deleteFromRedis(this.redisTemplate, PlatformCacheRef.REF_CORE, Community.class, newCommunity.getId());
		}
//		this.redisTemplate.executeInRedis(PlatformCacheRef.REF_CORE, new RedisCallback<Community>() {
//			@Override
//			public Community doInRedis(Jedis jedis) {
//				Gson gson = GsonCreatorOfCommunity.createCommunityGson();
//				String value = gson.toJson(newCommunity);
//				String key = BusinessObjectKeyBuilder.class2key(Community.class, newCommunity.getId());
//				jedis.set(key.getBytes(Constants.SYS_CHARSET), value.getBytes(Constants.SYS_CHARSET));
//				return null;
//			}
//		});
		serviceUtil.setObjectToRedis(this.redisTemplate, PlatformCacheRef.REF_CORE, newCommunity);
	}
}