/**
 * 
 */
package cn.com.innodev.pdp.business.sdk.adminservice.auth.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.innodev.pdp.admin.Admin;
import cn.com.innodev.pdp.admin.services.AdminService;
import cn.com.innodev.pdp.business.sdk.adminservice.auth.AdminLoginException;
import cn.com.innodev.pdp.business.sdk.adminservice.auth.AdminLoginException.ErrorCode;
import cn.com.innodev.pdp.business.sdk.adminservice.auth.AuthManager;
import cn.com.innodev.pdp.framework.Constants;
import cn.com.innodev.pdp.framework.services.AbstractPlatformServiceMgr;

import com.vanstone.centralserver.common.MyAssert;
import com.vanstone.common.util.random.RandomNumber;

/**
 * @author shipeng
 */
@Service("authManager")
public class AuthManagerImpl extends AbstractPlatformServiceMgr implements AuthManager {
	
	/** */
	private static final long serialVersionUID = 3466737799284131975L;
	
	private static Logger LOG = LoggerFactory.getLogger(AuthManagerImpl.class);
	
	@Autowired
	private AdminService adminService;
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.business.sdk.adminservice.AdminManager#login(java.lang.String, java.lang.String, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Admin login(String username, String password, boolean remeberPassword, HttpServletRequest servletRequest) throws AdminLoginException {
		MyAssert.hasText(username);
		MyAssert.hasText(password);
		MyAssert.notNull(servletRequest);
		final Admin loadAdmin = this.adminService.getAdminByAdminName(username);
		if (loadAdmin == null) {
			throw AdminLoginException.createException(ErrorCode.Admin_Name_Not_Found);
		}
		if (!loadAdmin.getAdminPwd().equals(password)) {
			throw AdminLoginException.createException(ErrorCode.Admin_Name_Password_Not_Match);
		}
		final Date lastLoginTime = new Date();
		//UPDATE Last Login Time
		this.adminService.updateAdminLastLoginTime(loadAdmin.getId(), lastLoginTime);
		loadAdmin.setLastLoginTime(lastLoginTime);
		//Sync HttpSession
		saveAdminToHttpSession(servletRequest, loadAdmin);
		return loadAdmin;
	}
	
	/**
	 * 写入Admin 到 HttpSession
	 * @param servletRequest
	 * @param admin
	 */
	private void saveAdminToHttpSession(HttpServletRequest servletRequest, Admin admin) {
		MyAssert.notNull(servletRequest);
		servletRequest.getSession().setAttribute(Constants.AUTH_ADMIN_SESSION_NAME, admin);
		LOG.info("Save Admin Object to HttpSession. [{},{}]", admin.getId(), admin.getAdminName());
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.business.sdk.adminservice.AdminManager#logout(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void logout(HttpServletRequest servletRequest) {
		MyAssert.notNull(servletRequest);
		servletRequest.getSession().invalidate();
		LOG.debug("Admin Logout system.");
	}
	
	@Override
	public boolean isAdminLogin(HttpServletRequest servletRequest) {
		MyAssert.notNull(servletRequest);
		if (servletRequest.getSession().getAttribute(Constants.AUTH_ADMIN_SESSION_NAME) != null) {
			return true;
		}
		return false;
	}

	@Override
	public Admin resetPassword(String adminId) {
		MyAssert.hasText(adminId);
		Admin admin = this.adminService.getAdmin(adminId);
		if (admin == null) {
			throw new IllegalArgumentException();
		}
		String password = RandomNumber.randomNumber(Constants.DEFAULT_STAFF_PASSWORD_LENGTH);
		this.adminService.updatePassword(adminId, password);
		admin.setAdminPwd(password);
		return admin;
	}
	
}
