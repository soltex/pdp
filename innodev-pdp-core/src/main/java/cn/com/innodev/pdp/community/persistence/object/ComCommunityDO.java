package cn.com.innodev.pdp.community.persistence.object;

import java.util.Date;

public class ComCommunityDO {
    private String id;

    private String communityName;

    private Double longitude;

    private Double latitude;

    private Date sysInsertTime;

    private Date sysUpdateTime;

    private Float avgPrice;

    private String address;

    private String qrFileId;

    private String qrFileExt;

    private Integer qrFileWidth;

    private Integer qrFileHeight;

    private Integer cityId;

    private Integer infoState;

    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQrFileId() {
        return qrFileId;
    }

    public void setQrFileId(String qrFileId) {
        this.qrFileId = qrFileId;
    }

    public String getQrFileExt() {
        return qrFileExt;
    }

    public void setQrFileExt(String qrFileExt) {
        this.qrFileExt = qrFileExt;
    }

    public Integer getQrFileWidth() {
        return qrFileWidth;
    }

    public void setQrFileWidth(Integer qrFileWidth) {
        this.qrFileWidth = qrFileWidth;
    }

    public Integer getQrFileHeight() {
        return qrFileHeight;
    }

    public void setQrFileHeight(Integer qrFileHeight) {
        this.qrFileHeight = qrFileHeight;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getInfoState() {
        return infoState;
    }

    public void setInfoState(Integer infoState) {
        this.infoState = infoState;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}