/**
 * 
 */
package cn.com.innodev.pdp.framework.bizcommon.region.event;

import cn.com.innodev.pdp.framework.bizcommon.region.City;
import cn.com.innodev.pdp.framework.repo.AbstractBusinessEvent;

/**
 * @author shipeng
 *
 */
public class DeleteCityEvent extends AbstractBusinessEvent {

	private City city;
	
	public DeleteCityEvent(Object source, City city) {
		super(source);
		this.city = city;
	}
	
	public City getCity() {
		return city;
	}
	
	/**
	 * 创建DeleteCityEvent对象
	 * @param source
	 * @param city
	 * @return
	 */
	public static DeleteCityEvent createDeleteCityEvent(Object source, City city) {
		return new DeleteCityEvent(source, city);
	}
	
}
