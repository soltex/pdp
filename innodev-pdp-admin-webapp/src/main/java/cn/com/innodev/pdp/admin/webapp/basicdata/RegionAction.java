/**
 * 
 */
package cn.com.innodev.pdp.admin.webapp.basicdata;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.com.innodev.pdp.business.sdk.adminservice.region.BatchImportResultBean;
import cn.com.innodev.pdp.business.sdk.adminservice.region.RegionImportException;
import cn.com.innodev.pdp.business.sdk.adminservice.region.RegionManager;
import cn.com.innodev.pdp.framework.Constants;
import cn.com.innodev.pdp.framework.bizcommon.CommonService;
import cn.com.innodev.pdp.framework.bizcommon.region.City;
import cn.com.innodev.pdp.framework.bizcommon.region.Province;
import cn.com.innodev.pdp.framework.web.DWZAjaxDialogObject;
import cn.com.innodev.pdp.framework.web.DWZAjaxObject;
import cn.com.innodev.pdp.framework.web.DWZAjaxObject.DWZStatusCode;

import com.vanstone.business.ObjectDuplicateException;
import com.vanstone.business.ObjectHasSubObjectException;
import com.vanstone.fs.FSException;
import com.vanstone.fs.FSFile;
import com.vanstone.fs.FSType;
import com.vanstone.webframework.AbstractBaseSpringAction;
import com.vanstone.webframework.utils.FSManagerExt;

/**
 * @author shipeng
 */
@Controller
@RequestMapping("/basicdata")
public class RegionAction extends AbstractBaseSpringAction {
	
	@Autowired
	private CommonService commonService;
	@Autowired
	private RegionManager regionManager;
	
	@RequestMapping("/view-provinces")
	public String addProvince(ModelMap modelMap) {
		Collection<Province> provinces = commonService.getProvincesWithCityCount();
		modelMap.put("provinces", provinces);
		return "/basicdata/view-provinces";
	}
	
	@RequestMapping("/add-province-action")
	@ResponseBody
	public DWZAjaxObject addProvinceAction(HttpServletRequest servletRequest, ModelMap modelMap) {
		String title = servletRequest.getParameter("title");
		if (StringUtils.isEmpty(title) || StringUtils.isBlank(title)) {
			throw new IllegalArgumentException();
		}
		Province province = new Province();
		province.setTitle(title);
		try {
			this.commonService.addProvince(province);
		} catch (ObjectDuplicateException e) {
			DWZAjaxObject object = DWZAjaxObject.create(DWZStatusCode.Error, "省份名称存在，请确认填写。");
			return object;
		}
		DWZAjaxObject object = DWZAjaxObject.createSuccessObject(null);
		object.setForwardUrl("/basicdata/view-provinces.jhtml");
		return object;
	}
	
	@RequestMapping("/update-province/{provinceId}")
	public String updateProvince(HttpServletRequest servletRequest, ModelMap modelMap, @PathVariable("provinceId")Integer provinceId) {
		Province province = this.commonService.getProvince(provinceId);
		if (province == null) {
			throw new IllegalArgumentException();
		}
		modelMap.put("province", province);
		return "/basicdata/update-province" ;
	}
	
	@RequestMapping("/delete-province/{provinceId}")
	@ResponseBody
	public DWZAjaxObject deleteProvince(@PathVariable("provinceId")Integer provinceId, @RequestParam(value="rel",required=false)String rel) {
		if (rel == null || rel.equals("")) {
			rel = Constants.DWZ_CONTAINER_HTML_ID;
		}
		try {
			this.commonService.deleteProvince(provinceId);
			DWZAjaxObject object = DWZAjaxObject.createSuccessObject();
			object.setRel(rel);
			object.setForwardUrl("/basicdata/view-provinces.jhtml");
			return object;
		} catch (ObjectHasSubObjectException e) {
			return DWZAjaxObject.create(DWZStatusCode.Error, "当前省份下存在城市信息，请检查。");
		}
	}
	
