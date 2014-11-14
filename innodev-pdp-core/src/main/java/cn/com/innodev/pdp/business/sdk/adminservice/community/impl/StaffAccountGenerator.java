/**
 * 
 */
package cn.com.innodev.pdp.business.sdk.adminservice.community.impl;


/**
 * @author shipeng
 */
public interface StaffAccountGenerator {
	
	/**
	 * 生成该工程公司最高权限默认账号
	 * @param community
	 * @return
	 */
	AccountObject generate(String communityId);
	
}
