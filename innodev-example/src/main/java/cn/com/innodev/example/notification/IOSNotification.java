/**
 * 
 */
package cn.com.innodev.example.notification;

/**
 * @author shipeng
 */
class IOSNotification extends AbstractNotification {
	
	public IOSNotification(String msgContent) {
		super(msgContent);
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.example.notification.AbstractNotification#sendInternal()
	 */
	@Override
	protected boolean sendInternal() throws NotificationException {
		LOG.info("IOS send notification.");
		return false;
	}
	
}
