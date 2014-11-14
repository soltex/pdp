/**
 * 
 */
package cn.com.innodev.pdp.community.event;

import cn.com.innodev.pdp.framework.event.AbstractPlatformApplicationEventOfSpring;

/**
 * 
 * @author shipeng
 */
public class UpdateRoomProprietorCountEvent extends AbstractPlatformApplicationEventOfSpring {

	private Integer roomId;
	
	public UpdateRoomProprietorCountEvent(Object source, int roomId) {
		super(source);
		this.roomId = roomId;
	}
	
	/** */
	private static final long serialVersionUID = 2749366737869930607L;

	public Integer getRoomId() {
		return roomId;
	}
	
}
