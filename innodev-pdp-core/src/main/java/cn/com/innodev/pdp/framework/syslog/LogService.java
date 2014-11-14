/**
 * 
 */
package cn.com.innodev.pdp.framework.syslog;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shipeng
 */
public interface LogService {
	
	public static final String SERVICE = "logService";
	
	/**
	 * 写入日志
	 * @param asyn
	 * @param globalLogType
	 * @param logLevel
	 * @param requestParams
	 * @param runtimeParaams
	 * @param logPattern
	 * @param objects
	 * @return
	 */
	SystemLog addLog(boolean asyn, GlobalLogType globalLogType, LogLevel logLevel, Map<String, String> requestParams, Map<String, String> runtimeParaams, String logPattern, Object...objects);
	
	/**
	 * 增加
	 * @param asyn 是否异步
	 * @param globalLogType 日志类型
	 * @param logLevel 日志级别
	 * @param servletRequest 当前Servlet请求
	 * @param runtimeParaams 运行时参数
	 * @param logPattern 日志模式
	 * @param objects 日志参数
	 * @return
	 */
	SystemLog addLog(boolean asyn, GlobalLogType globalLogType, LogLevel logLevel, HttpServletRequest servletRequest, Map<String, String> runtimeParaams, String logPattern, Object...objects);
	
	/**
	 * 删除日志
	 * @param logId
	 */
	void deleteLog(String logId);
	
	/**
	 * 通过检索条件删除日志
	 * @param searchLogBean
	 */
	void deleteLogs(BasicSearchBean searchLogBean);
	
	/**
	 * 删除全部日志
	 */
	void deleteAllLogs();
	
	/**
	 * 检索日志
	 * @param searchLogBean
	 * @param offset
	 * @param limit
	 * @return
	 */
	Collection<SystemLog> searchSystemLogs(SearchLogBean searchLogBean, long offset, long limit);
	
	/**
	 * 检索日志
	 * @param searchLogBean
	 * @return
	 */
	long searchTotalSystemLogs(SearchLogBean searchLogBean);
}
