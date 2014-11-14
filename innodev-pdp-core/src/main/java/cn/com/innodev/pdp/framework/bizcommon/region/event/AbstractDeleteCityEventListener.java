/**
 * 
 */
package cn.com.innodev.pdp.framework.bizcommon.region.event;

import com.vanstone.business.ObjectHasSubObjectException;

import cn.com.innodev.pdp.framework.repo.AbstractBusinessEventListenerAdapter;

/**
 * @author shipeng
 *
 */
public abstract class AbstractDeleteCityEventListener extends AbstractBusinessEventListenerAdapter {
	
	/** 删除城市事件监听组*/
	public static final String GROUP = "DELETE_CITY_EVENT_LISTENER_GROUP";
	
	public AbstractDeleteCityEventListener() {
		super(GROUP);
	}
	
	/**
	 * 删除城市信息事件监听
	 * @param event 事件响应
	 * @throws ObjectHasSubObjectException 当前城市信息下存在数据信息
	 */
	public abstract void onDeleteCitEvent(DeleteCityEvent event) throws ObjectHasSubObjectException;
	
}
