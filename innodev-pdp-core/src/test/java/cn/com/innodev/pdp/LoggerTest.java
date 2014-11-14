/**
 * 
 */
package cn.com.innodev.pdp;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.innodev.pdp.admin.Admin;

import com.vanstone.dal.id.IDBuilder;

/**
 * @author shipeng
 */
public class LoggerTest {
	
	private Logger LOG = LoggerFactory.getLogger(LoggerTest.class);
	
	@Test
	public void testWriteLog() {
		Admin admin = new Admin();
		admin.setId(IDBuilder.base58Uuid());
		admin.setAdminName("呵呵呵呵");
		LOG.trace("Save Admin to HttpSession [{},{}]", admin.getId(), admin.getAdminName());
		
	}
	
}
