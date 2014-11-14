/**
 * 
 */
package cn.com.innodev.example.repo;

/**
 * @author shipeng
 */
public interface BusinessEventListener {
	
	/**
	 * 获取业务事件名称
	 * @return
	 */
	String getName();
	
	/**
	 * 获取业务组名称
	 * @return
	 */
	String getGroup();
	
}
