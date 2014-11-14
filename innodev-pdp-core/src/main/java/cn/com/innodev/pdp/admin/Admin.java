/**
 * 
 */
package cn.com.innodev.pdp.admin;

import java.util.Date;

import com.vanstone.business.def.AbstractBusinessObject;

/**
 * Admin业务对象
 * @author shipeng
 */
public class Admin extends AbstractBusinessObject {

	/** */
	private static final long serialVersionUID = 1604696407576447776L;

	/** ID*/
	private String id;
	/** 账户名称*/
	private String adminName;
	/** 账户密码*/
	private String adminPwd;
	/** 上次登录时间*/
	private Date lastLoginTime;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vanstone.business.def.AbstractBusinessObject#getId()
	 */
	@Override
	public String getId() {
		return this.id;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPwd() {
		return adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
	
}
