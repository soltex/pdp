/**
 * 
 */
package cn.com.innodev.pdp.business.sdk.adminservice.community.impl;

import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import cn.com.innodev.pdp.business.sdk.adminservice.community.CommunityManager;
import cn.com.innodev.pdp.business.sdk.commmon.PDPSDKCommonManager;
import cn.com.innodev.pdp.community.Community;
import cn.com.innodev.pdp.community.ProjectCompany;
import cn.com.innodev.pdp.community.services.CommunityService;
import cn.com.innodev.pdp.community.services.StaffService;
import cn.com.innodev.pdp.community.staff.Staff;
import cn.com.innodev.pdp.community.staff.StaffRole;
import cn.com.innodev.pdp.community.staff.StaffState;
import cn.com.innodev.pdp.framework.Constants;
import cn.com.innodev.pdp.framework.PlatformError;
import cn.com.innodev.pdp.framework.bizcommon.ImageBean;
import cn.com.innodev.pdp.framework.bizcommon.ImageFormatIncorrentException;
import cn.com.innodev.pdp.framework.bizcommon.region.City;
import cn.com.innodev.pdp.framework.mail.MailManager;
import cn.com.innodev.pdp.framework.services.AbstractPlatformServiceMgr;

import com.google.gson.Gson;
import com.vanstone.business.MyAssert4Business;
import com.vanstone.business.ObjectDuplicateException;
import com.vanstone.business.serialize.GsonCreator;
import com.vanstone.common.MyAssert;
import com.vanstone.common.util.TemplateUtil;
import com.vanstone.common.util.image.ImagePropertyVO;
import com.vanstone.common.util.image.ImageUtil;
import com.vanstone.configuration.client.ConfigurationFactory;
import com.vanstone.configuration.client.IConfigurationManager;
import com.vanstone.fs.FSFile;
import com.vanstone.fs.FileType;
import com.vanstone.weedfs.client.RequestResult;
import com.vanstone.weedfs.client.WeedFile;
import com.vanstone.weedfs.client.impl.WeedFSClient;

/**
 * @author shipeng
 */
@Service("communityManager")
public class CommunityManagerImpl extends AbstractPlatformServiceMgr implements CommunityManager {
	
	/** */
	private static final long serialVersionUID = -9169649017667712574L;

	private static Logger LOG = LoggerFactory.getLogger(CommunityManagerImpl.class);
	
