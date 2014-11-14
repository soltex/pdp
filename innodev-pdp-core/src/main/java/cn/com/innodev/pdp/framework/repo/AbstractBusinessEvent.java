/**
 * 
 */
package cn.com.innodev.pdp.framework.repo;

/**
 * @author shipeng
 *
 */
public abstract class AbstractBusinessEvent implements BusinessEvent {

	private Object source;
	
	public AbstractBusinessEvent(Object source) {
		this.source = source;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.framework.repo.BusinessEvent#getSource()
	 */
	@Override
	public Object getSource() {
		return this.source;
	}
	
}