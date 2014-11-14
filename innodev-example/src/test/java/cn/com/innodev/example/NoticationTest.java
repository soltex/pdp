/**
 * 
 */
package cn.com.innodev.example;

import org.junit.Test;

import cn.com.innodev.example.notification.Notification;
import cn.com.innodev.example.notification.NotificationException;
import cn.com.innodev.example.notification.NotificationFactory;
import cn.com.innodev.example.notification.NotificationFactory.NotificationType;

/**
 * @author shipeng
 */
public class NoticationTest {
	@Test
	public void testSendNotification() {
		Notification notification = NotificationFactory.createNotification(NotificationType.IOS, "sdfsdfsdfsdfsdfsd");
		try {
			notification.send(false);
		} catch (NotificationException e) {
			e.printStackTrace();
		}
	}
}
