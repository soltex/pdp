package cn.com.innodev.pdp.proprietor.services.impl;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.innodev.pdp.proprietor.services.ValidatecodeException;
import cn.com.innodev.pdp.proprietor.services.ValidatecodeService;

@ContextConfiguration(locations = { 
		"classpath*:/core-application-context.xml",
		"classpath*:META-INF/*-application-context-module.xml" }
)
@RunWith(SpringJUnit4ClassRunner.class)
public class ValidatecodeServiceImplTest {
	
	@Autowired
	private ValidatecodeService validatecodeService;
	
	@Test
	public void testGenerate() {
		for (int i=0;i<100;i++) {
			validatecodeService.generate("13426173105");
			validatecodeService.generate(UUID.randomUUID().toString());
		}
	}

	@Test
	public void testValidate() {
		String mobile = "13426173105";
		String validatecode = "t6rf1";
		try {
			this.validatecodeService.validate(mobile, validatecode);
		} catch (ValidatecodeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testClearAllValidatecodes() {
		this.validatecodeService.clearAllValidatecodes();
	}

}
