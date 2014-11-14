/**
 * 
 */
package cn.com.innodev.pdp.admin.services.impl;

import cn.com.innodev.pdp.admin.persistence.object.AuthAdminDO;

import cn.com.innodev.pdp.admin.Admin;

/**
 * @author shipeng
 */
public class BeanUtil {
	
	public static Admin toAdmin(AuthAdminDO authAdminDO) {
		Admin admin = new Admin();
		admin.setAdminName(authAdminDO.getAdminName());
		admin.setAdminPwd(authAdminDO.getAdminPwd());
		admin.setId(authAdminDO.getId());
		admin.setLastLoginTime(authAdminDO.getLastLoginTime());
		return admin;
	}
	
	public static AuthAdminDO toAuthAdminDO(Admin admin) {
		AuthAdminDO authAdminDO = new AuthAdminDO();
		authAdminDO.setId(admin.getId());
		authAdminDO.setAdminName(admin.getAdminName());
		authAdminDO.setAdminPwd(admin.getAdminPwd());
		authAdminDO.setLastLoginTime(admin.getLastLoginTime());
		return authAdminDO;
	}
}
