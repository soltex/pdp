package cn.com.innodev.pdp.proprietor.services.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { 
		"classpath*:/core-application-context.xml",
		"classpath*:META-INF/*-application-context-module.xml" }
)
@RunWith(SpringJUnit4ClassRunner.class)
public class DefaultRepairReportNoGeneratorImplTest {

	@Autowired
	private RepairReportNoGenerator repairReportNoGenerator;
	
	@Test
	public void testGenerateNo() {
		System.out.println(repairReportNoGenerator.generateNo());
	}

}
