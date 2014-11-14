/**
 * 
 */
package cn.com.innodev.pdp.admin.webapp.community;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.com.innodev.pdp.business.sdk.adminservice.community.CommunityManager;
import cn.com.innodev.pdp.community.AlbumType;
import cn.com.innodev.pdp.community.Building;
import cn.com.innodev.pdp.community.Community;
import cn.com.innodev.pdp.community.CommunityImage;
import cn.com.innodev.pdp.community.FloorPlanType;
import cn.com.innodev.pdp.community.Room;
import cn.com.innodev.pdp.community.services.CommunityService;
import cn.com.innodev.pdp.framework.Constants;
import cn.com.innodev.pdp.framework.bizcommon.CommonService;
import cn.com.innodev.pdp.framework.bizcommon.ImageBean;
import cn.com.innodev.pdp.framework.bizcommon.ImageFormatIncorrentException;
import cn.com.innodev.pdp.framework.bizcommon.region.City;
import cn.com.innodev.pdp.framework.bizcommon.region.Province;
import cn.com.innodev.pdp.framework.web.DWZAjaxObject;
import cn.com.innodev.pdp.framework.web.DWZAjaxObject.DWZStatusCode;

import com.vanstone.business.ObjectDuplicateException;
import com.vanstone.business.lang.EnumUtils;
import com.vanstone.common.util.image.ImagePropertyVO;
import com.vanstone.common.util.image.ImageUtil;
import com.vanstone.common.util.web.PageInfo;
import com.vanstone.common.util.web.PageUtil;
import com.vanstone.fs.FSException;
import com.vanstone.fs.FSFile;
import com.vanstone.fs.FSManager;
import com.vanstone.fs.FSType;
import com.vanstone.webframework.AbstractBaseSpringAction;
import com.vanstone.webframework.utils.FSManagerExt;
import com.vanstone.weedfs.client.RequestResult;
import com.vanstone.weedfs.client.WeedFile;
import com.vanstone.weedfs.client.impl.WeedFSClient;

/**
 * @author shipeng
 */
@Controller
@RequestMapping("/community")
public class CommunityAction extends AbstractBaseSpringAction {
	
	@Autowired
	private CommonService commonService;
	@Autowired
	private CommunityManager communityManager;
	@Autowired
	private CommunityService communityService;
	
	@RequestMapping("/new-community")
	public String newCommunity(ModelMap modelMap, @ModelAttribute("communityForm")CommunityForm communityForm) {
		Collection<Province> provinces = this.commonService.getProvinces();
		modelMap.put("provinces", provinces);
		return "/community/new-community";
	}
	
	@RequestMapping("/newcommunity-action")
	@ResponseBody
	public DWZAjaxObject newCommunityAction(ModelMap modelMap, @ModelAttribute("communityForm")CommunityForm communityForm) {
		FSFile qrFSFile = null;
		try {
			qrFSFile = FSManagerExt.getInstance().uploadBySpring(communityForm.getQrOfCommunityMultipartFile(), FSType.Temporary);
		} catch (FSException e) {
			e.printStackTrace();
			DWZAjaxObject object = DWZAjaxObject.create(DWZStatusCode.Error, "文件格式错误或文件大小超出预知大小,请检查。");
			return object;
		}
		City city = this.commonService.getCity(communityForm.getCityId());
		try {
			communityManager.newCommunity(communityForm.getCommunityId(), communityForm.getCommunityName(), communityForm.getLongitude(), communityForm.getLatitude(), city, qrFSFile, communityForm.getCompanyName(), communityForm.getNotificationMail());
		} catch (ImageFormatIncorrentException e) {
			DWZAjaxObject object = DWZAjaxObject.create(DWZStatusCode.Error, "文件格式错误或文件大小超出预知大小,请检查。");
			return object;
		} catch (ObjectDuplicateException e) {
			DWZAjaxObject object = DWZAjaxObject.create(DWZStatusCode.Error, "社区引用ID重复，请重新填写。");
			return object;
		}
		DWZAjaxObject object = DWZAjaxObject.create(DWZStatusCode.Success);
		object.setForwardUrl("/community/view-communities.jhtml");
		return object;
	}
	
