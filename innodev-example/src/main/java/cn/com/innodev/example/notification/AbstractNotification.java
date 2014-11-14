/**
 * 
 */
package cn.com.innodev.example.notification;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.innodev.example.notification.filter.CharFilter;
import cn.com.innodev.example.notification.filter.DefaultCharFilter;

/**
 * @author shipeng
 */
abstract class AbstractNotification implements Notification {
	
	protected static Logger LOG = LoggerFactory.getLogger(AbstractNotification.class);
	
	private CharFilter charFilter;
	
	/**
	 * 信息内容
	 */
	private String msgContent;
	/**
	 * 发送时间
	 */
	private Date sysCreateTime;
	
	public AbstractNotification(String msgContent) {
		this(msgContent, null);
	}
	
	public AbstractNotification(String msgContent, CharFilter charFilter) {
		if (msgContent == null || "".equals(msgContent)) {
			throw new IllegalArgumentException();
		}
		this.msgContent = msgContent;
		if (charFilter == null) {
			charFilter = DefaultCharFilter.getCharFilter();
		}else{
			this.charFilter = charFilter;
		}
	}
	
	public Date getSysCreateTime() {
		return this.sysCreateTime;
	}

	public String getMsgContent() {
		return this.msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	
	@Override
	public boolean send(boolean asyn) throws NotificationException {
		String[] illegalStrings = this.filterContentInternal();
		if (illegalStrings != null && illegalStrings.length >0) {
			LOG.error("Filter Error : " + this.parseArray(illegalStrings));
			return false;
		}
		boolean isok = this.sendInternal();
		this.sysCreateTime = new Date();
		return isok;
	}
	
	private StringBuffer parseArray(String[] strs) {
		if (strs == null || strs.length <=0) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (String str : strs) {
			sb.append("[").append(str).append("[]");
		}
		return sb;
	}
	
	public CharFilter getCharFilter() {
		return charFilter;
	}
	
	/**
	 * 过滤信息内容
	 * @return
	 */
	protected String[] filterContentInternal() {
		return null;
	}
	
	/**
	 * 内容发送
	 * @return
	 * @throws NotificationException
	 */
	protected abstract boolean sendInternal() throws NotificationException;
	
}
