package cn.com.innodev.pdp.framework.syslog;

import java.util.Date;

import org.junit.Test;

public class SearchLogBeanTest {

	@Test
	public void testToString() {
		SearchLogBean bean = new SearchLogBean();
		bean.setStartSysInsertTime(new Date());
		bean.addLogLevel(LogLevel.INFO);
		System.out.println(bean.toString());
	}
	
}
