/**
 * 
 */
package cn.com.innodev.pdp.weixin.webapp.myaccount;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.innodev.pdp.business.sdk.commmon.PDPSDKCommonManager;
import cn.com.innodev.pdp.business.sdk.weixinservice.proprietor.WeixinProprietorManager;
import cn.com.innodev.pdp.community.Building;
import cn.com.innodev.pdp.community.Community;
import cn.com.innodev.pdp.community.Room;
import cn.com.innodev.pdp.community.services.CommunityService;
import cn.com.innodev.pdp.framework.bizcommon.Gender;
import cn.com.innodev.pdp.framework.bizcommon.ImageBean;
import cn.com.innodev.pdp.framework.web.DWZAjaxObject;
import cn.com.innodev.pdp.framework.web.DWZAjaxObject.DWZStatusCode;
import cn.com.innodev.pdp.proprietor.Proprietor;
import cn.com.innodev.pdp.proprietor.services.ProprietorService;
import cn.com.innodev.pdp.proprietor.services.ValidatecodeException;
import cn.com.innodev.pdp.weixin.webapp.util.WeixinUtil;

import com.vanstone.business.ObjectDuplicateException;
import com.vanstone.business.lang.EnumUtils;
import com.vanstone.centralserver.common.MyAssert;
import com.vanstone.common.util.image.ImagePropertyVO;
import com.vanstone.common.util.image.ImageUtil;
import com.vanstone.fs.FSException;
import com.vanstone.fs.FSFile;
import com.vanstone.fs.FSType;
import com.vanstone.webframework.utils.FSManagerExt;
import com.vanstone.weedfs.client.RequestResult;
import com.vanstone.weedfs.client.WeedFile;
import com.vanstone.weedfs.client.impl.WeedFSClient;

/**
 * @author shipeng
 */
@Controller("myAccountAction")
@RequestMapping("/{communityId}/myaccount")
public class MyAccountAction {
	
	private static Logger LOG = LoggerFactory.getLogger(MyAccountAction.class);
	
	@Autowired
	private WeixinProprietorManager weixinProprietorManager;
	@Autowired
	private ProprietorService proprietorService;
	@Autowired
	private CommunityService communityService;
	@Autowired
	private PDPSDKCommonManager pdpSDKCommonManager;
	
	private String buildMyAccountIndexURL(String communityID, String openID) {
		StringBuffer sb = new StringBuffer();
		sb.append("/").append(communityID);
		sb.append("/").append("myaccount").append("/index.jhtml?openId=").append(openID);
		return sb.toString();
	}
	
	@RequestMapping("/indexframe")
	public String myAccountIndexFrame(@PathVariable("communityId")String communityId, ModelMap modelMap, HttpServletRequest servletRequest) {
		String openId = WeixinUtil.retrievalOpenId(servletRequest, communityId);
		if (openId == null) {
			throw new IllegalArgumentException("OpenID is NULL.");
		}
		modelMap.put("communityId", communityId);
		modelMap.put("openId", openId);
		LOG.debug("myaccount->indexframe communityID [{}],openID [{}]", communityId, openId);
		return "/myaccount/indexframe";
	}
	
	@RequestMapping("/index")
	public String myAccountIndex(@PathVariable("communityId")String communityId, @RequestParam("openId") String openId, ModelMap modelMap, HttpServletRequest servletRequest) {
		modelMap.put("communityId", communityId);
		modelMap.put("openId", openId);
		return "/myaccount/index";
	}
	
	@RequestMapping("/mobile-authenticate")
	public String mobileAuthenticate(@PathVariable("communityId")String communityId, @RequestParam("openId") String openId, HttpServletRequest servletRequest, ModelMap modelMap,@ModelAttribute("myAccountForm")MyAccountForm myAccountForm) {
		modelMap.put("openId", openId);
		modelMap.put("communityId", communityId);
		Proprietor proprietor = this.proprietorService.getProprietorByWeixinOpenId(openId);
		modelMap.put("proprietor", proprietor);
		
		myAccountForm.setOpenId(proprietor.getWeixinOpenid());
		myAccountForm.setMobile(proprietor.getMobile());
		
		return "/myaccount/mobile-authenticate";
	}
	
	@RequestMapping("/mobile-authenticate-action")
	@ResponseBody
	public DWZAjaxObject mobileAuthenticationAction(@PathVariable("communityId")String communityId, @ModelAttribute("myAccountForm")MyAccountForm myAccountForm, HttpServletResponse servletResponse, HttpServletRequest requestServletRequest) {
		pdpSDKCommonManager.getAndValidateCommunity(communityId);
		try {
			this.weixinProprietorManager.mobileAuthenticateProprietor(communityId, myAccountForm.getOpenId(), myAccountForm.getMobile(), myAccountForm.getValidatecode(), servletResponse);
		} catch (ValidatecodeException e) {
			DWZAjaxObject object = DWZAjaxObject.create(DWZStatusCode.Error, "验证码错误。");
			return object;
		} catch (ObjectDuplicateException e) {
			DWZAjaxObject object = DWZAjaxObject.create(DWZStatusCode.Error, "手机号码重复，请选择其他手机号绑定注册。");
			return object;
		}
		DWZAjaxObject object = DWZAjaxObject.createSuccessObject("手机号绑定成功");
		object.setForwardUrl(this.buildMyAccountIndexURL(communityId, myAccountForm.getOpenId()));
		return object;
	}
	
