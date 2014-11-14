/**
 * 
 */
package cn.com.innodev.pdp.community.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import com.vanstone.business.ObjectDuplicateException;
import com.vanstone.centralserver.common.MyAssert;
import com.vanstone.weedfs.client.RequestResult;
import com.vanstone.weedfs.client.impl.WeedFSClient;

import cn.com.innodev.pdp.community.Community;
import cn.com.innodev.pdp.community.persistence.ComProjectStaffDOMapper;
import cn.com.innodev.pdp.community.persistence.object.ComProjectStaffDO;
import cn.com.innodev.pdp.community.services.CommunityService;
import cn.com.innodev.pdp.community.services.StaffService;
import cn.com.innodev.pdp.community.staff.Staff;
import cn.com.innodev.pdp.community.staff.StaffRole;
import cn.com.innodev.pdp.community.staff.StaffState;
import cn.com.innodev.pdp.framework.Constants;
import cn.com.innodev.pdp.framework.bizcommon.ImageBean;
import cn.com.innodev.pdp.framework.services.AbstractPlatformServiceMgr;

/**
 * @author shipeng
 */
@Service("staffService")
public class StaffServiceImpl extends AbstractPlatformServiceMgr implements StaffService {
	
	/** */
	private static final long serialVersionUID = 8482427003927949389L;
	
	private static Logger LOG = LoggerFactory.getLogger(StaffServiceImpl.class);
	
