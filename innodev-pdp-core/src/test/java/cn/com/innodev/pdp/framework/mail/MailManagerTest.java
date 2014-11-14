/**
 * 
 */
package cn.com.innodev.pdp.framework.mail;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author shipeng
 */
@ContextConfiguration(locations = { 
		"classpath*:/core-application-context.xml",
		"classpath*:META-INF/*-application-context-module.xml" }
)
@RunWith(SpringJUnit4ClassRunner.class)
public class MailManagerTest {
	
	@Autowired
	private MailManager mailManager;
	
	@Test
	public void testSendMail() {
		mailManager.send("shipengpipi@126.com","呵呵呵呵呵", "<html><body>呵呵呵和测试邮件</body></html>");
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
