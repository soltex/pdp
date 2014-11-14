/**
 * 
 */
package cn.com.innodev.pdp.business.sdk.adminservice.region.impl;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.innodev.pdp.business.sdk.adminservice.region.BatchImportResultBean;
import cn.com.innodev.pdp.business.sdk.adminservice.region.RegionImportException;
import cn.com.innodev.pdp.business.sdk.adminservice.region.RegionManager;
import cn.com.innodev.pdp.framework.bizcommon.CommonService;
import cn.com.innodev.pdp.framework.bizcommon.region.City;
import cn.com.innodev.pdp.framework.bizcommon.region.Province;
import cn.com.innodev.pdp.framework.services.AbstractPlatformServiceMgr;

import com.vanstone.business.ObjectDuplicateException;
import com.vanstone.common.MyAssert;
import com.vanstone.fs.FSFile;

/**
 * @author shipeng
 */
@Service("regionManager")
public class RegionManagerImpl extends AbstractPlatformServiceMgr implements RegionManager {

	/** */
	private static final long serialVersionUID = 3538441692425213063L;
	
	@Autowired
	private CommonService commonService;
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.business.sdk.adminservice.regionservice.RegionManager#batchImportRegions(com.vanstone.fs.FSFile)
	 */
	@Override
	public BatchImportResultBean batchImportRegions(FSFile fsFile) throws FileNotFoundException, IOException, RegionImportException {
		MyAssert.notNull(fsFile);
		HSSFWorkbook workbook = new HSSFWorkbook(fsFile.getInputStream());
		HSSFSheet sheet = workbook.getSheetAt(0);
		if (sheet == null) {
			throw new IllegalArgumentException();
		}
		BatchImportResultBean result = new BatchImportResultBean();
		int rows = sheet.getPhysicalNumberOfRows();
		if (rows <=1) {
			throw new RegionImportException();
		}
		String tempProvinceName = null;
		for (int rowNo=1;rowNo<rows;rowNo++) {
			HSSFRow hssfRow = sheet.getRow(rowNo);
			HSSFCell provinceNameHssfCell = hssfRow.getCell(0);
			HSSFCell cityNameHssfCell = hssfRow.getCell(1);
			String provinceName = provinceNameHssfCell.getStringCellValue();
			if (provinceName != null && !"".equals(provinceName)) {
				tempProvinceName = provinceName;
				Province province = new Province();
				province.setTitle(provinceName);
				try {
					commonService.addProvince(province);
				} catch (ObjectDuplicateException e) {
					result.addFailureName("省份名称 : 【" + tempProvinceName + "】重复");
				}
			}
			
			Province province = this.commonService.getProvinceByTitle(tempProvinceName);
			String cityName = cityNameHssfCell.getStringCellValue();
			City city = new City(province);
			city.setTitle(cityName);
			try {
				this.commonService.addCity(city);
			} catch (ObjectDuplicateException e) {
				result.addFailureName("城市名称：【" + cityName + "】重复");
			}
		}
		return result;
	}
	
}
