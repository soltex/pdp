/**
 * 
 */
package cn.com.innodev.pdp.gson;

/**
 * @author shipeng
 *
 */
public class SystemInfo {
	
	private UserInfo userInfo;
	private String content;
	
	public SystemInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
