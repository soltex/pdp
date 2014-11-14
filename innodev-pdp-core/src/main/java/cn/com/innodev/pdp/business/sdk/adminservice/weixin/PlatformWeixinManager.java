/**
 * 
 */
package cn.com.innodev.pdp.business.sdk.adminservice.weixin;

import java.util.Collection;

import cn.com.innodev.pdp.business.sdk.BusinessException;

import com.vanstone.centralserver.common.weixin.wrap.user.UserGroupInfo;

/**
 * @author shipeng
 */
public interface PlatformWeixinManager {
	
	/**
	 * 创建微信菜单
	 */
	boolean createWeixinEnum();
	
	/**
	 * 获取用户组信息
	 * @return
	 */
	Collection<UserGroupInfo> getUserGroupInfos() throws BusinessException;
	
}
