/**
 * 
 */
package cn.com.innodev.example.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import cn.com.innodev.example.common.ImageBean;

import com.vanstone.business.def.AbstractBusinessObject;

/**
 * 社区
 * @author shipeng
 */
public class Community extends AbstractBusinessObject {

	/** */
	private static final long serialVersionUID = 2256401864740136576L;

	/** 唯一ID值，不可修改，手动指定*/
	private String id;
	private String communityName;
	private Double longitude;
	private Double latitude;
	private String content;
	private Date sysInsertTime;
	private Date sysUpdateTime;
	private Collection<CommunityTag> tags = new ArrayList<CommunityTag>();
	private Float avgPrice;
	private String address;
	private ImageBean qrImageBean;
	
	/* (non-Javadoc)
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

	public Collection<CommunityTag> getTags() {
		return tags;
	}

	public void setTags(Collection<CommunityTag> tags) {
		this.tags = tags;
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

	public ImageBean getQrImageBean() {
		return qrImageBean;
	}

	public void setQrImageBean(ImageBean qrImageBean) {
		this.qrImageBean = qrImageBean;
	}

	public void setId(String id) {
		this.id = id;
	}

}
