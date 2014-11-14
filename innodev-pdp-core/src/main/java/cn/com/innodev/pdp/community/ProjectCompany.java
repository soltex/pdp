/**
 * 
 */
package cn.com.innodev.pdp.community;

import cn.com.innodev.pdp.framework.bizcommon.ImageBean;

/**
 * 项目公司
 * 
 * @author shipeng
 */
public class ProjectCompany {
	
	private String id;
	private ImageBean logoImageBean;
	private String companyName;
	private String companyEmail;
	
	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public ImageBean getLogoImageBean() {
		return logoImageBean;
	}

	public void setLogoImageBean(ImageBean logoImageBean) {
		this.logoImageBean = logoImageBean;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
