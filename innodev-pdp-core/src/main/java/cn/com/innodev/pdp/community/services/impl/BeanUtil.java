/**
 * 
 */
package cn.com.innodev.pdp.community.services.impl;

import java.util.Collection;
import java.util.Map;

import cn.com.innodev.pdp.community.AlbumType;
import cn.com.innodev.pdp.community.Announcement;
import cn.com.innodev.pdp.community.Building;
import cn.com.innodev.pdp.community.Community;
import cn.com.innodev.pdp.community.CommunityImage;
import cn.com.innodev.pdp.community.FloorPlanType;
import cn.com.innodev.pdp.community.InfoState;
import cn.com.innodev.pdp.community.ProjectCompany;
import cn.com.innodev.pdp.community.QA;
import cn.com.innodev.pdp.community.Room;
import cn.com.innodev.pdp.community.persistence.object.ComAnnouncementDO;
import cn.com.innodev.pdp.community.persistence.object.ComBuildingDO;
import cn.com.innodev.pdp.community.persistence.object.ComCommunityDO;
import cn.com.innodev.pdp.community.persistence.object.ComCommunityImageDO;
import cn.com.innodev.pdp.community.persistence.object.ComProjectCompanyDO;
import cn.com.innodev.pdp.community.persistence.object.ComProjectStaffDO;
import cn.com.innodev.pdp.community.persistence.object.ComQADO;
import cn.com.innodev.pdp.community.persistence.object.ComRoomDO;
import cn.com.innodev.pdp.community.staff.Staff;
import cn.com.innodev.pdp.community.staff.StaffRole;
import cn.com.innodev.pdp.community.staff.StaffState;
import cn.com.innodev.pdp.framework.bizcommon.ImageBean;
import cn.com.innodev.pdp.framework.bizcommon.region.City;

import com.vanstone.business.lang.EnumUtils;
import com.vanstone.weedfs.client.WeedFile;

/**
 * @author shipeng
 */
public class BeanUtil {
	
	public static ComCommunityDO toComCommunityDO(Community community) {
		ComCommunityDO comCommunityDO = new ComCommunityDO();
		comCommunityDO.setId(community.getId());
		comCommunityDO.setCommunityName(community.getCommunityName());
		comCommunityDO.setLongitude(community.getLongitude());
		comCommunityDO.setLatitude(community.getLatitude());
		comCommunityDO.setContent(community.getContent());
		comCommunityDO.setSysInsertTime(community.getSysInsertTime());
		comCommunityDO.setSysUpdateTime(community.getSysUpdateTime());
		comCommunityDO.setAvgPrice(community.getAvgPrice());
		if (community.getQrImageBean() != null) {
			comCommunityDO.setQrFileId(community.getQrImageBean().getWeedFile().getFileid());
			comCommunityDO.setQrFileExt(community.getQrImageBean().getWeedFile().getExtName());
			comCommunityDO.setQrFileWidth(community.getQrImageBean().getWidth());
			comCommunityDO.setQrFileHeight(community.getQrImageBean().getHeight());
		}
		comCommunityDO.setAddress(community.getAddress());
		comCommunityDO.setCityId(community.getCity().getId());
		comCommunityDO.setInfoState(community.getInfoState().getCode());
		return comCommunityDO;
	}
	
	public static Community toCommunity(ComCommunityDO comCommunityDO, City city) {
		Community community = new Community();
		initialCommunityInfo(community, comCommunityDO, city);
		return community;
	}
	
	/**
	 * 初始化Community
	 * @param community
	 * @param comCommunityDO
	 * @param city
	 */
	private static void initialCommunityInfo(Community community, ComCommunityDO comCommunityDO, City city) {
		community.setId(comCommunityDO.getId());
		community.setCommunityName(comCommunityDO.getCommunityName());
		community.setLongitude(comCommunityDO.getLongitude());
		community.setLatitude(comCommunityDO.getLatitude());
		community.setContent(comCommunityDO.getContent());
		community.setSysInsertTime(comCommunityDO.getSysInsertTime());
		community.setSysUpdateTime(comCommunityDO.getSysUpdateTime());
		community.setAvgPrice(comCommunityDO.getAvgPrice());
		if (comCommunityDO.getQrFileId() != null && !"".equals(comCommunityDO.getQrFileId())) {
			ImageBean qrImageBean = new ImageBean();
			WeedFile weedFile = new WeedFile(comCommunityDO.getQrFileId(), comCommunityDO.getQrFileExt());
			qrImageBean.setWeedFile(weedFile);
			qrImageBean.setWidth(comCommunityDO.getQrFileWidth());
			qrImageBean.setHeight(comCommunityDO.getQrFileHeight());
			community.setQrImageBean(qrImageBean);
		}
		community.setAddress(comCommunityDO.getAddress());
		community.setCity(city);
		InfoState infoState = EnumUtils.getByCode(comCommunityDO.getInfoState(), InfoState.class);
		community.setInfoState(infoState);
	}
	
