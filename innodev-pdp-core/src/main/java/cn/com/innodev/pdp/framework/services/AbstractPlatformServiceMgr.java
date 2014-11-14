/**
 * 
 */
package cn.com.innodev.pdp.framework.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.PlatformTransactionManager;

import com.vanstone.framework.business.services.AbstractBusinessService;

/**
 * 
 * @author shipeng
 */
public class AbstractPlatformServiceMgr extends AbstractBusinessService {
	
	/** */
	private static final long serialVersionUID = -2186063547793003801L;
	
	@Override
	@Autowired
	@Qualifier(value = "jdbcTransactionManager")
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		super.setTransactionManager(transactionManager);
	}
	
}
