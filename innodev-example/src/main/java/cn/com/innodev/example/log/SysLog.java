/**
 * 
 */
package cn.com.innodev.example.log;

import java.util.Date;

import cn.com.innodev.example.core.SysModule;

import com.vanstone.business.def.AbstractBusinessObject;

/**
 * 
 * @author shipeng
 */
public class SysLog extends AbstractBusinessObject {

	/** */
	private static final long serialVersionUID = -663061777868542928L;
	
	private String id;
	private Date sysInsertTime;
	private String content;
	private LogLevel logLevel = LogLevel.INFO;
	private SysModule sysModule;
	
	@Override
	public String getId() {
		return this.id;
	}
	
	public Date getSysInsertTime() {
		return sysInsertTime;
	}

	public void setSysInsertTime(Date sysInsertTime) {
		this.sysInsertTime = sysInsertTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LogLevel getLogLevel() {
		return logLevel;
	}
	
	public void setLogLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
	}

	public SysModule getSysModule() {
		return sysModule;
	}

	public void setSysModule(SysModule sysModule) {
		this.sysModule = sysModule;
	}

	@Override
	public String toJson() {
		return null;
	}
	
}
