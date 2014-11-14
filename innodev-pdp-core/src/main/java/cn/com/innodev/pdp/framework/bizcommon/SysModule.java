/**
 * 
 */
package cn.com.innodev.pdp.framework.bizcommon;

import com.vanstone.business.lang.BaseEnum;

/**
 * @author shipeng
 * 
 */
public enum SysModule implements BaseEnum<String> {
	
	Weixin_Webapp("微信系统");
	
	private String desc;

	private SysModule(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String getCode() {
		return this.toString();
	}

	@Override
	public String getDesc() {
		return this.desc;
	}
	
}
