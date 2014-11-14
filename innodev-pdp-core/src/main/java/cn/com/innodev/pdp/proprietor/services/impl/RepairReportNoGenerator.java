/**
 * 
 */
package cn.com.innodev.pdp.proprietor.services.impl;

/**
 * 报修单编号
 * @author shipeng
 */
public interface RepairReportNoGenerator {
	
	/**
	 * 生成报修单编号
	 * @return
	 */
	String generateNo();
	
	/**
	 * 清理报修单编号
	 * @return
	 */
	void clearNos();
	
}