	@RequestMapping("/room-authenticate")
	public String roomAuthentication(@PathVariable("communityId")String communityId, @RequestParam("openId") String openId, ModelMap modelMap, @ModelAttribute("myAccountForm")MyAccountForm myAccountForm, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		Community loadCommunity = pdpSDKCommonManager.getAndValidateCommunity(communityId);
		Map<Building, Collection<Room>> buildingRoomsMap = loadCommunity.getBuildingRoomsMap();
		modelMap.put("buildingRoomsMap", buildingRoomsMap);
		modelMap.put("community", loadCommunity);
		modelMap.put("openId", openId);
		return "/myaccount/room-authenticate";
	}
	
	@RequestMapping("/room-authenticate-action")
	@ResponseBody
	public DWZAjaxObject roomAuthenticationAction(@PathVariable("communityId")String communityId, @ModelAttribute("myAccountForm")MyAccountForm myAccountForm) {
		MyAssert.notEmpty(myAccountForm.getRoomIds());
		this.weixinProprietorManager.bindRooms(communityId, myAccountForm.getOpenId(), myAccountForm.getRoomIds());
		DWZAjaxObject object = DWZAjaxObject.createSuccessObject("房间号绑定成功");
		object.setForwardUrl(this.buildMyAccountIndexURL(communityId, myAccountForm.getOpenId()));
		return object;
	}
	
	@RequestMapping("/update-base-proprietor-info")
	public String updateBaseProprietorInfo(ModelMap modelMap,@PathVariable("communityId")String communityId, @RequestParam("openId") String openId, @ModelAttribute("myAccountForm")MyAccountForm myAccountForm, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		Proprietor loadProprietor = this.proprietorService.getProprietorByWeixinOpenId(openId);
		if (loadProprietor == null) {
			throw new IllegalArgumentException();
		}
		
		myAccountForm.setOpenId(openId);
		myAccountForm.setEmail(loadProprietor.getEmail());
		myAccountForm.setFullName(loadProprietor.getFullName());
		myAccountForm.setNickName(loadProprietor.getNickName());
		myAccountForm.setGender(loadProprietor.getGender() != null ? loadProprietor.getGender().getCode() : null);
		myAccountForm.setProfession(loadProprietor.getProfession());
		myAccountForm.setInterest(loadProprietor.getInterest());
		return "/myaccount/update-base-proprietor-info";
	}
	
	@RequestMapping("/update-base-proprietor-info-action")
	@ResponseBody
	public DWZAjaxObject updateBaseProprietorInfoAction(@PathVariable("communityId")String communityId, @ModelAttribute("myAccountForm")MyAccountForm myAccountForm) {
		Proprietor proprietor = this.pdpSDKCommonManager.getAndValidateProprietorByOpenId(myAccountForm.getOpenId());
		
		proprietor.setEmail(myAccountForm.getEmail());
		proprietor.setFullName(myAccountForm.getFullName());
		proprietor.setNickName(myAccountForm.getNickName());
		Gender gender = myAccountForm.getGender() != null ? (Gender)EnumUtils.getByCode(myAccountForm.getGender(), Gender.class) : null;
		proprietor.setGender(gender);
		proprietor.setProfession(myAccountForm.getProfession());
		proprietor.setInterest(myAccountForm.getInterest());
		this.proprietorService.updateBaseProprietor(proprietor);
		
		DWZAjaxObject object = DWZAjaxObject.createSuccessObject("编辑个人信息成功");
		object.setForwardUrl(this.buildMyAccountIndexURL(communityId, myAccountForm.getOpenId()));
		return object;
	}
	
	@RequestMapping("/update-proprietor-header")
	public String updateProprietorHeader(ModelMap modelMap,@PathVariable("communityId")String communityId, @RequestParam("openId") String openId, @ModelAttribute("myAccountForm")MyAccountForm myAccountForm, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		Proprietor loadProprietor = this.pdpSDKCommonManager.getAndValidateProprietorByOpenId(openId);
		modelMap.put("proprietor", loadProprietor);
		myAccountForm.setOpenId(openId);
		return "/myaccount/update-proprietor-header";
	}
	
	@RequestMapping("/update-proprietor-header-action")
	public DWZAjaxObject updateProprietorHeaderAction(@PathVariable("communityId")String communityId, @ModelAttribute("myAccountForm")MyAccountForm myAccountForm) {
		Proprietor proprietor = this.pdpSDKCommonManager.getAndValidateProprietorByOpenId(myAccountForm.getOpenId());
		
		FSFile headerFsFile = null;
		try {
			headerFsFile = FSManagerExt.getInstance().uploadBySpring(myAccountForm.getHeaderMultipartFile(), FSType.Temporary);
		} catch (FSException e) {
			DWZAjaxObject object = DWZAjaxObject.create(DWZStatusCode.Error, "头像图片文件格式错误,请重新选择头像文件");
			return object;
		}
		ImageBean imageBean = null;
		ImagePropertyVO imagePropertyVO = null;
		try {
			imagePropertyVO = ImageUtil.getProperty(headerFsFile.getInputStream());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			DWZAjaxObject object = DWZAjaxObject.create(DWZStatusCode.Error, "文件服务器错误，请联系管理员");
			return object;
		}
		
		WeedFSClient weedFSClient = new WeedFSClient();
		RequestResult requestResult = weedFSClient.upload(headerFsFile.getFile());
		if (!requestResult.isSuccess()) {
			DWZAjaxObject object = DWZAjaxObject.create(DWZStatusCode.Error, "文件服务器错误，请联系管理员");
			return object;
		}
		
		imageBean = new ImageBean(new WeedFile(requestResult.getFid(), headerFsFile.getExtensionName()), imagePropertyVO);
		this.proprietorService.updateHeadImageBean(proprietor.getId(), imageBean);
		DWZAjaxObject object = DWZAjaxObject.createSuccessObject("更新头像信息成功");
		object.setForwardUrl(this.buildMyAccountIndexURL(communityId, myAccountForm.getOpenId()));
		return object;
	}
}
