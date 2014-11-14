/**
 * 
 */
package cn.com.innodev.pdp.business.sdk.adminservice.auth;

import javax.servlet.http.HttpServletRequest;

import cn.com.innodev.pdp.admin.Admin;

/**
 * @author shipeng
 */
public interface AuthManager {
	
	public static final String SERVICE = "authManager";
	
	/**
	 * 登录系统
	 * @param username
	 * @param password
	 * @param servletRequest
	 * @return
	 * @throws AdminLoginException
	 */
	Admin login(String username, String password, boolean remeberPassword, HttpServletRequest servletRequest) throws AdminLoginException;
	
	/**
	 * 退出系统
	 * @param servletRequest
	 */
	void logout(HttpServletRequest servletRequest);
	
	/**
	 * 是否管理员登录
	 * @param servletRequest
	 * @return
	 */
	boolean isAdminLogin(HttpServletRequest servletRequest);
	
	/**
	 * @param adminId
	 * @return
	 */
	Admin resetPassword(String adminId);
	
}
