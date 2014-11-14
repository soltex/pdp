/**
 * 
 */
package cn.com.innodev.pdp.community;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import cn.com.innodev.pdp.framework.bizcommon.ImageBean;
import cn.com.innodev.pdp.framework.bizcommon.region.City;

import com.vanstone.business.def.AbstractBusinessObject;

/**
 * @author shipeng
 */
public class Community extends AbstractBusinessObject {

	private static final long serialVersionUID = 3969413192349300946L;
	
	private String id;
	private String communityName;
	private Double longitude;
	private Double latitude;
	private String content;
	private Date sysInsertTime;
	private Date sysUpdateTime;
	private Float avgPrice;
	private String address;
	private ImageBean qrImageBean;
	private City city;
	private InfoState infoState = InfoState.Completed;
	
	/** 项目公司*/
	private ProjectCompany projectCompany;
	/** 图片集合*/
	private Map<AlbumType, Collection<CommunityImage>> communityImages = new LinkedHashMap<AlbumType, Collection<CommunityImage>>();
	
	/** 楼号影射Map*/
	private Map<Integer, Building> buildingDataMap = new LinkedHashMap<Integer, Building>();
	/** 楼号ID和房间号映射*/
	private Map<Integer, Collection<Room>> buildingIDRoomsMap = new LinkedHashMap<Integer, Collection<Room>>();
	
	public Community(){}
	
	public Community(ProjectCompany projectCompany, Map<AlbumType, Collection<CommunityImage>> communityImages, Map<Building, Collection<Room>> buildingRoomsMap) {
		this.projectCompany = projectCompany;
		this.communityImages = communityImages;
		if (buildingRoomsMap != null && buildingRoomsMap.size() >0) {
			Collection<Building> buildings = buildingRoomsMap.keySet();
			//初始化Building
			for (Building building : buildings) {
				buildingDataMap.put(building.getId(), building);
			}
			//初始化楼号影射
			for (Map.Entry<Building, Collection<Room>> entry : buildingRoomsMap.entrySet()) {
				buildingIDRoomsMap.put(entry.getKey().getId(), entry.getValue());
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vanstone.business.def.AbstractBusinessObject#getId()
	 */
	@Override
	public String getId() {
		return this.id;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSysInsertTime() {
		return sysInsertTime;
	}

	public void setSysInsertTime(Date sysInsertTime) {
		this.sysInsertTime = sysInsertTime;
	}

	public Date getSysUpdateTime() {
		return sysUpdateTime;
	}

	public void setSysUpdateTime(Date sysUpdateTime) {
		this.sysUpdateTime = sysUpdateTime;
	}

	public Float getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(Float avgPrice) {
		this.avgPrice = avgPrice;
	}

	public ImageBean getQrImageBean() {
		return qrImageBean;
	}

	public void setQrImageBean(ImageBean qrImageBean) {
		this.qrImageBean = qrImageBean;
	}

	public void setId(String id) {
		this.id = id;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	public InfoState getInfoState() {
		return infoState;
	}

	public void setInfoState(InfoState infoState) {
		this.infoState = infoState;
	}

	public ProjectCompany getProjectCompany() {
		return projectCompany;
	}

	public Map<AlbumType, Collection<CommunityImage>> getCommunityImages() {
		return communityImages;
	}

	public void clearCommunityImages() {
		this.communityImages.clear();
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 获取Building & Rooms Map，格式为 ： 
	 * {building bean info} : [room,room,room] <br >
	 * {building bean info} : [room,room,room] <br >
	 * {building bean info} : [room,room,room] <br >
	 * @return
	 */
	public Map<Building, Collection<Room>> getBuildingRoomsMap() {
		if (buildingDataMap == null || buildingDataMap.size() <=0) {
			return null;
		}
		Map<Building,Collection<Room>> dataMap = new LinkedHashMap<Building, Collection<Room>>();
		for (Entry<Integer, Building> buildingEntry : this.buildingDataMap.entrySet()) {
			dataMap.put(buildingEntry.getValue(), this.buildingIDRoomsMap.get(buildingEntry.getKey()));
		}
		return dataMap;
	}
	
}
