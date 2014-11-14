/**
 * 
 */
package cn.com.innodev.pdp.framework.bizcommon.region.event;

import cn.com.innodev.pdp.framework.repo.AbstractBusinessEvent;

/**
 * @author shipeng
 *
 */
public class RelCityDataEvent extends AbstractBusinessEvent {

	private int cityId;
	
	public RelCityDataEvent(Object source, int cityId) {
		super(source);
		this.cityId = cityId;
	}
	
	public int getCityId() {
		return cityId;
	}
	
}
