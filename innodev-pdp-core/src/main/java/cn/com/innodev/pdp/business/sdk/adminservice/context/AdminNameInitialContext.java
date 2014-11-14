/**
 * 
 */
package cn.com.innodev.pdp.business.sdk.adminservice.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vanstone.business.ObjectDuplicateException;
import com.vanstone.framework.business.ServiceManagerFactory;

import cn.com.innodev.pdp.admin.Admin;
import cn.com.innodev.pdp.admin.services.AdminService;
import cn.com.innodev.pdp.framework.Constants;

/**
 * @author shipeng
 *
 */
public class AdminNameInitialContext implements ServletContextListener {

	private static Logger LOG = LoggerFactory.getLogger(AdminNameInitialContext.class);
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		AdminService adminService = ServiceManagerFactory.getInstance().getService(AdminService.SERVICE);
		Admin admin = new Admin();
		admin.setAdminName(Constants.DEFAULT_ADMIN_NAME);
		admin.setAdminPwd(Constants.DEFAULT_ADMIN_PASSWORD);
		try {
			adminService.addAdmin(admin);
		} catch (ObjectDuplicateException e) {
			LOG.info("Initial Default Admin to DB [ {}, {} ]", admin.getAdminName(), admin.getAdminPwd());
		}
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		LOG = null;
	}
	
}
