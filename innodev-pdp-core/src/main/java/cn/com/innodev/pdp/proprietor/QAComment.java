/**
 * 
 */
package cn.com.innodev.pdp.proprietor;

import java.util.Date;

import cn.com.innodev.pdp.community.QA;

import com.vanstone.business.def.AbstractBusinessObject;

/**
 * 
 * @author shipeng
 */
public class QAComment extends AbstractBusinessObject {

	/** */
	private static final long serialVersionUID = -3542500091051186271L;
	private String id;
	private Proprietor fromProprietor;
	private Proprietor toProprietor;
	private Date sysInsertTime;
	private Date sysUpdateTime;
	private QA qa;
	private String content;

	public QAComment(QA qa, Proprietor fromProprietor, Proprietor toProprietor) {
		this.qa = qa;
		this.fromProprietor = fromProprietor;
		this.toProprietor = toProprietor;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Proprietor getFromProprietor() {
		return fromProprietor;
	}

	public Proprietor getToProprietor() {
		return toProprietor;
	}

	public QA getQa() {
		return qa;
	}

	public void setId(String id) {
		this.id = id;
	}

}
