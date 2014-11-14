/**
 * 
 */
package cn.com.innodev.pdp.proprietor;

import java.util.Date;

/**
 * @author shipeng
 * 
 */
public class ValidatecodeBean {

	private String mobile;
	private String validatecode;
	private Date expireTime;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getValidatecode() {
		return validatecode;
	}

	public void setValidatecode(String validatecode) {
		this.validatecode = validatecode;
	}

	public Date getExpireTime() {
		return expireTime;
	}
	
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

}
