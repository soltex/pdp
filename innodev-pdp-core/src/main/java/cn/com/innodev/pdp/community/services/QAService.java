/**
 * 
 */
package cn.com.innodev.pdp.community.services;

import java.util.Collection;

import cn.com.innodev.pdp.community.Community;
import cn.com.innodev.pdp.community.QA;

/**
 * @author shipeng
 *
 */
public interface QAService {

	public static final String SERVICE = "qaService";
	
	/**
	 * 添加QA
	 * @param qa
	 * @return
	 */
	QA addQA(QA qa);
	
	/**
	 * 更新QA信息
	 * @param qa
	 * @return
	 */
	QA updateQA(QA qa);
	
	/**
	 * 删除QA
	 * @param id
	 */
	void deleteQA(String id);
	
	/**
	 * 获取QA
	 * @param qaId
	 * @return
	 */
	QA getQA(String qaId);
	
	/**
	 * 获取QA列表
	 * @param community
	 * @param offset
	 * @param limit
	 * @return
	 */
	Collection<QA> getQAs(Community community, int offset, int limit);
	
	/**
	 * 获取QA列表数量
	 * @param community
	 * @return
	 */
	int getTotalQAs(Community community);
	
}
