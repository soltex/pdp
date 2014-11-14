/**
 * 
 */
package cn.com.innodev.pdp.community;

import java.util.Date;

import com.vanstone.business.def.AbstractBusinessObject;

/**
 * 社区公告
 * @author shipeng
 */
public class Announcement extends AbstractBusinessObject {
	
	/** */
	private static final long serialVersionUID = 5234516830330844908L;

	private String id;
	private String title;
	private Date sysInsertTime;
	private Community community;
	private String content;

	public Announcement(Community community) {
		this.community = community;
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

	public Community getCommunity() {
		return community;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
