/**
 * 
 */
package cn.com.innodev.pdp.admin.webapp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.innodev.pdp.business.sdk.adminservice.auth.AuthManager;
import cn.com.innodev.pdp.framework.web.DWZAjaxDialogObject;
import cn.com.innodev.pdp.framework.web.DWZAjaxObject.DWZStatusCode;

import com.vanstone.webframework.AbstractBaseSpringAction;

/**
 * @author shipeng
 */
@Controller("profileAction")
@RequestMapping("/profile")
public class ProfileAction extends AbstractBaseSpringAction {
	
	@Autowired
	private AuthManager authManager;
	
	@RequestMapping("/update-profile-password")
	public String updateProfilePassword(ModelMap modelMap,HttpServletRequest servletRequest, @ModelAttribute("profileForm")ProfileForm profileForm) {
		return  "/profile/update-profile-password";
	}
	
	@RequestMapping("/update-profile-password-action")
	@ResponseBody
	public DWZAjaxDialogObject updateProfilePasswordAction(ModelMap modelMap, @ModelAttribute("profileForm")ProfileForm profileForm) {
		DWZAjaxDialogObject object = DWZAjaxDialogObject.create(DWZStatusCode.Success, true);
		object.setMessage("编辑密码成功.");
		return object;
	}
}
