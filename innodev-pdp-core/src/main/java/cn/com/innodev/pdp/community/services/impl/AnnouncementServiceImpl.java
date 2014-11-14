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

import com.vanstone.business.MyAssert4Business;
import com.vanstone.centralserver.common.MyAssert;
import com.vanstone.dal.id.IDBuilder;

import cn.com.innodev.pdp.community.Announcement;
import cn.com.innodev.pdp.community.Community;
import cn.com.innodev.pdp.community.persistence.ComAnnouncementDOMapper;
import cn.com.innodev.pdp.community.persistence.object.ComAnnouncementDO;
import cn.com.innodev.pdp.community.services.AnnouncementService;
import cn.com.innodev.pdp.community.services.CommunityService;
import cn.com.innodev.pdp.framework.services.AbstractPlatformServiceMgr;

/**
 * @author shipeng
 */
@Service("announcementService")
public class AnnouncementServiceImpl extends AbstractPlatformServiceMgr implements AnnouncementService {
	
	/** */
	private static final long serialVersionUID = 552625392234531507L;
	
	@Autowired
	private ComAnnouncementDOMapper comAnnouncementDOMapper;
	@Autowired
	private CommunityService communityService;
	
	@Override
	public Announcement addAnnouncement(final Announcement announcement) {
		MyAssert.notNull(announcement);
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				announcement.setId(IDBuilder.base58Uuid());
				announcement.setSysInsertTime(new Date());
				ComAnnouncementDO model = BeanUtil.toComAnnouncementDO(announcement);
				comAnnouncementDOMapper.insert(model);
			}
		});
		return announcement;
	}
	
	@Override
	public Announcement updateAnnouncement(final Announcement announcement) {
		final Announcement loadAnnouncement = this.getAnnouncement(announcement.getId());
		if (loadAnnouncement == null) {
			throw new IllegalArgumentException();
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				loadAnnouncement.setContent(announcement.getTitle());
				loadAnnouncement.setTitle(announcement.getTitle());
				ComAnnouncementDO model = BeanUtil.toComAnnouncementDO(loadAnnouncement);
				comAnnouncementDOMapper.updateByPrimaryKeyWithBLOBs(model);
			}
		});
		return loadAnnouncement;
	}

	@Override
	public void deleteAnnouncement(final String announcementId) {
		Announcement loadAnnouncement = this.getAnnouncement(announcementId);
		if (loadAnnouncement == null) {
			return;
		}
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				comAnnouncementDOMapper.deleteByPrimaryKey(announcementId);
			}
		});
	}
	
	@Override
	public Announcement getAnnouncement(String announcementId) {
		ComAnnouncementDO model = this.comAnnouncementDOMapper.selectByPrimaryKey(announcementId);
		if (model == null) {
			return null;
		}
		return BeanUtil.toAnnouncement(model, communityService.getCommunity(model.getCommunityId()));
	}
	
	@Override
	public Collection<Announcement> getAnnouncements(Community community , int offset, int limit) {
		MyAssert4Business.objectInitialized(community);
		Community loadCommunity = communityService.getCommunity(community.getId());
		if (loadCommunity == null) {
			throw new IllegalArgumentException();
		}
		List<ComAnnouncementDO> comAnnouncementDOs = this.comAnnouncementDOMapper.selectPageBreak(community.getId(), new RowBounds(offset, limit));
		if (comAnnouncementDOs == null || comAnnouncementDOs.size() <=0) {
			return null;
		}
		Collection<Announcement> announcements = new ArrayList<Announcement>();
		for (ComAnnouncementDO model : comAnnouncementDOs) {
			Announcement announcement = BeanUtil.toAnnouncement(model, loadCommunity);
			announcements.add(announcement);
		}
		return announcements;
	}

	@Override
	public int getTotalAnnouncements(Community community) {
		MyAssert4Business.objectInitialized(community);
		Community loadCommunity = communityService.getCommunity(community.getId());
		if (loadCommunity == null) {
			throw new IllegalArgumentException();
		}
		return this.comAnnouncementDOMapper.selectCount(community.getId());
	}
	
}
