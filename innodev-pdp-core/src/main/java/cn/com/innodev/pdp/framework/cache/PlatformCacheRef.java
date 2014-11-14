/**
 * 
 */
package cn.com.innodev.pdp.framework.cache;

import com.vanstone.redis.RedisIdDefine;

/**
 * 平台缓冲引用定义
 * @author shipeng
 */
public enum PlatformCacheRef implements RedisIdDefine {
	
	REF_CORE;
	;
	
	@Override
	public String getId() {
		return this.toString();
	}
	
}
