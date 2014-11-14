/**
 * 
 */
package cn.com.innodev.pdp.community.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.vanstone.framework.business.ServiceManagerFactory;

import cn.com.innodev.pdp.community.event.UpdateRoomProprietorCountEvent;
import cn.com.innodev.pdp.community.services.CommunityService;
import cn.com.innodev.pdp.proprietor.services.ProprietorService;

/**
 * 响应UpdateRoomProprietorCountEvent事件
 * @author shipeng
 */
@Component("onUpdateRoomProprietorCountEventListener")
public class OnUpdateRoomProprietorCountEventListener implements ApplicationListener<UpdateRoomProprietorCountEvent> {

	private static Logger LOG = LoggerFactory.getLogger(OnUpdateRoomProprietorCountEventListener.class);
	
	@Override
	public void onApplicationEvent(UpdateRoomProprietorCountEvent event) {
		LOG.debug("Update Room Proprietor Count Event , RoomID [{}]", event.getRoomId());
		ProprietorService proprietorService = ServiceManagerFactory.getInstance().getService(ProprietorService.SERVICE);
		int count = proprietorService.getTotalProprietorsByRoomId(event.getRoomId());
		CommunityService communityService = ServiceManagerFactory.getInstance().getService(CommunityService.SERVICE);
		communityService.updateRoomProprietorCount(event.getRoomId(), count);
	}
	
}
