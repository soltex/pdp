/**
 * 
 */
package cn.com.innodev.pdp.admin.services.impl;

import org.springframework.stereotype.Component;

/**
 * @author shipeng
 */
@Component("communityOnDeleteAdminEventListener")
public class CommunityOnDeleteAdminEventListener extends DeleteAdminListener {

	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.admin.services.impl.DeleteAdminListener#onDelete()
	 */
	@Override
	public void onDelete() {
		System.out.println("呵呵呵呵呵呵--删除Admin Event");
	}
	
}
