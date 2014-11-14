package cn.com.innodev.example.notification;

import java.util.Date;

/**
 * Notification
 * @author shipeng
 */
public interface Notification {
	
	/**
	 * 获取发送时间
	 * @return
	 */
	Date getSysCreateTime();
	
	/**
	 * 获取通知内容
	 * @return
	 */
	String getMsgContent();
	
	/**
	 * 设定通知内容
	 * @param msgContent
	 */
	void setMsgContent(String msgContent);
	
	/**
	 * 发送消息
	 * @return
	 * @throws NotificationException
	 */
	boolean send(boolean asyn) throws NotificationException;
}
