/**
 * 
 */
package cn.com.innodev.pdp.community.staff;

import java.util.Date;

import cn.com.innodev.pdp.community.Community;
import cn.com.innodev.pdp.framework.bizcommon.ImageBean;

import com.vanstone.business.def.AbstractBusinessObject;

/**
 * 项目公司员工
 * 
 * @author shipeng
 */
public class Staff extends AbstractBusinessObject {

	/** */
	private static final long serialVersionUID = -4177381256512795360L;

	/** 流水ID */
	private Integer id;
	/** 账户名 */
	private String accountName;
	/** 账户密码 */
	private String accountPassword;
	/** 姓名 */
	private String fullName;
	/** 邮箱 */
	private String email;
	/** 手机号 */
	private String mobile1;
	/** 手机号 */
	private String mobile2;
	/** 手机号 */
	private String mobile3;
	/** 手机号 */
	private String mobile4;
	/** 系统写入时间 */
	private Date sysInsertTime;
	/** 系统更新时间 */
	private Date sysUpdateTime;
	/** 上次登录时间 */
	private Date lastLoginTime;
	/** 头像信息 */
	private ImageBean headImageBean;
	/** 所在部门 */
	private String department;
	/** 角色信息 */
	private StaffRole staffRole = StaffRole.Project_Company_Admin;
	/** 员工状态 */
	private StaffState staffState = StaffState.Active;
	private Community community;
	private String content;
	
	public Staff(Community community) {
		this.community = community;
	}
	
	@Override
	public Integer getId() {
		return this.id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountPassword() {
		return accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile1() {
		return mobile1;
	}

	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	public String getMobile2() {
		return mobile2;
	}

	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}

	public String getMobile3() {
		return mobile3;
	}

	public void setMobile3(String mobile3) {
		this.mobile3 = mobile3;
	}

	public String getMobile4() {
		return mobile4;
	}

	public void setMobile4(String mobile4) {
		this.mobile4 = mobile4;
	}

	public Date getSysInsertTime() {
		return sysInsertTime;
	}

	public void setSysInsertTime(Date sysInsertTime) {
		this.sysInsertTime = sysInsertTime;
	}

	public Date getSysUpdateTime() {
		return sysUpdateTime;
	}

	public void setSysUpdateTime(Date sysUpdateTime) {
		this.sysUpdateTime = sysUpdateTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public ImageBean getHeadImageBean() {
		return headImageBean;
	}

	public void setHeadImageBean(ImageBean headImageBean) {
		this.headImageBean = headImageBean;
	}
	
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public StaffRole getStaffRole() {
		return staffRole;
	}

	public void setStaffRole(StaffRole staffRole) {
		this.staffRole = staffRole;
	}

	public StaffState getStaffState() {
		return staffState;
	}

	public void setStaffState(StaffState staffState) {
		this.staffState = staffState;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Community getCommunity() {
		return community;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