	@RequestMapping("/view-communities")
	public String viewCommunities(ModelMap modelMap, @ModelAttribute("communityForm")CommunityForm communityForm , @RequestParam(value="p",required=false)Integer p) {
		if (p == null) {
			p = 1;
		}
		int allrows = this.communityService.getTotalCommunities(communityForm.getKey());
		PageUtil<Community> pageUtil = new PageUtil<Community>(allrows, p, Constants.DEFAULT_ADMIN_PAGESIZE);
		PageInfo<Community> pageInfo = pageUtil.getPageInfo();
		Collection<Community> communities = this.communityService.getCommunities(communityForm.getKey(), pageUtil.getOffset(), pageUtil.getSize());
		pageInfo.addObjects(communities);
		modelMap.put("pageInfo", pageInfo);
		return "/community/view-communities";
	}
	
	@RequestMapping("/update-basic-community/{id}")
	public String updateBasicCommunity(@PathVariable("id")String communityId, ModelMap modelMap, @ModelAttribute("communityForm")CommunityForm form) {
		Community community = this.communityService.getCommunity(communityId);
		if (community == null) {
			throw new IllegalArgumentException();
		}
		Collection<Province> provinces = this.commonService.getProvinces();
		Collection<City> cities = this.commonService.getCities(community.getCity().getProvince().getId());
		modelMap.put("provinces", provinces);
		modelMap.put("cities", cities);
		modelMap.put("community", community);
		form.setProvinceId(community.getCity().getProvince().getId());
		form.setCityId(community.getCity().getId());
		form.setCommunityName(community.getCommunityName());
		form.setLongitude(community.getLongitude());
		form.setLatitude(community.getLatitude());
		form.setContent(community.getContent());
		form.setAvgPrice(community.getAvgPrice());
		form.setAddress(community.getAddress());
		return "/community/update-basic-community";
	}
	
	@RequestMapping("/update-basic-community-action/{id}")
	@ResponseBody
	public DWZAjaxObject updateBasicCommunityAction(@PathVariable("id")String communityId, ModelMap modelMap, @ModelAttribute("communityForm")CommunityForm communityForm) {
		Community community = this.communityService.getCommunity(communityId);
		if (community == null) {
			throw new IllegalArgumentException();
		}
		community.setCommunityName(communityForm.getCommunityName());
		community.setLongitude(communityForm.getLongitude());
		community.setLatitude(communityForm.getLatitude());
		community.setContent(communityForm.getContent());
		community.setAvgPrice(communityForm.getAvgPrice());
		community.setAddress(communityForm.getAddress());
		community.setCity(this.commonService.getCity(communityForm.getCityId()));
		this.communityService.updateCommunityBaseInfo(community);
		return DWZAjaxObject.createSuccessObject("修改社区基本信息成功。");
	}
	
	@RequestMapping("/update-community-qr/{id}")
	public String updateCommunityQR(@PathVariable("id")String communityId, ModelMap modelMap, @ModelAttribute("communityForm")CommunityForm communityForm) {
		
		Community community = this.communityService.getCommunity(communityId);
		if (community == null) {
			throw new IllegalArgumentException();
		}
		modelMap.put("community", community);
		return "/community/update-community-qr";
	}
	
	@RequestMapping("/update-community-qr-action/{id}")
	@ResponseBody
	public DWZAjaxObject updateCommunityQRAction(@PathVariable("id")String communityId, ModelMap modelMap, @ModelAttribute("communityForm")CommunityForm communityForm) {
		MultipartFile qrMultipartFile = communityForm.getQrOfCommunityMultipartFile();
		if (qrMultipartFile == null || qrMultipartFile.getName() == null || qrMultipartFile.getName().equals("")) { 
			return DWZAjaxObject.create(DWZStatusCode.Error, "上传文件格式错误，请重新选择");
		}
		try {
			FSFile qrFSFile = FSManagerExt.getInstance().uploadBySpring(qrMultipartFile, FSType.Temporary);
			communityManager.updateCommunityQRFsFile(communityId, qrFSFile);
			DWZAjaxObject object = DWZAjaxObject.createSuccessObject("修改成功");
			object.setForwardUrl("/community/update-community-qr/" + communityId + ".jhtml");
			return object;
		} catch (FSException e) {
			return DWZAjaxObject.create(DWZStatusCode.Error, "上传文件格式错误，请重新选择");
		} catch (ImageFormatIncorrentException e) {
			return DWZAjaxObject.create(DWZStatusCode.Error, "上传文件格式错误，请重新选择");
		}
	}
	
