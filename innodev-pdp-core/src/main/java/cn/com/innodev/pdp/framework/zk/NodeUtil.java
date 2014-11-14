/**
 * 
 */
package cn.com.innodev.pdp.framework.zk;

import cn.com.innodev.pdp.framework.Constants;

/**
 * @author shipeng
 */
public class NodeUtil {
	
	/**
	 * 获取锁路径
	 * @param nodeName
	 * @return
	 */
	public static String buildPlatformLockPath(String nodeName) {
		if (nodeName == null || nodeName.equals("")) {
			throw new IllegalArgumentException();
		}
		if (!nodeName.startsWith(Constants.SYSTEM_SEPARATOR)) {
			nodeName = Constants.SYSTEM_SEPARATOR + nodeName;
		}
		return Constants.INNODEV_PDP_ZK_CACHE_LOCK_NODE + nodeName;
	}
	
}
