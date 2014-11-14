/**
 * 
 */
package cn.com.innodev.pdp.proprietor;

import java.util.Date;

import cn.com.innodev.pdp.framework.bizcommon.ImageBean;

import com.vanstone.business.def.AbstractBusinessObject;

/**
 * @author shipeng
 */
public class VisitorLog extends AbstractBusinessObject {

	/** */
	private static final long serialVersionUID = 8579026840042745906L;

	private Integer id;
	private ImageBean qrImageBean;
	private Date sysInsertTime;
	private String accessorName;
	private String accessPurpose;
	private Date expectLeaveTime;
	private VisitorQRState visitorQRState;
	private Proprietor proprietor;
	
	public VisitorLog(Proprietor proprietor) {
		this.proprietor = proprietor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vanstone.business.def.AbstractBusinessObject#getId()
	 */
	@Override
	public Integer getId() {
		return this.id;
	}

	public ImageBean getQrImageBean() {
		return qrImageBean;
	}

	public void setQrImageBean(ImageBean qrImageBean) {
		this.qrImageBean = qrImageBean;
	}

	public Date getSysInsertTime() {
		return sysInsertTime;
	}

	public void setSysInsertTime(Date sysInsertTime) {
		this.sysInsertTime = sysInsertTime;
	}

	public String getAccessorName() {
		return accessorName;
	}

	public void setAccessorName(String accessorName) {
		this.accessorName = accessorName;
	}

	public String getAccessPurpose() {
		return accessPurpose;
	}

	public void setAccessPurpose(String accessPurpose) {
		this.accessPurpose = accessPurpose;
	}

	public Date getExpectLeaveTime() {
		return expectLeaveTime;
	}
	
	public void setExpectLeaveTime(Date expectLeaveTime) {
		this.expectLeaveTime = expectLeaveTime;
	}
	
	public VisitorQRState getVisitorQRState() {
		return visitorQRState;
	}

	public void setVisitorQRState(VisitorQRState visitorQRState) {
		this.visitorQRState = visitorQRState;
	}
	
	public Proprietor getProprietor() {
		return proprietor;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
}
