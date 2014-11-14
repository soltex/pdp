/**
 * 
 */
package cn.com.innodev.pdp.framework.systask.impl;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.vanstone.centralserver.common.MyAssert;

import cn.com.innodev.pdp.framework.Constants;
import cn.com.innodev.pdp.framework.PlatformError;
import cn.com.innodev.pdp.framework.systask.SysTaskManager;

/**
 * 任务执行器
 * @author shipeng
 */
@Service("sysTaskManager")
public class SysTaskManagerImpl implements SysTaskManager {
	
	/** 默认线程池 */
	private ExecutorService executorService = Executors.newFixedThreadPool(Constants.DEFAULT_SYS_TASK_THREAD_NUM);
	
	private static Logger LOG = LoggerFactory.getLogger(SysTaskManagerImpl.class);
	
	public SysTaskManagerImpl() {
		LOG.info("Task Manager Initial Ok. Default Thread Num [{}]", Constants.DEFAULT_SYS_TASK_THREAD_NUM);
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.framework.systask.SysTaskManager#executeTask(java.util.concurrent.Callable, boolean)
	 */
	@Override
	public <T> T executeTask(Callable<T> callable, boolean asyn) {
		MyAssert.notNull(callable);
		LOG.debug("Start execute Task. ");
		Future<T> future = executorService.submit(callable);
		if (asyn) {
			LOG.debug("ASYN execute Callable.");
			return null;
		}
		try {
			LOG.debug("SYNC execute Callable.");
			T t = future.get();
			return t;
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new PlatformError(e);
		} catch (ExecutionException e) {
			e.printStackTrace();
			throw new PlatformError(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.framework.systask.SysTaskManager#close()
	 */
	@Override
	public void close() {
		this.executorService.shutdown();
	}

}
