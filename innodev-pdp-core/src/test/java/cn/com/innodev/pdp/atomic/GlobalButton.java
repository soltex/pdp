/**
 * 
 */
package cn.com.innodev.pdp.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author shipeng
 */
public class GlobalButton {
	
	/** 默认关闭状态*/
	public AtomicBoolean switchButton = new AtomicBoolean(false);
	
	private GlobalButton(){}
	
	private static class GlobalButtonInstance {
		private static final GlobalButton instance = new GlobalButton();
	}
	
	/**
	 * 获取当前实例
	 * @return
	 */
	public static GlobalButton getInstance() {
		return GlobalButtonInstance.instance;
	}
	
	/**
	 * 关闭开关
	 */
	public void close() {
		switchButton.set(false);
	}
	
	/**
	 * 开启开关
	 */
	public void start() {
		switchButton.set(true);
	}
	
	/**
	 * 获取当前状态
	 * @return
	 */
	public boolean isStarted() {
		return this.switchButton.get();
	}
	
}
