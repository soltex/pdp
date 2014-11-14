/**
 * 
 */
package cn.com.innodev.pdp.admin.webapp.admin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.innodev.pdp.admin.Admin;
import cn.com.innodev.pdp.admin.services.AdminService;
import cn.com.innodev.pdp.business.sdk.adminservice.auth.AuthManager;
import cn.com.innodev.pdp.framework.Constants;
import cn.com.innodev.pdp.framework.web.DWZAjaxObject;
import cn.com.innodev.pdp.framework.web.DWZAjaxObject.DWZStatusCode;

import com.vanstone.business.ObjectDuplicateException;
import com.vanstone.common.util.random.RandomNumber;
import com.vanstone.common.util.web.PageInfo;
import com.vanstone.common.util.web.PageUtil;
import com.vanstone.webframework.AbstractBaseSpringAction;

/**
 * @author shipeng
 */
@Controller
@RequestMapping("/admin")
public class AdminAction extends AbstractBaseSpringAction {
	
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private AuthManager authManager;
	
	@RequestMapping("/add-admin")
	public String addAdmin(ModelMap modelMap,@ModelAttribute("adminForm")AdminForm adminForm) {
		return "/admin/add-admin";
	}
	
	@RequestMapping("/add-admin-action")
	@ResponseBody
	public DWZAjaxObject addAdminAction(ModelMap modelMap, @ModelAttribute("adminForm")AdminForm adminForm) {
		Admin admin = new Admin();
		admin.setAdminName(adminForm.getAdminName());
		admin.setAdminPwd(adminForm.getAdminPwd() != null && !adminForm.getAdminPwd().equals("") ? adminForm.getAdminPwd() : RandomNumber.randomNumber(Constants.DEFAULT_STAFF_PASSWORD_LENGTH));
		try {
			admin = adminService.addAdmin(admin);
		} catch (ObjectDuplicateException e) {
			e.printStackTrace();
			return DWZAjaxObject.create(DWZStatusCode.Error, "账户名重复，请检查后重新填写。");
		}
		
		DWZAjaxObject object = DWZAjaxObject.createSuccessObject(null);
		object.setForwardUrl("/admin/view-admins.jhtml");
		return object;
	}
	
	@RequestMapping("/view-admins")
	public String getAdmins(ModelMap modelMap, @RequestParam(value="p",required=false)Integer p) {
		if (p == null) {
			p = 1;
		}
		int total = this.adminService.getTotalAdmins();
		PageUtil<Admin> pageUtil = new PageUtil<Admin>(total, p, Constants.DEFAULT_ADMIN_PAGESIZE);
		Collection<Admin> admins = this.adminService.getAdmins(pageUtil.getOffset(), pageUtil.getSize());
		PageInfo<Admin> pageInfo = pageUtil.getPageInfo();
		pageInfo.addObjects(admins);
		modelMap.put("pageInfo", pageInfo);
		return "/admin/view-admins";
	}
		
	@RequestMapping("/reset-password")
	@ResponseBody
	public DWZAjaxObject resetPassword(ModelMap modelMap, @RequestParam(value="p",required=false)Integer p, @RequestParam("id")String id, @RequestParam(value="rel",required=false)String rel) {
		if (rel == null || rel.equals("")) {
			rel = Constants.DWZ_CONTAINER_HTML_ID;
		}
		this.authManager.resetPassword(id);
		DWZAjaxObject object = DWZAjaxObject.createSuccessObject();
		object.setForwardUrl("/admin/view-admins.jhtml?p=" + p);
		object.setRel(rel);
		return object;
	}
	
}
