/**
 * 
 */
package cn.com.innodev.pdp.weixin.webapp.listener;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.innodev.pdp.business.sdk.weixinservice.log.WeixinLogManager;

import com.vanstone.business.serialize.GsonCreator;
import com.vanstone.centralserver.common.weixin.wrap.msg.Event4Click;
import com.vanstone.centralserver.common.weixin.wrap.msg.Event4Location;
import com.vanstone.centralserver.common.weixin.wrap.msg.Event4Scan;
import com.vanstone.centralserver.common.weixin.wrap.msg.Event4Subscribe;
import com.vanstone.centralserver.common.weixin.wrap.msg.Event4Unsubscribe;
import com.vanstone.centralserver.common.weixin.wrap.msg.Event4View;
import com.vanstone.centralserver.common.weixin.wrap.msg.Msg4Image;
import com.vanstone.centralserver.common.weixin.wrap.msg.Msg4Link;
import com.vanstone.centralserver.common.weixin.wrap.msg.Msg4Location;
import com.vanstone.centralserver.common.weixin.wrap.msg.Msg4SpeechRecognition;
import com.vanstone.centralserver.common.weixin.wrap.msg.Msg4Text;
import com.vanstone.centralserver.common.weixin.wrap.msg.Msg4Video;
import com.vanstone.centralserver.common.weixin.wrap.msg.Msg4Voice;
import com.vanstone.centralserver.common.weixin.wrap.msg.mass.Event4MassSendJobFinish;
import com.vanstone.framework.business.ServiceManagerFactory;
import com.vanstone.weixin.client.listener.IWeixinListener;

/**
 * 微信日志写入监听器
 * 
 * @author shipeng
 */
public class WeixinLogListener implements IWeixinListener {
	
	private WeixinLogManager weixinLogManager = ServiceManagerFactory.getInstance().getService(WeixinLogManager.SERVICE);
	
	@Override
	public void onClickEvent(Event4Click event4Click, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		weixinLogManager.addWeixinServerRuntimeLog(servletRequest, Event4Click.class.getSimpleName(), GsonCreator.getPrettyString(event4Click));
	}
	
	@Override
	public void onErrorMsg(String errorMsg, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		weixinLogManager.addWeixinServerRuntimeLog(servletRequest, "ErrorMsg", errorMsg);
	}
	
	@Override
	public void onFirstSubscribeEvent(Event4Subscribe event4Subscribe, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		weixinLogManager.addWeixinServerRuntimeLog(servletRequest, Event4Subscribe.class.getSimpleName(), GsonCreator.getPrettyString(event4Subscribe));
	}

	@Override
	public void onImageMsg(Msg4Image msg4Image, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		weixinLogManager.addWeixinServerRuntimeLog(servletRequest, Msg4Image.class.getSimpleName(), GsonCreator.getPrettyString(msg4Image));
	}
	
	@Override
	public void onLinkMsg(Msg4Link msg4Link, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		weixinLogManager.addWeixinServerRuntimeLog(servletRequest, Msg4Link.class.getSimpleName(), GsonCreator.getPrettyString(msg4Link));
	}
	
	@Override
	public void onLocationEvent(Event4Location event4Location, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		weixinLogManager.addWeixinServerRuntimeLog(servletRequest, Event4Location.class.getSimpleName(), GsonCreator.getPrettyString(event4Location));
	}

	@Override
	public void onLocationMsg(Msg4Location msg4Location, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		weixinLogManager.addWeixinServerRuntimeLog(servletRequest, Msg4Location.class.getSimpleName(), GsonCreator.getPrettyString(msg4Location));
	}

	@Override
	public void onScanEvent(Event4Scan event4Scan, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		weixinLogManager.addWeixinServerRuntimeLog(servletRequest, Event4Scan.class.getSimpleName(), GsonCreator.getPrettyString(event4Scan));
	}

	@Override
	public void onSpeechRecognitionMsg(Msg4SpeechRecognition msg4SpeechRecognition, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		weixinLogManager.addWeixinServerRuntimeLog(servletRequest, Msg4SpeechRecognition.class.getSimpleName(), GsonCreator.getPrettyString(msg4SpeechRecognition));
	}

	@Override
	public void onSubscribeEvent(Event4Subscribe event4Subscribe, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		weixinLogManager.addWeixinServerRuntimeLog(servletRequest, Event4Subscribe.class.getSimpleName(), GsonCreator.getPrettyString(event4Subscribe));
	}

	@Override
	public void onTextMsg(Msg4Text msg4Text, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		weixinLogManager.addWeixinServerRuntimeLog(servletRequest, Msg4Text.class.getSimpleName(), GsonCreator.getPrettyString(msg4Text));
	}

	@Override
	public void onUnsubscribeEvent(Event4Unsubscribe event4Unsubscribe, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		weixinLogManager.addWeixinServerRuntimeLog(servletRequest, Event4Unsubscribe.class.getSimpleName(), GsonCreator.getPrettyString(event4Unsubscribe));
	}
	
	@Override
	public void onVideoMsg(Msg4Video msg4Video, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		weixinLogManager.addWeixinServerRuntimeLog(servletRequest, Msg4Video.class.getSimpleName(), GsonCreator.getPrettyString(msg4Video));
	}
	
	@Override
	public void onViewEvent(Event4View event4View, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		weixinLogManager.addWeixinServerRuntimeLog(servletRequest, Event4View.class.getSimpleName(), GsonCreator.getPrettyString(event4View));
	}
	
	@Override
	public void onVoiceMsg(Msg4Voice msg4Voice, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		weixinLogManager.addWeixinServerRuntimeLog(servletRequest, Msg4Voice.class.getSimpleName(), GsonCreator.getPrettyString(msg4Voice));
	}

	@Override
	public void onMassSendJobFinishEvent(Event4MassSendJobFinish event4MassSendJobFinish, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		weixinLogManager.addWeixinServerRuntimeLog(servletRequest, Event4MassSendJobFinish.class.getSimpleName(), GsonCreator.getPrettyString(event4MassSendJobFinish));
	}
	
}
