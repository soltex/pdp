/**
 * 
 */
package cn.com.innodev.pdp.framework.bizcommon;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 基础检索条件
 * @author shipeng
 */
public abstract class AbstractBasicCondition {
	
	/**
	 * 是否存在检索条件
	 * @return
	 */
	public abstract boolean existCondition() ;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
