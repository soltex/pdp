/**
 * 
 */
package cn.com.innodev.pdp.business.sdk.adminservice.system;

import java.util.Collection;
import java.util.Map;

import com.vanstone.centralserver.common.conf.VanstoneConf;
import com.vanstone.elasticsearch.conf.ESConfig;
import com.vanstone.fs.Setting;
import com.vanstone.redis.conf.RedisPoolConf;
import com.vanstone.weedfs.conf.WeedFSConf;

/**
 * 平台缓冲管理
 * @author shipeng
 */
public interface SystemManager {
	
	public static final String SERVICE = "systemManager";
	
	/**
	 * 获取全部Cache数量
	 * @return
	 */
	long getTotalCacheKeies();
	
	/**
	 * 获取模糊CacheKey数量
	 * @return
	 */
	Collection<CacheValueBean> getFuzzyCacheBeans(String fuzzyKey);
	
	/**
	 * 获取Cache String Value
	 * @param key
	 * @return
	 */
	String getCacheStringValue(String key);
	
	/**
	 * 清理Redis
	 */
	void flushAllOfRedis();
	
	/**
	 * 通过Key删除
	 * @param key
	 */
	void deleteByKey(String key);
	
	/**
	 * 获取LocalFsInfo
	 * @return
	 */
	Setting getLocalFSInfo();
	
	/**
	 * 获取WeedFSInfo
	 * @return
	 */
	WeedFSConf getWeedFSInfo();
	
	/**
	 * 获取ElasticsearchInfo
	 * @return
	 */
	ESConfig getElasticsearchInfo();
	
	/**
	 * 获取RedisInfo
	 * @return
	 */
	Map<String,RedisPoolConf> getRedisInfo();
	
	/**
	 * 获取CentralServerInfo
	 * @return
	 */
	VanstoneConf getCentralServerInfo();
	
}
