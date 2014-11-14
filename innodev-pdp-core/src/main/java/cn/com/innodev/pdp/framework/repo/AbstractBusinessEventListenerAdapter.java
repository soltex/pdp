/**
 * 
 */
package cn.com.innodev.pdp.framework.repo;

import javax.annotation.PostConstruct;

import com.vanstone.common.MyAssert;

/**
 * @author shipeng
 */
public abstract class AbstractBusinessEventListenerAdapter implements BusinessEventListener {

	private String name;
	private String group;
	
	public AbstractBusinessEventListenerAdapter(String groupName) {
		MyAssert.hasText(groupName);
		this.name = this.getClass().getName();
		this.group = groupName;
		//this.init();
	}
	
	@PostConstruct
	public void init() {
		GlobalRepo.getInstance().regist(this.getGroup(), this);
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.example.repo.BusinessEventListener#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}
	
	public String getGroup() {
		return group;
	}
	
}
