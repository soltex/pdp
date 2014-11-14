/**
 * 
 */
package cn.com.innodev.pdp.business.sdk;

import cn.com.innodev.pdp.framework.PlatformException;

/**
 * @author shipeng
 */
public class BusinessException extends PlatformException {
	
	/** */
	private static final long serialVersionUID = 8142230873007427943L;
	
	public BusinessException() {
		super();
	}
	
	public BusinessException(String errorMsg) {
		super(errorMsg);
	}
	
	public BusinessException(Exception exception) {
		super(exception);
	}
}
