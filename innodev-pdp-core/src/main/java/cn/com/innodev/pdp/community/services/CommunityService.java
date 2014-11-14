/**
 * 
 */
package cn.com.innodev.pdp.community.services;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import cn.com.innodev.pdp.community.AlbumType;
import cn.com.innodev.pdp.community.Building;
import cn.com.innodev.pdp.community.Community;
import cn.com.innodev.pdp.community.CommunityImage;
import cn.com.innodev.pdp.community.ProjectCompany;
import cn.com.innodev.pdp.community.Room;
import cn.com.innodev.pdp.framework.bizcommon.ImageBean;

import com.vanstone.business.ObjectDuplicateException;
import com.vanstone.business.ObjectHasSubObjectException;

/**
 * 社区业务方法
 * @author shipeng
 */
public interface CommunityService {
	
	public static final String SERVICE = "communityService";
	
	/**
	 * 新建社区信息,并创建默认账号
	 * @param community
	 * @param projectCompany
	 * @return
	 * @throws ObjectDuplicateException
	 */
	Community addCommunity(Community community, ProjectCompany projectCompany) throws ObjectDuplicateException;
	
	/**
	 * 删除社区信息
	 * @param id
	 * @throws ObjectHasSubObjectException 旗下存在业主信息,抛出异常
	 */
	void deleteCommunity(String id) throws ObjectHasSubObjectException;
	
	/**
	 * 更新社区基本信息
	 * @param baseInfoCommunity
	 * @return
	 */
	Community updateCommunityBaseInfo(Community baseInfoCommunity);
	
	/**
	 * 删除二维码图片
	 * @param communityId
	 * @return
	 */
	Community deleteQRImage(String communityId);
	
	/**
	 * 更新二维码图片信息
	 * @param communityId
	 * @param imageBean
	 * @return
	 */
	void updateQRImage(String communityId, ImageBean imageBean);
	
	/**
	 * 查看社区列表信息,key 可检索社区名称，社区介绍，社区地址，城市名称，省份名称
	 * @param offset
	 * @param limit
	 * @return
	 */
	Collection<Community> getCommunities(String key, int offset, int limit);
	
	/**
	 * 查看社区数量
	 * @param key
	 * @return
	 */
	int getTotalCommunities(String key);
	
	/**
	 * 通过ID获取社区详细信息,包含相册信息.,员工信息, 工程公司信息
	 * @param communityId
	 * @return
	 */
	Community getCommunity(String communityId);
	
	/**
	 * 获取工程公司信息
	 * @param communityId
	 * @return
	 */
	ProjectCompany getProjectCompany(String communityId);
	
	/**
	 * 更新工程公司基本信息
	 * @param communityId
	 * @param name
	 * @return
	 */
	void updateProjectCompany(String communityId, String name, String email);
	
	/**
	 * 删除工程公司Logo图片信息
	 * @param communityId
	 */
	void deleteProjectCompanyLogoInfo(String communityId);
	
	/**
	 * 更新工程公司Logo图片信息
	 * @param communityId
	 * @param imageBean
	 * @return
	 */
	void updateProjectCompanyLogoInfo(String communityId, ImageBean imageBean);
	
	/**
	 * 添加相册
	 * @param communityAlbum
	 * @return
	 */
	CommunityImage addCommunityImage(Community community, CommunityImage communityImage);
	
	/**
	 * 更新相册基本信息
	 * @param communityAlbum
	 * @return
	 */
	void updateCommunityImage(CommunityImage communityImage);
	
	/**
	 * 删除相册信息
	 * @param albumId
	 * @return
	 */
	void deleteCommunityImage(String imageId);
	
	/**
	 * 获取社区图片信息
	 * @param imageId
	 * @return
	 */
	CommunityImage getCommunityImage(String imageId);
	
	/**
	 * 获取社区相册列表
	 * @param communityId
	 * @return
	 */
	Map<AlbumType, Collection<CommunityImage>> getCommunityImages(String communityId);
	
	/**
	 * 添加building
	 * @param buildNo
	 * @param content
	 * @param roomNos
	 * @return
	 * @throws ObjectDuplicateException
	 */
	Building addBuilding(Community community, String buildNo, String content, Set<String> roomNos) throws ObjectDuplicateException;
	
	/**
	 * 更新building基本信息
	 * @param buildingId
	 * @param buildNo
	 * @param content
	 * @return
	 * @throws ObjectDuplicateException
	 */
	Building updateBaseBuilding(int buildingId, String buildNo, String content) throws ObjectDuplicateException;
	
	/**
	 * 删除building
	 * @param building
	 * @throws ObjectHasSubObjectException
	 */
	void deleteBuilding(int building) throws ObjectHasSubObjectException;
	
	/**
	 * 获取building信息
	 * @param buildingId
	 * @return
	 */
	Building getBuilding(int buildingId);
	
	/**
	 * 获取楼号以及房间号映射关系
	 * @param communityId
	 * @return
	 */
	Map<Building, Collection<Room>> getBuildingDataMap(String communityId);
	
	/**
	 * 添加房间号
	 * @param buildingId
	 * @param roomNo
	 * @return
	 * @throws ObjectDuplicateException
	 */
	Room addRoom(int buildingId, String roomNo) throws ObjectDuplicateException;
	
	/**
	 * 更新房间信息
	 * @param roomId
	 * @param roomNo
	 * @return
	 * @throws ObjectDuplicateException
	 */
	void updateRoom(int roomId, String roomNo) throws ObjectDuplicateException;
	
	/**
	 * 删除房间信息
	 * @param roomid
	 * @throws ObjectHasSubObjectException
	 */
	void deleteRoom(int roomid) throws ObjectHasSubObjectException;
	
	/**
	 * 获取Room对象信息
	 * @param roomid
	 * @return
	 */
	Room getRoom(int roomid);
	
	/**
	 * 通过Ids获取Rooms Map
	 * @param ids
	 * @return
	 */
	Map<Integer, Room> getRooms(Collection<Integer> ids);
	
	/**
	 * 更新房间下
	 * @param roomid
	 * @param count
	 */
	void updateRoomProprietorCount(int roomid, int count);
	
	/**
	 * 将Community缓冲失效
	 * @param communityId
	 */
	void expireCommunityCache(String communityId);
	
	/**
	 * 房间号缓冲失效
	 * @param roomId
	 */
	void expireRoomCache(int roomId);
	
}
