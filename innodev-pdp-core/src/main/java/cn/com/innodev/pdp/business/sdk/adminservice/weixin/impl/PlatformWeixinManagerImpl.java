/**
 * 
 */
package cn.com.innodev.pdp.business.sdk.adminservice.weixin.impl;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.innodev.pdp.business.sdk.BusinessException;
import cn.com.innodev.pdp.business.sdk.adminservice.weixin.PlatformWeixinManager;
import cn.com.innodev.pdp.framework.Constants;
import cn.com.innodev.pdp.framework.services.AbstractPlatformServiceMgr;
import cn.com.innodev.pdp.framework.syslog.LogService;

import com.vanstone.centralserver.common.weixin.WeixinException;
import com.vanstone.centralserver.common.weixin.wrap.ButtonType;
import com.vanstone.centralserver.common.weixin.wrap.menu.Menu;
import com.vanstone.centralserver.common.weixin.wrap.menu.MenuItem;
import com.vanstone.centralserver.common.weixin.wrap.oauth2.Scope;
import com.vanstone.centralserver.common.weixin.wrap.user.UserGroupInfo;
import com.vanstone.weixin.client.IWeixinAPIManager;
import com.vanstone.weixin.client.WeixinClientFactory;

/**
 * @author shipeng
 */
@Service("platformWeixinManager")
public class PlatformWeixinManagerImpl extends AbstractPlatformServiceMgr implements PlatformWeixinManager {
	
	/** */
	private static final long serialVersionUID = 7994259658859618712L;
	
	private static Logger LOG = LoggerFactory.getLogger(PlatformWeixinManagerImpl.class);
	
	@Autowired
	private LogService logService;
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.business.sdk.adminservice.PlatformWeixinManager#createWeixinEnum()
	 */
	@Override
	public boolean createWeixinEnum() {
		IWeixinAPIManager weixinApiManager = WeixinClientFactory.getWeixinAPIManager();
		
		Collection<String> appnames = weixinApiManager.getAppnames();
		if (appnames == null || appnames.size() <=0) {
			throw new IllegalArgumentException("Appnames not empty.");
		}
		
		for (String appname : appnames) {
			LOG.debug("Weixin Appname {}", appname);
			try {
				Menu weixinMenu = new Menu();
				
				//社区菜单
				MenuItem communityMenuItem = new MenuItem("社区");
				
				MenuItem communityWallItem = new MenuItem(ButtonType.Click, "社区活动墙", Constants.Weixin_Event_Define.CLICK_COMMUNITY_WALL_EVENT.getCode());
				communityMenuItem.addSubMenuItem(communityWallItem);
				
				MenuItem aboutusItem = new MenuItem(ButtonType.Click, "关于我们",  Constants.Weixin_Event_Define.CLICK_ABOUTUS_EVENT.getCode());
				communityMenuItem.addSubMenuItem(aboutusItem);
				
				MenuItem ourStaffItem = new MenuItem(ButtonType.View, "我们的员工",  weixinApiManager.getOAuth2Url(appname, Constants.WEIXIN_SERVER_DOMAIN_NAME + "/" + appname + "/our-staffs.jhtml", Scope.snsapi_base, null));
				communityMenuItem.addSubMenuItem(ourStaffItem);
				
				weixinMenu.addMenuItem(communityMenuItem);
				
				//我的账户
				MenuItem myAccountMenuItem = new MenuItem(ButtonType.View, "我的账户", weixinApiManager.getOAuth2Url(appname, Constants.WEIXIN_SERVER_DOMAIN_NAME + "/" + appname + "/myaccount/indexframe.jhtml", Scope.snsapi_base, null));
				
//				MenuItem updatePasswordItem = new MenuItem(ButtonType.View, "修改密码", Constants.WEIXIN_SERVER_DOMAIN_NAME + "/" + appname + "/my/update-password.jhtml");
//				myAccountMenuItem.addSubMenuItem(updatePasswordItem);
//				
//				MenuItem mobileAuthItem = new MenuItem(ButtonType.View, "手机认证", Constants.WEIXIN_SERVER_DOMAIN_NAME  + "/" + appname + "/my/mobile-authentication.jhtml");
//				myAccountMenuItem.addSubMenuItem(mobileAuthItem);
//				
//				MenuItem roomAuthItem = new MenuItem(ButtonType.View, "房间认证", Constants.WEIXIN_SERVER_DOMAIN_NAME  + "/" + appname + "/my/room-authentication.jhtml");
//				myAccountMenuItem.addSubMenuItem(roomAuthItem);
//				
//				MenuItem updateProfileItem = new MenuItem(ButtonType.View, "个人信息修改", Constants.WEIXIN_SERVER_DOMAIN_NAME  +  "/" + appname + "/my/update-profile.jhtml");
//				myAccountMenuItem.addSubMenuItem(updateProfileItem);
				
				weixinMenu.addMenuItem(myAccountMenuItem);
				
				//服务
				MenuItem serviceMenuItem = new MenuItem("服务");
				
				MenuItem announcementItem = new MenuItem(ButtonType.View, "社区公告", Constants.WEIXIN_SERVER_DOMAIN_NAME  + "/" + appname + "/view-announcements.jhtml");
				serviceMenuItem.addSubMenuItem(announcementItem);
				
				MenuItem accessorQRItem = new MenuItem(ButtonType.Click, "申请访客二维码", Constants.Weixin_Event_Define.CLICK_ACCESSOR_QR_EVENT.getCode());
				serviceMenuItem.addSubMenuItem(accessorQRItem);
				
				MenuItem applyRepairReportItem = new MenuItem(ButtonType.Click, "申请报修单", Constants.Weixin_Event_Define.CLICK_APPLY_REPAIR_REPORT_EVENT.getCode());
				serviceMenuItem.addSubMenuItem(applyRepairReportItem);
				
				MenuItem qaItem = new MenuItem(ButtonType.View, "常用知识", Constants.WEIXIN_SERVER_DOMAIN_NAME + "/" + appname + "/view-qas.jhtml");
				serviceMenuItem.addSubMenuItem(qaItem);
				
				weixinMenu.addMenuItem(serviceMenuItem);
				
				weixinApiManager.createWeixinMenu(appname, weixinMenu);
			} catch (WeixinException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	@Override
	public Collection<UserGroupInfo> getUserGroupInfos() throws BusinessException {
		IWeixinAPIManager weixinAPIManager = WeixinClientFactory.getWeixinAPIManager();
		try {
			return weixinAPIManager.getUserGroupInfos(Constants.WEIXIN_SERVICE_NUM);
		} catch (WeixinException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}
	
}