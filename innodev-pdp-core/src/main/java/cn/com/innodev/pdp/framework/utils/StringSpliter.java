/**
 * 
 */
package cn.com.innodev.pdp.framework.utils;

import org.apache.commons.lang.StringUtils;

/**
 * @author shipeng
 */
public class StringSpliter {
	
	/**
	 * 通过逗号，空格，回车，中文逗号，中文空格
	 * @param str
	 * @return
	 */
	public static String[] split(String str) {
		if (str == null || "".equals(str)) {
			return null;
		}
		return StringUtils.split(str, ",， 　\n");
	}
	
	public static void main(String[] args) {
		String str = "a,d,d,e，ss,ss aaaaa, 谁水水水水　kkkk,";
		String[] strs = split(str);
		for (String s : strs) {
			System.out.println(" -- " + s);
		}
	}
}
