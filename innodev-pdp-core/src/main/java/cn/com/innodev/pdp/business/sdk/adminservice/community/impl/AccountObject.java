/**
 * 
 */
package cn.com.innodev.pdp.business.sdk.adminservice.community.impl;

/**
 * @author shipeng
 * 
 */
public class AccountObject {

	private String accountName;
	private String password;
	
	public AccountObject(String accountName,String password) {
		this.accountName = accountName;
		this.password = password;
	}
	
	public String getAccountName() {
		return accountName;
	}

	public String getPassword() {
		return password;
	}

}
