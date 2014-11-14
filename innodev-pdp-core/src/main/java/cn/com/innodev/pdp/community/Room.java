/**
 * 
 */
package cn.com.innodev.pdp.community;

import com.vanstone.business.def.AbstractBusinessObject;
import com.vanstone.centralserver.common.MyAssert;

/**
 * @author shipeng
 * 
 */
public class Room extends AbstractBusinessObject {

	/** */
	private static final long serialVersionUID = 2162643394863201019L;
	private Integer id;
	private String roomNo;
	private Building building;
	
	public Room(Building building) {
		MyAssert.notNull(building);
		this.building = building;
	}
	
	@Override
	public Integer getId() {
		return this.id;
	}
	
	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public Building getBuilding() {
		return building;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
