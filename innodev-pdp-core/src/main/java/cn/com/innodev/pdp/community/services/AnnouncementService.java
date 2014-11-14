/**
 * 
 */
package cn.com.innodev.pdp.community.services;

import java.util.Collection;

import cn.com.innodev.pdp.community.Announcement;
import cn.com.innodev.pdp.community.Community;

/**
 * @author shipeng
 *
 */
public interface AnnouncementService {
	
	public static final String SERVICE = "announcementService";
	
	/**
	 * 添加社区公告
	 * @param announcement
	 * @return
	 */
	Announcement addAnnouncement(Announcement announcement);
	
	/**
	 * 更新公告信息
	 * @param announcement
	 * @return
	 */
	Announcement updateAnnouncement(Announcement announcement);
	
	/**
	 * 删除公告
	 * @param announcementId
	 */
	void deleteAnnouncement(String announcementId);
	
	/**
	 * 获取公告信息
	 * @param announcementId
	 * @return
	 */
	Announcement getAnnouncement(String announcementId);
	
	/**
	 * 获取公告列表
	 * @param community
	 * @param offset
	 * @param limit
	 * @return
	 */
	Collection<Announcement> getAnnouncements(Community community, int offset, int limit);
	
	/**
	 * 获取公告列表数量
	 * @param community
	 * @return
	 */
	int getTotalAnnouncements(Community community);
	
}
