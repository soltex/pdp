/**
 * 
 */
package cn.com.innodev.pdp.proprietor.services.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import cn.com.innodev.pdp.community.Community;
import cn.com.innodev.pdp.community.QA;
import cn.com.innodev.pdp.community.Room;
import cn.com.innodev.pdp.community.services.CommunityService;
import cn.com.innodev.pdp.community.services.QAService;
import cn.com.innodev.pdp.community.services.StaffService;
import cn.com.innodev.pdp.community.staff.Staff;
import cn.com.innodev.pdp.framework.bizcommon.ImageBean;
import cn.com.innodev.pdp.framework.services.AbstractPlatformServiceMgr;
import cn.com.innodev.pdp.proprietor.Proprietor;
import cn.com.innodev.pdp.proprietor.ProprietorState;
import cn.com.innodev.pdp.proprietor.QAComment;
import cn.com.innodev.pdp.proprietor.RepairReport;
import cn.com.innodev.pdp.proprietor.RepairReportState;
import cn.com.innodev.pdp.proprietor.VisitorLog;
import cn.com.innodev.pdp.proprietor.persistence.PropProprietorDOMapper;
import cn.com.innodev.pdp.proprietor.persistence.PropProprietorRoomRelDOMapper;
import cn.com.innodev.pdp.proprietor.persistence.PropQACommentDOMapper;
import cn.com.innodev.pdp.proprietor.persistence.object.PropProprietorDO;
import cn.com.innodev.pdp.proprietor.persistence.object.PropProprietorDOWithBLOBs;
import cn.com.innodev.pdp.proprietor.persistence.object.PropProprietorRoomRelDOKey;
import cn.com.innodev.pdp.proprietor.persistence.object.PropQACommentDO;
import cn.com.innodev.pdp.proprietor.services.ProprietorService;
import cn.com.innodev.pdp.proprietor.services.QRSceneExpireException;
import cn.com.innodev.pdp.proprietor.services.QRValidatedException;

import com.vanstone.business.MyAssert4Business;
import com.vanstone.business.ObjectDuplicateException;
import com.vanstone.business.ObjectHasSubObjectException;
import com.vanstone.business.def.IDsUtil;
import com.vanstone.centralserver.common.MyAssert;
import com.vanstone.dal.id.IDBuilder;
import com.vanstone.weedfs.client.RequestResult;
import com.vanstone.weedfs.client.impl.WeedFSClient;

/**
 * @author shipeng
 */
@Service("proprietorService")
public class ProprietorServiceImpl extends AbstractPlatformServiceMgr implements ProprietorService {

	/** */
	private static final long serialVersionUID = 868031225660354290L;
	
	private static Logger LOG = LoggerFactory.getLogger(ProprietorServiceImpl.class);
	
	@Autowired
	private PropProprietorDOMapper propProprietorDOMapper;
	@Autowired
	private PropProprietorRoomRelDOMapper propProprietorRoomRelDOMapper;
	@Autowired
	private PropQACommentDOMapper propQACommentDOMapper;
	@Autowired
	private CommunityService communityService;
	@Autowired
	private QAService qaService;
	@Autowired
	private StaffService staffService;
	
	@Override
	public Proprietor addProprietor(final String weixinOpenId, final Community community)throws ObjectDuplicateException {
		final PropProprietorDOWithBLOBs tempModel = this.propProprietorDOMapper.selectByOpenId(weixinOpenId);
		if (tempModel != null) {
			throw new ObjectDuplicateException();
		}
		String id = this.execute(new TransactionCallback<String>() {
			@Override
			public String doInTransaction(TransactionStatus arg0) {
				PropProprietorDOWithBLOBs model = new PropProprietorDOWithBLOBs();
				model.setId(IDBuilder.base58Uuid());
				model.setWeixinOpenid(weixinOpenId);
				model.setCommunityId(community.getId());
				model.setSysInsertTime(new Date());
				model.setSysUpdateTime(new Date());
				model.setPropState(ProprietorState.Weixin_Wait_Auth.getCode());
				propProprietorDOMapper.insert(model);
				return model.getId();
			}
		});
		return this.getProprietor(id);
	}
	
