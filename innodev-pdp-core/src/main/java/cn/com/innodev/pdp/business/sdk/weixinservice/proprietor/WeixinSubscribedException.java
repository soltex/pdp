/**
 * 
 */
package cn.com.innodev.pdp.business.sdk.weixinservice.proprietor;

import cn.com.innodev.pdp.framework.PlatformException;

/**
 * 微信已经订阅异常
 * @author shipeng
 */
public class WeixinSubscribedException extends PlatformException {
	
	/** */
	private static final long serialVersionUID = 3057944042468053131L;
	
	private String openId;
	
	public WeixinSubscribedException(String weixinOpenId) {
		this.openId = weixinOpenId;
	}

	public String getOpenId() {
		return openId;
	}
	
}
