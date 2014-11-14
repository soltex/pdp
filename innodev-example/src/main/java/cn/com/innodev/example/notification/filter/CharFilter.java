/**
 * 
 */
package cn.com.innodev.example.notification.filter;

/**
 * @author shipeng
 *
 */
public interface CharFilter {
	
	/** 非法字符配置文件*/
	public static final String ILLEGAL_CHARS_FILE = "/illegal-chars.txt";
	
	/**
	 * 过滤非法字符
	 * @param msg
	 * @return
	 */
	String[] filterIllegalChars(String msg);
	
}
