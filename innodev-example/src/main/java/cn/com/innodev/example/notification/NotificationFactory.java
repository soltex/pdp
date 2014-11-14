/**
 * 
 */
package cn.com.innodev.example.notification;

/**
 * 
 * @author shipeng
 */
public class NotificationFactory {
	
	/**
	 * 创建Notification 
	 * @param type
	 * @return
	 */
	public static Notification createNotification(NotificationType notificationType, String msgContent) {
		switch (notificationType) {
		case APN:
			return new APNNotification(msgContent);
		case IOS:
			return new IOSNotification(msgContent);
		}
		throw new IllegalArgumentException("Notification Type Error.");
	}
	
	public enum NotificationType {
		APN, IOS;
	}
}
