package cn.com.innodev.pdp.proprietor.persistence.object;

import java.util.Date;

public class PropProprietorDO {
    private String id;

    private String mobile;

    private String email;

    private String fullName;

    private String nickName;

    private String pwd;

    private String weixinOpenid;

    private Integer gender;

    private String profession;

    private String headerFileId;

    private String headerFileExt;

    private Integer headerFileWidth;

    private Integer headerFileHeight;

    private Integer propState;

    private Date sysInsertTime;

    private Date sysUpdateTime;

    private String communityId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getWeixinOpenid() {
        return weixinOpenid;
    }

    public void setWeixinOpenid(String weixinOpenid) {
        this.weixinOpenid = weixinOpenid;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getHeaderFileId() {
        return headerFileId;
    }

    public void setHeaderFileId(String headerFileId) {
        this.headerFileId = headerFileId;
    }

    public String getHeaderFileExt() {
        return headerFileExt;
    }

    public void setHeaderFileExt(String headerFileExt) {
        this.headerFileExt = headerFileExt;
    }

    public Integer getHeaderFileWidth() {
        return headerFileWidth;
    }

    public void setHeaderFileWidth(Integer headerFileWidth) {
        this.headerFileWidth = headerFileWidth;
    }

    public Integer getHeaderFileHeight() {
        return headerFileHeight;
    }

    public void setHeaderFileHeight(Integer headerFileHeight) {
        this.headerFileHeight = headerFileHeight;
    }

    public Integer getPropState() {
        return propState;
    }

    public void setPropState(Integer propState) {
        this.propState = propState;
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