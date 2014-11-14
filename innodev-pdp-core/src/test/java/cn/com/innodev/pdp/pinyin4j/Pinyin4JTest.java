/**
 * 
 */
package cn.com.innodev.pdp.pinyin4j;

import org.junit.Test;

import com.vanstone.common.util.PinyinUtil;

/**
 * Pinyin4JTest
 * @author shipeng
 */
public class Pinyin4JTest {
	
	@Test
	public void testPinyin4J() {
		String fullname = "方堃";
		System.out.println(PinyinUtil.cnstr2pinyinstr(fullname));
	}
	
}
