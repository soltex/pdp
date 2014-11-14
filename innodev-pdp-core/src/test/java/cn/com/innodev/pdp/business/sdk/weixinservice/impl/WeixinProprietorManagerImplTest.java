package cn.com.innodev.pdp.business.sdk.weixinservice.impl;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.innodev.pdp.business.sdk.weixinservice.proprietor.WeixinProprietorManager;

@ContextConfiguration(locations = { 
		"classpath*:/core-application-context.xml",
		"classpath*:META-INF/*-application-context-module.xml" }
)
@RunWith(SpringJUnit4ClassRunner.class)
public class WeixinProprietorManagerImplTest {

	@Autowired
	private WeixinProprietorManager weixinProprietorManager;
	
	@Test
	public void testGenerateValidatecode() {
		weixinProprietorManager.generateValidatecode("13426173105");
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSubscribeWeixinProprietor() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testUnsubscribeWeixinProprietor() {
		fail("Not yet implemented");
	}

	@Test
	public void testMobileAuthenticateProprietor() {
		fail("Not yet implemented");
	}

	@Test
	public void testBindRooms() {
		fail("Not yet implemented");
	}

	@Test
	public void testLogin() {
		fail("Not yet implemented");
	}

}
