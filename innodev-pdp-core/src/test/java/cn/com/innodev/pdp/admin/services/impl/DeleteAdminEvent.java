/**
 * 
 */
package cn.com.innodev.pdp.admin.services.impl;

import cn.com.innodev.pdp.admin.Admin;
import cn.com.innodev.pdp.framework.repo.AbstractBusinessEvent;

/**
 * @author shipeng
 *
 */
public class DeleteAdminEvent extends AbstractBusinessEvent {

	private Admin admin;
	
	public DeleteAdminEvent(Object source, Admin admin) {
		super(source);
	}

	public Admin getAdmin() {
		return admin;
	}
	
}
