/**
 * 
 */
package cn.com.innodev.example.notification;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author shipeng
 */
public class TaskManager {
	
	private static class ThreadManagerInstance {
		private static TaskManager instance = new TaskManager();
	}
	
	private ExecutorService executorService = Executors.newFixedThreadPool(20);
	
	private TaskManager(){}
	
	public static TaskManager getInstance() {
		return ThreadManagerInstance.instance;
	}
	
	/**
	 * @param callable
	 * @param asyn
	 * @return
	 */
	public <T> T execute(Callable<T> callable, boolean asyn) {
		Future<T> future = this.executorService.submit(callable);
		if (asyn) {
			return null;
		}
		try {
			return future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ExecutionException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 关闭当前Manager
	 */
	public void close() {
		this.executorService.shutdown();
	}
}
