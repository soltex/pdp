/**
 * 
 */
package cn.com.innodev.pdp.business.sdk.weixinservice.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 微信公共管理器
 * @author shipeng
 */
public interface WeixinCommonManager {
	
	/**
	 * 从本地Cookie中获取到WeixinInfoBean
	 * @param servletRequest
	 * @param servletResponse
	 * @return
	 */
	WeixinInfoBean getWeixinInfoBean(HttpServletRequest servletRequest, HttpServletResponse servletResponse);
	
	
}
