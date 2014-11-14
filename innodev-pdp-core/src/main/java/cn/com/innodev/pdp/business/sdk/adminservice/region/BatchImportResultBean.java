/**
 * 
 */
package cn.com.innodev.pdp.business.sdk.adminservice.region;

import java.util.ArrayList;
import java.util.Collection;

import com.vanstone.common.MyAssert;

/**
 * @author shipeng
 * 
 */
public class BatchImportResultBean {
	
	private int successCount = 0;
	private Collection<String> failureCollection = new ArrayList<String>();

	public int getSuccessCount() {
		return successCount;
	}
	
	public Collection<String> getFailureCollection() {
		return failureCollection;
	}
	
	public void incSuccessCount() {
		this.successCount ++;
	}
	
	/**
	 * 添加失败名称
	 * @param name
	 * @return
	 */
	public void addFailureName(String name) {
		MyAssert.hasText(name);
		this.failureCollection.add(name);
	}
	
	/**
	 * 是否存在失败名称
	 * @return
	 */
	public boolean existFailureNames() {
		return this.failureCollection.size() >0;
	}
	
}
