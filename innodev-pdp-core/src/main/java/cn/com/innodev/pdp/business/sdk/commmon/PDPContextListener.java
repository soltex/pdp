/**
 * 
 */
package cn.com.innodev.pdp.business.sdk.commmon;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.innodev.pdp.framework.zk.PlatformZKManager;

import com.vanstone.weixin.client.WeixinClientFactory;

/**
 * @author shipeng
 */
public class PDPContextListener implements ServletContextListener {
	
	private static Logger LOG = LoggerFactory.getLogger(PDPContextListener.class);
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		PlatformZKManager.getInstance().close();
		LOG.info("Platform ZK Manager Close.");
		WeixinClientFactory.getWeixinAPIManager().close();
		LOG.info("Weixin Client Manager Close.");
	}
	
}
