/**
 * 
 */
package cn.com.innodev.pdp.business.sdk.weixinservice.log.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.innodev.pdp.business.sdk.weixinservice.log.WeixinLogManager;
import cn.com.innodev.pdp.framework.services.AbstractPlatformServiceMgr;
import cn.com.innodev.pdp.framework.syslog.GlobalLogType;
import cn.com.innodev.pdp.framework.syslog.LogLevel;
import cn.com.innodev.pdp.framework.syslog.LogService;
import cn.com.innodev.pdp.framework.syslog.SystemLog;

/**
 * @author shipeng
 */
@Service("weixinLogManager")
public class WeixinLogManagerImpl extends AbstractPlatformServiceMgr implements WeixinLogManager {

	/** */
	private static final long serialVersionUID = 678806166025357459L;
	
	@Autowired
	private LogService logService;
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.business.sdk.weixinservice.WeixinLogManager#addWeixinServerRuntimeLog(javax.servlet.http.HttpServletRequest, java.lang.String, java.lang.String)
	 */
	@Override
	public SystemLog addWeixinServerRuntimeLog(HttpServletRequest servletRequest, String eventType, String json) {
		return logService.addLog(true, GlobalLogType.WEIXIN_SERVER_LOG, LogLevel.INFO, servletRequest, null, 
				GlobalLogType.WEIXIN_SERVER_LOG.getPattern(), new Object[]{eventType, json});
	}
	
}
