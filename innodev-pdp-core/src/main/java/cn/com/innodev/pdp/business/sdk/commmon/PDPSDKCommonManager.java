/**
 * 
 */
package cn.com.innodev.pdp.business.sdk.commmon;

import cn.com.innodev.pdp.community.Community;
import cn.com.innodev.pdp.proprietor.Proprietor;

/**
 * @author shipeng
 */
public interface PDPSDKCommonManager {
	
	public static final String SERVICE = "pdpSDKCommonManager";
	
	/**
	 * 获取并验证当前社区信息，如Community Equals Null ，throws IllegalArgument
	 * @param communityID
	 * @return
	 */
	Community getAndValidateCommunity(String communityID);
	
	/**
	 * 获取Proprietor并验证
	 * @param openId
	 * @return
	 */
	Proprietor getAndValidateProprietorByOpenId(String openId);
	
	/**
	 * 获取Proprietor并验证
	 * @param id
	 * @return
	 */
	Proprietor getAndValidateProprietorById(String id);
	
}
