/**
 * 
 */
package cn.com.innodev.pdp.community.staff;

import com.vanstone.business.lang.BaseEnum;

/**
 * 员工状态
 * @author shipeng
 */
public enum StaffState implements BaseEnum<Integer> {

	Active("启用",1), Forbit("禁用",10);
	
	private String desc;
	private Integer code;
	
	private StaffState(String desc, Integer code) {
		this.desc = desc;
		this.code = code;
	}
	
	/* (non-Javadoc)
	 * @see com.vanstone.business.lang.BaseEnum#getCode()
	 */
	@Override
	public Integer getCode() {
		return this.code;
	}

	/* (non-Javadoc)
	 * @see com.vanstone.business.lang.BaseEnum#getDesc()
	 */
	@Override
	public String getDesc() {
		return this.desc;
	}
	
}
