/**
 * 
 */
package cn.com.innodev.pdp.framework.repo;

import java.util.Iterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author shipeng
 */
@ContextConfiguration(locations = { 
		"classpath*:/core-application-context.xml",
		"classpath*:META-INF/*-application-context-module.xml" }
)
@RunWith(SpringJUnit4ClassRunner.class)
public class GlobalRepoTest {
	
	@Test
	public void testGetBusinessEventListenerByGroup() {
		
		GlobalRepo globalRepo = GlobalRepo.getInstance();
		Iterator<BusinessEventListener> iterator = globalRepo.getBusinessEventListenersIteratorByGroup("DELETE_ADMIN_LISTENER");
		while (iterator.hasNext()) {
			BusinessEventListener listener= iterator.next();
			System.out.println(listener.getGroup() + " == " + listener.getName());
		}
		
	}
}
