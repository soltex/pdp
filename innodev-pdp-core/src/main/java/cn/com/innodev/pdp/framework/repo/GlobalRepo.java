/**
 * 
 */
package cn.com.innodev.pdp.framework.repo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vanstone.common.MyAssert;

/**
 * 全局业务EventListener仓库
 * @author shipeng
 */
public class GlobalRepo {
	
	private static Logger LOG = LoggerFactory.getLogger(GlobalRepo.class);
	
	/** 全局仓库 group : list of listener */
	private Map<String, List<BusinessEventListener>> repoDataMap = new ConcurrentHashMap<String, List<BusinessEventListener>>();
	
	public static class GlobalRepoInstance {
		private static GlobalRepo instance = new GlobalRepo();
	}
	
	/**
	 * 获取当前实例
	 * @return
	 */
	public static GlobalRepo getInstance() {
		return GlobalRepoInstance.instance;
	}
	
	/**
	 * BusinessEventListener注册
	 * @param groupName
	 * @param listener
	 */
	public void regist(String group , BusinessEventListener listener) {
		MyAssert.hasText(group);
		MyAssert.notNull(listener);
		if (repoDataMap.get(group) == null) {
			repoDataMap.put(group, new ArrayList<BusinessEventListener>());
		}
		repoDataMap.get(group).add(listener);
		LOG.info("Registy BusinessEvent Listener , {},{},{}",listener.getGroup() , listener.getName(), listener.getClass());
	}
	
//	/**
//	 * 获取业务事件监听器列表
//	 * @param group
//	 * @return
//	 */
//	public <T extends BusinessEventListener> Collection<T> getBusinessEventListenersByGroup(String group) {
//		MyAssert.hasText(group);
//		return this.repoDataMap.get(group);
//	}
	
	/**
	 * 获取迭代器
	 * @param group
	 * @return
	 */
	public Iterator<BusinessEventListener> getBusinessEventListenersIteratorByGroup(String group) {
		Collection<BusinessEventListener> listeners = this.repoDataMap.get(group);
		if (listeners != null && listeners.size() >0) {
			return listeners.iterator();
		}
		return null;
	}
	
//	/**
//	 * 通过Group清理监听器
//	 * @param group
//	 */
//	public void clearByGroup(String group) {
//		MyAssert.hasText(group);
//		Collection<BusinessEventListener> businessEventListeners = this.getBusinessEventListenersByGroup(group);
//		if (businessEventListeners == null || businessEventListeners.size() <=0) {
//			return;
//		}
//		businessEventListeners.clear();
//	}
	
	/**
	 * 清理全部业务对象
	 */
	public void clearAllBusinessEventListeners() {
		this.repoDataMap.clear();
	}
	
}
