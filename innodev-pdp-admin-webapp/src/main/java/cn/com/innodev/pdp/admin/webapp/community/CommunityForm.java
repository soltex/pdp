/**
 * 
 */
package cn.com.innodev.pdp.admin.webapp.community;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.com.innodev.pdp.framework.web.AbstractActionForm;

/**
 * @author shipeng
 */
public class CommunityForm extends AbstractActionForm {

	private String communityId;
	private String communityName;
	private Double longitude;
	private Double latitude;
	private Integer cityId;
	private Integer provinceId;
	private Float avgPrice;
	private String address;
	private String content;
	private MultipartFile qrOfCommunityMultipartFile;
	private String companyName;
	private String notificationMail;

	private MultipartFile[] uploadFiles;

	private String key;

	
	private List<Integer> albumTypes = new ArrayList<Integer>();
	private List<Integer> floorPlanTypes = new ArrayList<Integer>();
	private List<String> fids = new ArrayList<String>();
	private List<String> extNames = new ArrayList<String>();
	private List<String> titles = new ArrayList<String>();

	private String buildingNo;
	private String roomNos;
	
	public String getBuildingNo() {
		return buildingNo;
	}

	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}

	public String getRoomNos() {
		return roomNos;
	}

	public void setRoomNos(String roomNos) {
		this.roomNos = roomNos;
	}

	
	
	
	
	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public MultipartFile getQrOfCommunityMultipartFile() {
		return qrOfCommunityMultipartFile;
	}

	public void setQrOfCommunityMultipartFile(MultipartFile qrOfCommunityMultipartFile) {
		this.qrOfCommunityMultipartFile = qrOfCommunityMultipartFile;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getNotificationMail() {
		return notificationMail;
	}

	public void setNotificationMail(String notificationMail) {
		this.notificationMail = notificationMail;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Float getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(Float avgPrice) {
		this.avgPrice = avgPrice;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MultipartFile[] getUploadFiles() {
		return uploadFiles;
	}

	public void setUploadFiles(MultipartFile[] uploadFiles) {
		this.uploadFiles = uploadFiles;
	}

	public List<Integer> getAlbumTypes() {
		return albumTypes;
	}

	public void setAlbumTypes(List<Integer> albumTypes) {
		this.albumTypes = albumTypes;
	}

	public List<Integer> getFloorPlanTypes() {
		return floorPlanTypes;
	}

	public void setFloorPlanTypes(List<Integer> floorPlanTypes) {
		this.floorPlanTypes = floorPlanTypes;
	}

	public List<String> getFids() {
		return fids;
	}

	public void setFids(List<String> fids) {
		this.fids = fids;
	}

	public List<String> getTitles() {
		return titles;
	}

	public void setTitles(List<String> titles) {
		this.titles = titles;
	}

	public List<String> getExtNames() {
		return extNames;
	}

	public void setExtNames(List<String> extNames) {
		this.extNames = extNames;
	}
	
}
