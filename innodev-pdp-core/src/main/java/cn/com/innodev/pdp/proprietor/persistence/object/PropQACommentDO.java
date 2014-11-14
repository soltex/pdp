package cn.com.innodev.pdp.proprietor.persistence.object;

import java.util.Date;

public class PropQACommentDO {
    private String id;

    private String fromPropId;

    private String toPropId;

    private Date sysInsertTime;

    private Date sysUpdateTime;

    private String qaId;

    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromPropId() {
        return fromPropId;
    }

    public void setFromPropId(String fromPropId) {
        this.fromPropId = fromPropId;
    }

    public String getToPropId() {
        return toPropId;
    }

    public void setToPropId(String toPropId) {
        this.toPropId = toPropId;
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

    public String getQaId() {
        return qaId;
    }

    public void setQaId(String qaId) {
        this.qaId = qaId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}