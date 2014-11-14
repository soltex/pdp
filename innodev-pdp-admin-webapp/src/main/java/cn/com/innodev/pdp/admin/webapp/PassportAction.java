/**
 * 
 */
package cn.com.innodev.pdp.admin.webapp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.innodev.pdp.business.sdk.adminservice.auth.AdminLoginException;
import cn.com.innodev.pdp.business.sdk.adminservice.auth.AdminLoginException.ErrorCode;
import cn.com.innodev.pdp.business.sdk.adminservice.auth.AuthManager;
import cn.com.innodev.pdp.framework.web.DWZAjaxObject;
import cn.com.innodev.pdp.framework.web.DWZAjaxObject.DWZStatusCode;

import com.vanstone.webframework.AbstractBaseSpringAction;

/**
 * PassportAction
 * @author shipeng
 */
@Controller("passportAction")
@RequestMapping("")
public class PassportAction extends AbstractBaseSpringAction {
	
	@Autowired
	private AuthManager authManager;
	
	@RequestMapping("/login")
	public String login(HttpServletRequest servletRequest, HttpServletResponse servletResponse, @ModelAttribute("passportForm")PassportForm passportForm, ModelMap modelMap) {
		return "/login";
	}
	
	@RequestMapping("/login-action")
	@ResponseBody
	public DWZAjaxObject loginAction(HttpServletRequest servletRequest, HttpServletResponse servletResponse, @ModelAttribute("passportForm")PassportForm passportForm, ModelMap modelMap) {
		try {
			authManager.login(passportForm.getUserName(), passportForm.getUserPwd(), passportForm.isRememberPassword(), servletRequest);
		} catch (AdminLoginException e) {
			DWZAjaxObject bean = DWZAjaxObject.create(DWZStatusCode.Error);
			if (e.getErrorCode().equals(ErrorCode.Admin_Name_Not_Found)) {
				bean.setMessage("管理员账号不存在，请检查");
				return bean;
			}
			if (e.getErrorCode().equals(ErrorCode.Admin_Name_Password_Not_Match)) {
				bean.setMessage("管理员账户密码不匹配，请检查");
				return bean;
			}
		}
		DWZAjaxObject bean = DWZAjaxObject.create(DWZStatusCode.Success);
		bean.addParam("redirectUrl", "/index.jhtml");
		return bean;
	}
	
	@RequestMapping("/logout")
	public String logout(@ModelAttribute("passportForm")PassportForm passportForm, ModelMap modelMap, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		this.authManager.logout(servletRequest);
		return "redirect:/login.jhtml";
	}
	
}
