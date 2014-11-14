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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.innodev.pdp.framework.Constants;
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
public class SysTemplateAction extends AbstractBaseSpringAction {
	
	@RequestMapping("/view-systemplate-conf")
	public String viewSysTemplateConf(ModelMap modelMap) {
		IConfigurationManager configurationManager = ConfigurationFactory.getConfigurationManager();
		Map<Constants.Global_SysTemplate, String> dataMap = new LinkedHashMap<Constants.Global_SysTemplate, String>();
		Constants.Global_SysTemplate[] global_SysTemplates = Constants.Global_SysTemplate.values();
		for (Constants.Global_SysTemplate global_SysTemplate : global_SysTemplates) {
			dataMap.put(global_SysTemplate, configurationManager.getValue(Constants.INNODEV_PDP_SYSTEMPLATE_GROUP, global_SysTemplate.getCode()));
		}
		modelMap.put("dataMap", dataMap);
		return "/basicdata/view-systemplate-conf";
	}
	
	@RequestMapping("/save-systemplate-conf-action")
	@SuppressWarnings("unchecked")
	@ResponseBody
	public DWZAjaxObject saveSystemplateConfAction(HttpServletRequest servletRequest) {
		Enumeration<String> paramNames = servletRequest.getParameterNames();
		IConfigurationManager configurationManager = ConfigurationFactory.getConfigurationManager();
		IConfSDKManager confSDKManager = ConfSDKFactory.getConfSDKManager();
		
		while (paramNames.hasMoreElements()) {
			String name = paramNames.nextElement();
			String value = servletRequest.getParameter(name);
			if (value == null || "".equals(value)) {
				throw new IllegalArgumentException();
			}
			String tmpValue = configurationManager.getValue(Constants.INNODEV_PDP_SYSTEMPLATE_GROUP, name);
			if (tmpValue == null || "".equals(tmpValue)) {
				//写入新配置信息
				confSDKManager.addConf(Constants.INNODEV_PDP_SYSTEMPLATE_GROUP, name, value);
			}else{
				//更新配置信息
				try {
					confSDKManager.updateConf(Constants.INNODEV_PDP_SYSTEMPLATE_GROUP, name, value);
				} catch (ConfNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		DWZAjaxObject object = DWZAjaxObject.create(DWZStatusCode.Success);
		object.setMessage("系统模板配置信息保存成功.");
		object.setForwardUrl("/basicdata/view-systemplate-conf.jhtml");
		return object;
	}
}
