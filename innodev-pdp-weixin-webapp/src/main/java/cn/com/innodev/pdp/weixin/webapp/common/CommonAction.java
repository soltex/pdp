/**
 * 
 */
package cn.com.innodev.pdp.weixin.webapp.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.innodev.pdp.business.sdk.weixinservice.proprietor.WeixinProprietorManager;
import cn.com.innodev.pdp.framework.web.DWZAjaxObject;

/**
 * @author shipeng
 */
@Controller
@RequestMapping("/common")
public class CommonAction {
	
	@Autowired
	private WeixinProprietorManager weixinProprietorManager;
	
	@RequestMapping("/retrieval-validatecode/{mobile}")
	@ResponseBody
	public DWZAjaxObject retrievalValidatecode(@PathVariable("mobile")String mobile) {
		weixinProprietorManager.generateValidatecode(mobile);
		DWZAjaxObject object = DWZAjaxObject.createSuccessObject("短信已下发到手机，请查收");
		return object;
	}
	
}
