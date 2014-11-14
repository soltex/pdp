/**
 * 
 */
package cn.com.innodev.pdp.community.event;

import cn.com.innodev.pdp.framework.event.AbstractPlatformApplicationEventOfSpring;

/**
 * @author shipeng
 *
 */
public class DeleteCommunityEvent extends AbstractPlatformApplicationEventOfSpring {

	/** */
	private static final long serialVersionUID = -7266511056765816010L;

	public DeleteCommunityEvent(Object source) {
		super(source);
	}
	
}
