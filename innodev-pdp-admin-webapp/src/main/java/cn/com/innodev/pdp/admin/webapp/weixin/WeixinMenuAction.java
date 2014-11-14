/**
 * 
 */
package cn.com.innodev.pdp.admin.webapp.weixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.innodev.pdp.business.sdk.adminservice.weixin.PlatformWeixinManager;
import cn.com.innodev.pdp.framework.web.DWZAjaxObject;
import cn.com.innodev.pdp.framework.web.DWZAjaxObject.DWZStatusCode;

/**
 * @author shipeng
 */
@Controller
@RequestMapping("/weixin")
public class WeixinMenuAction {
	
	@Autowired
	private PlatformWeixinManager platformWeixinManager;
	
	@RequestMapping("/weixin-menu-conf")
	public String viewWeixinMenuConf() {
		return "/weixin/weixin-menu-conf";
	}
	
	@RequestMapping("/reinitial-weixin-menu-action")
	@ResponseBody
	public DWZAjaxObject reinitialWeixinMenuAciton() {
		boolean isok = platformWeixinManager.createWeixinEnum();
		if (isok) {
			DWZAjaxObject dwzAjaxObject = DWZAjaxObject.create(DWZStatusCode.Success);
			dwzAjaxObject.setMessage("微信菜单初始化完成，请在微信端检测初始化结果");
			return dwzAjaxObject;
		}else {
			DWZAjaxObject dwzAjaxObject = DWZAjaxObject.create(DWZStatusCode.Error);
			dwzAjaxObject.setMessage("微信菜单初始化错误，请到控制台检查错误信息");
			return dwzAjaxObject;
		}
	}
}
