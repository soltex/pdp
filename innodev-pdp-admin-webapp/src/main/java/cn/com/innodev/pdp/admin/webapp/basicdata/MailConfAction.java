/**
 * 
 */
package cn.com.innodev.pdp.admin.webapp.basicdata;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.innodev.pdp.framework.Constants;
import cn.com.innodev.pdp.framework.Constants.MailConf;
import cn.com.innodev.pdp.framework.mail.MailManager;
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
public class MailConfAction extends AbstractBaseSpringAction {

	private static Logger LOG = LoggerFactory.getLogger(MailConfAction.class);
	
	@Autowired
	private MailManager mailManager;
	
	@RequestMapping("/view-mail-conf")
	public String viewMailConf(ModelMap modelMap) {
		IConfigurationManager configurationManager = ConfigurationFactory.getConfigurationManager();
		MailConf[] mailConfs = Constants.MailConf.values();
		Map<MailConf, String> dataMap = new LinkedHashMap<Constants.MailConf, String>();
		for (MailConf mailConf : mailConfs) {
			dataMap.put(mailConf, configurationManager.getValue(Constants.INNODEV_PDP_MAIL_GROUP, mailConf.getCode()));
		}
		modelMap.put("dataMap", dataMap);
		return "/basicdata/view-mail-conf";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/save-mail-conf-action")
	@ResponseBody
	public DWZAjaxObject saveMailConfAction(HttpServletRequest servletRequest) {
		Enumeration<String> paramNames = servletRequest.getParameterNames();
		IConfigurationManager configurationManager = ConfigurationFactory.getConfigurationManager();
		IConfSDKManager confSDKManager = ConfSDKFactory.getConfSDKManager();
		
		while (paramNames.hasMoreElements()) {
			String name = paramNames.nextElement();
			String value = servletRequest.getParameter(name);
			if (value == null || "".equals(value)) {
				throw new IllegalArgumentException();
			}
			String tmpValue = configurationManager.getValue(Constants.INNODEV_PDP_MAIL_GROUP, name);
			if (tmpValue == null || "".equals(tmpValue)) {
				//写入新配置信息
				confSDKManager.addConf(Constants.INNODEV_PDP_MAIL_GROUP, name, value);
			}else{
				//更新配置信息
				try {
					confSDKManager.updateConf(Constants.INNODEV_PDP_MAIL_GROUP, name, value);
				} catch (ConfNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		DWZAjaxObject object = DWZAjaxObject.create(DWZStatusCode.Success);
		object.setMessage("邮件主机配置信息保存成功.");
		return object;
	}
	
	@RequestMapping("/mail-tools")
	public String viewMailTools(ModelMap modelMap, @ModelAttribute("mailToolsForm")MailToolsForm mailToolsForm) {
		return "/basicdata/mail-tools";
	}
	
	@RequestMapping("/batch-send-mail-action")
	@ResponseBody
	public DWZAjaxObject batchSendMailAction(ModelMap modelMap, @ModelAttribute("mailToolsForm")MailToolsForm mailToolsForm) {
		String strEmails = mailToolsForm.getEmails();
		String[] emails = StringSpliter.split(strEmails);
		for (String email : emails) {
			if (email != null && !"".equals(email)) {
				LOG.debug("Send To Mail Address {}", email);
				mailManager.send(email, mailToolsForm.getMailTitle(), mailToolsForm.getMailContent());
			}
		}
		return DWZAjaxObject.createSuccessObject("批量发送成功,请查收邮件.");
	}
}
