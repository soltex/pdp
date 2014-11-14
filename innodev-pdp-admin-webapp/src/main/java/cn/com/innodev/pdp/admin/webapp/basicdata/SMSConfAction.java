/**
 * 
 */
package cn.com.innodev.pdp.admin.webapp.basicdata;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.innodev.pdp.framework.Constants;
import cn.com.innodev.pdp.framework.Constants.SMS_Conf;
import cn.com.innodev.pdp.framework.sms.SMS;
import cn.com.innodev.pdp.framework.sms.SMSCreator;
import cn.com.innodev.pdp.framework.utils.StringSpliter;
import cn.com.innodev.pdp.framework.web.DWZAjaxObject;
import cn.com.innodev.pdp.framework.web.DWZAjaxObject.DWZStatusCode;

import com.vanstone.configuration.client.ConfigurationFactory;
import com.vanstone.configuration.client.IConfigurationManager;
import com.vanstone.configuration.sdk.ConfNotFoundException;
import com.vanstone.configuration.sdk.ConfSDKFactory;
import com.vanstone.configuration.sdk.IConfSDKManager;
import com.vanstone.webframework.AbstractBaseSpringAction;

/**
 * 
 * @author shipeng
 */
@Controller
@RequestMapping("/basicdata")
public class SMSConfAction extends AbstractBaseSpringAction {

	@RequestMapping("/view-sms-conf")
	public String viewSMSConf(ModelMap modelMap) {
		IConfigurationManager configurationManager = ConfigurationFactory.getConfigurationManager();
		SMS_Conf[] smsConfs = Constants.SMS_Conf.values();
		Map<SMS_Conf, String> dataMap = new LinkedHashMap<Constants.SMS_Conf, String>();
		for (SMS_Conf smsConf : smsConfs) {
			dataMap.put(smsConf, configurationManager.getValue(Constants.INNODEV_PDP_SMS_GROUP, smsConf.getCode()));
		}
		modelMap.put("dataMap", dataMap);
		return "/basicdata/view-sms-conf";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/save-sms-conf-action")
	@ResponseBody
	public DWZAjaxObject saveSMSConfAction(HttpServletRequest servletRequest) {
		Enumeration<String> paramNames = servletRequest.getParameterNames();
		IConfigurationManager configurationManager = ConfigurationFactory.getConfigurationManager();
		IConfSDKManager confSDKManager = ConfSDKFactory.getConfSDKManager();
		
		while (paramNames.hasMoreElements()) {
			String name = paramNames.nextElement();
			String value = servletRequest.getParameter(name);
			if (value == null || "".equals(value)) {
				throw new IllegalArgumentException();
			}
			String tmpValue = configurationManager.getValue(Constants.INNODEV_PDP_SMS_GROUP, name);
			if (tmpValue == null || "".equals(tmpValue)) {
				//写入新配置信息
				confSDKManager.addConf(Constants.INNODEV_PDP_SMS_GROUP, name, value);
			}else{
				//更新配置信息
				try {
					confSDKManager.updateConf(Constants.INNODEV_PDP_SMS_GROUP, name, value);
				} catch (ConfNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		DWZAjaxObject object = DWZAjaxObject.create(DWZStatusCode.Success);
		object.setMessage("短信配置信息保存成功.");
		return object;
	}
	
	@RequestMapping("/sms-tools")
	public String viewSmsTools(ModelMap modelMap, @ModelAttribute("smsToolsForm")SmsToolsForm smsToolsForm) {
		return "/basicdata/sms-tools";
	}
	
	@RequestMapping("/batch-send-sms-action")
	@ResponseBody
	public DWZAjaxObject batchSendSMSAction(ModelMap modelMap, @ModelAttribute("smsToolsForm")SmsToolsForm smsToolsForm) {
		String strMobiles = smsToolsForm.getMobiles();
		String[] mobiles = StringSpliter.split(strMobiles);
		for (String mobile : mobiles) {
			if (mobile != null && !mobile.equals("")) {
				SMS sms = SMSCreator.getInstance().createSMS(mobile, smsToolsForm.getSmsContent());
				sms.send(true);
			}
		}
		return DWZAjaxObject.createSuccessObject("批量发送成功,请查收短信.");
	}
}
