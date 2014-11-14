/**
 * 
 */
package cn.com.innodev.pdp.admin.services;

import java.util.Collection;
import java.util.Date;

import cn.com.innodev.pdp.admin.Admin;

import com.vanstone.business.ObjectDuplicateException;

/**
 * @author shipeng
 */
public interface AdminService {
	
	public static final String SERVICE = "adminService";
	
	/**
	 * 添加管理员
	 * @param admin
	 * @return
	 */
	Admin addAdmin(Admin admin) throws ObjectDuplicateException;
	
	/**
	 * 通过ID获取Admin
	 * @param id
	 * @return
	 */
	Admin getAdmin(String id);
	
	/**
	 * 通过AdminName获取Admin
	 * @param adminName
	 * @return
	 */
	Admin getAdminByAdminName(String adminName);
	
	/**
	 * 更新最新登录时间
	 * @param id
	 * @param lastLoginTime
	 */
	void updateAdminLastLoginTime(String id, Date lastLoginTime);
	
	/**
	 * 更新密码
	 * @param id
	 * @param newPassword
	 * @return
	 */
	Admin updatePassword(String id, String newPassword);
	
	Collection<Admin> getAdmins(int offset, int limit);
	
	int getTotalAdmins();
	
}
