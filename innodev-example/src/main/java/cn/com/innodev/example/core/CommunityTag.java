/**
 * 
 */
package cn.com.innodev.example.core;

import com.vanstone.business.def.AbstractBusinessObject;

/**
 * 
 * @author shipeng
 */
public class CommunityTag extends AbstractBusinessObject {

	/** */
	private static final long serialVersionUID = -7323197160067005770L;

	private String id;
	private String tagName;
	
	@Override
	public String getId() {
		return this.id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
