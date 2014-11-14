/**
 * 
 */
package cn.com.innodev.pdp.validatecode;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.innodev.pdp.framework.bizcommon.DefaultValidatecodeGenerator;
import cn.com.innodev.pdp.framework.bizcommon.ValidatecodeGenerator;

/**
 * @author shipeng
 *
 */
public class ValidatecodeGeneratorTest {
	
	private static Logger LOG = LoggerFactory.getLogger(ValidatecodeGeneratorTest.class);
	
	@Test
	public void testGenerateValidatecode() {
		ValidatecodeGenerator validatecodeGenerator = new DefaultValidatecodeGenerator();
		LOG.info(validatecodeGenerator.generate(4));
	}
}
