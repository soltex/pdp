/**
 * 
 */
package cn.com.innodev.pdp.admin.webapp.example;

import com.vanstone.business.lang.BaseEnum;

/**
 * 
 * @author shipeng
 */
public enum ExampleState implements BaseEnum<Integer> {
	Active("激活状态",0), Forbit("禁用状态",1);
	
	private Integer code;
	private String desc;
	
	ExampleState(String desc, Integer code) {
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
