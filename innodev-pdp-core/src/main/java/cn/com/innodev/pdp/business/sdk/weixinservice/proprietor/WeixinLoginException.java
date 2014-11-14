/**
 * 
 */
package cn.com.innodev.pdp.business.sdk.weixinservice.proprietor;

import cn.com.innodev.pdp.framework.PlatformException;

/**
 * 
 * @author shipeng
 */
public class WeixinLoginException extends PlatformException {

	/** */
	private static final long serialVersionUID = 6092396892404091477L;
	
	private ErrorCode errorCode;
	
	public WeixinLoginException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}
	
	public static enum ErrorCode {
		USERNAME_NOT_FOUND,
		USERNAME_PASSWORD_NOT_MATCH;
	}
	
}
