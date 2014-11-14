/**
 * 
 */
package cn.com.innodev.pdp.business.sdk.adminservice.community.impl;

import org.springframework.stereotype.Service;

import cn.com.innodev.pdp.framework.Constants;

import com.vanstone.business.MyAssert4Business;
import com.vanstone.common.util.random.RandomNumber;

/**
 * @author shipeng
 */
@Service
public class DefaultStaffAccountGenerator implements StaffAccountGenerator {
	
	@Override
	public AccountObject generate(String communityId) {
		MyAssert4Business.hasLength(communityId);
		String accountName = communityId + "_" + Constants.PROJECT_COMPANY_DEFAULT_ACCOUNT_NAME + RandomNumber.randomNumber(4);
		String password = RandomNumber.randomNumber(6);
		return new AccountObject(accountName, password);
	}
	
}
