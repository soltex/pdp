/**
 * 
 */
package cn.com.innodev.pdp.proprietor;

import java.util.Date;

import cn.com.innodev.pdp.community.Community;
import cn.com.innodev.pdp.framework.bizcommon.ImageBean;

import com.vanstone.business.def.AbstractBusinessObject;

/**
 * @author shipeng
 */
public class RepairReport extends AbstractBusinessObject {

	/** */
	private static final long serialVersionUID = -2641544357938131316L;

	/** ID*/
	private String id;
	/** 报修单编号*/
	private String repairReportNo;
	/** 社区*/
	private Community community;
	/** 业主*/
	private Proprietor proprietor;
	private ImageBean imageBean1;
	private ImageBean imageBean2;
	private ImageBean imageBean3;
	private ImageBean imageBean4;
	private ImageBean imageBean5;
	private ImageBean imageBean6;
	private Date sysInsertTime;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vanstone.business.def.AbstractBusinessObject#getId()
	 */
	@Override
	public String getId() {
		return this.id;
	}

	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public Proprietor getProprietor() {
		return proprietor;
	}

	public void setProprietor(Proprietor proprietor) {
		this.proprietor = proprietor;
	}

	public ImageBean getImageBean1() {
		return imageBean1;
	}

	public void setImageBean1(ImageBean imageBean1) {
		this.imageBean1 = imageBean1;
	}

	public ImageBean getImageBean2() {
		return imageBean2;
	}

	public void setImageBean2(ImageBean imageBean2) {
		this.imageBean2 = imageBean2;
	}

	public ImageBean getImageBean3() {
		return imageBean3;
	}

	public void setImageBean3(ImageBean imageBean3) {
		this.imageBean3 = imageBean3;
	}

	public ImageBean getImageBean4() {
		return imageBean4;
	}

	public void setImageBean4(ImageBean imageBean4) {
		this.imageBean4 = imageBean4;
	}

	public ImageBean getImageBean5() {
		return imageBean5;
	}

	public void setImageBean5(ImageBean imageBean5) {
		this.imageBean5 = imageBean5;
	}

	public ImageBean getImageBean6() {
		return imageBean6;
	}

	public void setImageBean6(ImageBean imageBean6) {
		this.imageBean6 = imageBean6;
	}

	public Date getSysInsertTime() {
		return sysInsertTime;
	}

	public void setSysInsertTime(Date sysInsertTime) {
		this.sysInsertTime = sysInsertTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRepairReportNo() {
		return repairReportNo;
	}

	public void setRepairReportNo(String repairReportNo) {
		this.repairReportNo = repairReportNo;
	}
	
}
