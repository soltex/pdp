/**
 * 
 */
package cn.com.innodev.pdp.business.sdk.commmon.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.innodev.pdp.business.sdk.commmon.PDPSDKCommonManager;
import cn.com.innodev.pdp.community.Community;
import cn.com.innodev.pdp.community.services.CommunityService;
import cn.com.innodev.pdp.proprietor.Proprietor;
import cn.com.innodev.pdp.proprietor.services.ProprietorService;

import com.vanstone.common.MyAssert;

/**
 * 
 * @author shipeng
 */
@Service("pdpSDKCommonManager")
public class PDPSDKCommonManagerImpl implements PDPSDKCommonManager {

	@Autowired
	private CommunityService communityService;
	@Autowired
	private ProprietorService proprietorService;
	
	@Override
	public Community getAndValidateCommunity(String communityID) {
		MyAssert.hasText(communityID);
		Community loadCommunity = communityService.getCommunity(communityID);
		if (loadCommunity == null) {
			throw new IllegalArgumentException();
		}
		return loadCommunity;
	}

	@Override
	public Proprietor getAndValidateProprietorByOpenId(String openId) {
		MyAssert.hasText(openId);
		Proprietor loadProprietor = this.proprietorService.getProprietorByWeixinOpenId(openId);
		if (loadProprietor == null) {
			throw new IllegalArgumentException();
		}
		return loadProprietor;
	}
	
	@Override
	public Proprietor getAndValidateProprietorById(String id) {
		MyAssert.hasText(id);
		Proprietor loadProprietor = this.proprietorService.getProprietor(id);
		if (loadProprietor == null) {
			throw new IllegalArgumentException();
		}
		return loadProprietor;
	}
	
}
