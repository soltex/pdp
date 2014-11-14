/**
 * 
 */
package cn.com.innodev.pdp.community.services;

import java.util.Collection;

import cn.com.innodev.pdp.community.Community;
import cn.com.innodev.pdp.community.staff.Staff;
import cn.com.innodev.pdp.community.staff.StaffRole;
import cn.com.innodev.pdp.community.staff.StaffState;
import cn.com.innodev.pdp.framework.bizcommon.ImageBean;

import com.vanstone.business.ObjectDuplicateException;

/**
 * @author shipeng
 *
 */
public interface StaffService {
	
	public static final String SERVICE = "staffService";
	
	/**
	 * 添加员工信息
	 * @param staff
	 * @return
	 * @throws ObjectDuplicateException
	 */
	Staff addStaff(Staff staff) throws ObjectDuplicateException;
	
	/**
	 * 更新员工状态信息
	 * @param staffId
	 * @param staffState
	 * @return
	 */
	Staff updateStaffState(int staffId, StaffState staffState);
	
	/**
	 * 更新员工角色信息
	 * @param staffId
	 * @param staffRole
	 * @return
	 */
	Staff updateStaffRole(int staffId, StaffRole staffRole);
	
	/**
	 * 获取员工信息
	 * @param staffId
	 * @return
	 */
	Staff getStaff(int staffId);
	
	/**
	 * 删除员工信息
	 * @param staffId
	 */
	void deleteStaff(int staffId);
	
	/**
	 * 通过账号获取员工信息
	 * @param accountName
	 * @return
	 */
	Staff getStaffByAccountName(String accountName);
	
	/**
	 * 更新员工基本信息
	 * @param staff
	 * @return
	 */
	Staff updateStaffBaseInfo(Staff staff);
	
	/**
	 * 更新员工头像信息
	 * @param staffId
	 * @param imageBean
	 * @return
	 */
	Staff updateStaffHeadImageBean(int staffId, ImageBean imageBean);
	
	/**
	 * 删除头像信息
	 * @param staffId
	 */
	void deleteStaffHeadImageBean(int staffId);
	
	/**
	 * 获取员工列表信息
	 * @param community
	 * @param staffRole
	 * @param offset
	 * @param limit
	 * @return
	 */
	Collection<Staff> getStaffs(Community community, StaffRole staffRole, int offset, int limit);
	
	/**
	 * 获取员工总数
	 * @param community
	 * @param staffRole
	 * @return
	 */
	int getTotalStaffs(Community community, StaffRole staffRole);
}
