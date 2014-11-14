/**
 * 
 */
package cn.com.innodev.pdp.framework.syslog;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.vanstone.business.def.AbstractBusinessObject;
import com.vanstone.business.serialize.GsonCreator;

/**
 * 系统日志
 * @author shipeng
 */
public class SystemLog extends AbstractBusinessObject {
	
	private static final long serialVersionUID = 1884792617777520520L;
	
	private String id;
	private Date sysInsertTime;
	private String logContent;
	private GlobalLogType globalLogType;
	private LogLevel logLevel;
	private Map<String, String> requestParams = new LinkedHashMap<String, String>();
	private Map<String, String> runtimeParams = new LinkedHashMap<String, String>();
	
	@Override
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Date getSysInsertTime() {
		return sysInsertTime;
	}

	public void setSysInsertTime(Date sysInsertTime) {
		this.sysInsertTime = sysInsertTime;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	public GlobalLogType getGlobalLogType() {
		return globalLogType;
	}

	public void setGlobalLogType(GlobalLogType globalLogType) {
		this.globalLogType = globalLogType;
	}

	public LogLevel getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
	}

	public Map<String, String> getRequestParams() {
		return requestParams;
	}
	
	public void addRequestParam(String key, String value) {
		this.requestParams.put(key, value);
	}
	
	public void addRequestParams(Map<String, String> params) {
		this.requestParams.putAll(params);
	}
	
	public Map<String, String> getRuntimeParams() {
		return runtimeParams;
	}
	
	public void addRuntimeParam(String key, String value) {
		this.runtimeParams.put(key, value);
	}
	
	public void addRuntimeParams(Map<String, String> params) {
		this.runtimeParams.putAll(params);
	}
	
	@Override
	public String toJson() {
		Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
		dataMap.put("id", this.getId());
		dataMap.put("sysInsertTime", this.getSysInsertTime() != null ? this.getSysInsertTime().getTime() : null);
		dataMap.put("logContent_ik", this.getLogContent());
		dataMap.put("logContent_pinyin", this.getLogContent());
		dataMap.put("logContent_ansj", this.getLogContent());
		dataMap.put("globalLogType", this.getGlobalLogType());
		dataMap.put("logLevel", this.getLogLevel());
		dataMap.put("requestParams", this.getRequestParams());
		dataMap.put("runtimeParams", this.getRuntimeParams());
		Gson gson = GsonCreator.create();
		return gson.toJson(dataMap);
	}
}
