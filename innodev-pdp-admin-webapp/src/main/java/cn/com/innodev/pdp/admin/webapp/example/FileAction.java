/**
 * 
 */
package cn.com.innodev.pdp.admin.webapp.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.vanstone.fs.FSException;
import com.vanstone.fs.FSFile;
import com.vanstone.fs.FSType;
import com.vanstone.webframework.utils.FSManagerExt;
import com.vanstone.weedfs.client.RequestResult;
import com.vanstone.weedfs.client.impl.WeedFSClient;

import cn.com.innodev.pdp.framework.web.DWZAjaxObject;
import cn.com.innodev.pdp.framework.web.DWZAjaxObject.DWZStatusCode;

/**
 * 
 * @author shipeng
 */
@Controller
@RequestMapping("")
public class FileAction {
	
	@RequestMapping("/uploadfiles")
	public String uploadFiles(@ModelAttribute("uploadfilesForm")UploadFilesForm form) {
		return "/example/uploadfiles";
	}
	
	@RequestMapping("/uploadfiles-action")
	@ResponseBody
	public DWZAjaxObject uploadfilesAction(@ModelAttribute("uploadfilesForm")UploadFilesForm form) {
		MultipartFile[] multipartFiles = form.getUploadFiles();
		if (multipartFiles == null || multipartFiles.length <=0) {
			DWZAjaxObject object = DWZAjaxObject.create(DWZStatusCode.Error, "请选择文件");
			return object;
		}
		WeedFSClient weedFSClient = new WeedFSClient();
		StringBuffer sb = new StringBuffer();
		for (MultipartFile multipartFile : multipartFiles) {
			try {
				FSFile fsFile = FSManagerExt.getInstance().uploadBySpring(multipartFile, FSType.Temporary);
				RequestResult requestResult = weedFSClient.upload(fsFile.getFile());
				sb.append(fsFile.getFileid()).append("<br />").append(requestResult.getFid());
			} catch (FSException e) {
				e.printStackTrace();
			}
		}
		DWZAjaxObject object = DWZAjaxObject.createSuccessObject("上传成功，共上传" + sb.toString());
		return object;
	}
}
