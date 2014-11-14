/**
 * 
 */
package cn.com.innodev.pdp.business.sdk.weixinservice.log;

import javax.servlet.http.HttpServletRequest;

import cn.com.innodev.pdp.framework.syslog.SystemLog;

/**
 * @author shipeng
 *
 */
public interface WeixinLogManager {
	
	public static final String SERVICE = "weixinLogManager";
	
	/**
	 * 写入微信
	 * @param servletRequest
	 * @param pattern
	 * @param contentParams
	 * @return
	 */
	SystemLog addWeixinServerRuntimeLog(HttpServletRequest servletRequest, String eventType, String json);
	
}
