/**
 * 
 */
package cn.com.innodev.pdp.proprietor.services;

import cn.com.innodev.pdp.framework.PlatformException;

/**
 * @author shipeng
 */
public class ValidatecodeException extends PlatformException {
	
	/** */
	private static final long serialVersionUID = -379879548903967864L;
	
	private ErrorCode errorCode;
	
	public ValidatecodeException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
	public ErrorCode getErrorCode() {
		return this.errorCode;
	}
	
	/**
	 * 创建ValidatecodeException
	 * @param errorCode
	 * @return
	 */
	public static ValidatecodeException createValidatecodeException(ErrorCode errorCode) {
		return new ValidatecodeException(errorCode);
	}
	
	public static enum ErrorCode {
		/** 手机号不存在*/
		Mobile_Not_Found, 
		/** 验证码不匹配*/
		Valdiatecode_Not_Match, 
		/** 验证码失效*/
		Validatecode_Expire;
	}
}
