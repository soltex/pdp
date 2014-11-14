/**
 * 
 */
package cn.com.innodev.pdp.business.sdk.weixinservice.proprietor.impl;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.innodev.pdp.business.sdk.weixinservice.proprietor.WeixinLoginException;
import cn.com.innodev.pdp.business.sdk.weixinservice.proprietor.WeixinLoginException.ErrorCode;
import cn.com.innodev.pdp.business.sdk.weixinservice.proprietor.WeixinProprietorManager;
import cn.com.innodev.pdp.business.sdk.weixinservice.proprietor.WeixinSubscribedException;
import cn.com.innodev.pdp.community.Community;
import cn.com.innodev.pdp.community.Room;
import cn.com.innodev.pdp.community.services.CommunityService;
import cn.com.innodev.pdp.framework.Constants;
import cn.com.innodev.pdp.framework.PlatformError;
import cn.com.innodev.pdp.framework.bizcommon.ImageBean;
import cn.com.innodev.pdp.framework.bizcommon.ImageFormatIncorrentException;
import cn.com.innodev.pdp.framework.services.AbstractPlatformServiceMgr;
import cn.com.innodev.pdp.framework.sms.SMS;
import cn.com.innodev.pdp.framework.sms.SMSCreator;
import cn.com.innodev.pdp.proprietor.Proprietor;
import cn.com.innodev.pdp.proprietor.ProprietorState;
import cn.com.innodev.pdp.proprietor.ValidatecodeBean;
import cn.com.innodev.pdp.proprietor.services.ProprietorService;
import cn.com.innodev.pdp.proprietor.services.ValidatecodeException;
import cn.com.innodev.pdp.proprietor.services.ValidatecodeService;

import com.vanstone.business.CommonDateUtil;
import com.vanstone.business.ObjectDuplicateException;
import com.vanstone.centralserver.common.MyAssert;
import com.vanstone.centralserver.common.weixin.WeixinException;
import com.vanstone.centralserver.common.weixin.wrap.msg.CCMsg4Text;
import com.vanstone.centralserver.common.weixin.wrap.msg.ReplyMsg4Text;
import com.vanstone.centralserver.common.weixin.wrap.oauth2.Scope;
import com.vanstone.common.util.MessageUtil;
import com.vanstone.common.util.image.ImagePropertyVO;
import com.vanstone.common.util.image.ImageUtil;
import com.vanstone.configuration.client.ConfigurationFactory;
import com.vanstone.configuration.client.IConfigurationManager;
import com.vanstone.fs.FSFile;
import com.vanstone.fs.FileType;
import com.vanstone.weedfs.client.RequestResult;
import com.vanstone.weedfs.client.WeedFile;
import com.vanstone.weedfs.client.impl.WeedFSClient;
import com.vanstone.weixin.client.IWeixinAPIManager;
import com.vanstone.weixin.client.WeixinClientFactory;

/**
 * 
 * @author shipeng
 */
@Service("weixinProprietorManager")
public class WeixinProprietorManagerImpl extends AbstractPlatformServiceMgr implements WeixinProprietorManager {

	/** */
	private static final long serialVersionUID = -6217023027217003542L;

	private static Logger LOG = LoggerFactory.getLogger(WeixinProprietorManagerImpl.class);
	