	@RequestMapping("/update-province-action/{provinceId}")
	@ResponseBody
	public DWZAjaxDialogObject updateProvinceAction(HttpServletRequest servletRequest, ModelMap modelMap,@PathVariable("provinceId")Integer provinceId) {
		Province province = this.commonService.getProvince(provinceId);
		if (province == null) {
			throw new IllegalArgumentException();
		}
		String title = servletRequest.getParameter("title");
		province.setTitle(title);
		try {
			this.commonService.updateProvince(province);
		} catch (ObjectDuplicateException e) {
			DWZAjaxDialogObject object = DWZAjaxDialogObject.create(DWZStatusCode.Error, false);
			object.setMessage("省份名称重复，请检查确认。");
			return object;
		}
		DWZAjaxDialogObject object = DWZAjaxDialogObject.create(DWZStatusCode.Success, true);
		object.setForwardUrl("/basicdata/view-provinces.jhtml");
		return object;
	}
	
	@RequestMapping("/view-cities")
	public String viewCities(ModelMap modelMap, @RequestParam(value="provinceId",required=false)Integer provinceId) {
		Collection<Province> provinces = commonService.getProvinces();
		modelMap.put("provinces", provinces);
		Province province = null;
		if (provinceId != null) {
			province = this.commonService.getProvince(provinceId);
		}
		Collection<City> cities = null;
		if (province != null) {
			cities = this.commonService.getCitiesWithBusinessObjectCount(provinceId);
		}else{
			cities = this.commonService.getCitiesWithBusinessObjectCount();
		}
		modelMap.put("provinces", provinces);
		modelMap.put("province", province);
		modelMap.put("cities", cities);
		return "/basicdata/view-cities";
	}
	
	@RequestMapping("/add-city-action")
	@ResponseBody
	public DWZAjaxObject addCityAction(HttpServletRequest servletRequest, ModelMap modelMap) {
		String strProvinceId = servletRequest.getParameter("provinceId");
		String title = servletRequest.getParameter("title");
		if (strProvinceId == null || "".equals(strProvinceId) || title == null || "".equals(title)) {
			throw new IllegalArgumentException();
		}
		Integer provinceId = Integer.parseInt(strProvinceId);
		Province province = this.commonService.getProvince(provinceId);
		if (province == null) {
			throw new IllegalArgumentException();
		}
		City city = new City(province);
		city.setTitle(title);
		try {
			this.commonService.addCity(city);
		} catch (ObjectDuplicateException e) {
			e.printStackTrace();
			DWZAjaxObject result = DWZAjaxObject.create(DWZStatusCode.Error, "城市名称重复。");
			return result;
		}
		DWZAjaxObject result = DWZAjaxObject.createSuccessObject("添加城市成功");
		result.setForwardUrl("/basicdata/view-cities.jhtml?provinceId=" + provinceId);
		return result;
	}
	
	@RequestMapping("/batch-import-regions")
	public String batchImportRegions(ModelMap modelMap, HttpServletRequest servletRequest) {
		return "/basicdata/batch-import-regions";
	}
	
	@RequestMapping("/batch-import-regions-action")
	@ResponseBody
	public DWZAjaxObject batchImportRegionsAction(ModelMap modelMap, HttpServletRequest servletRequest, @RequestParam("regionsMultipartFile")MultipartFile regionsMultipartFile) {
		try {
			FSFile fsFile = FSManagerExt.getInstance().uploadBySpring(regionsMultipartFile, FSType.Temporary);
			BatchImportResultBean bean = regionManager.batchImportRegions(fsFile);
			DWZAjaxObject result = null;
			if (bean.existFailureNames()) {
				StringBuffer sb  =new StringBuffer();
				for (String name : bean.getFailureCollection()) {
					sb.append(name + ",");
				}
				result = DWZAjaxObject.create(DWZStatusCode.Error, sb.toString());
				return result;
			}else{
				result = DWZAjaxObject.create(DWZStatusCode.Success, "数据导入成功");
				return result;
			}
		} catch (FSException e) {
			e.printStackTrace();
			DWZAjaxObject result = DWZAjaxObject.create(DWZStatusCode.Error, "上传文件格式错误.");
			return result;
		} catch (RegionImportException e) {
			e.printStackTrace();
			DWZAjaxObject result = DWZAjaxObject.create(DWZStatusCode.Error, "上传文件格式错误.");
			return result;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			DWZAjaxObject result = DWZAjaxObject.create(DWZStatusCode.Error, "上传文件格式错误.");
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			DWZAjaxObject result = DWZAjaxObject.create(DWZStatusCode.Error, "上传文件格式错误.");
			return result;
		}
	}
}
