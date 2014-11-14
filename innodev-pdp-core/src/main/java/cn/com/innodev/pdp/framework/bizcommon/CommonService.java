/**
 * 
 */
package cn.com.innodev.pdp.framework.bizcommon;

import java.util.Collection;

import com.vanstone.business.ObjectDuplicateException;
import com.vanstone.business.ObjectHasSubObjectException;

import cn.com.innodev.pdp.framework.bizcommon.region.City;
import cn.com.innodev.pdp.framework.bizcommon.region.Province;

/**
 * 公共服务类
 * @author shipeng
 */
public interface CommonService {
	
	public static final String SERVICE = "commonService";
	
	/**
	 * 添加省份
	 * @param province
	 * @return
	 */
	Province addProvince(Province province) throws ObjectDuplicateException;
	
	/**
	 * 更新省份信息
	 * @param province
	 * @return
	 */
	Province updateProvince(Province province) throws ObjectDuplicateException;
	
	/**
	 * 获取省份信息
	 * @param provinceId
	 * @return
	 */
	Province getProvince(int provinceId);
	
	/**
	 * 通过tilte获取Province
	 * @param title
	 * @return
	 */
	Province getProvinceByTitle(String title);
	
	/**
	 * 删除省份信息
	 * @param provinceId
	 */
	void deleteProvince(int provinceId) throws ObjectHasSubObjectException;
	
	/**
	 * 获取全部省份信息
	 * @return
	 */
	Collection<Province> getProvinces();
	
	/**
	 * 获取省份关联城市数量
	 * @return
	 */
	Collection<Province> getProvincesWithCityCount();
	
	/**
	 * 添加城市信息
	 * @param city
	 * @return
	 * @throws ObjectDuplicateException 当省份下存在相同城市名称，throws异常
	 */
	City addCity(City city) throws ObjectDuplicateException;
	
	/**
	 * 更新城市信息
	 * @param city
	 * @throws ObjectDuplicateException 当省份下存在相同城市名称，throws异常
	 * @return
	 */
	City updateCity(City city) throws ObjectDuplicateException;
	
	/**
	 * 获取城市信息
	 * @param cityId
	 * @return
	 */
	City getCity(int cityId);
	
	/**
	 * 删除城市信息
	 * @param cityId
	 */
	void deleteCity(int cityId) throws ObjectHasSubObjectException;
	
	/**
	 * 获取省份下的城市信息
	 * @param provinceId
	 * @return
	 */
	Collection<City> getCities(int provinceId);
	
	/**
	 * 获取全部城市信息
	 * @return
	 */
	Collection<City> getCities();
	
	/**
	 * 获取城市列表
	 * @param provinceId
	 * @return
	 */
	Collection<City> getCitiesWithBusinessObjectCount(int provinceId);
	
	/**
	 * 获取城市列表
	 * @return
	 */
	Collection<City> getCitiesWithBusinessObjectCount();
	
}
