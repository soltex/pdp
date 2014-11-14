/**
 * 
 */
package cn.com.innodev.pdp.weixin.webapp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.innodev.pdp.framework.Constants;

import com.vanstone.centralserver.common.weixin.wrap.msg.AbstractMsg;
import com.vanstone.weixin.client.controller.DefaultWeixinProcessorServlet;

/**
 * 
 * @author shipeng
 */
public class PDPWeixinProcessorServlet extends DefaultWeixinProcessorServlet {

	/** */
	private static final long serialVersionUID = 6239495849254928540L;
	
	private static Logger LOG = LoggerFactory.getLogger(PDPWeixinProcessorServlet.class);
	
	@Override
	public AbstractMsg doPreMsgHandler(HttpServletRequest servletRequest, HttpServletResponse servletResponse, AbstractMsg msg) { 
		LOG.debug(servletRequest.getRequestURL().toString());
		String communitId = Constants.parseCommunityIdByHttpServletRequest(servletRequest);
		LOG.debug("CURRENT CommunityID {}", communitId);
		msg.addParam(Constants.COMMUNITY_ID_PARAM_IN_MSG, communitId);
		return msg;
	}
    
//	@Override
//    protected void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
//		this.doPost(servletRequest, servletResponse);
//	}
//	
//	@Override
//    protected void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
//		if (weixinAPIManager.validateWeixin(servletRequest, servletResponse)) {
//			LOG.info("Weixin API Validate ECHOSTR OK.");
//			return;
//		}
//		AbstractMsg msg = msgResolver.resolve(servletRequest);
//		if (msg == null) {
//			return;
//		}
//		handler.eventHandler(msg, servletRequest, servletResponse);
//	}
	
}
