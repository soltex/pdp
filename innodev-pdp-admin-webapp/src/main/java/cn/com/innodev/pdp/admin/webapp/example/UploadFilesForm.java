/**
 * 
 */
package cn.com.innodev.pdp.admin.webapp.example;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author shipeng
 */
public class UploadFilesForm {
	
	private MultipartFile[] uploadFiles;

	public MultipartFile[] getUploadFiles() {
		return uploadFiles;
	}

	public void setUploadFiles(MultipartFile[] uploadFiles) {
		this.uploadFiles = uploadFiles;
	}
	
}
