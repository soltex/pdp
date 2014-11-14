/**
 * 
 */
package cn.com.innodev.pdp.weedfs;

import java.io.File;

import org.junit.Test;

import com.google.gson.Gson;
import com.vanstone.business.serialize.GsonCreator;
import com.vanstone.common.util.random.RandomString;
import com.vanstone.weedfs.client.RequestResult;
import com.vanstone.weedfs.client.impl.WeedFSClient;

/**
 * @author shipeng
 */
public class WeedFSTest {
	
	@Test
	public void testUploadWeedFS() {
		WeedFSClient weedFsClient = new WeedFSClient();
		File dir = new File("/var/aiyutian/tmp1");
		File[] files = dir.listFiles();
		for (File file : files) {
			RequestResult requestResult = weedFsClient.upload(file);
			System.out.println(requestResult.getFid());
		}
	}
	
	@Test
	public void testDeleteWeedFile() {
		String fileid = "6,0e8ca4efe40b08";
		WeedFSClient weedFSClient = new WeedFSClient();
		RequestResult requestResult = weedFSClient.delete(fileid);
		Gson gson = GsonCreator.createPretty();
		System.out.println(gson.toJson(requestResult));
	}
	
	@Test
	public void testGeneratePassword() {
		for (int i=0;i<100;i++) {
			System.out.println(RandomString.randomString(4));
		}
	}
}
