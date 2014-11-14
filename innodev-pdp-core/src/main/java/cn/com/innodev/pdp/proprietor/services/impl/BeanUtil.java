/**
 * 
 */
package cn.com.innodev.pdp.proprietor.services.impl;

import com.vanstone.business.lang.EnumUtils;
import com.vanstone.weedfs.client.WeedFile;

import cn.com.innodev.pdp.community.Community;
import cn.com.innodev.pdp.framework.bizcommon.Gender;
import cn.com.innodev.pdp.framework.bizcommon.ImageBean;
import cn.com.innodev.pdp.proprietor.Proprietor;
import cn.com.innodev.pdp.proprietor.ProprietorState;
import cn.com.innodev.pdp.proprietor.persistence.object.PropProprietorDOWithBLOBs;

/**
 * @author shipeng
 */
public class BeanUtil {
	
	/**
	 * 转换为业主对象，Rooms未初始化
	 * The following are not being converted. 
	 * java.util.Collection rooms;
	 * @param propProprietorDO
	 * @return
	 */
	public static Proprietor toProprietor(PropProprietorDOWithBLOBs propProprietorDO, Community community) {
		Proprietor proprietor = new Proprietor(community);
		proprietor.setId(propProprietorDO.getId());
		proprietor.setMobile(propProprietorDO.getMobile());
		proprietor.setEmail(propProprietorDO.getEmail());
		proprietor.setFullName(propProprietorDO.getFullName());
		proprietor.setNickName(propProprietorDO.getNickName());
		proprietor.setPwd(propProprietorDO.getPwd());
		proprietor.setWeixinOpenid(propProprietorDO.getWeixinOpenid());
		Gender gender = EnumUtils.getByCode(propProprietorDO.getGender(), Gender.class);
		if (gender != null) {
			proprietor.setGender(gender);
		}
		proprietor.setProfession(propProprietorDO.getProfession());
		proprietor.setSysInsertTime(propProprietorDO.getSysInsertTime());
		proprietor.setSysUpdateTime(propProprietorDO.getSysUpdateTime());
		proprietor.setInterest(propProprietorDO.getInterest());
		ProprietorState proprietorState = EnumUtils.getByCode(propProprietorDO.getPropState(), ProprietorState.class);
		if (propProprietorDO.getHeaderFileId() != null && !"".equals(propProprietorDO.getHeaderFileId())) {
			ImageBean imageBean = new ImageBean();
			WeedFile weedFile = new WeedFile(propProprietorDO.getHeaderFileId(), propProprietorDO.getHeaderFileExt());
			imageBean.setWeedFile(weedFile);
			imageBean.setWidth(propProprietorDO.getHeaderFileWidth());
			imageBean.setHeight(propProprietorDO.getHeaderFileHeight());
			proprietor.setHeadImageBean(imageBean);
		}
		if (proprietorState != null) {
			proprietor.setProprietorState(proprietorState);
		}
		return proprietor;
	}
	
	/**
	 * 转换为PropProprietorDO
	 * @param proprietor
	 * @return
	 */
	public static PropProprietorDOWithBLOBs toPropProprietorDO(Proprietor proprietor) {
		PropProprietorDOWithBLOBs propProprietorDO = new PropProprietorDOWithBLOBs();
		propProprietorDO.setId(proprietor.getId());
		propProprietorDO.setMobile(proprietor.getMobile());
		propProprietorDO.setEmail(proprietor.getEmail());
		propProprietorDO.setFullName(proprietor.getFullName());
		propProprietorDO.setNickName(proprietor.getNickName());
		propProprietorDO.setPwd(proprietor.getPwd());
		propProprietorDO.setWeixinOpenid(proprietor.getWeixinOpenid());
		propProprietorDO.setGender(proprietor.getGender() != null ? proprietor.getGender().getCode() : null);
		propProprietorDO.setPropState(proprietor.getProprietorState() != null ? proprietor.getProprietorState().getCode() : null);
		propProprietorDO.setProfession(proprietor.getProfession());
		propProprietorDO.setInterest(proprietor.getInterest());
		propProprietorDO.setSysInsertTime(proprietor.getSysInsertTime());
		propProprietorDO.setSysUpdateTime(proprietor.getSysUpdateTime());
		if (proprietor.getHeadImageBean() != null) {
			propProprietorDO.setHeaderFileId(proprietor.getHeadImageBean().getWeedFile().getFileid());
			propProprietorDO.setHeaderFileExt(proprietor.getHeadImageBean().getWeedFile().getExtName());
			propProprietorDO.setHeaderFileWidth(proprietor.getHeadImageBean().getWidth());
			propProprietorDO.setHeaderFileHeight(proprietor.getHeadImageBean().getHeight());
		}
		propProprietorDO.setCommunityId(proprietor.getCommunity().getId());
		return propProprietorDO;
	}
}
