package cn.com.innodev.pdp.framework.bizcommon.impl;

import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.innodev.pdp.business.sdk.adminservice.system.SystemManager;
import cn.com.innodev.pdp.framework.bizcommon.CommonService;
import cn.com.innodev.pdp.framework.bizcommon.region.City;
import cn.com.innodev.pdp.framework.bizcommon.region.Province;

import com.google.gson.Gson;
import com.vanstone.business.ObjectDuplicateException;
import com.vanstone.business.serialize.GsonCreator;

@ContextConfiguration(locations = { 
		"classpath*:/core-application-context.xml",
		"classpath*:META-INF/*-application-context-module.xml" }
)
@RunWith(SpringJUnit4ClassRunner.class)
public class CommonServiceImplTest {
	
	@Autowired
	private CommonService commonService;
	@Autowired
	private SystemManager systemManager;
	
	@Test
	public void testAddProvince() {
		Province province = new Province();
		province.setTitle("河北");
		try {
			this.commonService.addProvince(province);
		} catch (ObjectDuplicateException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateProvince() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetProvince() {
		systemManager.flushAllOfRedis();
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		final int provinceId = 2;
		for (int i=0;i<100;i++) {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					Province province = commonService.getProvince(provinceId);
					System.out.println(province.getTitle());
				}
			});
		}
		executorService.shutdown();
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (executorService.isShutdown()) {
			break;
		}
	}
	
	@Test
	public void testGetProvincesWithCount() {
		Collection<Province> provinces = this.commonService.getProvincesWithCityCount();
		if (provinces != null && provinces.size() >0) {
			for (Province province : provinces) {
				Gson gson = GsonCreator.createPretty();
				System.out.println(gson.toJson(province));
			}
		}
	}
	@Test
	public void testDeleteProvince() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetProvinces() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddCity() {
		Province province = this.commonService.getProvince(1);
		City city = new City(province);
		city.setTitle("tangshan");
		try {
			this.commonService.addCity(city);
		} catch (ObjectDuplicateException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateCity() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCity() {
		int cityId = 1;
		City city = this.commonService.getCity(cityId);
		Gson gson = GsonCreator.createPretty();
		System.out.println(gson.toJson(city));
	}

	@Test
	public void testDeleteCity() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCities() {
		fail("Not yet implemented");
	}

}
