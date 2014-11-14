package cn.com.innodev.pdp.framework.syslog.impl;

import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.vanstone.business.serialize.GsonCreator;

import cn.com.innodev.pdp.framework.syslog.GlobalLogType;
import cn.com.innodev.pdp.framework.syslog.LogLevel;
import cn.com.innodev.pdp.framework.syslog.LogService;
import cn.com.innodev.pdp.framework.syslog.SearchLogBean;
import cn.com.innodev.pdp.framework.syslog.SystemLog;

@ContextConfiguration(locations = { 
		"classpath*:/core-application-context.xml",
		"classpath*:META-INF/*-application-context-module.xml" }
)
@RunWith(SpringJUnit4ClassRunner.class)
public class LogServiceImplTest {
	
	@Autowired
	private LogService logService;
	
	@Test
	public void testAddLogBooleanGlobalLogTypeLogLevelMapOfStringStringMapOfStringStringStringObjectArray() {
		ExecutorService executorService = Executors.newFixedThreadPool(100);
		for (int i=0;i<100000;i++) {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					logService.addLog(true, GlobalLogType.WEIXIN_SERVER_LOG, LogLevel.INFO, new LinkedHashMap<String, String>(),
							new LinkedHashMap<String, String>(), "我靠了，你说呢{0}", new Object[] { UUID.randomUUID().toString() });
				}
			});
		}
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executorService.shutdown();
		while (executorService.isShutdown()) {
			break;
		}
	}

	@Test
	public void testAddLogBooleanGlobalLogTypeLogLevelHttpServletRequestMapOfStringStringStringObjectArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteLog() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteLogs() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAllLogs() {
		this.logService.deleteAllLogs();
	}

	@Test
	public void testSearchSystemLogs() {
		SearchLogBean searchLogBean = new SearchLogBean();
		searchLogBean.setKey("wk");
		Collection<SystemLog> systemLogs = this.logService.searchSystemLogs(searchLogBean, 0, 100);
		for (SystemLog systemLog : systemLogs) {
			Gson gson = GsonCreator.create();
			System.out.println(gson.toJson(systemLog));
		}
	}
	
	@Test
	public void testSearchTotalSystemLogs() {
		fail("Not yet implemented");
	}

}
