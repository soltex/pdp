/**
 * 
 */
package cn.com.innodev.pdp.admin.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import cn.com.innodev.pdp.admin.Admin;
import cn.com.innodev.pdp.admin.persistence.AuthAdminDOMapper;
import cn.com.innodev.pdp.admin.persistence.object.AuthAdminDO;
import cn.com.innodev.pdp.admin.services.AdminService;
import cn.com.innodev.pdp.framework.services.AbstractPlatformServiceMgr;

import com.vanstone.business.ObjectDuplicateException;
import com.vanstone.centralserver.common.MyAssert;
import com.vanstone.dal.id.IDBuilder;

/**
 * AdminService实现类
 * @author shipeng
 */
@Service("adminService")
public class AdminServiceImpl extends AbstractPlatformServiceMgr implements AdminService {
	
	/** */
	private static final long serialVersionUID = 2787914141483594104L;
	
	@Autowired
	private AuthAdminDOMapper authAdminDOMapper;
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.admin.services.AdminService#addAdmin(cn.com.innodev.pdp.admin.Admin)
	 */
	@Override
	public synchronized Admin addAdmin(final Admin admin) throws ObjectDuplicateException{
		MyAssert.notNull(admin);
		AuthAdminDO model = this.authAdminDOMapper.selectByAdminName(admin.getAdminName());
		if (model != null) {
			throw new ObjectDuplicateException();
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				admin.setId(IDBuilder.base58Uuid());
				AuthAdminDO adminDO = BeanUtil.toAuthAdminDO(admin);
				authAdminDOMapper.insert(adminDO);
			}
		});
		return admin;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.admin.services.AdminService#getAdmin(java.lang.String)
	 */
	@Override
	public Admin getAdmin(String id) {
		MyAssert.hasText(id);
		AuthAdminDO model = this.authAdminDOMapper.selectByPrimaryKey(id);
		if (model == null) {
			return null;
		}
		return BeanUtil.toAdmin(model);
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.admin.services.AdminService#updatePassword(java.lang.String, java.lang.String)
	 */
	@Override
	public Admin updatePassword(final String id, final String newPassword) {
		MyAssert.hasText(id);
		MyAssert.hasText(newPassword);
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				AuthAdminDO authAdminDO = new AuthAdminDO();
				authAdminDO.setId(id);
				authAdminDO.setAdminPwd(newPassword);
				authAdminDOMapper.updateByPrimaryKeySelective(authAdminDO);
			}
		});
		return this.getAdmin(id);
	}
	
	@Override
	public Admin getAdminByAdminName(final String adminName) {
		AuthAdminDO authAdminDO = this.authAdminDOMapper.selectByAdminName(adminName);
		if (authAdminDO == null) {
			return null;
		}
		return BeanUtil.toAdmin(authAdminDO);
	}
		
	@Override
	public void updateAdminLastLoginTime(final String id, final Date lastLoginTime) {
		MyAssert.hasText(id);
		MyAssert.notNull(lastLoginTime);
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				AuthAdminDO authAdminDO = new AuthAdminDO();
				authAdminDO.setId(id);
				authAdminDO.setLastLoginTime(lastLoginTime);
				authAdminDOMapper.updateByPrimaryKeySelective(authAdminDO);
			}
		});
	}

	@Override
	public Collection<Admin> getAdmins(int offset, int limit) {
		List<AuthAdminDO> models = this.authAdminDOMapper.selectAll(new RowBounds(offset, limit));
		if (models == null || models.size() <=0) {
			return null;
		}
		Collection<Admin> admins = new ArrayList<Admin>();
		for (AuthAdminDO model : models) {
			admins.add(BeanUtil.toAdmin(model));
		}
		return admins;
	}
	
	@Override
	public int getTotalAdmins() {
		return this.authAdminDOMapper.selectAllTotal();
	}
	
}
