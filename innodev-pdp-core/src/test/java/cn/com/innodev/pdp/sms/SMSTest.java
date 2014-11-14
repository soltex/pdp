/**
 * 
 */
package cn.com.innodev.pdp.sms;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.innodev.pdp.framework.sms.SMS;
import cn.com.innodev.pdp.framework.sms.SMSCreator;

/**
 * 
 * @author shipeng
 */
@ContextConfiguration(locations = { 
		"classpath*:/core-application-context.xml",
		"classpath*:META-INF/*-application-context-module.xml" }
)
@RunWith(SpringJUnit4ClassRunner.class)
public class SMSTest {
	
	@Test
	public void testSendSMS() {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for (int i=0;i<30;i++) {
			executorService.submit(new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					System.out.println("短信发送");
					SMSCreator.getInstance().createSMS("13426173105", "呵呵呵呵呵_" + UUID.randomUUID().toString()).send(true);
					return null;
				}
			});
		}
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSendSingleSMS() {
		SMS sms = SMSCreator.getInstance().createSMS("13426173105", "你好啊，呵呵呵呵呵");
		sms.send(true);
		
		
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
