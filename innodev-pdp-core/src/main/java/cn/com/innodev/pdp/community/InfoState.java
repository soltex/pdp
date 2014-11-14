/**
 * 
 */
package cn.com.innodev.pdp.community;

import com.vanstone.business.lang.BaseEnum;

/**
 * @author shipeng
 *
 */
public enum InfoState implements BaseEnum<Integer> {
	
	New("新建",0), Completed("完成",10);
	
	private String desc;
	private Integer code;
	
	private InfoState(String desc, Integer code) {
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