	@Autowired
	private ComProjectStaffDOMapper comProjectStaffDOMapper;
	@Autowired
	private CommunityService communityService;
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.community.services.CommunityService#addStaff(cn.com.innodev.pdp.community.staff.Staff)
	 */
	@Override
	public Staff addStaff(final Staff staff) throws ObjectDuplicateException {
		MyAssert.notNull(staff);
		ComProjectStaffDO comProjectStaffDO = this.comProjectStaffDOMapper.selectByAccountName(staff.getAccountName());
		if (comProjectStaffDO != null) {
			throw new ObjectDuplicateException();
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				staff.setSysInsertTime(new Date());
				staff.setSysUpdateTime(new Date());
				if (staff.getAccountPassword() == null || staff.getAccountPassword().equals("")) {
					staff.setAccountPassword(RandomStringUtils.random(Constants.DEFAULT_STAFF_PASSWORD_LENGTH));
				}
				ComProjectStaffDO model = BeanUtil.toComProjectStaffDO(staff);
				comProjectStaffDOMapper.insert(model);
				staff.setId(model.getId());
			}
		});
		return staff;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.community.services.CommunityService#updateStaffState(int, cn.com.innodev.pdp.community.staff.StaffState)
	 */
	@Override
	public Staff updateStaffState(final int staffId, final StaffState staffState) {
		Staff loadStaff = this.getStaff(staffId);
		if (loadStaff == null) {
			throw new IllegalArgumentException();
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				ComProjectStaffDO model = new ComProjectStaffDO();
				model.setId(staffId);
				model.setStaffState(staffState.getCode());
				model.setSysUpdateTime(new Date());
				comProjectStaffDOMapper.updateByPrimaryKeySelective(model);
			}
		});
		return this.getStaff(staffId);
	}

	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.community.services.CommunityService#updateStaffRole(int, cn.com.innodev.pdp.community.staff.StaffRole)
	 */
	@Override
	public Staff updateStaffRole(final int staffId, final StaffRole staffRole) {
		Staff loadStaff = this.getStaff(staffId);
		if (loadStaff == null) {
			throw new IllegalArgumentException();
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				ComProjectStaffDO model = new ComProjectStaffDO();
				model.setId(staffId);
				model.setStaffRole(staffRole.getCode());
				model.setSysUpdateTime(new Date());
				comProjectStaffDOMapper.updateByPrimaryKeySelective(model);
			}
		});
		return this.getStaff(staffId);
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.community.services.CommunityService#getStaff(int)
	 */
	@Override
	public Staff getStaff(int staffId) {
		ComProjectStaffDO model = this.comProjectStaffDOMapper.selectByPrimaryKey(staffId);
		if (model == null) {
			return null;
		}
		return BeanUtil.toStaff(model, communityService.getCommunity(model.getCommunityId()));
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.community.services.CommunityService#deleteStaff(int)
	 */
	@Override
	public void deleteStaff(final int staffId) {
		Staff loadStaff = this.getStaff(staffId);
		if (loadStaff == null) {
			throw new IllegalArgumentException();
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				comProjectStaffDOMapper.deleteByPrimaryKey(staffId);
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.community.services.CommunityService#getStaffByAccountName(java.lang.String)
	 */
	@Override
	public Staff getStaffByAccountName(String accountName) {
		MyAssert.hasText(accountName);
		ComProjectStaffDO model = this.comProjectStaffDOMapper.selectByAccountName(accountName);
		if (model == null) {
			return null;
		}
		return BeanUtil.toStaff(model, communityService.getCommunity(model.getCommunityId()));
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.community.services.CommunityService#updateStaffBaseInfo(cn.com.innodev.pdp.community.staff.Staff)
	 */
	@Override
	public Staff updateStaffBaseInfo(final Staff staff) {
		return null;
	}

	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.community.services.CommunityService#updateStaffHeaderImageBean(int, cn.com.innodev.pdp.framework.bizcommon.ImageBean)
	 */
	@Override
	public Staff updateStaffHeadImageBean(final int staffId, final ImageBean imageBean) {
		MyAssert.notNull(imageBean);
		Staff loadStaff = this.getStaff(staffId);
		if (loadStaff == null) {
			throw new IllegalArgumentException();
		}
		if (loadStaff.getHeadImageBean() != null) {
			this.deleteStaffHeadImageBean(staffId);
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				comProjectStaffDOMapper.updateHead(staffId, new Date(), imageBean.getWeedFile().getFileid(), imageBean.getWeedFile().getExtName(), 
						imageBean.getWidth(), imageBean.getHeight());
			}
		});
		return this.getStaff(staffId);
	}
	
	@Override
	public void deleteStaffHeadImageBean(final int staffId) {
		Staff loadStaff = this.getStaff(staffId);
		if (loadStaff == null) {
			throw new IllegalArgumentException();
		}
		if (loadStaff.getHeadImageBean() != null) {
			this.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
					comProjectStaffDOMapper.updateHead(staffId, new Date(), null, null, null, null);
				}
			});
			WeedFSClient weedFSClient = new WeedFSClient();
			RequestResult requestResult = weedFSClient.delete(loadStaff.getHeadImageBean().getWeedFile().getFileid());
			if (requestResult != null && !requestResult.isSuccess()) {
				LOG.error("DELETE WeedFS Failure, FileId [{}]", loadStaff.getHeadImageBean().getWeedFile().getFileid());
			}
		}
	}

	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.community.services.CommunityService#getStaffs(cn.com.innodev.pdp.community.Community, cn.com.innodev.pdp.community.staff.StaffRole, int, int)
	 */
	@Override
	public Collection<Staff> getStaffs(Community community, StaffRole staffRole, int offset, int limit) {
		MyAssert.notNull(community);
		Community loadCommunity = communityService.getCommunity(community.getId());
		if (loadCommunity == null) {
			throw new IllegalArgumentException();
		}
		List<ComProjectStaffDO> comProjectStaffDOs = this.comProjectStaffDOMapper.selectBy_CommunityId_StaffRole(community.getId(), staffRole != null?staffRole.getCode() : null, new RowBounds(offset, limit));
		if (comProjectStaffDOs == null || comProjectStaffDOs.size() <=0) {
			return null;
		}
		Collection<Staff> staffs = new ArrayList<Staff>();
		for (ComProjectStaffDO model : comProjectStaffDOs) {
			Staff staff = BeanUtil.toStaff(model, loadCommunity);
			staffs.add(staff);
		}
		return staffs;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.community.services.CommunityService#getTotalStaffs(cn.com.innodev.pdp.community.Community, cn.com.innodev.pdp.community.staff.StaffRole)
	 */
	@Override
	public int getTotalStaffs(Community community, StaffRole staffRole) {
		MyAssert.notNull(community);
		Community loadCommunity = communityService.getCommunity(community.getId());
		if (loadCommunity == null) {
			throw new IllegalArgumentException();
		}
		return this.comProjectStaffDOMapper.selectCountBy_CommunityId_StaffRole(community.getId(), staffRole != null?staffRole.getCode() : null);
	}

}
