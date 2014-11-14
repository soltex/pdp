/**
 * 
 */
package cn.com.innodev.pdp.gson;

import com.vanstone.common.util.MD5Util;

/**
 * @author shipeng
 *
 */
public class UserInfo {
	
	private String id;
	private String userName;
	private String password;
	
	protected UserInfo() {}
	
	public UserInfo(String userName) {
		this.id = MD5Util.MD5(userName);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
