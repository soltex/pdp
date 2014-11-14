/**
 * 
 */
package cn.com.innodev.pdp.framework.sms;

import java.util.Date;

/**
 * 短信
 * @author shipeng
 */
public interface SMS {
	
	/**
	 * 获取短信手机号
	 * @return
	 */
	String getMobile();
	
	/**
	 * 获取内容
	 * @return
	 */
	String getContent();
	
	/**
	 * 获取发送时间
	 * @return
	 */
	Date getCreateTime();
	
	/**
	 * 发送短信
	 * @param asyn
	 * @return
	 */
	boolean send(boolean asyn);
	
}