	@Autowired
	private ValidatecodeService validatecodeService;
	@Autowired
	private ProprietorService proprietorService;
	@Autowired
	private CommunityService communityService;
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.business.sdk.weixinservice.WeixinProprietorManager#generateValidatecode(java.lang.String)
	 */
	@Override
	public void generateValidatecode(String mobile) {
		MyAssert.hasText(mobile);
		ValidatecodeBean validatecodeBean = validatecodeService.generate(mobile);
		IConfigurationManager configurationManager = ConfigurationFactory.getConfigurationManager();
		String template = configurationManager.getValue(Constants.INNODEV_PDP_SYSTEMPLATE_GROUP, Constants.Global_SysTemplate.Mobile_Auth_Template.getCode());
		String content = MessageUtil.pattern2String(template, new Object[]{validatecodeBean.getValidatecode()});
		SMS sms = SMSCreator.getInstance().createSMS(mobile, content);
		sms.send(true);
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.business.sdk.weixinservice.WeixinProprietorManager#subscribeWeixinProprietor(java.lang.String, java.lang.String)
	 */
	@Override
	public Proprietor subscribeWeixinProprietor(String communityId, String weixinOpenId, String serverOpenId, HttpServletResponse servletResponse) throws WeixinSubscribedException {
		MyAssert.hasText(communityId);
		MyAssert.hasText(weixinOpenId);
		MyAssert.hasText(serverOpenId);
		MyAssert.notNull(servletResponse);
		
		Community loadCommunity = this.communityService.getCommunity(communityId);
		if (loadCommunity == null) {
			throw new IllegalArgumentException();
		}
		Proprietor proprietor = this.proprietorService.getProprietorByWeixinOpenId(weixinOpenId);
		
		if (proprietor != null) {
			if (proprietor.getProprietorState().equals(ProprietorState.Cancel_Weixin)) {
				//已经为订阅客户，但是已经取消订阅
				if (proprietor.getRooms() != null && proprietor.getRooms().size() >0) {
					proprietorService.updateProprietorState(proprietor.getId(), ProprietorState.Finished_Auth);
				}else if (proprietor.getMobile() != null && !proprietor.getMobile().equals("")) {
					proprietorService.updateProprietorState(proprietor.getId(), ProprietorState.Mobile_Email_Auth);
				}else {
					proprietorService.updateProprietorState(proprietor.getId(), ProprietorState.Weixin_Wait_Auth);
				}
				LOG.info("Proprietor RE_Subscribe, OpenId [{}]", weixinOpenId);
			}else {
				//已订阅
				throw new WeixinSubscribedException(weixinOpenId); 
			}
		}
		try {
			proprietor = this.proprietorService.addProprietor(weixinOpenId, loadCommunity);
			LOG.info("New Proprietor registy ok, OpenId [{}]", weixinOpenId);
		} catch (ObjectDuplicateException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}
		
		IWeixinAPIManager weixinApiManager = WeixinClientFactory.getWeixinAPIManager();
		ReplyMsg4Text text = new ReplyMsg4Text();
		text.setCreateTime(new Date());
		text.setFromUserName(serverOpenId);
		text.setToUserName(weixinOpenId);
		StringBuffer content = new StringBuffer();
		content.append("欢迎使用互联物业微信，请先登录您的账号：");
		content.append("<a href='");
		content.append(weixinApiManager.getOAuth2Url(communityId, Constants.WEIXIN_SERVER_DOMAIN_NAME + "/" + communityId + "/login.jhtml" , Scope.snsapi_base, null));
		content.append("' >").append("马上登录").append("</a>");
		content.append("\n");
		content.append("互联物业微信版使用指南 ：");
		content.append("<a href='#'>马上查阅</a>");
		text.setContent(content.toString());
		weixinApiManager.sendReplyMsg4Text(text, servletResponse);
		return proprietor;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.business.sdk.weixinservice.WeixinProprietorManager#login(java.lang.String, java.lang.String)
	 */
	@Override
	public Proprietor login(String mobile, String password, String openId) throws WeixinLoginException {
		MyAssert.hasText(mobile);
		MyAssert.hasText(password);
		MyAssert.hasText(openId);
		Proprietor proprietor = this.proprietorService.getProprietorByMobile(mobile);
		if (proprietor == null) {
			throw new WeixinLoginException(ErrorCode.USERNAME_NOT_FOUND);
		}
		if (proprietor.getWeixinOpenid() == null || proprietor.getWeixinOpenid().equals("")) {
			throw new IllegalArgumentException();
		}
		if (!proprietor.getPwd().equals(password)) {
			throw new WeixinLoginException(ErrorCode.USERNAME_PASSWORD_NOT_MATCH);
		}
		if (!proprietor.getWeixinOpenid().equals(openId)) {
			proprietor = this.proprietorService.updateWeixinOpenId(proprietor.getId(), openId);
		}
		return proprietor;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.business.sdk.weixinservice.WeixinProprietorManager#unsubscribeWeixinProprietor(java.lang.String)
	 */
	@Override
	public Proprietor unsubscribeWeixinProprietor(final String weixinOpenId) {
		MyAssert.hasText(weixinOpenId);
		Proprietor loadProprietor = this.proprietorService.getProprietorByWeixinOpenId(weixinOpenId);
		if (loadProprietor == null) {
			throw new IllegalArgumentException();
		}
		loadProprietor = this.proprietorService.updateProprietorState(loadProprietor.getId(), ProprietorState.Cancel_Weixin);
		LOG.info("Unsubscibe Proprietor OpenId [{}]", weixinOpenId);
		return loadProprietor;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.business.sdk.weixinservice.WeixinProprietorManager#mobileAuthenticateProprietor(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Proprietor mobileAuthenticateProprietor(String communityId, String weixinOpenId, String mobile, String validatecode, HttpServletResponse servletResponse) throws ValidatecodeException, ObjectDuplicateException {
		MyAssert.hasText(communityId);
		MyAssert.hasText(weixinOpenId);
		MyAssert.hasText(mobile);
		MyAssert.hasText(validatecode);
		MyAssert.notNull(servletResponse);
		
		Community loadCommunity = this.communityService.getCommunity(communityId);
		if (loadCommunity == null) {
			throw new IllegalArgumentException();
		}
		Proprietor loadProprietor = this.proprietorService.getProprietorByWeixinOpenId(weixinOpenId);
		if (loadProprietor == null) {
			throw new IllegalArgumentException();
		}
		boolean isFirstTime = loadProprietor.getMobile() == null || loadProprietor.getMobile().equals("") ? true : false;
		//验证码验证
		validatecodeService.validate(mobile, validatecode);
		//手机授权
		loadProprietor =  this.proprietorService.authenticateMobile(loadProprietor.getId(), mobile);
		//提示消息发送
		CCMsg4Text text = new CCMsg4Text();
		text.setTouser(weixinOpenId);
		StringBuffer content = new StringBuffer();
		if (isFirstTime) {
			//首次认证
			content.append("互联物业信息状态更变提醒 ： \n");
			content.append("您首次绑定的手机号为 : ").append(mobile).append("\n");
			content.append(CommonDateUtil.date2String(new Date(), "yyyy-MM-dd"));
		}else{
			//更变手机号
			content.append("互联物业信息状态更变提醒 ： \n");
			content.append("您更改绑定的手机号为 : ").append(mobile).append("\n");
			content.append(CommonDateUtil.date2String(new Date(), "yyyy-MM-dd"));
		}
		text.setContent(content.toString());
		IWeixinAPIManager weixinAPIManager = WeixinClientFactory.getWeixinAPIManager();
		try {
			weixinAPIManager.sendCCMsgText(communityId, text);
		} catch (WeixinException e) {
			e.printStackTrace();
			LOG.error("Weixin Send CC Text MSG Error : {},{}", e.getErrorCode().getDesc() , e.getErrorCode().getCode());
		}
		return loadProprietor;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.business.sdk.weixinservice.WeixinProprietorManager#bindRooms(java.lang.String, java.util.Collection)
	 */
	@Override
	public Proprietor bindRooms(String communityId, String weixinOpenId, Collection<Integer> roomIds) {
		MyAssert.hasText(communityId);
		MyAssert.hasText(weixinOpenId);
		MyAssert.notEmpty(roomIds);
		
		Community loadCommunity = this.communityService.getCommunity(communityId);
		if (loadCommunity == null) {
			throw new IllegalArgumentException();
		}
		Proprietor loadProprietor = this.proprietorService.getProprietorByWeixinOpenId(weixinOpenId);
		if (loadProprietor == null) {
			throw new IllegalArgumentException();
		}
		boolean isFirstTime = loadProprietor.getRooms() == null || loadProprietor.getRooms().size() <=0 ? true : false;
		
		if (loadProprietor.getProprietorState() == null || loadProprietor.getProprietorState().equals(ProprietorState.Cancel_Weixin)) {
			throw new IllegalArgumentException();
		}
		Map<Integer, Room> rooms = this.communityService.getRooms(roomIds);
		if (rooms == null || rooms.size() <=0) {
			throw new IllegalArgumentException();
		}
		
		loadProprietor = this.proprietorService.updateRooms(loadProprietor.getId(), rooms.values());
		
		//提示消息发送
		CCMsg4Text text = new CCMsg4Text();
		text.setTouser(weixinOpenId);
		StringBuffer content = new StringBuffer();
		if (isFirstTime) {
			//首次认证
			content.append("互联物业信息状态更变提醒 ： \n");
			content.append("您首次绑定的房间号为 : ");
			for (Room room : loadProprietor.getRooms()) {
				content.append("楼号 : ").append(room.getBuilding().getBuildingNo()).append(" 房间号 : ").append(room.getRoomNo()).append("\n");
			}
			content.append(CommonDateUtil.date2String(new Date(), "yyyy-MM-dd"));
		}else{
			//更变手机号
			content.append("互联物业信息状态更变提醒 ： \n");
			content.append("您更改房间号为 : ").append("\n");
			for (Room room : loadProprietor.getRooms()) {
				content.append("楼号 : ").append(room.getBuilding().getBuildingNo()).append(" 房间号 : ").append(room.getRoomNo()).append("\n");
			}
			content.append(CommonDateUtil.date2String(new Date(), "yyyy-MM-dd"));
		}
		text.setContent(content.toString());
		IWeixinAPIManager weixinAPIManager = WeixinClientFactory.getWeixinAPIManager();
		try {
			weixinAPIManager.sendCCMsgText(communityId, text);
		} catch (WeixinException e) {
			e.printStackTrace();
			LOG.error("Weixin Send CC Text MSG Error : {},{}", e.getErrorCode().getDesc() , e.getErrorCode().getCode());
		}
		return loadProprietor;
	}

	@Override
	public void updateProprietorHeaderImage(String id, FSFile fsFile) throws ImageFormatIncorrentException {
		MyAssert.hasText(id);
		MyAssert.notNull(fsFile);
		Proprietor loadProprietor = this.proprietorService.getProprietor(id);
		if (loadProprietor == null) {
			throw new IllegalArgumentException();
		}
		if (!fsFile.getFileType().equals(FileType.Image)){
			throw new ImageFormatIncorrentException();
		}
		WeedFSClient weedFSClient = new WeedFSClient();
		RequestResult requestResult = weedFSClient.upload(fsFile.getFile());
		if (!requestResult.isSuccess()) {
			throw new PlatformError("WeedFS Server Error. ");
		}
		String fid = requestResult.getFid();
		ImageBean imageBean = null;
		try {
			ImagePropertyVO imagePropertyVO = ImageUtil.getProperty(fsFile.getInputStream());
			imageBean = new ImageBean(new WeedFile(fid, fsFile.getExtensionName()), imagePropertyVO);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
		this.proprietorService.updateHeadImageBean(id, imageBean);
	}
	
}
