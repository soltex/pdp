/**
 * 
 */
package cn.com.innodev.example;

import java.io.IOException;

import org.junit.Test;

import com.vanstone.common.util.MD5Util;

/**
 * @author shipeng
 *
 */
public class MD5UtilTest {
	
	@Test
	public void testMD5() {
		String str = "1,23232323232";
		try {
			System.out.println(MD5Util.getHashCode(str));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
