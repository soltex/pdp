/**
 * 
 */
package cn.com.innodev.pdp.proprietor;

import com.vanstone.business.lang.BaseEnum;

/**
 * 
 * @author shipeng
 */
public enum RepairReportState implements BaseEnum<Integer> {
	
	Apply_Repair_Report("申请报修单",0), 
	Finish_Repair_Report("完成报修单填写",5),
	Handled_Repair_Report("已受理报修单",10),
	Finsish_Repair_Report("完成报修单",15);
	;
	
	private String desc;
	private Integer code;
	
	private RepairReportState(String desc, Integer code) {
		this.desc = desc;
		this.code = code;
	}
	
	@Override
	public Integer getCode() {
		return this.code;
	}

	@Override
	public String getDesc() {
		return this.desc;
	}
	
}