	/**
	 * 转换为Community
	 * @param comCommunityDO
	 * @param city
	 * @param projectCompany
	 * @param communityImages
	 * @return
	 */
	public static Community toCommunity(ComCommunityDO comCommunityDO, City city, ProjectCompany projectCompany, Map<AlbumType, Collection<CommunityImage>> communityImages, Map<Building, Collection<Room>> buildingRoomsMap) {
		Community community = new Community(projectCompany, communityImages, buildingRoomsMap);
		initialCommunityInfo(community, comCommunityDO, city);
		return community;
	}
	
	public static ComProjectCompanyDO toComProjectCompanyDO(ProjectCompany projectCompany) {
		ComProjectCompanyDO comProjectCompanyDO = new ComProjectCompanyDO();
		comProjectCompanyDO.setCompanyName(projectCompany.getCompanyName());
		comProjectCompanyDO.setCommunityId(projectCompany.getId());
		if (projectCompany.getLogoImageBean() != null) {
			comProjectCompanyDO.setLogoFileId(projectCompany.getLogoImageBean().getWeedFile().getFileid());
			comProjectCompanyDO.setLogoFileExt(projectCompany.getLogoImageBean().getWeedFile().getExtName());
			comProjectCompanyDO.setLogoFileWidth(projectCompany.getLogoImageBean().getWidth());
			comProjectCompanyDO.setLogoFileHeight(projectCompany.getLogoImageBean().getHeight());
		}
		comProjectCompanyDO.setCompanyEmail(projectCompany.getCompanyEmail());
		return comProjectCompanyDO;
	}
	
	public static ProjectCompany toProjectCompany(ComProjectCompanyDO comProjectCompanyDO) {
		ProjectCompany projectCompany = new ProjectCompany();
		projectCompany.setCompanyName(comProjectCompanyDO.getCompanyName());
		projectCompany.setId(comProjectCompanyDO.getCommunityId());
		if (comProjectCompanyDO.getLogoFileId() != null && !comProjectCompanyDO.getLogoFileId().equals("")) {
			ImageBean imageBean = new ImageBean();
			WeedFile weedFile = new WeedFile(comProjectCompanyDO.getLogoFileId(), comProjectCompanyDO.getLogoFileExt());
			imageBean.setWidth(comProjectCompanyDO.getLogoFileWidth());
			imageBean.setHeight(comProjectCompanyDO.getLogoFileHeight());
			imageBean.setWeedFile(weedFile);
			projectCompany.setLogoImageBean(imageBean);
		}
		projectCompany.setCompanyEmail(comProjectCompanyDO.getCompanyEmail());
		return projectCompany;
	}
	
	public static Announcement toAnnouncement(ComAnnouncementDO comAnnouncementDO, Community community) {
		Announcement announcement = new Announcement(community);
		announcement.setId(comAnnouncementDO.getId());
		announcement.setTitle(comAnnouncementDO.getTitle());
		announcement.setContent(comAnnouncementDO.getContent());
		announcement.setSysInsertTime(comAnnouncementDO.getSysInsertTime());
		return announcement;
	}
	
	public static ComAnnouncementDO toComAnnouncementDO(Announcement announcement) {
		ComAnnouncementDO comAnnouncementDO = new ComAnnouncementDO();
		comAnnouncementDO.setId(announcement.getId());
		comAnnouncementDO.setTitle(announcement.getTitle());
		comAnnouncementDO.setContent(announcement.getContent());
		comAnnouncementDO.setSysInsertTime(announcement.getSysInsertTime());
		comAnnouncementDO.setCommunityId(announcement.getCommunity().getId());
		return comAnnouncementDO;
	}
	
