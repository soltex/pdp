/**
 * 
 */
package cn.com.innodev.pdp.framework.syslog;

import cn.com.innodev.pdp.framework.bizcommon.SysModule;

import com.vanstone.business.lang.BaseEnum;

/**
 * 全局日志类型
 * @author shipeng
 */
public enum GlobalLogType implements BaseEnum<String>{
	
	WEIXIN_SERVER_LOG("微信服务器日志", "消息类型 : {0}, 消息Json内容 : {1}", SysModule.Weixin_Webapp)
	;
	private String desc;
	private String pattern;
	private SysModule sysModule;
	
	private GlobalLogType(String desc, String pattern, SysModule sysModule) {
		this.desc = desc;
		this.pattern = pattern;
		this.sysModule = sysModule;
	}
	
	@Override
	public String getCode() {
		return this.toString();
	}

	@Override
	public String getDesc() {
		return this.desc;
	}
	
	public SysModule getSysModule() {
		return sysModule;
	}

	public String getPattern() {
		return pattern;
	}
	
}
