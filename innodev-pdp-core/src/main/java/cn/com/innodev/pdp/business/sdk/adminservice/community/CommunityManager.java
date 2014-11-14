/**
 * 
 */
package cn.com.innodev.pdp.business.sdk.adminservice.community;

import cn.com.innodev.pdp.community.Community;
import cn.com.innodev.pdp.framework.bizcommon.ImageFormatIncorrentException;
import cn.com.innodev.pdp.framework.bizcommon.region.City;

import com.vanstone.business.ObjectDuplicateException;
import com.vanstone.fs.FSFile;

/**
 * @author shipeng
 */
public interface CommunityManager {
	
	/**
	 * 新建社区
	 * 处理图片信息
	 * 新建默认管理员账号
	 * 发送通知邮件通知提醒功能
	 * @param communityId
	 * @param communityName
	 * @param longitude
	 * @param latitude
	 * @param city
	 * @param qrOfCommunityFsFile
	 * @param companyName
	 * @param notificationMail
	 * @return
	 * @throws ObjectDuplicateException 社区id重复
	 * @throws ImageFormatIncorrentException 图片格式错误
	 */
	Community newCommunity(String communityId, String communityName, Double longitude, Double latitude, 
			City city, FSFile qrOfCommunityFsFile, String companyName, String notificationMail) throws ObjectDuplicateException, ImageFormatIncorrentException;
	
	/**
	 * 更新社区二维码图片
	 * @param communitId
	 * @param qrOfCommunityFsFile
	 * @throws ImageFormatIncorrentException
	 */
	void updateCommunityQRFsFile(String communitId, FSFile qrOfCommunityFsFile) throws ImageFormatIncorrentException;
	
}
