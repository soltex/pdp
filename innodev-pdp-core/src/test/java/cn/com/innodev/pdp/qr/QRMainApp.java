/**
 * 
 */
package cn.com.innodev.pdp.qr;

import java.io.File;

import redis.clients.jedis.Jedis;
import cn.com.innodev.pdp.framework.Constants;

import com.google.gson.Gson;
import com.vanstone.business.serialize.GsonCreator;
import com.vanstone.centralserver.common.weixin.WeixinException;
import com.vanstone.centralserver.common.weixin.wrap.qrcode.Ticket;
import com.vanstone.framework.business.services.ServiceUtil;
import com.vanstone.redis.RedisCallbackWithoutResult;
import com.vanstone.redis.RedisTemplate;
import com.vanstone.weixin.client.IWeixinAPIManager;
import com.vanstone.weixin.client.WeixinClientFactory;

/**
 * 
 * @author shipeng
 */
public class QRMainApp {
	
	public static void main(String[] args) {
		IWeixinAPIManager weixinAPIManager = WeixinClientFactory.getWeixinAPIManager();
		try {
			Ticket ticket = weixinAPIManager.getTmpQCTicket(Constants.WEIXIN_SERVICE_NUM, 1800, -2147483648);
			Gson gson = GsonCreator.createPretty();
			System.out.println(gson.toJson(ticket));
			weixinAPIManager.downloadQCImage(ticket.getTicket(), new File("d:/aaa.jpg"));
		} catch (WeixinException e) {
			System.out.println(e.getErrorCode().getDesc() + " -- " + e.getErrorCode().getCode());
			e.printStackTrace();
		}
		weixinAPIManager.close();
		
		RedisTemplate redisTemplate = null;
//		ServiceUtil<Community, String> serviceUtil = new ServiceUtil<Community, String>();
//		serviceUtil.deleteFromRedis(redisTemplate, redisIdDefine, key);
//		BusinessObjectKeyBuilder.class2key(Community.class, "asdasd");
//		redisTemplate.executeInRedis(arg0, new RedisCallbackWithoutResult() {
//			@Override
//			public void doInRedisWithoutResult(Jedis arg0) {
//				
//			}
//		});
		
		//主要业务对象单体对象存储
		ServiceUtil<UserBean, String> serviceUtil = new ServiceUtil<UserBean, String>();
		//
		redisTemplate.executeInRedis(null, new RedisCallbackWithoutResult() {
			@Override
			public void doInRedisWithoutResult(Jedis jedis) {
			}
		});
	}
}
