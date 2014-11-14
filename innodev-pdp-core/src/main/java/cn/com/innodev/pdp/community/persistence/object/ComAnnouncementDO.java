package cn.com.innodev.pdp.community.persistence.object;

import java.util.Date;

public class ComAnnouncementDO {
    private String id;

    private String title;

    private Date sysInsertTime;

    private String communityId;

    private String content;

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

    public Date getSysInsertTime() {
        return sysInsertTime;
    }

    public void setSysInsertTime(Date sysInsertTime) {
        this.sysInsertTime = sysInsertTime;
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