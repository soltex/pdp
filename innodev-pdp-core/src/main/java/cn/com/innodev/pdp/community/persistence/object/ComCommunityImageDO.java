package cn.com.innodev.pdp.community.persistence.object;

import java.util.Date;

public class ComCommunityImageDO {
    private String id;

    private String title;

    private Integer albumType;

    private Integer floorPlanType;

    private String imageFileId;

    private String imageFileExt;

    private Integer imageFileWidth;

    private Integer imageFileHeight;

    private Date sysInsertTime;

    private Date sysUpdateTime;

    private String communityId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAlbumType() {
        return albumType;
    }

    public void setAlbumType(Integer albumType) {
        this.albumType = albumType;
    }

    public Integer getFloorPlanType() {
        return floorPlanType;
    }

    public void setFloorPlanType(Integer floorPlanType) {
        this.floorPlanType = floorPlanType;
    }

    public String getImageFileId() {
        return imageFileId;
    }

    public void setImageFileId(String imageFileId) {
        this.imageFileId = imageFileId;
    }

    public String getImageFileExt() {
        return imageFileExt;
    }

    public void setImageFileExt(String imageFileExt) {
        this.imageFileExt = imageFileExt;
    }

    public Integer getImageFileWidth() {
        return imageFileWidth;
    }

    public void setImageFileWidth(Integer imageFileWidth) {
        this.imageFileWidth = imageFileWidth;
    }

    public Integer getImageFileHeight() {
        return imageFileHeight;
    }

    public void setImageFileHeight(Integer imageFileHeight) {
        this.imageFileHeight = imageFileHeight;
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

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }
}