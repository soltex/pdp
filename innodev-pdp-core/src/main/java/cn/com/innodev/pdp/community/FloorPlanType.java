/**
 * 
 */
package cn.com.innodev.pdp.community;

import com.vanstone.business.lang.BaseEnum;

/**
 * 户型类型
 * @author shipeng
 */
public enum FloorPlanType implements BaseEnum<Integer> {
	
	One_Bedroom("一室",1),
	Tow_Bedroom("二室",2),
	Three_Bedroom("三室",3),
	Four_Bedroom("四室",4),
	Five_Bedroom("五室",5);
	;
	
	private String desc;
	private Integer code;
	
	private FloorPlanType(String desc, Integer code) {
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
