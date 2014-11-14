/**
 * 
 */
package cn.com.innodev.pdp.framework.bizcommon.region.event;

import cn.com.innodev.pdp.framework.repo.AbstractBusinessEventListenerAdapter;

/**
 * @author shipeng
 *
 */
public abstract class AbstractRelCityDataEventListener extends AbstractBusinessEventListenerAdapter {
	
	public static final String GROUP = "REL_CITY_DATA_EVENT_GROUP";
	
	public AbstractRelCityDataEventListener() {
		super(GROUP);
	}
	
	public abstract int onCommunityCount(RelCityDataEvent event);
}
