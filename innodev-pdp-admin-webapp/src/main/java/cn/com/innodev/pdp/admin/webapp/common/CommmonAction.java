/**
 * 
 */
package cn.com.innodev.pdp.admin.webapp.common;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.com.innodev.pdp.framework.bizcommon.CommonService;
import cn.com.innodev.pdp.framework.bizcommon.region.City;

import com.vanstone.webframework.AbstractBaseSpringAction;

/**
 * @author shipeng
 *
 */
@Controller
@RequestMapping("/common")
public class CommmonAction extends AbstractBaseSpringAction {

	@Autowired
	private CommonService commonService;
	
	@RequestMapping("/view-cities-select")
	public String viewCitiesSelect(@RequestParam(value="provinceId",required=false)Integer provinceId, ModelMap modelMap) {
		if (provinceId != null) {
			Collection<City> cities = this.commonService.getCities(provinceId);
			modelMap.put("cities", cities);
		}
		return "/common/view-cities-select";
	}
	
}
