/**
 * 
 */
package cn.com.innodev.pdp.split;

import org.apache.commons.lang.StringUtils;

/**
 * @author shipeng
 *
 */
public class StringSplitMainApp {
	
	public static void main(String[] args) {
		String str = "111,23,12,12,3,ss\n,,sdfsdfsdfsdfsdf";
		String[] strs = StringUtils.split(str, "\n,");
		for (String str1 : strs) {
			System.out.println(str1);
		}
	}
	
}
