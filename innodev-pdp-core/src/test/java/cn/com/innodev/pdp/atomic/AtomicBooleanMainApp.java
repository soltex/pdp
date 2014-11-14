/**
 * 
 */
package cn.com.innodev.pdp.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shipeng
 */
public class AtomicBooleanMainApp {
	
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(100);
		for (int i=0;i<200;i++) {
			final int temp = i;
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					int mod = temp%2;
					if (mod == 0) {
						GlobalButton.getInstance().close();
					}else{
						GlobalButton.getInstance().start();
					}
					System.out.println("当前开关状态 : " + GlobalButton.getInstance().isStarted());
				}
			});
		}
		executorService.shutdown();
	}
	
}
