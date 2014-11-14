/**
 * 
 */
package cn.com.innodev.pdp.community;

import java.util.Date;

import cn.com.innodev.pdp.framework.bizcommon.ImageBean;

import com.vanstone.business.def.AbstractBusinessObject;

/**
 * 社区图片
 * @author shipeng
 */
public class CommunityImage extends AbstractBusinessObject {

	private static final long serialVersionUID = 3693764442787485976L;
	
	private String id;
	private String title;
	/** 相册类型 */
	private AlbumType albumType;
	/** 户型图类型 */
	private FloorPlanType floorPlanType;
	private ImageBean imageBean;
	private Date sysInsertTime;
	private Date sysUpdateTime;
	//private Community community;
	
	/**
	 * 默认创建户型图
	 * 
	 * @param floorPlanType
	 */
	public CommunityImage(FloorPlanType floorPlanType) {
		this.floorPlanType = floorPlanType;
		this.albumType = AlbumType.Floor_Plan_Type;
	}
	
	/**
	 * 创建相册图
	 * 
	 * @param albumType
	 */
	public CommunityImage(AlbumType albumType) {
		if (albumType == null || albumType.equals(AlbumType.Floor_Plan_Type)) {
			throw new IllegalArgumentException();
		}
		this.floorPlanType = null;
		this.albumType = albumType;
	}
	
	@Override
	public String getId() {
		return this.id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ImageBean getImageBean() {
		return imageBean;
	}

	public void setImageBean(ImageBean imageBean) {
		this.imageBean = imageBean;
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

	public AlbumType getAlbumType() {
		return albumType;
	}
	
	public FloorPlanType getFloorPlanType() {
		return floorPlanType;
	}

	public void setId(String id) {
		this.id = id;
	}

}
