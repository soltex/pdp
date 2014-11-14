/**
 * 
 */
package cn.com.innodev.pdp;

import cn.com.innodev.pdp.framework.Constants;

import java.util.regex.Matcher;

/**
 * 
 * @author shipeng
 */
public class ConstantsMainApp {
	
	public static void main(String[] args) {
		
		String url = "http://weixin-service.c-cap.com.cn/weixinprocess/sdfsdfsdfsdfds.action?param=12312312";
		Matcher matcher = Constants.WEIXIN_PROCESS_URL_PATTERN.matcher(url);
		System.out.println(matcher.find());
		System.out.println(matcher.group(1));
	}
	
}
