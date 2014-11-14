package cn.com.innodev.pdp.community.persistence.object;

import java.util.Date;

public class ComProjectStaffDO {
    private Integer id;

    private String accountName;

    private String accountPassword;

    private String fullName;

    private String email;

    private String mobile1;

    private String mobile2;

    private String mobile3;

    private String mobile4;

    private Date sysInsertTime;

    private Date sysUpdateTime;

    private Date lastLoginTime;

    private String headImageFileId;

    private String headImageFileExt;

    private Integer headImageFileWidth;

    private Integer headImageFileHeight;

    private String department;

    private Integer staffRole;

    private Integer staffState;

    private String communityId;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile1() {
        return mobile1;
    }

    public void setMobile1(String mobile1) {
        this.mobile1 = mobile1;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public String getMobile3() {
        return mobile3;
    }

    public void setMobile3(String mobile3) {
        this.mobile3 = mobile3;
    }

    public String getMobile4() {
        return mobile4;
    }

    public void setMobile4(String mobile4) {
        this.mobile4 = mobile4;
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

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getHeadImageFileId() {
        return headImageFileId;
    }

    public void setHeadImageFileId(String headImageFileId) {
        this.headImageFileId = headImageFileId;
    }

    public String getHeadImageFileExt() {
        return headImageFileExt;
    }

    public void setHeadImageFileExt(String headImageFileExt) {
        this.headImageFileExt = headImageFileExt;
    }

    public Integer getHeadImageFileWidth() {
        return headImageFileWidth;
    }

    public void setHeadImageFileWidth(Integer headImageFileWidth) {
        this.headImageFileWidth = headImageFileWidth;
    }

    public Integer getHeadImageFileHeight() {
        return headImageFileHeight;
    }

    public void setHeadImageFileHeight(Integer headImageFileHeight) {
        this.headImageFileHeight = headImageFileHeight;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getStaffRole() {
        return staffRole;
    }

    public void setStaffRole(Integer staffRole) {
        this.staffRole = staffRole;
    }

    public Integer getStaffState() {
        return staffState;
    }

    public void setStaffState(Integer staffState) {
        this.staffState = staffState;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}