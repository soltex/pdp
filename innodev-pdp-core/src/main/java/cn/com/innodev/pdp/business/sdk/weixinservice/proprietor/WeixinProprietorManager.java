/**
 * 
 */
package cn.com.innodev.pdp.business.sdk.weixinservice.proprietor;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import cn.com.innodev.pdp.framework.bizcommon.ImageFormatIncorrentException;
import cn.com.innodev.pdp.proprietor.Proprietor;
import cn.com.innodev.pdp.proprietor.services.ValidatecodeException;

import com.vanstone.business.ObjectDuplicateException;
import com.vanstone.fs.FSFile;

/**
 * @author shipeng
 */
public interface WeixinProprietorManager {
	
	/** SERVICE*/
	public static final String SERVICE = "weixinProprietorManager";
	
	/**
	 * 生成验证码, 并下发短信,以及验证手机号码合法性
	 * @param mobile
	 */
	void generateValidatecode(String mobile);
	
	/**
	 * 订阅微信服务号
	 * 如为首次订阅则发送提示信息给客户端
	 * 如已订阅，则throws异常信息
	 * @param communityId
	 * @param weixinOpenId
	 * @return
	 * @throws WeixinSubscribedException
	 */
	Proprietor subscribeWeixinProprietor(String communityId, String weixinOpenId, String serverOpenId, HttpServletResponse servletResponse) throws WeixinSubscribedException;
	
	/**
	 * 微信端登陆
	 * 对OpenId如果不相同，修改当前用户使用的OpenId
	 * @param mobile
	 * @param password
	 * @return
	 * @throws WeixinLoginException
	 */
	Proprietor login(String mobile, String password, String openId) throws WeixinLoginException;
	
	/**
	 * 取消订阅微信服务号
	 * @param weixinOpenId
	 * @return
	 */
	Proprietor unsubscribeWeixinProprietor(String weixinOpenId);
	
	/**
	 * 手机认证
	 * 如手机认证成功后，推送消息给客户
	 * @param weixinOpenId
	 * @param mobile
	 * @param validatecode
	 * @param password
	 * @return
	 */
	Proprietor mobileAuthenticateProprietor(String communityId, String weixinOpenId, String mobile, String validatecode, HttpServletResponse servletResponse) throws ValidatecodeException, ObjectDuplicateException;
	
	/**
	 * 绑定房间号
	 * @param weixinOpenId
	 * @param roomIds
	 * @return
	 */
	Proprietor bindRooms(String communityId, String weixinOpenId, Collection<Integer> roomIds);
	
	/**
	 * 更新头像信息
	 * @param id
	 * @param fsFile
	 */
	void updateProprietorHeaderImage(String id, FSFile fsFile) throws ImageFormatIncorrentException;
	
}