	public static CommunityImage toCommunityImage(ComCommunityImageDO comCommunityImageDO) {
		AlbumType albumType = EnumUtils.getByCode(comCommunityImageDO.getAlbumType(), AlbumType.class);
		CommunityImage communityImage = null;
		if(albumType.equals(AlbumType.Floor_Plan_Type)) {
			FloorPlanType floorPlanType = EnumUtils.getByCode(comCommunityImageDO.getFloorPlanType(), FloorPlanType.class);
			communityImage = new CommunityImage(floorPlanType);
		}else{
			communityImage = new CommunityImage(albumType);
		}
		communityImage.setId(comCommunityImageDO.getId());
		communityImage.setTitle(comCommunityImageDO.getTitle());
		communityImage.setSysInsertTime(comCommunityImageDO.getSysInsertTime());
		communityImage.setSysUpdateTime(comCommunityImageDO.getSysUpdateTime());
		if (comCommunityImageDO.getImageFileId() != null && !"".equals(comCommunityImageDO.getImageFileId())) {
			ImageBean imageBean = new ImageBean();
			imageBean.setWeedFile(new WeedFile(comCommunityImageDO.getImageFileId(), comCommunityImageDO.getImageFileExt()));
			imageBean.setHeight(comCommunityImageDO.getImageFileHeight());
			imageBean.setWidth(comCommunityImageDO.getImageFileWidth());
			communityImage.setImageBean(imageBean);
		}
		return communityImage;
	}
	
	public static ComCommunityImageDO toComCommunityImageDO(CommunityImage communityImage, String communityID) {
		ComCommunityImageDO model = new ComCommunityImageDO();
		model.setId(communityImage.getId());
		model.setTitle(communityImage.getTitle());
		model.setAlbumType(communityImage.getAlbumType().getCode());
		if (communityImage.getFloorPlanType() != null) {
			model.setFloorPlanType(communityImage.getFloorPlanType().getCode());
		}
		model.setSysInsertTime(communityImage.getSysInsertTime());
		model.setSysUpdateTime(communityImage.getSysUpdateTime());
		model.setCommunityId(communityID);
		if (communityImage.getImageBean() != null) {
			model.setImageFileId(communityImage.getImageBean().getWeedFile().getFileid());
			model.setImageFileExt(communityImage.getImageBean().getWeedFile().getExtName());
			model.setImageFileWidth(communityImage.getImageBean().getWidth());
			model.setImageFileHeight(communityImage.getImageBean().getHeight());
		}
		return model;
	}
	
	public static QA toQA(ComQADO comQADO, Community community) {
		QA qa = new QA(community);
		qa.setId(comQADO.getId());
		qa.setTitle(comQADO.getTitle());
		qa.setContent(comQADO.getContent());
		qa.setSysInsertTime(comQADO.getSysInsertTime());
		qa.setSysUpdateTime(comQADO.getSysUpdateTime());
		return qa;
	}
	
	public static ComQADO toComQADO(QA qa) {
		ComQADO comQADO = new ComQADO();
		comQADO.setId(qa.getId());
		comQADO.setTitle(qa.getTitle());
		comQADO.setContent(qa.getContent());
		comQADO.setSysInsertTime(qa.getSysInsertTime());
		comQADO.setSysUpdateTime(qa.getSysUpdateTime());
		comQADO.setCommunityId(qa.getCommunity().getId());
		return comQADO;
	}
	
	public static ComBuildingDO toComBuildingDO(Building building, Community community) {
		ComBuildingDO comBuildingDO = new ComBuildingDO();
		comBuildingDO.setBuildingNo(building.getBuildingNo());
		comBuildingDO.setCommunityId(community.getId());
		comBuildingDO.setContent(building.getContent());
		comBuildingDO.setId(building.getId());
		return comBuildingDO;
	}
	
	public static Building toBuilding(ComBuildingDO comBuildingDO) {
		Building building = new Building();
		building.setBuildingNo(comBuildingDO.getBuildingNo());
		building.setContent(comBuildingDO.getContent());
		building.setId(comBuildingDO.getId());
		return building;
	}
	
	public static ComRoomDO toComRoomDO(Room room) {
		ComRoomDO comRoomDO = new ComRoomDO();
		comRoomDO.setBuildingId(room.getBuilding().getId());
		comRoomDO.setId(room.getId());
		comRoomDO.setRoomNo(room.getRoomNo());
		return comRoomDO;
	}
	