	@RequestMapping("/delete-community-qr/{id}")
	@ResponseBody
	public DWZAjaxObject deleteCommunityQR(@PathVariable("id")String communityId, @RequestParam(value="rel",required=false)String rel, ModelMap modelMap) {
		Community community = this.communityService.getCommunity(communityId);
		if (community == null) {
			throw new IllegalArgumentException();
		}
		if (rel == null || rel.equals("")) {
			rel = Constants.DWZ_CONTAINER_HTML_ID;
		}
		this.communityService.deleteQRImage(communityId);
		DWZAjaxObject object = DWZAjaxObject.createSuccessObject(null);
		object.setForwardUrl("/community/update-community-qr/" + communityId + ".jhtml?rand=" + System.currentTimeMillis());
		object.setRel(rel);
		return object;
	}
	
	@RequestMapping("/update-project-company/{id}")
	public String updateProjectCompany(@PathVariable("id")String communityId, ModelMap modelMap, @ModelAttribute("communityForm")CommunityForm communityForm) {
		Community community = this.communityService.getCommunity(communityId);
		if (community == null) {
			throw new IllegalArgumentException();
		}
		modelMap.put("community", community);
		communityForm.setCompanyName(community.getProjectCompany().getCompanyName());
		communityForm.setNotificationMail(community.getProjectCompany().getCompanyEmail());
		return  "/community/update-project-company";
	}
	
	@RequestMapping("/update-project-company-action/{id}")
	@ResponseBody
	public DWZAjaxObject updateProjectCompanyAction(@PathVariable("id")String communityId, ModelMap modelMap, @ModelAttribute("communityForm")CommunityForm communityForm) {
		Community community = this.communityService.getCommunity(communityId);
		if (community == null) {
			throw new IllegalArgumentException();
		}
		modelMap.put("community", community);
		this.communityService.updateProjectCompany(communityId, communityForm.getCompanyName(), communityForm.getNotificationMail());
		return DWZAjaxObject.createSuccessObject("修改工程公司信息成功。");
	}
	
	@RequestMapping("/add-community-images/{communityId}")
	public String updateCommunityImages(@PathVariable("communityId")String communityId, ModelMap modelMap, @ModelAttribute("communityForm")CommunityForm communityForm) {
		Community community = this.communityService.getCommunity(communityId);
		if (community == null) {
			throw new IllegalArgumentException();
		}
		modelMap.put("community", community);
		return "/community/add-community-images";
	}
	
	@RequestMapping("/upload-community-images-action/{communityId}")
	@ResponseBody
	public DWZAjaxObject uploadCommunityImagesAction(@PathVariable("communityId")String communityId, ModelMap modelMap, @ModelAttribute("communityForm")CommunityForm communityForm) {
		Community community = this.communityService.getCommunity(communityId);
		if (community == null) {
			throw new IllegalArgumentException();
		}
		modelMap.put("community", community);
		WeedFSClient weedFSClient = new WeedFSClient();
		if (communityForm.getUploadFiles() == null || communityForm.getUploadFiles().length <=0) {
			return DWZAjaxObject.create(DWZStatusCode.Error, "请选择文件，文件必须为文件。");
		}
		Collection<ImageBean> imageBeans = new ArrayList<ImageBean>();
		StringBuffer sb = new StringBuffer();
		for (MultipartFile multipartFile : communityForm.getUploadFiles()) {
			try {
				FSFile fsFile = FSManagerExt.getInstance().uploadBySpring(multipartFile, FSType.Temporary);
				ImagePropertyVO imagePropertyVO = ImageUtil.getProperty(fsFile.getInputStream());
				RequestResult requestResult = weedFSClient.upload(fsFile.getFile());
				if (requestResult.getFid() == null || requestResult.getFid().equals("")) {
					return DWZAjaxObject.create(DWZStatusCode.Error, "WeedFS Server Error");
				}
				ImageBean imageBean = new ImageBean(new WeedFile(requestResult.getFid(), fsFile.getExtensionName()), imagePropertyVO);
				imageBeans.add(imageBean);
				sb.append(imageBean.getWeedFile().getFileid()).append(".").append(imageBean.getWeedFile().getExtName()).append(";");
			} catch (FSException e) {
				e.printStackTrace();
				return DWZAjaxObject.create(DWZStatusCode.Error, "文件不合法，请确认文件是否为图片信息以及文件大小是否合法。");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return DWZAjaxObject.create(DWZStatusCode.Error, "文件不合法，请确认文件是否为图片信息以及文件大小是否合法。");
			}
		}
		DWZAjaxObject object = DWZAjaxObject.createSuccessObject("文件上传成功，请在以下编辑文件标题以及文件类型");
		object.setRel(communityForm.getRel());
		object.setForwardUrl("/community/load-uploadfiles.jhtml?fileids=" + sb.toString()+"&communityId=" + communityId);
		object.addParam("imageBeans", imageBeans);
		return object;
	}
	
