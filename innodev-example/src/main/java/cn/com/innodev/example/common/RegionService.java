/**
 * 
 */
package cn.com.innodev.example.common;

import java.util.Collection;

/**
 * @author shipeng
 */
public interface RegionService {
	
	Province addProvince(Province province);
	
	Province updateProvince(Province province);
	
	Province getProvince(int provinceId);
	
	void deleteProvince(int provinceId);
	
	Collection<Province> getProvinces();
	
	City addCity(City city);
	
	City updateCity(City city);
	
	City getCity(int cityId);
	
	void deleteCity(int cityId);
	
	Collection<City> getCities(int provinceId);
	
}
