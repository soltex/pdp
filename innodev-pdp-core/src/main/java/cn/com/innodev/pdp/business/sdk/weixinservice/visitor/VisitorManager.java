/**
 * 
 */
package cn.com.innodev.pdp.business.sdk.weixinservice.visitor;

import java.util.Date;

import cn.com.innodev.pdp.proprietor.VisitorLog;
import cn.com.innodev.pdp.proprietor.services.QRSceneExpireException;
import cn.com.innodev.pdp.proprietor.services.QRValidatedException;

/**
 * 访客业务管理
 * @author shipeng
 */
public interface VisitorManager {
	
	/**
	 * 完成访客二维码信息填写
	 * 生成微信二维码文件，并上传到WeedFS
	 * 通过微信OpenId发送访客二维码图片消息
	 * @param id
	 * @param accessorName
	 * @param accessPurpose
	 * @param expectLeaveTime
	 * @return
	 */
	VisitorLog finishVisitorLog(int id, String accessorName, String accessPurpose, Date expectLeaveTime) throws QRSceneExpireException, QRValidatedException;
	
}
