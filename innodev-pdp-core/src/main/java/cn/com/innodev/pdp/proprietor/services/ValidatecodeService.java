/**
 * 
 */
package cn.com.innodev.pdp.proprietor.services;

import cn.com.innodev.pdp.proprietor.ValidatecodeBean;

/**
 * 验证码生成
 * @author shipeng
 */
public interface ValidatecodeService {
	
	public static final String SERVICE = "validatecodeService";
	
	/**
	 * 生成验证码对象,并返回验证码Bean
	 * @param mobile
	 * @return
	 */
	ValidatecodeBean generate(String mobile);
	
	/**
	 * 验证验证码
	 * @param mobile
	 * @param validatecode
	 */
	void validate(String mobile, String validatecode) throws ValidatecodeException;
	
	/**
	 * 清理全部验证码
	 */
	void clearAllValidatecodes();
	
}
