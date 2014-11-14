/**
 * 
 */
package cn.com.innodev.pdp.gm;

import java.io.File;

import org.junit.Test;

import com.vanstone.gm.GMHandlerFactory;
import com.vanstone.gm.IGMImageHandler;

/**
 * @author shipeng
 *
 */
public class GMTest {

	@Test
	public void testScaleImage() {
		IGMImageHandler handler = GMHandlerFactory.getGMImageHandler();
		handler.scaleImage(new File("/var/3.jpg"), new File("/var/4.jpg"), 200, 200);
	}
	
}