	@RequestMapping("/add-community-images-action/{communityId}")
	@ResponseBody
	public DWZAjaxObject addCommunityImagesAction(@PathVariable("communityId")String communityId, ModelMap modelMap, @ModelAttribute("communityForm")CommunityForm communityForm) {
		
		Community loadCommunity = this.communityService.getCommunity(communityId);
		if (loadCommunity == null) {
			throw new IllegalArgumentException();
		}
		modelMap.put("community", loadCommunity);
		List<String> fids = communityForm.getFids();
		if (fids == null || fids.size() <=0) {
			throw new IllegalArgumentException();
		}
		Collection<CommunityImage> communityImages = new ArrayList<CommunityImage>();
		WeedFSClient weedFsClient = new WeedFSClient();
		for (int i=0;i<fids.size();i++) {
			Integer albumTypeValue = communityForm.getAlbumTypes().get(i);
			if (albumTypeValue == null) {
				continue;
			}
			CommunityImage image = null;
			AlbumType albumType = EnumUtils.getByCode(albumTypeValue, AlbumType.class);
			FloorPlanType floorPlanType = null;
			if (albumType.equals(AlbumType.Floor_Plan_Type)) {
				Integer floorTypeValue = communityForm.getFloorPlanTypes().get(i);
				if (floorTypeValue == null) {
					return DWZAjaxObject.create(DWZStatusCode.Error, "请选择户型类型,请重新选定.");
				}
				floorPlanType = EnumUtils.getByCode(floorTypeValue, FloorPlanType.class);
				image = new CommunityImage(floorPlanType);
			}else{
				image = new CommunityImage(albumType);
			}
			String title = communityForm.getTitles().get(i);
			image.setTitle(title);
			FSFile fsFile = FSManager.getInstance().newAndCreateFSFile(UUID.randomUUID().toString(), communityForm.getExtNames().get(i), FSType.Temporary);
			weedFsClient.downloadToFSFile(fids.get(i), fsFile);
			try {
				ImagePropertyVO propertyVO = ImageUtil.getProperty(fsFile.getInputStream());
				ImageBean imageBean = new ImageBean(new WeedFile(fids.get(i), communityForm.getExtNames().get(i)),propertyVO);
				image.setImageBean(imageBean);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return DWZAjaxObject.create(DWZStatusCode.Error, "图片文件错误，请重新检查");
			}
			communityImages.add(image);
		}
		if (communityImages.size() <=0) {
			return DWZAjaxObject.create(DWZStatusCode.Error, "请选择文件，重新上传!");
		}
		for (CommunityImage communityImage : communityImages) {
			this.communityService.addCommunityImage(loadCommunity, communityImage);
		}
		DWZAjaxObject object = DWZAjaxObject.createSuccessObject(null);
		object.setForwardUrl("/community/view-community-images/" + communityId + ".jhtml");
		return object;
	}
	
	@RequestMapping("/view-community-images/{communityId}")
	public String viewCommunityImages(@PathVariable("communityId")String communityId, ModelMap modelMap, @ModelAttribute("communityForm")CommunityForm communityForm) {
		Community loadCommunity = this.communityService.getCommunity(communityId);
		if (loadCommunity == null) {
			throw new IllegalArgumentException();
		}
		Map<AlbumType,Collection<CommunityImage>> dataMap = this.communityService.getCommunityImages(communityId);
		modelMap.put("dataMap", dataMap);
		modelMap.put("community", loadCommunity);
		return "/community/view-community-images";
	}
	
