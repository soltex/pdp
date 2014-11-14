/**
 * 
 */
package cn.com.innodev.pdp.framework.web;

import cn.com.innodev.pdp.framework.Constants;

/**
 * 基础ActionForm
 * @author shipeng
 */
public class AbstractActionForm {
	
	/** 关联节点*/
	private String rel = Constants.DWZ_CONTAINER_HTML_ID;
	
	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}
	
}