	public static Room toRoom(ComRoomDO comRoomDO, Building building) {
		Room room = new Room(building);
		room.setId(comRoomDO.getId());
		room.setRoomNo(comRoomDO.getRoomNo());
		return room;
	}
	
	public static Staff toStaff(ComProjectStaffDO comProjectStaffDO, Community community) {
		Staff staff = new Staff(community);
		staff.setAccountName(comProjectStaffDO.getAccountName());
		staff.setAccountPassword(comProjectStaffDO.getAccountPassword());
		staff.setFullName(comProjectStaffDO.getFullName());
		staff.setEmail(comProjectStaffDO.getEmail());
		staff.setMobile1(comProjectStaffDO.getMobile1());
		staff.setMobile2(comProjectStaffDO.getMobile2());
		staff.setMobile3(comProjectStaffDO.getMobile3());
		staff.setMobile4(comProjectStaffDO.getMobile4());
		staff.setSysInsertTime(comProjectStaffDO.getSysInsertTime());
		staff.setSysUpdateTime(comProjectStaffDO.getSysUpdateTime());
		staff.setLastLoginTime(comProjectStaffDO.getLastLoginTime());
		if (comProjectStaffDO.getHeadImageFileId() != null && !"".equals(comProjectStaffDO.getHeadImageFileId())) {
			ImageBean imageBean = new ImageBean();
			WeedFile weedFile = new WeedFile(comProjectStaffDO.getHeadImageFileId(), comProjectStaffDO.getHeadImageFileExt());
			imageBean.setWidth(comProjectStaffDO.getHeadImageFileWidth());
			imageBean.setHeight(comProjectStaffDO.getHeadImageFileHeight());
			imageBean.setWeedFile(weedFile);
			staff.setHeadImageBean(imageBean);
		}
		staff.setDepartment(comProjectStaffDO.getDepartment());
		StaffRole staffRole = EnumUtils.getByCode(comProjectStaffDO.getStaffRole(), StaffRole.class);
		staff.setStaffRole(staffRole);
		StaffState staffState = EnumUtils.getByCode(comProjectStaffDO.getStaffState(), StaffState.class);
		staff.setStaffState(staffState);
		staff.setContent(comProjectStaffDO.getContent());
		return staff;
 	}
	
	public static ComProjectStaffDO toComProjectStaffDO(Staff staff) {
		ComProjectStaffDO comProjectStaffDO = new ComProjectStaffDO();
		comProjectStaffDO.setId(staff.getId());
		comProjectStaffDO.setAccountName(staff.getAccountName());
		comProjectStaffDO.setAccountPassword(staff.getAccountPassword());
		comProjectStaffDO.setFullName(staff.getFullName());
		comProjectStaffDO.setEmail(staff.getEmail());
		comProjectStaffDO.setMobile1(staff.getMobile1());
		comProjectStaffDO.setMobile2(staff.getMobile2());
		comProjectStaffDO.setMobile3(staff.getMobile3());
		comProjectStaffDO.setMobile4(staff.getMobile4());
		comProjectStaffDO.setSysInsertTime(staff.getSysInsertTime());
		comProjectStaffDO.setSysUpdateTime(staff.getSysUpdateTime());
		comProjectStaffDO.setLastLoginTime(staff.getLastLoginTime());
		if (staff.getHeadImageBean() != null) {
			comProjectStaffDO.setHeadImageFileId(staff.getHeadImageBean().getWeedFile().getFileid());
			comProjectStaffDO.setHeadImageFileExt(staff.getHeadImageBean().getWeedFile().getExtName());
			comProjectStaffDO.setHeadImageFileWidth(staff.getHeadImageBean().getWidth());
			comProjectStaffDO.setHeadImageFileHeight(staff.getHeadImageBean().getHeight());
		}
		comProjectStaffDO.setDepartment(staff.getDepartment());
		comProjectStaffDO.setStaffRole(staff.getStaffRole().getCode());
		comProjectStaffDO.setStaffState(staff.getStaffState().getCode());
		comProjectStaffDO.setContent(staff.getContent());
		comProjectStaffDO.setCommunityId(staff.getCommunity().getId());
		return comProjectStaffDO;
	}
}
