/**
 * 
 */
package cn.com.innodev.pdp.framework.bizcommon;

/**
 * @author shipeng
 */
public interface ValidatecodeGenerator {
	
	/** 验证码默认长度*/
	public static final int DEFAULT_CODE_LENGTH = 4;
	
	String generate(int length);
	
	String generate();
	
}
