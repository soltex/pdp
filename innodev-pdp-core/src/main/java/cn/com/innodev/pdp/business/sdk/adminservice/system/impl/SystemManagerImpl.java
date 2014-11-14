/**
 * 
 */
package cn.com.innodev.pdp.business.sdk.adminservice.system.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import cn.com.innodev.pdp.business.sdk.adminservice.system.CacheValueBean;
import cn.com.innodev.pdp.business.sdk.adminservice.system.SystemManager;
import cn.com.innodev.pdp.framework.cache.PlatformCacheRef;
import cn.com.innodev.pdp.framework.services.AbstractPlatformServiceMgr;

import com.vanstone.centralserver.common.MyAssert;
import com.vanstone.centralserver.common.conf.VanstoneConf;
import com.vanstone.elasticsearch.ElasticsearchTemplate;
import com.vanstone.elasticsearch.conf.ESConfig;
import com.vanstone.fs.Setting;
import com.vanstone.redis.RedisCallback;
import com.vanstone.redis.RedisCallbackWithoutResult;
import com.vanstone.redis.RedisTemplate;
import com.vanstone.redis.conf.RedisPoolConf;
import com.vanstone.weedfs.conf.WeedFSConf;

/**
 * @author shipeng
 */
@Service("systemManager")
public class SystemManagerImpl extends AbstractPlatformServiceMgr implements SystemManager {
	
	/** */
	private static final long serialVersionUID = -1042429156914785319L;
	
	private static Logger LOG = LoggerFactory.getLogger(SystemManagerImpl.class);
	
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.business.sdk.systemservice.PlatformCacheManager#getTotalCacheKeies()
	 */
	@Override
	public long getTotalCacheKeies() {
		return this.redisTemplate.executeInRedis(PlatformCacheRef.REF_CORE, new RedisCallback<Long>() {
			@Override
			public Long doInRedis(Jedis jedis) {
				Set<String> keies = jedis.keys("*");
				if (keies == null || keies.size() <0) {
					return 0L;
				}
				return (long)keies.size();
			}
		});
	}
	
	@Override
	public Collection<CacheValueBean> getFuzzyCacheBeans(final String fuzzyKey) {
		return this.redisTemplate.executeInRedis(PlatformCacheRef.REF_CORE, new RedisCallback< Collection<CacheValueBean>>() {
			@Override
			public  Collection<CacheValueBean> doInRedis(Jedis jedis) {
				String key = fuzzyKey != null && !fuzzyKey.equals("") ? fuzzyKey : "*";
				Set<String> keies = jedis.keys(key);
				if (keies == null || keies.size() <0) {
					return null;
				}
				List<CacheValueBean> beans = new ArrayList<CacheValueBean>();
				List<String> values = jedis.mget(keies.toArray(new String[keies.size()]));
				int i = 0;
				Iterator<String> iterator = keies.iterator();
				while (iterator.hasNext()) {
					String k = iterator.next();
					String v = values.get(i);
					CacheValueBean bean = new CacheValueBean();
					bean.setKey(k);
					bean.setValue(v);
					beans.add(bean);
					i++;
				}
				return beans;
			}
		});
	}

	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.business.sdk.systemservice.PlatformCacheManager#getCacheStringValue(java.lang.String)
	 */
	@Override
	public String getCacheStringValue(final String key) {
		MyAssert.hasText(key);
		return this.redisTemplate.executeInRedis(PlatformCacheRef.REF_CORE, new RedisCallback<String>() {
			@Override
			public String doInRedis(Jedis jedis) {
				return jedis.get(key);
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.business.sdk.systemservice.PlatformCacheManager#deleteByKey(java.lang.String)
	 */
	@Override
	public void deleteByKey(final String key) {
		MyAssert.hasText(key);
		this.redisTemplate.executeInRedis(PlatformCacheRef.REF_CORE, new RedisCallbackWithoutResult() {
			@Override
			public void doInRedisWithoutResult(Jedis jedis) {
				jedis.del(key);
			}
		});
	}

	@Override
	public Setting getLocalFSInfo() {
		return Setting.getInstance();
	}
	
	@Override
	public WeedFSConf getWeedFSInfo() {
		return WeedFSConf.getWeedFSConf();
	}
	
	@Override
	public ESConfig getElasticsearchInfo() {
		return this.elasticsearchTemplate.getEsConfig();
	}
	
	@Override
	public Map<String,RedisPoolConf> getRedisInfo() {
		return this.redisTemplate.getConfs();
	}
	
	@Override
	public VanstoneConf getCentralServerInfo() {
		return VanstoneConf.getInstance();
	}

	@Override
	public void flushAllOfRedis() {
		PlatformCacheRef[] refs = PlatformCacheRef.values();
		for (PlatformCacheRef ref : refs) {
			this.redisTemplate.flushAll(ref);
			LOG.info("Flush All Objects in Redis {}", ref.getId());
		}
	}
}