	@Override
	public Proprietor updateWeixinOpenId(final String id, final String weixinOpenId) {
		MyAssert.hasText(id);
		MyAssert.hasText(weixinOpenId);
		Proprietor proprietor = this.getProprietor(id);
		if (proprietor == null) {
			throw new IllegalArgumentException();
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				PropProprietorDOWithBLOBs model = new PropProprietorDOWithBLOBs();
				model.setId(id);
				model.setWeixinOpenid(weixinOpenId);
				model.setSysUpdateTime(new Date());
				propProprietorDOMapper.updateByPrimaryKeySelective(model);
			}
		});
		return this.getProprietor(id);
	}
	
	@Override
	public Proprietor updateProprietorMobile(final String id, final String mobile) throws ObjectDuplicateException {
		Proprietor loadProprietor = this.getProprietor(id);
		if (loadProprietor == null) {
			throw new IllegalArgumentException();
		}
		PropProprietorDOWithBLOBs tempModel = this.propProprietorDOMapper.selectByMobile_Notself(id, mobile);
		if (tempModel != null) {
			throw new ObjectDuplicateException();
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				PropProprietorDOWithBLOBs model = new PropProprietorDOWithBLOBs();
				model.setId(IDBuilder.base58Uuid());
				model.setMobile(mobile);
				model.setSysInsertTime(new Date());
				model.setSysUpdateTime(new Date());
				propProprietorDOMapper.updateByPrimaryKeySelective(model);
			}
		});
		return this.getProprietor(id);
	}
	
	@Override
	public Proprietor authenticateMobile(final String id, final String mobile) throws ObjectDuplicateException {
		Proprietor loadProprietor = this.getProprietor(id);
		if (loadProprietor == null) {
			throw new IllegalArgumentException();
		}
		PropProprietorDOWithBLOBs tempModel = this.propProprietorDOMapper.selectByMobile_Notself(id, mobile);
		if (tempModel != null) {
			throw new ObjectDuplicateException();
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				PropProprietorDOWithBLOBs model = new PropProprietorDOWithBLOBs();
				model.setId(id);
				model.setMobile(mobile);
				model.setPropState(ProprietorState.Mobile_Email_Auth.getCode());
				model.setSysUpdateTime(new Date());
				propProprietorDOMapper.updateByPrimaryKeySelective(model);
			}
		});
		return this.getProprietor(id);
	}
	
	@Override
	public Proprietor updateRooms(final String proprietorId, final Collection<Room> rooms) {
		MyAssert.hasText(proprietorId);
		MyAssert.notEmpty(rooms);
		
		Proprietor loadProprietor = this.getProprietor(proprietorId);
		if (loadProprietor == null) {
			throw new IllegalArgumentException();
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				//清理关联
				propProprietorRoomRelDOMapper.deleteByProprietorId(proprietorId);
				//批量写入
				for (Room room : rooms) {
					PropProprietorRoomRelDOKey key = new PropProprietorRoomRelDOKey();
					key.setProprietorId(proprietorId);
					key.setRoomId(room.getId());
					propProprietorRoomRelDOMapper.insert(key);
				}
				//ROOM_IDS
				StringBuffer sb = new StringBuffer();
				for (Room room : rooms) {
					sb.append(room.getId()).append(",");
				}
				PropProprietorDOWithBLOBs tempModel = new PropProprietorDOWithBLOBs();
				tempModel.setId(proprietorId);
				tempModel.setRoomids(sb.toString());
				tempModel.setSysUpdateTime(new Date());
				propProprietorDOMapper.updateByPrimaryKeySelective(tempModel);
				
				//写入数量
				for (Room room : rooms) {
					int count = propProprietorRoomRelDOMapper.selectCountByRoomId(room.getId());
					communityService.updateRoomProprietorCount(room.getId(), count);
				}
			}
		});
		return this.getProprietor(proprietorId);
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.proprietor.services.ProprietorService#updateBaseProprietor(cn.com.innodev.pdp.proprietor.Proprietor)
	 */
	@Override
	public Proprietor updateBaseProprietor(final Proprietor proprietor) {
		final Proprietor loadProprietor = this.getProprietor(proprietor.getId());
		if (loadProprietor == null) {
			throw new IllegalArgumentException();
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				loadProprietor.setEmail(proprietor.getEmail());
				loadProprietor.setFullName(proprietor.getFullName());
				loadProprietor.setNickName(proprietor.getNickName());
				loadProprietor.setGender(proprietor.getGender());
				loadProprietor.setProfession(proprietor.getProfession());
				loadProprietor.setInterest(proprietor.getInterest());
				loadProprietor.setSysUpdateTime(new Date());
				PropProprietorDOWithBLOBs model = BeanUtil.toPropProprietorDO(loadProprietor);
				propProprietorDOMapper.updateByPrimaryKeyWithBLOBs(model);
			}
		});
		return loadProprietor;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.proprietor.services.ProprietorService#deleteProprietor(java.lang.String)
	 */
	@Override
	public void deleteProprietor(String ProprietorId) throws ObjectHasSubObjectException {
		//TODD 暂时不实现
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.proprietor.services.ProprietorService#forceDeleteProprietor(java.lang.String)
	 */
	@Override
	public void forceDeleteProprietor(String ProprietorId) {
		//TODD 暂时不实现
		
	}

	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.proprietor.services.ProprietorService#getProprietor(java.lang.String)
	 */
	@Override
	public Proprietor getProprietor(String proprietorId) {
		PropProprietorDOWithBLOBs propProprietorDOWithBLOBs = this.propProprietorDOMapper.selectByPrimaryKey(proprietorId);
		if (propProprietorDOWithBLOBs == null) {
			return null;
		}
		Proprietor proprietor = BeanUtil.toProprietor(propProprietorDOWithBLOBs, this.communityService.getCommunity(propProprietorDOWithBLOBs.getCommunityId()));
		if (propProprietorDOWithBLOBs.getRoomids() != null && !"".equals(propProprietorDOWithBLOBs.getRoomids())) {
			Collection<Integer> ids = IDsUtil.commaString2IntegerCollection(propProprietorDOWithBLOBs.getRoomids());
			Map<Integer, Room> roomMap = communityService.getRooms(ids);
			for (Map.Entry<Integer, Room> entry : roomMap.entrySet()) {
				proprietor.addRoom(entry.getValue());
			}
		}
		return proprietor;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.proprietor.services.ProprietorService#getProprietorByMobile(java.lang.String)
	 */
	@Override
	public Proprietor getProprietorByMobile(String mobile) {
		MyAssert.hasText(mobile);
		PropProprietorDOWithBLOBs model = this.propProprietorDOMapper.selectByMobile(mobile);
		if (model == null) {
			return null;
		}
		return this.getProprietor(model.getId());
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.proprietor.services.ProprietorService#getProprietorByWeixinOpenId(java.lang.String)
	 */
	@Override
	public Proprietor getProprietorByWeixinOpenId(String weixinOpenId) {
		MyAssert.hasText(weixinOpenId);
		PropProprietorDOWithBLOBs model = this.propProprietorDOMapper.selectByOpenId(weixinOpenId);
		if (model == null) {
			return null;
		}
		return this.getProprietor(model.getId());
	}
	
	@Override
	public Proprietor updateProprietorState(final String proprietorId, final ProprietorState proprietorState) {
		Proprietor loadProprietor = this.getProprietor(proprietorId);
		if (loadProprietor == null) {
			throw new IllegalArgumentException();
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				PropProprietorDOWithBLOBs model = new PropProprietorDOWithBLOBs();
				model.setId(proprietorId);
				model.setPropState(proprietorState.getCode());
				model.setSysUpdateTime(new Date());
				propProprietorDOMapper.updateByPrimaryKeySelective(model);
			}
		});
		return this.getProprietor(proprietorId);
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.proprietor.services.ProprietorService#updateProprietorPassword(java.lang.String, java.lang.String)
	 */
	@Override
	public Proprietor updateProprietorPassword(final String proprietorId, final String newPassword) {
		Proprietor loadProprietor = this.getProprietor(proprietorId);
		if (loadProprietor == null) {
			throw new IllegalArgumentException();
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				PropProprietorDOWithBLOBs propProprietorDO = new PropProprietorDOWithBLOBs();
				propProprietorDO.setSysUpdateTime(new Date());
				propProprietorDO.setId(proprietorId);
				propProprietorDO.setPwd(newPassword);
				propProprietorDOMapper.updateByPrimaryKeySelective(propProprietorDO);
			}
		});
		return this.getProprietor(proprietorId);
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.proprietor.services.ProprietorService#deleteHeadImageBean(java.lang.String)
	 */
	@Override
	public void deleteHeadImageBean(final String proprietorId) {
		final Proprietor loadProprietor = this.getProprietor(proprietorId);
		if (loadProprietor == null) {
			throw new IllegalArgumentException();
		}
		if (loadProprietor.getHeadImageBean() != null) {
			this.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
					PropProprietorDO model = new PropProprietorDO();
					model.setSysUpdateTime(new Date());
					model.setId(proprietorId);
					model.setHeaderFileExt(null);
					model.setHeaderFileId(null);
					model.setHeaderFileWidth(null);
					model.setHeaderFileHeight(null);
				}
			});
			WeedFSClient weedFSClient = new WeedFSClient();
			RequestResult requestResult = weedFSClient.delete(loadProprietor.getHeadImageBean().getWeedFile().getFileid());
			if (requestResult != null && !requestResult.isSuccess()) {
				LOG.error("DELETE WeedFS Failure , FSFile ID [{}]" , proprietorId);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.proprietor.services.ProprietorService#updateHeadImageBean(java.lang.String, cn.com.innodev.pdp.framework.bizcommon.ImageBean)
	 */
	@Override
	public void updateHeadImageBean(final String proprietorId, final ImageBean imageBean) {
		final Proprietor loadProprietor = this.getProprietor(proprietorId);
		if (loadProprietor == null) {
			throw new IllegalArgumentException();
		}
		
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				propProprietorDOMapper.updateHead(proprietorId, new Date(), imageBean.getWeedFile().getFileid(), 
						imageBean.getWeedFile().getExtName(), imageBean.getWidth(), imageBean.getHeight());
			}
		});
		
		if (loadProprietor.getHeadImageBean() != null) {
			WeedFSClient weedFSClient = new WeedFSClient();
			ImageBean loadImageBean = loadProprietor.getHeadImageBean();
			RequestResult requestResult = weedFSClient.delete(loadImageBean.getWeedFile().getFileid());
			if (requestResult != null && !requestResult.isSuccess()) {
				LOG.error("DELETE Old WeedFS Failure , FSFile ID [{}]" , loadImageBean.getWeedFile().getFileid());
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.proprietor.services.ProprietorService#getProprietors(cn.com.innodev.pdp.community.Community, java.lang.String, int, int)
	 */
	@Override
	public Collection<Proprietor> getProprietors(Community community, String key, int offset, int limit) {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.proprietor.services.ProprietorService#getTotalProprietors(cn.com.innodev.pdp.community.Community, java.lang.String)
	 */
	@Override
	public int getTotalProprietors(Community community, String key) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.proprietor.services.ProprietorService#getProprietorsMap(java.util.Collection)
	 */
	@Override
	public Map<String, Proprietor> getProprietorsMap(Collection<String> proprietorIds) {
		if (proprietorIds == null || proprietorIds.size() <=0) {
			return null;
		}
		List<PropProprietorDOWithBLOBs> models = this.propProprietorDOMapper.selectByIds(proprietorIds);
		if (models == null || models.size() <=0) {
			return null;
		}
		Collection<Integer> roomids = new HashSet<Integer>();
		for (PropProprietorDOWithBLOBs model : models) {
			if (model.getRoomids() != null && !model.getRoomids().equals("")) {
				Collection<Integer> tmpids = IDsUtil.commaString2IntegerCollection(model.getRoomids());
				roomids.addAll(tmpids);
			}
		}
		Map<Integer, Room> roomMap = this.communityService.getRooms(roomids);
		Map<String, Proprietor> dataMap = new LinkedHashMap<String, Proprietor>();
		for (PropProprietorDOWithBLOBs model : models) {
			Proprietor proprietor = BeanUtil.toProprietor(model, this.communityService.getCommunity(model.getCommunityId()));
			if (model.getRoomids() != null && !"".equals(model.getRoomids())) {
				Collection<Integer> tmpids = IDsUtil.commaString2IntegerCollection(model.getRoomids());
				for (Integer tmpid : tmpids) {
					Room room = roomMap.get(tmpid);
					proprietor.addRoom(room);
				}
			}
			dataMap.put(proprietor.getId(), proprietor);
		}
		return dataMap.size() >0 ? dataMap : null;
	}
	
	@Override
	public QAComment addQAComment(final QA qa, final Proprietor fromProprietor, final Proprietor toProprietor, final String content) {
		MyAssert4Business.objectInitialized(qa);
		MyAssert4Business.objectInitialized(fromProprietor);
		MyAssert4Business.objectInitialized(toProprietor);
		MyAssert4Business.hasText(content);
		final QA loadQa = this.qaService.getQA(qa.getId());
		if (loadQa == null) {
			throw new IllegalArgumentException();
		}
		return this.execute(new TransactionCallback<QAComment>() {
			@Override
			public QAComment doInTransaction(TransactionStatus arg0) {
				PropQACommentDO model = new PropQACommentDO();
				model.setContent(content);
				model.setFromPropId(fromProprietor.getId());
				model.setToPropId(toProprietor.getId());
				model.setSysInsertTime(new Date());
				model.setSysUpdateTime(new Date());
				model.setQaId(qa.getId());
				model.setId(IDBuilder.base58Uuid());
				propQACommentDOMapper.insert(model);
				QAComment comment = new QAComment(loadQa, fromProprietor, toProprietor);
				comment.setContent(content);
				comment.setId(model.getId());
				comment.setSysInsertTime(model.getSysInsertTime());
				comment.setSysUpdateTime(model.getSysUpdateTime());
				return comment;
			}
		});
	}
	
	@Override
	public void deleteQAComment(final String qaCommentId) {
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				propQACommentDOMapper.deleteByPrimaryKey(qaCommentId);
			}
		});
	}
	
	@Override
	public Collection<QAComment> getQAComments(QA qa, int offset, int limit) {
		MyAssert4Business.objectInitialized(qa);
		QA loadQa = this.qaService.getQA(qa.getId());
		if (loadQa == null) {
			throw new IllegalArgumentException();
		}
		List<PropQACommentDO> propQACommentDOs = this.propQACommentDOMapper.selectByQaId(qa.getId(), new RowBounds(offset, limit));
		if (propQACommentDOs == null || propQACommentDOs.size() <=0) {
			return null;
		}
		Set<String> proprietorIds = new HashSet<String>();
		for (PropQACommentDO qaCommentDO : propQACommentDOs) {
			proprietorIds.add(qaCommentDO.getFromPropId());
			proprietorIds.add(qaCommentDO.getToPropId());
		}
		Map<String, Proprietor> proprietorMap = this.getProprietorsMap(proprietorIds);
		Collection<QAComment> qaComments = new ArrayList<QAComment>();
		for (PropQACommentDO qaCommentDO : propQACommentDOs) { 
			QAComment qaComment = new QAComment(loadQa, proprietorMap.get(qaCommentDO.getFromPropId()), proprietorMap.get(qaCommentDO.getToPropId()));
			qaComment.setContent(qaCommentDO.getContent());
			qaComment.setId(qaCommentDO.getId());
			qaComment.setSysInsertTime(qaCommentDO.getSysInsertTime());
			qaComment.setSysUpdateTime(qaCommentDO.getSysUpdateTime());
			qaComments.add(qaComment);
		}
		return qaComments;
	}
	
	@Override
	public int getTotalQAComments(QA qa) {
		MyAssert4Business.objectInitialized(qa);
		QA loadQa = this.qaService.getQA(qa.getId());
		if (loadQa == null) {
			throw new IllegalArgumentException();
		}
		return this.propQACommentDOMapper.selectCountByQaId(loadQa.getId());
	}
	
	@Override
	public Collection<QAComment> getQAComments(Community community, int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getTotalComments(Community community) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalProprietorsByRoomId(int roomId) {
		return this.propProprietorRoomRelDOMapper.selectByRoomId(roomId);
	}

	@Override
	public RepairReport applyRepairReport(Community community, Proprietor proprietor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RepairReport saveRepairReport(String id, String repireLocation, Date expectRairTime, String content,
			Collection<File> files) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RepairReport handleRepairReport(String id, Staff staffHandler, Staff repairHander, String result) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<RepairReport> searchRepairReportsOfCommunity(Community community,
			RepairReportState repairReportState, String key, int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int searchTotalRepairReportsOfCommunity(Community community, RepairReportState repairReportState, String key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<RepairReport> searchRepairReportsOfProprietor(Proprietor proprietor,
			RepairReportState repairReportState, String key, int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int searchTotalRepairReportsOfProprietor(Proprietor proprietor, RepairReportState repairReportState,
			String key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public VisitorLog applyVisitorLog(Proprietor proprietor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VisitorLog updateVisitorLog(int id, String accessorName, String accessPurpose, Date expectLeaveTime,
			ImageBean imageBean) throws QRSceneExpireException, QRValidatedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VisitorLog validateVisitorLogByScene(int scene) throws QRSceneExpireException, QRValidatedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<VisitorLog> getVisitorLogs(Proprietor proprietor, Date startTime, Date endTime, int offset,
			int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalVisitorLogs(Proprietor proprietor, Date startTime, Date endTime) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<VisitorLog> getVisitorLogs(Proprietor proprietor, Date startTime, Date endTime, String key,
			int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalVisitorLogs(Proprietor proprietor, Date startTime, Date endTime, String key) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
