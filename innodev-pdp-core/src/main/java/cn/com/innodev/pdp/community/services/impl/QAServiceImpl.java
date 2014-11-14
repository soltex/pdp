/**
 * 
 */
package cn.com.innodev.pdp.community.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import com.vanstone.dal.id.IDBuilder;

import cn.com.innodev.pdp.community.Community;
import cn.com.innodev.pdp.community.QA;
import cn.com.innodev.pdp.community.persistence.ComQADOMapper;
import cn.com.innodev.pdp.community.persistence.object.ComQADO;
import cn.com.innodev.pdp.community.services.CommunityService;
import cn.com.innodev.pdp.community.services.QAService;
import cn.com.innodev.pdp.framework.services.AbstractPlatformServiceMgr;

/**
 * @author shipeng
 */
@Service("qaService")
public class QAServiceImpl extends AbstractPlatformServiceMgr implements QAService {
	
	/** */
	private static final long serialVersionUID = -2082838819239545513L;
	
	@Autowired
	private ComQADOMapper comQADOMapper;
	@Autowired
	private CommunityService communityService;
	
	@Override
	public QA addQA(final QA qa) {
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				qa.setId(IDBuilder.base58Uuid());
				qa.setSysInsertTime(new Date());
				qa.setSysUpdateTime(new Date());
				ComQADO model = BeanUtil.toComQADO(qa);
				comQADOMapper.insert(model);
			}
		});
		return qa;
	}

	@Override
	public QA updateQA(final QA qa) {
		final QA loadQa = this.getQA(qa.getId());
		if (loadQa == null) {
			throw new IllegalArgumentException();
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				loadQa.setSysUpdateTime(new Date());
				loadQa.setTitle(qa.getTitle());
				loadQa.setContent(qa.getContent());
				ComQADO model = BeanUtil.toComQADO(loadQa);
				comQADOMapper.updateByPrimaryKeyWithBLOBs(model);
			}
		});
		return loadQa;
	}

	@Override
	public void deleteQA(final String id) {
		final QA loadQa = this.getQA(id);
		if (loadQa == null) {
			throw new IllegalArgumentException();
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				comQADOMapper.deleteByPrimaryKey(id);
			}
		});
	}
	
	@Override
	public QA getQA(String qaId) {
		ComQADO model = this.comQADOMapper.selectByPrimaryKey(qaId);
		if (model == null) {
			return null;
		}
		return BeanUtil.toQA(model, communityService.getCommunity(model.getCommunityId()));
	}
	
	@Override
	public Collection<QA> getQAs(Community community, int offset, int limit) {
		final Community loadCommunity = communityService.getCommunity(community.getId());
		if (loadCommunity == null) {
			throw new IllegalArgumentException();
		}
		List<ComQADO> models = this.comQADOMapper.selectByCommunityId(community.getId(), new RowBounds(offset, limit));
		if (models == null || models.size() <=0) {
			return null;
		}
		Collection<QA> qas = new ArrayList<QA>();
		for (ComQADO model : models) {
			QA qa = BeanUtil.toQA(model, loadCommunity);
			qas.add(qa);
		}
		return qas;
	}
	
	@Override
	public int getTotalQAs(Community community) {
		final Community loadCommunity = communityService.getCommunity(community.getId());
		if (loadCommunity == null) {
			throw new IllegalArgumentException();
		}
		return this.comQADOMapper.selectCountByCommunityId(community.getId());
	}
	
}
