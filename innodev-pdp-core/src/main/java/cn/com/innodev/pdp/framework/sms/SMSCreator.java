/**
 * 
 */
package cn.com.innodev.pdp.framework.sms;

import cn.com.innodev.pdp.framework.sms.support.TodaynicSMSSupport;

/**
 * 短信创建器
 * @author shipeng
 */
public class SMSCreator {
	
	private static class SMSCreatorInstance {
		private static final SMSCreator instance = new SMSCreator();
	}
	
	public static SMSCreator getInstance() {
		return SMSCreatorInstance.instance;
	}
	
	/**
	 * 创建短信
	 * @param mobile
	 * @param content
	 * @return
	 */
	public SMS createSMS(String mobile, String content) {
		return new TodaynicSMSSupport(mobile, content);
	}
	
}
