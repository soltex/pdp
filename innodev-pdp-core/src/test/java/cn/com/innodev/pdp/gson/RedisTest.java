/**
 * 
 */
package cn.com.innodev.pdp.gson;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;
import cn.com.innodev.pdp.community.Community;
import cn.com.innodev.pdp.framework.cache.PlatformCacheRef;

import com.google.gson.Gson;
import com.vanstone.business.serialize.GsonCreator;
import com.vanstone.redis.RedisCallback;
import com.vanstone.redis.RedisTemplate;

/**
 * @author shipeng
 *
 */
@ContextConfiguration(locations = { 
		"classpath*:/core-application-context.xml",
		"classpath*:META-INF/*-application-context-module.xml" }
)
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTest {
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Test
	public void testGetValueByRedis() {
		final String key = "cn.com.innodev.pdp.community.Community_asdas";
		String value = redisTemplate.executeInRedis(PlatformCacheRef.REF_CORE, new RedisCallback<String>() {
			@Override
			public String doInRedis(Jedis jedis) {
				String value = jedis.get(key);
				System.out.println(" ========================================================== ");
				System.out.println(value);
				System.out.println(" ========================================================== ");
				return value;
			}
		});
		Gson gson = GsonCreator.createPretty();
		Community loadCommunity = gson.fromJson(value, Community.class);
		System.out.println(loadCommunity);
		
	}
	
}
