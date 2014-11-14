/**
 * 
 */
package cn.com.innodev.pdp.weixin.webapp.listener;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.innodev.pdp.business.sdk.weixinservice.proprietor.WeixinProprietorManager;
import cn.com.innodev.pdp.business.sdk.weixinservice.proprietor.WeixinSubscribedException;
import cn.com.innodev.pdp.weixin.webapp.util.WeixinUtil;

import com.google.gson.Gson;
import com.vanstone.business.serialize.GsonCreator;
import com.vanstone.centralserver.common.weixin.wrap.msg.Event4Click;
import com.vanstone.centralserver.common.weixin.wrap.msg.Event4Subscribe;
import com.vanstone.centralserver.common.weixin.wrap.msg.Event4Unsubscribe;
import com.vanstone.centralserver.common.weixin.wrap.msg.ReplyMsg4Text;
import com.vanstone.framework.business.ServiceManagerFactory;
import com.vanstone.weixin.client.IWeixinAPIManager;
import com.vanstone.weixin.client.WeixinClientFactory;
import com.vanstone.weixin.client.listener.AbstractWeixinListenerAdapter;

/**
 * @author shipeng
 */
public class BizListener extends AbstractWeixinListenerAdapter {

	/** 微信Api管理器*/
	private IWeixinAPIManager weixinAPIManager = WeixinClientFactory.getWeixinAPIManager();
	
	private WeixinProprietorManager weixinProprietorManager = ServiceManagerFactory.getInstance().getService(WeixinProprietorManager.SERVICE);
	
	@Override
	public void onSubscribeEvent(Event4Subscribe event, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		String communityId = WeixinUtil.getCommunitIDFromMSG(event);
		try {
			Gson gson = GsonCreator.createPretty();
			System.out.println(gson.toJson(event));
			weixinProprietorManager.subscribeWeixinProprietor(communityId, event.getFromUserName(), event.getToUserName(), servletResponse);
		} catch (WeixinSubscribedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onUnsubscribeEvent(Event4Unsubscribe event, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		//取消关注
		this.weixinProprietorManager.unsubscribeWeixinProprietor(event.getToUserName());
	}
	
	@Override
	public void onClickEvent(Event4Click event, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		String communityId = WeixinUtil.getCommunitIDFromMSG(event);
		ReplyMsg4Text replyMsg4Text = new ReplyMsg4Text();
		String content = "当前访问的社区为 【" + communityId + "】";
		replyMsg4Text.setContent(content);
		replyMsg4Text.setCreateTime(new Date());
		replyMsg4Text.setFromUserName(event.getToUserName());
		replyMsg4Text.setToUserName(event.getFromUserName());
		weixinAPIManager.sendReplyMsg4Text(replyMsg4Text, servletResponse);
	}
	
}
