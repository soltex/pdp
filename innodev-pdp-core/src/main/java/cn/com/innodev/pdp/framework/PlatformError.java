/**
 * 
 */
package cn.com.innodev.pdp.framework;

import com.vanstone.business.VanstoneError;

/**
 * @author shipeng
 *
 */
public class PlatformError extends VanstoneError {

	private static final long serialVersionUID = 2945103920492765161L;
	
	public PlatformError() {
		super();
	}
	
	public PlatformError(String errorMsg) {
		super(errorMsg);
	}
	
	public PlatformError(Exception e) {
		super(e);
	}
	
}
