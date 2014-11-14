/**
 * 
 */
package cn.com.innodev.pdp.framework.bizcommon;

import com.vanstone.business.lang.BaseEnum;

/**
 * @author shipeng
 *
 */
public enum Gender implements BaseEnum<Integer> {
	
	Male("男",1),FMale("女",0),None("保密",-1)
	;
	private String desc;
	private Integer code;
	
	private Gender(String desc, Integer code) {
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
