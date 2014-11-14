/**
 * 
 */
package cn.com.innodev.pdp.community.staff;

import com.vanstone.business.lang.BaseEnum;

/**
 * 项目公司员工角色
 * @author shipeng
 */
public enum StaffRole implements BaseEnum<Integer> {
	
	Project_Company_Admin("项目公司管理员",1), Repairman("修理工",2);
	
	private String desc;
	private Integer code;
	
	private StaffRole(String desc, Integer code) {
		this.desc = desc;
		this.code = code;
	}
	
	@Override
	public Integer getCode() {
		return this.code;
	}
	
	@Override
	public String getDesc() {
		return this.desc;
	}
	
}
