package cn.com.innodev.pdp.business.sdk.adminservice.weixin.impl;

import static org.junit.Assert.*;

import java.util.Collection;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vanstone.centralserver.common.weixin.wrap.user.UserGroupInfo;

import cn.com.innodev.pdp.business.sdk.BusinessException;
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
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetUserGroupInfos() {
		Collection<UserGroupInfo> userGroupInfos = null;
		try {
			userGroupInfos = platformWeixinManager.getUserGroupInfos();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		for (UserGroupInfo userGroupInfo : userGroupInfos) {
			System.out.println(ToStringBuilder.reflectionToString(userGroupInfo, ToStringStyle.SHORT_PREFIX_STYLE));
		}
	}
}
