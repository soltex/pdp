package cn.com.innodev.pdp.business.sdk.adminservice.region.impl;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.innodev.pdp.business.sdk.adminservice.region.RegionImportException;
import cn.com.innodev.pdp.business.sdk.adminservice.region.RegionManager;

import com.vanstone.fs.FSFile;
import com.vanstone.fs.FSManager;
import com.vanstone.fs.FSType;

@ContextConfiguration(locations = { 
		"classpath*:/core-application-context.xml",
		"classpath*:META-INF/*-application-context-module.xml" }
)
@RunWith(SpringJUnit4ClassRunner.class)
public class RegionManagerImplTest {

	@Autowired
	private RegionManager regionManager;
	
	@Test
	public void testBatchImportRegions() {
		FSFile fsFile = FSManager.getInstance().getFsFile("/region-template.xls", FSType.Temporary);
		try {
			regionManager.batchImportRegions(fsFile);
		} catch (RegionImportException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
