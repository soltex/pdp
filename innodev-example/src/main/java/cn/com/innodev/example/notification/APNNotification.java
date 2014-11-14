/**
 * 
 */
package cn.com.innodev.example.notification;

/**
 * @author shipeng
 */
class APNNotification extends AbstractNotification {
	
	public APNNotification(String msgString) {
		super(msgString);
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.example.notification.AbstractNotification#sendInternal()
	 */
	@Override
	protected boolean sendInternal() throws NotificationException {
		LOG.debug("APN Push Message : {}" , this.getMsgContent());
		
		return false;
	}

}
