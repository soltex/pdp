/**
 * 
 */
package cn.com.innodev.pdp.community;

import com.vanstone.business.def.AbstractBusinessObject;

/**
 * @author shipeng
 * 
 */
public class Building extends AbstractBusinessObject {

	/** */
	private static final long serialVersionUID = 931152582580295616L;

	private Integer id;
	private String buildingNo;
	private String content;
	
	@Override
	public Integer getId() {
		return this.id;
	}
	
	public String getBuildingNo() {
		return buildingNo;
	}

	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
