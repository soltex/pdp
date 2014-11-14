/**
 * 
 */
package cn.com.innodev.example.notification.filter;

/**
 * @author shipeng
 */
public class DefaultCharFilter implements CharFilter {

	private static class DefaultCharFilterInstance {
		private static final DefaultCharFilter INSTANCE = new DefaultCharFilter();
	}
	
	public static CharFilter getCharFilter() {
		return DefaultCharFilterInstance.INSTANCE;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.example.notification.filter.CharFilter#filterIllegalChars(java.lang.String)
	 */
	public String[] filterIllegalChars(String msg) {
		return null;
	}

}
