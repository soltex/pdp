/**
 * 
 */
package cn.com.innodev.pdp.admin.services.impl;

import cn.com.innodev.pdp.framework.repo.AbstractBusinessEventListenerAdapter;

/**
 * @author shipeng
 */
public abstract class DeleteAdminListener extends AbstractBusinessEventListenerAdapter {

	public DeleteAdminListener() {
		super("DELETE_ADMIN_LISTENER");
	}
	
	public abstract void onDelete() ;
	
}
