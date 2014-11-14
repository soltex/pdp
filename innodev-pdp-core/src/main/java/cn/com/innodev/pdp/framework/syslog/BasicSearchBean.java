/**
 * 
 */
package cn.com.innodev.pdp.framework.syslog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import cn.com.innodev.pdp.framework.bizcommon.AbstractBasicCondition;
import cn.com.innodev.pdp.framework.bizcommon.SysModule;

/**
 * @author shipeng
 */
public class BasicSearchBean extends AbstractBasicCondition {
	
	private Date startSysInsertTime;
	private Date endSysInsertTime;
	private Collection<SysModule> sysModules = new ArrayList<SysModule>();
	private Collection<LogLevel> logLevels = new ArrayList<LogLevel>();
	
	public Date getStartSysInsertTime() {
		return startSysInsertTime;
	}

	public void setStartSysInsertTime(Date startSysInsertTime) {
		this.startSysInsertTime = startSysInsertTime;
	}

	public Date getEndSysInsertTime() {
		return endSysInsertTime;
	}
	
	public void setEndSysInsertTime(Date endSysInsertTime) {
		this.endSysInsertTime = endSysInsertTime;
	}

	public Collection<SysModule> getSysModules() {
		return sysModules;
	}
	
	public boolean existSysModules() {
		return this.sysModules.size() >0;
	}
	
	public Collection<LogLevel> getLogLevels() {
		return logLevels;
	}
	
	public void addSysModule(SysModule sysModule) {
		this.sysModules.add(sysModule);
	}
	
	public void addLogLevel(LogLevel logLevel) {
		this.logLevels.add(logLevel);
	}
	
	public boolean existLogLevel() {
		return this.logLevels.size() >0;
	}
	
	/**
	 * 存在检索条件
	 * @return
	 */
	@Override
	public boolean existCondition() {
		if (this.startSysInsertTime == null && this.endSysInsertTime == null 
				&& (this.logLevels == null || this.logLevels.size() <=0) 
				&& (this.sysModules == null || this.sysModules.size() <=0)) {
			return false;
		}
		return true;
	}
}
