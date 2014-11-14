/**
 * 
 */
package cn.com.innodev.pdp.framework.syslog;

/**
 * 检索日志常量
 * 
 * @author shipeng
 */
public class SearchLogBean extends BasicSearchBean {
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public boolean existCondition() {
		if (this.key != null && !"".equals(this.key)) {
			return true;
		}
		return super.existCondition();
	}
	
}
