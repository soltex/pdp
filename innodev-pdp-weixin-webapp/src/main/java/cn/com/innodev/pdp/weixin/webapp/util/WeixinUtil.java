/**
 * 
 */
package cn.com.innodev.pdp.weixin.webapp.util;

import javax.servlet.http.HttpServletRequest;

import cn.com.innodev.pdp.framework.Constants;

import com.vanstone.centralserver.common.weixin.WeixinException;
import com.vanstone.centralserver.common.weixin.wrap.msg.AbstractMsg;
import com.vanstone.centralserver.common.weixin.wrap.oauth2.OAuth2AccessTokenResult;
import com.vanstone.centralserver.common.weixin.wrap.oauth2.OAuth2CallbackResult;
import com.vanstone.weixin.client.IWeixinAPIManager;
import com.vanstone.weixin.client.WeixinClientFactory;

/**
 * 
 * @author shipeng
 */
public class WeixinUtil {
	
	/**
	 * 从微信MSG中获取社区ID
	 * @param msg
	 * @return
	 */
	public static String getCommunitIDFromMSG(AbstractMsg msg) {
		return (String)msg.getExtendParams().get(Constants.COMMUNITY_ID_PARAM_IN_MSG);
	}
	
	/**
	 * 获取OpenId
	 * @param servletRequest
	 * @param communityId
	 * @return
	 */
	public static String retrievalOpenId(HttpServletRequest servletRequest, String communityId) {
		IWeixinAPIManager weixinApiManager = WeixinClientFactory.getWeixinAPIManager();
		OAuth2CallbackResult oAuth2CallbackResult =weixinApiManager.getOAuth2CallbackResult(servletRequest);
		if (oAuth2CallbackResult != null) {
			try {
				OAuth2AccessTokenResult accessTokenResult = weixinApiManager.getOAuth2AccessTokenResult(communityId, oAuth2CallbackResult.getCode());
				return accessTokenResult.getOpenId();
			} catch (WeixinException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
