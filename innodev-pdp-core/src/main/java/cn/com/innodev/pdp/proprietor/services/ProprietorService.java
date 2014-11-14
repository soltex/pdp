/**
 * 
 */
package cn.com.innodev.pdp.proprietor.services;

import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import cn.com.innodev.pdp.community.Community;
import cn.com.innodev.pdp.community.QA;
import cn.com.innodev.pdp.community.Room;
import cn.com.innodev.pdp.community.staff.Staff;
import cn.com.innodev.pdp.framework.bizcommon.ImageBean;
import cn.com.innodev.pdp.proprietor.Proprietor;
import cn.com.innodev.pdp.proprietor.ProprietorState;
import cn.com.innodev.pdp.proprietor.QAComment;
import cn.com.innodev.pdp.proprietor.RepairReport;
import cn.com.innodev.pdp.proprietor.RepairReportState;
import cn.com.innodev.pdp.proprietor.VisitorLog;

import com.vanstone.business.ObjectDuplicateException;
import com.vanstone.business.ObjectHasSubObjectException;

/**
 * 业主相关业务方法
 * @author shipeng
 */
public interface ProprietorService {
	
	public static final String SERVICE = "proprietorService";
	
	/**
	 * 通过Weixin OPENID 添加Proprietor
	 * @param weixinOpenId
	 * @param community
	 * @return
	 * @throws ObjectDuplicateException
	 */
	Proprietor addProprietor(String weixinOpenId, Community community) throws ObjectDuplicateException;
	
	/**
	 * 更新OpenId
	 * @param id
	 * @param weixinOpenId
	 * @return
	 */
	Proprietor updateWeixinOpenId(String id, String weixinOpenId);
	
	/**
	 * 授权手机号以及密码
	 * @param id
	 * @param mobile
	 * @param password
	 * @return
	 * @throws ObjectDuplicateException
	 */
	Proprietor authenticateMobile(String id, String mobile) throws ObjectDuplicateException;
	
	/**
	 * 更新手机号码
	 * @param id
	 * @param mobile
	 * @return
	 */
	Proprietor updateProprietorMobile(String id, String mobile) throws ObjectDuplicateException;
	
	/**
	 * 更新密码
	 * @param proprietorId
	 * @param newPassword
	 * @return
	 */
	Proprietor updateProprietorPassword(String proprietorId, String newPassword);
	
	/**
	 * 更新Room信息
	 * @param proprietorId
	 * @param rooms
	 * @return
	 */
	Proprietor updateRooms(String proprietorId, Collection<Room> rooms);
	
	/**
	 * 更新业主基本信息
	 * @param proprietor
	 * @return
	 * @throws ObjectDuplicateException
	 */
	Proprietor updateBaseProprietor(Proprietor proprietor);
	
	/**
	 * 删除业主信息
	 * @param ProprietorId
	 * @throws ObjectHasSubObjectException
	 */
	void deleteProprietor(String ProprietorId) throws ObjectHasSubObjectException;
	
	/**
	 * 强制删除业主信息
	 * @param ProprietorId
	 */
	void forceDeleteProprietor(String ProprietorId);
	
	/**
	 * 通过Id获取业主信息
	 * @param ProprietorId
	 * @return
	 */
	Proprietor getProprietor(String ProprietorId);
	
	/**
	 * 通过手机号获取
	 * @param mobile
	 * @return
	 */
	Proprietor getProprietorByMobile(String mobile);
	
	/**
	 * 通过OpenId获取
	 * @param weixinOpenId
	 * @return
	 */
	Proprietor getProprietorByWeixinOpenId(String weixinOpenId);
	
	/**
	 * 更新业主状态信息
	 * @param proprietorId
	 * @param proprietorState
	 * @return
	 */
	Proprietor updateProprietorState(String proprietorId, ProprietorState proprietorState);
	
	/**
	 * 删除头像
	 * @param proprietorId
	 * @return
	 */
	void deleteHeadImageBean(String proprietorId);
	
	/**
	 * 更新头像信息
	 * @param proprietorId
	 * @param imageBean
	 * @return
	 */
	void updateHeadImageBean(String proprietorId, ImageBean imageBean);
	
	/**
	 * 查询业主信息
	 * @param community
	 * @param key
	 * @param offset
	 * @param limit
	 * @return
	 */
	Collection<Proprietor> getProprietors(Community community, String key, int offset, int limit);
	
	/**
	 * 查询业主信息数量
	 * @param community
	 * @param key
	 * @return
	 */
	int getTotalProprietors(Community community, String key);
	
	/**
	 * 获取房间下的业主数量
	 * @param roomId
	 * @return
	 */
	int getTotalProprietorsByRoomId(int roomId);
	
	/**
	 * 通过Ids获取Proprietors
	 * @param proprietorIds
	 * @return
	 */
	Map<String, Proprietor> getProprietorsMap(Collection<String> proprietorIds);
	
