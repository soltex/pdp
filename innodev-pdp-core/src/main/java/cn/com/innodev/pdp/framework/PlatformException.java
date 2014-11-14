/**
 * 
 */
package cn.com.innodev.pdp.framework;

import com.vanstone.business.VanstoneException;

/**
 * 系统异常
 * @author shipeng
 */
public class PlatformException extends VanstoneException {
	
	private static final long serialVersionUID = 2080233831900302463L;
	
	public PlatformException() {
		super();
	}
	
	public PlatformException(String errorMsg) {
		super(errorMsg);
	}
	
	public PlatformException(Exception e) {
		super(e);
	}
}
