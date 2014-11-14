/**
 * 
 */
package cn.com.innodev.pdp.admin.webapp;

/**
 * @author shipeng
 * 
 */
public class PassportForm {
	private String userName;
	private String userPwd;
	private boolean rememberPassword = false;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public boolean isRememberPassword() {
		return rememberPassword;
	}

	public void setRememberPassword(boolean rememberPassword) {
		this.rememberPassword = rememberPassword;
	}

}