	/**
	 * 添加QA评论
	 * @param qa
	 * @param fromProprietor
	 * @param toProprietor
	 * @param content
	 * @return
	 */
	QAComment addQAComment(QA qa, Proprietor fromProprietor, Proprietor toProprietor,String content);
	
	/**
	 * 删除QA 评论
	 * @param qaCommentId
	 */
	void deleteQAComment(String qaCommentId);
	
	/**
	 * 查看QA下评论信息
	 * @param qa
	 * @param offset
	 * @param limit
	 * @return
	 */
	Collection<QAComment> getQAComments(QA qa, int offset, int limit);
	
	/**
	 * 查看QA下评论信息
	 * @param qa
	 * @return
	 */
	int getTotalQAComments(QA qa);
	
	/**
	 * 查看社区下评论信息
	 * @param community
	 * @param offset
	 * @param limit
	 * @return
	 */
	Collection<QAComment> getQAComments(Community community, int offset, int limit);
	
	/**
	 * 查看社区下评论信息
	 * @param community
	 * @return
	 */
	int getTotalComments(Community community);
	
	/**
	 * 申请保修单
	 * @param community
	 * @param proprietor
	 * @return
	 */
	RepairReport applyRepairReport(Community community, Proprietor proprietor);
	
	/**
	 * 保存填写保修
	 * @param id
	 * @param repireLocation
	 * @param expectRairTime
	 * @param content
	 * @param files
	 * @return
	 */
	RepairReport saveRepairReport(String id, String repireLocation, Date expectRairTime, String content, Collection<File> files);
	
	/**
	 * 机构管理员处理保修单
	 * @param id
	 * @param staffHandler
	 * @param repairHander
	 * @param result
	 * @return
	 */
	RepairReport handleRepairReport(String id, Staff staffHandler, Staff repairHander, String result);
	
	/**
	 * 检索报修单
	 * @param community
	 * @param repairReportState
	 * @param key
	 * @param offset
	 * @param limit
	 * @return
	 */
	Collection<RepairReport> searchRepairReportsOfCommunity(Community community, RepairReportState repairReportState, String key, int offset, int limit);
	
	/**
	 * 检索报修单
	 * @param community
	 * @param repairReportState
	 * @param key
	 * @return
	 */
	int searchTotalRepairReportsOfCommunity(Community community, RepairReportState repairReportState, String key);
	
	/**
	 * 检索报修单
	 * @param proprietor
	 * @param repairReportState
	 * @param key
	 * @param offset
	 * @param limit
	 * @return
	 */
	Collection<RepairReport> searchRepairReportsOfProprietor(Proprietor proprietor, RepairReportState repairReportState, String key, int offset, int limit);
	
	/**
	 * 检索报修单
	 * @param proprietor
	 * @param repairReportState
	 * @param key
	 * @return
	 */
	int searchTotalRepairReportsOfProprietor(Proprietor proprietor, RepairReportState repairReportState, String key);
	
	/**
	 * 申请访客二维码Sence
	 * @param proprietor
	 * @return
	 */
	VisitorLog applyVisitorLog(Proprietor proprietor);
	
	/**
	 * 更新访客日志信息
	 * @param id
	 * @param accessorName
	 * @param accessPurpose
	 * @param expectLeaveTime
	 * @param imageBean 二维码文件,如为null，则不进行修改
	 * @return
	 * @throws QRSceneExpireException
	 * @throws QRValidatedException
	 */
	VisitorLog updateVisitorLog(int id, String accessorName, String accessPurpose, Date expectLeaveTime, ImageBean imageBean) throws QRSceneExpireException, QRValidatedException;
	
	/**
	 * 验证访客二维码
	 * @param scene
	 * @return
	 * @throws VisitorException
	 */
	VisitorLog validateVisitorLogByScene(int scene) throws QRSceneExpireException, QRValidatedException;
	
	/**
	 * @param proprietor
	 * @param startTime
	 * @param endTime
	 * @param offset
	 * @param limit
	 * @return
	 */
	Collection<VisitorLog> getVisitorLogs(Proprietor proprietor, Date startTime, Date endTime, int offset, int limit);
	
	/**
	 * @param proprietor
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	int getTotalVisitorLogs(Proprietor proprietor, Date startTime, Date endTime);
	
	/**
	 * @param proprietor
	 * @param startTime
	 * @param endTime
	 * @param key
	 * @param offset
	 * @param limit
	 * @return
	 */
	Collection<VisitorLog> getVisitorLogs(Proprietor proprietor, Date startTime, Date endTime, String key, int offset, int limit);
	
	/**
	 * @param proprietor
	 * @param startTime
	 * @param endTime
	 * @param key
	 * @return
	 */
	int getTotalVisitorLogs(Proprietor proprietor, Date startTime, Date endTime, String key);
	
}
