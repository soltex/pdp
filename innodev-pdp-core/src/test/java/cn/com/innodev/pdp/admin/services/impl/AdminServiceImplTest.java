package cn.com.innodev.pdp.admin.services.impl;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.vanstone.business.ObjectDuplicateException;
import com.vanstone.business.serialize.GsonCreator;

import cn.com.innodev.pdp.admin.Admin;
import cn.com.innodev.pdp.admin.services.AdminService;

@ContextConfiguration(locations = { 
		"classpath*:/core-application-context.xml",
		"classpath*:META-INF/*-application-context-module.xml" }
)
@RunWith(SpringJUnit4ClassRunner.class)
public class AdminServiceImplTest {

	@Autowired
	private AdminService adminService;
	
	@Test
	public void testAddAdmin() {
		String adminName = "admin_123";
		String adminPwd = "admin_123";
		Admin admin = new Admin();
		admin.setAdminName(adminName);
		admin.setAdminPwd(adminPwd);
		try {
			this.adminService.addAdmin(admin);
		} catch (ObjectDuplicateException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAdmin() {
		Admin admin = this.adminService.getAdmin("XtbFzsSJSzRBBZTpNXRKgK");
		if (admin != null) {
			Gson gson = GsonCreator.createPretty();
			System.out.println(gson.toJson(admin));
		}
	}

	@Test
	public void testUpdatePassword() {
		this.adminService.updatePassword("XtbFzsSJSzRBBZTpNXRKgK", "heheheh");
	}

	@Test
	public void testGetAdminByAdminName() {
		Admin admin = this.adminService.getAdminByAdminName("admin_123");
		if (admin != null) {
			Gson gson = GsonCreator.createPretty();
			System.out.println(gson.toJson(admin));
		}
	}

	@Test
	public void testUpdateAdminLastLoginTime() {
		this.adminService.updateAdminLastLoginTime("XtbFzsSJSzRBBZTpNXRKgK", new Date());
	}
}
