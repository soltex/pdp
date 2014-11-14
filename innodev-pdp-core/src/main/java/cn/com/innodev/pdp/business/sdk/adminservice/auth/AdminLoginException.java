/**
 * 
 */
package cn.com.innodev.pdp.business.sdk.adminservice.auth;

import cn.com.innodev.pdp.framework.PlatformException;

/**
 * @author shipeng
 *
 */
public class AdminLoginException extends PlatformException {

	/** */
	private static final long serialVersionUID = 456871409800842671L;
	
	private ErrorCode errorCode;
	
	public AdminLoginException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}
	
	/**
	 * 创建异常
	 * @param errorCode
	 * @return
	 */
	public static AdminLoginException createException(ErrorCode errorCode) {
		return new AdminLoginException(errorCode);
	}
	
	public static enum ErrorCode {
		Admin_Name_Not_Found,
		Admin_Name_Password_Not_Match;
	}
}
