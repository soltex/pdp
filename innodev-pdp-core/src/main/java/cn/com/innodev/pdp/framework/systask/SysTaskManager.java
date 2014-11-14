/**
 * 
 */
package cn.com.innodev.pdp.framework.systask;

import java.util.concurrent.Callable;

/**
 * 系统任务管理器
 * @author shipeng
 */
public interface SysTaskManager {
	
	public static final String SERVICE = "sysTaskManager";
	
	/**
	 * 系统执行任务
	 * @param callable
	 * @param asyn
	 * @return
	 */
	<T extends Object> T executeTask(Callable<T> callable, boolean asyn);
	
	/**
	 * 关闭任务引擎
	 */
	void close();
	
}
