package cn.com.innodev.pdp.business.sdk.adminservice.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.innodev.pdp.business.sdk.adminservice.weixin.PlatformWeixinManager;

@ContextConfiguration(locations = { 
		"classpath*:/core-application-context.xml",
		"classpath*:META-INF/*-application-context-module.xml" }
)
@RunWith(SpringJUnit4ClassRunner.class)
public class PlatformWeixinManagerImplTest {

	@Autowired
	private PlatformWeixinManager platformWeixinManager;
	
	@Test
	public void testCreateWeixinEnum() {
		boolean isok = platformWeixinManager.createWeixinEnum();
		System.out.println(isok);
	}
	
}