	@Autowired
	private CommunityService communityService;
	@Autowired
	private StaffAccountGenerator staffAccountGenerator;
	@Autowired
	private MailManager mailManager;
	@Autowired
	private StaffService staffService;
	@Autowired
	private PDPSDKCommonManager pdpSDKCommonManager;
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.business.sdk.adminservice.community.CommunityManager#newCommunity(java.lang.String, java.lang.String, java.lang.Double, java.lang.Double, cn.com.innodev.pdp.framework.bizcommon.region.City, com.vanstone.fs.FSFile, java.lang.String, java.lang.String)
	 */
	@Override
	public Community newCommunity(final String communityId, final String communityName, final Double longitude, final Double latitude,
			final City city, final FSFile qrOfCommunityFsFile, final String companyName, final String notificationMail)
			throws ObjectDuplicateException, ImageFormatIncorrentException {
		MyAssert.hasText(communityId);
		MyAssert.hasText(communityName);
		MyAssert.notNull(longitude);
		MyAssert.notNull(latitude);
		MyAssert4Business.objectInitialized(city);
		MyAssert.notNull(qrOfCommunityFsFile);
		MyAssert.hasText(communityName);
		MyAssert.hasLength(notificationMail);
		
		//社区Id判断
		pdpSDKCommonManager.getAndValidateCommunity(communityId);
		
		//文件写入WeedFS，并判断文件是否合法
		ImagePropertyVO imagePropertyVO = null;
		try {
			imagePropertyVO = ImageUtil.getProperty(qrOfCommunityFsFile.getInputStream());
		} catch (Exception e) {
			LOG.error("Upload Image Error ,{}", qrOfCommunityFsFile.getPhysicalFilepath());
			throw new ImageFormatIncorrentException();
		} 
		if (LOG.isDebugEnabled()) {
			Gson gson = GsonCreator.createPretty();
			LOG.debug(gson.toJson(imagePropertyVO));
		}
		WeedFSClient weedFSClient = new WeedFSClient();
		RequestResult requestResult = weedFSClient.upload(qrOfCommunityFsFile.getFile());
		if (LOG.isDebugEnabled()) {
			Gson gson = GsonCreator.createPretty();
			LOG.debug(gson.toJson(requestResult));
		}
		String qrFileID = requestResult.getFid();
		if (StringUtils.isEmpty(qrFileID)) {
			throw new ImageFormatIncorrentException();
		}
		ImageBean imageBean = new ImageBean();
		imageBean.setHeight(imagePropertyVO.getHeight());
		imageBean.setWidth(imagePropertyVO.getWidth());
		imageBean.setWeedFile(new WeedFile(qrFileID, qrOfCommunityFsFile.getExtensionName()));
		
		//初始化社区以及工程公司信息
		final Community community = new Community();
		community.setId(communityId);
		community.setCommunityName(communityName);
		community.setLongitude(longitude);
		community.setLatitude(latitude);
		community.setCity(city);
		community.setQrImageBean(imageBean);
		
		final ProjectCompany pc = new ProjectCompany();
		pc.setCompanyEmail(notificationMail);
		pc.setCompanyName(companyName);
		
		Staff staff = this.execute(new TransactionCallback<Staff>() {
			@Override
			public Staff doInTransaction(TransactionStatus arg0) {
				//社区以及工程公司信息写入
				Community tempCommunity = null;
				try {
					tempCommunity = communityService.addCommunity(community, pc);
				} catch (ObjectDuplicateException e) {
					e.printStackTrace();
					throw new IllegalArgumentException();
				}
				//创建默认账号
				AccountObject accountObject = staffAccountGenerator.generate(communityId);
				Staff staff = new Staff(tempCommunity);
				staff.setAccountName(accountObject.getAccountName());
				staff.setAccountPassword(accountObject.getPassword());
				staff.setStaffRole(StaffRole.Project_Company_Admin);
				staff.setStaffState(StaffState.Active);
				try {
					staffService.addStaff(staff);
				} catch (ObjectDuplicateException e) {
					e.printStackTrace();
					throw new IllegalArgumentException();
				}
				return staff;
			}
		});
		//发送邮件给NotificationMail人员
		Map<String, Object> mailParams = new LinkedHashMap<String, Object>();
		mailParams.put("communityName", communityName);
		mailParams.put("provinceTitle", city.getProvince().getTitle());
		mailParams.put("cityTitle", city.getTitle());
		mailParams.put("longitude", longitude);
		mailParams.put("latitude", latitude);
		mailParams.put("companyName", companyName);
		mailParams.put("accountName", staff.getAccountName());
		mailParams.put("accountPassword", staff.getAccountPassword());
		IConfigurationManager configurationManager = ConfigurationFactory.getConfigurationManager();
		String title = configurationManager.getValue(Constants.INNODEV_PDP_SYSTEMPLATE_GROUP, Constants.Global_SysTemplate.Admin_New_Community_Notice_Mail_Subject_Template.getCode());
		String htmlContent = configurationManager.getValue(Constants.INNODEV_PDP_SYSTEMPLATE_GROUP,Constants.Global_SysTemplate.Admin_New_Community_Notice_Mail_Content_Template.getCode());
		htmlContent = TemplateUtil.template2String(htmlContent, mailParams);
		mailManager.send(notificationMail, title , htmlContent);
		return this.communityService.getCommunity(communityId);
	}

	@Override
	public void updateCommunityQRFsFile(String communityId, FSFile qrOfCommunityFsFile)
			throws ImageFormatIncorrentException {
		MyAssert.hasText(communityId);
		MyAssert.notNull(qrOfCommunityFsFile);
		
		//社区Id判断
		pdpSDKCommonManager.getAndValidateCommunity(communityId);
		
		//图片文件格式错误
		if (!qrOfCommunityFsFile.getFileType().equals(FileType.Image)) {
			throw new ImageFormatIncorrentException();
		}
		
		ImagePropertyVO imagePropertyVO = null;
		try {
			imagePropertyVO = ImageUtil.getProperty(qrOfCommunityFsFile.getInputStream());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new ImageFormatIncorrentException();
		}
		
		WeedFSClient weedFSClient = new WeedFSClient();
		RequestResult requestResult = weedFSClient.upload(qrOfCommunityFsFile.getFile());
		if (requestResult.getFid()== null || requestResult.getFid().equals("")) {
			throw new PlatformError("weedfs server error.");
		}
		
		ImageBean imageBean = new ImageBean(new WeedFile(requestResult.getFid(), qrOfCommunityFsFile.getExtensionName()) , imagePropertyVO);
		this.communityService.updateQRImage(communityId, imageBean);
	}

}
