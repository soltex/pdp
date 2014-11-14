/**
 * 
 */
package cn.com.innodev.pdp.proprietor.services.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import cn.com.innodev.pdp.framework.Constants;
import cn.com.innodev.pdp.framework.bizcommon.DefaultValidatecodeGenerator;
import cn.com.innodev.pdp.framework.bizcommon.ValidatecodeGenerator;
import cn.com.innodev.pdp.framework.services.AbstractPlatformServiceMgr;
import cn.com.innodev.pdp.proprietor.ValidatecodeBean;
import cn.com.innodev.pdp.proprietor.persistence.PropValidatecodeDOMapper;
import cn.com.innodev.pdp.proprietor.persistence.object.PropValidatecodeDO;
import cn.com.innodev.pdp.proprietor.services.ValidatecodeException;
import cn.com.innodev.pdp.proprietor.services.ValidatecodeService;
import cn.com.innodev.pdp.proprietor.services.ValidatecodeException.ErrorCode;

import com.vanstone.common.MyAssert;

/**
 * @author shipeng
 */
@Service("validatecodeService")
public class ValidatecodeServiceImpl extends AbstractPlatformServiceMgr implements ValidatecodeService {
	
	/** */
	private static final long serialVersionUID = 9177317448557184507L;
	
	@Autowired
	private PropValidatecodeDOMapper propValidatecodeDOMapper;
	
	private ValidatecodeGenerator valdiateGenerator = new DefaultValidatecodeGenerator();
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.proprietor.services.ValidatecodeService#generate(java.lang.String)
	 */
	@Override
	public ValidatecodeBean generate(final String mobile) {
		MyAssert.hasText(mobile);
		PropValidatecodeDO model = this.propValidatecodeDOMapper.selectByPrimaryKey(mobile);
		final String validatecode = valdiateGenerator.generate();
		if (model == null) {
			ValidatecodeBean validatecodeBean = this.execute(new TransactionCallback<ValidatecodeBean>() {
				@Override
				public ValidatecodeBean doInTransaction(TransactionStatus arg0) {
					PropValidatecodeDO propValidatecodeDO = new PropValidatecodeDO();
					propValidatecodeDO.setMobile(mobile);
					propValidatecodeDO.setValidatecode(validatecode);
					propValidatecodeDO.setExpireTime(calculateValidatecodeExpireTime(new Date()));
					propValidatecodeDOMapper.insert(propValidatecodeDO);
					ValidatecodeBean validatecodeBean = new ValidatecodeBean();
					validatecodeBean.setMobile(mobile);
					validatecodeBean.setExpireTime(propValidatecodeDO.getExpireTime());
					validatecodeBean.setValidatecode(validatecode);
					return validatecodeBean;
				}
			});
			return validatecodeBean;
		}else{
			ValidatecodeBean validatecodeBean = this.execute(new TransactionCallback<ValidatecodeBean>() {
				@Override
				public ValidatecodeBean doInTransaction(TransactionStatus arg0) {
					PropValidatecodeDO propValidatecodeDO = new PropValidatecodeDO();
					propValidatecodeDO.setMobile(mobile);
					propValidatecodeDO.setValidatecode(validatecode);
					propValidatecodeDO.setExpireTime(calculateValidatecodeExpireTime(new Date()));
					propValidatecodeDOMapper.updateByPrimaryKey(propValidatecodeDO);
					ValidatecodeBean validatecodeBean = new ValidatecodeBean();
					validatecodeBean.setMobile(mobile);
					validatecodeBean.setExpireTime(propValidatecodeDO.getExpireTime());
					validatecodeBean.setValidatecode(validatecode);
					return validatecodeBean;
				}
			});
			return validatecodeBean;
		}
	}
	
	/**
	 * 计算
	 * @param now
	 * @return
	 */
	private Date calculateValidatecodeExpireTime(Date now) {
		MyAssert.notNull(now);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.HOUR_OF_DAY, Constants.DEFAULT_VALIDATECODE_EXPIRE_HOUR);
		return calendar.getTime();
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.proprietor.services.ValidatecodeService#validate(java.lang.String, java.lang.String)
	 */
	@Override
	public void validate(String mobile, String validatecode) throws ValidatecodeException {
		MyAssert.hasText(mobile);
		MyAssert.hasText(validatecode);
		PropValidatecodeDO model = this.propValidatecodeDOMapper.selectByPrimaryKey(mobile);
		if (model == null) {
			throw ValidatecodeException.createValidatecodeException(ErrorCode.Mobile_Not_Found);
		}
		if (model.getExpireTime().getTime() < new Date().getTime()) {
			throw ValidatecodeException.createValidatecodeException(ErrorCode.Validatecode_Expire);
		}
		if (!model.getValidatecode().equals(validatecode)) {
			throw ValidatecodeException.createValidatecodeException(ErrorCode.Valdiatecode_Not_Match);
		}
	}
	
	@Override
	public void clearAllValidatecodes() {
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				propValidatecodeDOMapper.deleteAll();
			}
		});
	}
	
}
