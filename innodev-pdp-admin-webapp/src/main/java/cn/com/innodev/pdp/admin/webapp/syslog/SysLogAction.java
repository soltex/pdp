/**
 * 
 */
package cn.com.innodev.pdp.admin.webapp.syslog;

import java.util.Collection;

import org.springframework.ui.ModelMap;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cn.com.innodev.pdp.framework.syslog.LogService;
import cn.com.innodev.pdp.framework.syslog.SystemLog;
import cn.com.innodev.pdp.framework.web.DWZAjaxObject;
import cn.com.innodev.pdp.framework.web.DWZAjaxObject.DWZStatusCode;

import com.vanstone.common.util.web.PageInfo;
import com.vanstone.common.util.web.PageUtil;
import com.vanstone.webframework.AbstractBaseSpringAction;

/**
 * @author shipeng
 */
@Controller
@RequestMapping("/syslog")
public class SysLogAction extends AbstractBaseSpringAction {

	@Autowired
	private LogService logService;
	
	@RequestMapping("/search-syslogs")
	public String searchSysLogs(HttpServletRequest servletRequest, HttpServletResponse servletResponse, ModelMap modelMap, @RequestParam(value="p", required=false)Integer p) {
		if (p == null) {
			p = 1;
		}
		long allrows = this.logService.searchTotalSystemLogs(null);
		PageUtil<SystemLog> pageUtil = new PageUtil<SystemLog>((int)allrows, p, 20);
		PageInfo<SystemLog> pageInfo = pageUtil.getPageInfo();
		Collection<SystemLog> systemLogs = this.logService.searchSystemLogs(null, pageUtil.getOffset(), pageUtil.getSize());
		pageInfo.addObjects(systemLogs);
		modelMap.put("pageInfo", pageInfo);
		return "/syslog/search-syslogs";
	}
	
	@RequestMapping(value="/delete-syslog/{id}")
	public DWZAjaxObject deleteSysLog(@PathVariable("id")String id) {
		return DWZAjaxObject.create(DWZStatusCode.Success);
	}
	
}
