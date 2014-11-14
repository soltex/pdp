/**
 * 
 */
package cn.com.innodev.pdp.community;

import java.util.Date;

import com.vanstone.business.def.AbstractBusinessObject;

/**
 * @author shipeng
 * 
 */
public class QA extends AbstractBusinessObject {

	/** */
	private static final long serialVersionUID = 9015621775244938536L;
	
	private String id;
	private String title;
	private String content;
	private Date sysInsertTime;
	private Date sysUpdateTime;
	private Community community;
	
	public QA(Community community) {
		this.community = community;
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
	
	public void setId(String id) {
		this.id = id;
	}

	public Community getCommunity() {
		return community;
	}
	
}
