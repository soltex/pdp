/**
 * 
 */
package cn.com.innodev.pdp.business.sdk.adminservice.region;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.vanstone.fs.FSFile;

/**
 * 区域数据开发包
 * @author shipeng
 */
public interface RegionManager {
	
	public static final String SERVICE = "regionManager";
	
	/**
	 * 批量导入区域数据
	 * @param fsFile
	 * @return
	 */
	BatchImportResultBean batchImportRegions(FSFile fsFile) throws FileNotFoundException, IOException, RegionImportException ;
	
}
