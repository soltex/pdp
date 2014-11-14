/**
 * 
 */
package cn.com.innodev.pdp.framework.bizcommon.impl;

import cn.com.innodev.pdp.framework.bizcommon.persistence.object.SysCityDO;
import cn.com.innodev.pdp.framework.bizcommon.persistence.object.SysProvinceDO;
import cn.com.innodev.pdp.framework.bizcommon.region.City;
import cn.com.innodev.pdp.framework.bizcommon.region.Province;

/**
 * @author shipeng
 *
 */
public class BeanUtil {
	
	public static Province toProvince(SysProvinceDO sysProvinceDO) {
		Province province = new Province();
		province.setId(sysProvinceDO.getId());
		province.setTitle(sysProvinceDO.getTitle());
		province.setFirstLetter(sysProvinceDO.getFirstLetter().charAt(0));
		return province;
	}
	
	public static SysProvinceDO toSysProvinceDO(Province province) {
		SysProvinceDO sysProvinceDO = new SysProvinceDO();
		sysProvinceDO.setId(province.getId());
		sysProvinceDO.setFirstLetter(String.valueOf(province.getFirstLetter()));
		sysProvinceDO.setTitle(province.getTitle());
		return sysProvinceDO;
	}
	
	public static City toCity(SysCityDO sysCityDO, Province province) {
		City city = new City(province);
		city.setId(sysCityDO.getId());
		city.setTitle(sysCityDO.getTitle());
		city.setFirstLetter(sysCityDO.getFirstLetter().charAt(0));
		return city;
	}
	
	public static City toCity(SysCityDO sysCityDO, Province province, int communityCount) {
		City city = new City(province, communityCount);
		city.setId(sysCityDO.getId());
		city.setTitle(sysCityDO.getTitle());
		city.setFirstLetter(sysCityDO.getFirstLetter().charAt(0));
		return city;
	}
	
	public static SysCityDO toSysCityDO(City city) {
		SysCityDO sysCityDO = new SysCityDO();
		if (city.getFirstLetter() != null) {
			sysCityDO.setFirstLetter(String.valueOf(city.getFirstLetter()));
		}
		sysCityDO.setId(city.getId());
		sysCityDO.setProvinceId(city.getProvince().getId());
		sysCityDO.setTitle(city.getTitle());
		return sysCityDO;
	}
}
