/**
 * 
 */
package cn.com.innodev.pdp.framework.bizcommon.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import cn.com.innodev.pdp.framework.Constants;
import cn.com.innodev.pdp.framework.bizcommon.region.City;
import cn.com.innodev.pdp.framework.bizcommon.region.Province;
import cn.com.innodev.pdp.framework.bizcommon.region.Region;
import cn.com.innodev.pdp.framework.cache.PlatformCacheRef;
import cn.com.innodev.pdp.framework.services.AbstractPlatformServiceMgr;

import com.vanstone.framework.business.services.ServiceUtil;
import com.vanstone.redis.RedisCallbackWithoutResult;
import com.vanstone.redis.RedisTemplate;

/**
 * @author shipeng
 */
@Service("commonCacheService")
@Deprecated
public class CommonCacheService extends AbstractPlatformServiceMgr {

	/** */
	private static final long serialVersionUID = -4440123421688994331L;
	
	private static Logger LOG = LoggerFactory.getLogger(CommonCacheService.class);
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	/**
	 * 添加Region对象到缓冲中
	 * @param region
	 */
	public void saveRegionObjectToCache(Region region) {
		if (region instanceof Province) {
			LOG.info("Write Province Object to Redis,[{}]" + region.getId());
			Province province = (Province)region;
			ServiceUtil<Province, String> serviceUtil = new ServiceUtil<Province, String>();
			serviceUtil.setObjectToRedis(this.redisTemplate, PlatformCacheRef.REF_CORE , buildProvinceObjectCacheKey(region.getId()), province);
		} else if (region instanceof City) {
			LOG.info("Write City Object to Redis,[{}]" + region.getId());
			City city = (City)region;
			ServiceUtil<City, String> serviceUtil = new ServiceUtil<City, String>();
			serviceUtil.setObjectToRedis(this.redisTemplate, PlatformCacheRef.REF_CORE , buildCityObjectCacheKey(region.getId()), city);
		}
		this.flushRegionObjects();
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Region> T getRegionById(int regionId, Class<T> clazz) {
		if (clazz.isAssignableFrom(Province.class)) {
			//省份信息
			ServiceUtil<Province, String> serviceUtil = new ServiceUtil<Province, String>();
			String key = this.buildProvinceObjectCacheKey(regionId);
			return (T)serviceUtil.getObjectFromRedisByKey(this.redisTemplate, PlatformCacheRef.REF_CORE, Province.class, key);
		}
		if (clazz.isAssignableFrom(City.class)) {
			ServiceUtil<City, String> serviceUtil = new ServiceUtil<City, String>();
			String key = this.buildCityObjectCacheKey(regionId);
			return (T)serviceUtil.getObjectFromRedisByKey(this.redisTemplate, PlatformCacheRef.REF_CORE, City.class, key);
		}
		throw new IllegalArgumentException("Province or city not match.");
	}
	
	public String buildProvinceObjectCacheKey(Integer id) {
		return Constants.COMMON_REGION_CACHE_KEY_REPFIX + Constants.COMMON_REGION_PROVINCE_OBJECT_NAME + id;
	}
	
	public String buildCityObjectCacheKey(Integer id) {
		return Constants.COMMON_REGION_CACHE_KEY_REPFIX + Constants.COMMON_REGION_CITY_OBJECT_NAME + id;
	}
	
	public String buildAllProvinceObjectsCacheKey() {
		return Constants.COMMON_REGIONLIST_CACHE_KEY_PREFIX + Constants.COMMON_REGION_PROVINCE_OBJECT_NAME;
	}
	
	/**
	 * 清理Region相关缓冲
	 */
	public void flushRegionObjects() {
		this.redisTemplate.executeInRedis(PlatformCacheRef.REF_CORE, new RedisCallbackWithoutResult() {
			@Override
			public void doInRedisWithoutResult(Jedis jedis) {
				Set<String> keies = jedis.keys(Constants.COMMON_REGION_CACHE_ALL);
				if (keies != null && keies.size() >0) {
					jedis.del(keies.toArray(new String[keies.size()]));
					LOG.info("Flush Region Objects . [{}}" , Constants.COMMON_REGION_CACHE_ALL);
 				}
			}
		});
	}
}
