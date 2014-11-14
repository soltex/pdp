/**
 * 
 */
package cn.com.innodev.example.repo;

/**
 * @author shipeng
 */
public abstract class AbstractBusinessEventListenerAdapter implements BusinessEventListener {

	/* (non-Javadoc)
	 * @see cn.com.innodev.example.repo.BusinessEventListener#getName()
	 */
	@Override
	public String getName() {
		return this.getClass().getName();
	}
	
}
