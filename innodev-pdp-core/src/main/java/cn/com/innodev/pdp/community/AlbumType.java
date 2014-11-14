/**
 * 
 */
package cn.com.innodev.pdp.community;

import com.vanstone.business.lang.BaseEnum;

/**
 *  相册类型
 * @author shipeng
 */
public enum AlbumType implements BaseEnum<Integer> {
	
	Floor_Plan_Type("户型图",0),Around_Environment("周边环境",1),Traffic_Environment("交通环境",2)
	;
	
	private String desc;
	private Integer code;
	
	private AlbumType(String desc, Integer code) {
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
