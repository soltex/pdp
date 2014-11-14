/**
 * 
 */
package cn.com.innodev.pdp.framework.bizcommon;

import org.springframework.stereotype.Service;

import com.vanstone.common.util.random.RandomNumber;

/**
 * @author shipeng
 *
 */
@Service
public class DefaultValidatecodeGenerator implements ValidatecodeGenerator {
	
	@Override
	public String generate(int length) {
		if (length <=0) {
			length = DEFAULT_CODE_LENGTH;
		}
		return RandomNumber.randomNumber(length);
	}

	@Override
	public String generate() {
		return this.generate(0);
	}
	
}
