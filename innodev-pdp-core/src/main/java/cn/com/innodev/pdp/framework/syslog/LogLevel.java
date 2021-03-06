/**
 * 
 */
package cn.com.innodev.pdp.framework.syslog;

import com.vanstone.business.lang.BaseEnum;

/**
 * 日志级别，默认Info
 * @author shipeng
 */
public enum LogLevel implements BaseEnum<Integer> {
	
	INFO("信息", 1), ERROR("错误",0);
	
	private String desc;
	private Integer code;
	
	private LogLevel(String desc, Integer code) {
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