	@RequestMapping("/delete-community-image/{communityId}/{imageId}")
	@ResponseBody
	public DWZAjaxObject deleteCommunityImage(@PathVariable("communityId")String communityId, @RequestParam(value="rel",required=false)String rel, @PathVariable("imageId")String imageId, ModelMap modelMap) {
		Community loadCommunity = this.communityService.getCommunity(communityId);
		if (loadCommunity == null) {
			throw new IllegalArgumentException();
		}
		if (rel == null || "".equals(rel)) {
			rel = Constants.DWZ_CONTAINER_HTML_ID;
		}
		CommunityImage communityImage = this.communityService.getCommunityImage(imageId);
		if (communityImage == null) {
			throw new IllegalArgumentException();
		}
		this.communityService.deleteCommunityImage(imageId);
		DWZAjaxObject object = DWZAjaxObject.create(DWZStatusCode.Success);
		object.setForwardUrl("/community/view-community-images/" + communityId + ".jhtml");
		object.setRel(rel);
		return object;
	}
	
	@RequestMapping("/load-uploadfiles")
	public String loadUploadfiles(@RequestParam("fileids")String fileids, @RequestParam("communityId")String communityId, ModelMap modelMap) {
		Community loadCommunity = this.communityService.getCommunity(communityId);
		if (loadCommunity == null) {
			throw new IllegalArgumentException();
		}
		modelMap.put("community", loadCommunity);
		
		String[] fids = StringUtils.split(fileids, ";");
		Collection<ImageBean> imageBeans = new ArrayList<ImageBean>();
		for (String fid : fids) {
			String id = FilenameUtils.getBaseName(fid);
			String extName = FilenameUtils.getExtension(fid);
			ImageBean imageBean = new ImageBean();
			WeedFile weedFile = new WeedFile(id, extName);
			imageBean.setWeedFile(weedFile);
			imageBeans.add(imageBean);
		}
		modelMap.put("imageBeans", imageBeans);
		modelMap.put("albumTypes", AlbumType.values());
		modelMap.put("floorPlanTypes", FloorPlanType.values());
		modelMap.put("communityId", communityId);
		
		return "/community/load-uploadfileids";
	}
	
	@RequestMapping("/add-building/{communityId}")
	public String addBuilding(@PathVariable("communityId")String communityId,ModelMap modelMap,@ModelAttribute("communityForm")CommunityForm communityForm) {
		Community loadCommunity = this.communityService.getCommunity(communityId);
		if (loadCommunity == null) {
			throw new IllegalArgumentException();
		}
		modelMap.put("community", loadCommunity);
		return "/community/add-building";
	}
	
	@RequestMapping("/add-building-action/{communityId}")
	@ResponseBody
	public DWZAjaxObject addBuildingAction(@PathVariable("communityId")String communityId,ModelMap modelMap, @ModelAttribute("communityForm")CommunityForm communityForm) {
		Community loadCommunity = this.communityService.getCommunity(communityId);
		if (loadCommunity == null) {
			throw new IllegalArgumentException();
		}
		modelMap.put("community", loadCommunity);
		
		String buildingNo = communityForm.getBuildingNo();
		String content = communityForm.getContent();
		String strRoomNos = communityForm.getRoomNos();
		String[] roomNos = StringUtils.split(strRoomNos, ",，　\r\n");
		Set<String> nos = new LinkedHashSet<String>();
		for (String no : roomNos) {
			if (no != null && !no.equals("")) {
				nos.add(no);
			}
		}
		if (nos.size() <=0) {
			return DWZAjaxObject.create(DWZStatusCode.Error, "房间号不合法，请检查后重新填写。");
		}
		try {
			this.communityService.addBuilding(loadCommunity, buildingNo, content, nos);
			DWZAjaxObject object = DWZAjaxObject.createSuccessObject();
			object.setForwardUrl("/community/view-buildings/" + communityId + ".jhtml");
			object.setRel(communityForm.getRel());
			return object;
		} catch (ObjectDuplicateException e) {
			return DWZAjaxObject.create(DWZStatusCode.Error, "楼号重复，请检查后重新填写。");
		}
	}
	
	@RequestMapping("/view-buildings/{communityId}") 
	public String viewBuildings(@PathVariable("communityId")String communityId,ModelMap modelMap) {
		Community loadCommunity = this.communityService.getCommunity(communityId);
		if (loadCommunity == null) {
			throw new IllegalArgumentException();
		}
		modelMap.put("community", loadCommunity);
		Map<Building, Collection<Room>> dataMap = this.communityService.getBuildingDataMap(communityId);
		modelMap.put("dataMap", dataMap);
		return "/community/view-buildings";
	}
	
}
