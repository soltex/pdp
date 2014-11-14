/**
 * 
 */
package cn.com.innodev.pdp.framework.zk;

import java.io.IOException;
import java.util.Properties;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.innodev.pdp.framework.Constants;

/**
 * 
 * @author shipeng
 */
public class PlatformZKManager {
	
	public static final String CONF = "/innodev-pdp-zk.properties";
	
	private static Logger LOG = LoggerFactory.getLogger(PlatformZKManager.class);
	
	private static class ZookeeperManagerInstance {
		private static PlatformZKManager instance = new PlatformZKManager();
		static {
			instance.start();
		}
	}
	
	private CuratorFramework curatorFramework;
	private String connectionString;
	private int connectionTimeoutMs;
	
	private PlatformZKManager() {
		Properties properties = new Properties();
		try {
			properties.load(this.getClass().getResourceAsStream(CONF));
			this.connectionString = properties.getProperty("zk.connectionString");
			this.connectionTimeoutMs = Integer.parseInt(properties.getProperty("zk.connectionTimeoutMs"));
		} catch (IOException e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
		curatorFramework = CuratorFrameworkFactory.builder().connectString(connectionString).retryPolicy(new RetryNTimes(Integer.MAX_VALUE, 1000)).connectionTimeoutMs(connectionTimeoutMs).build();
	}
	
	/**
	 * 获取Zookeeper单例
	 * @return
	 */
	public static PlatformZKManager getInstance() {
		return ZookeeperManagerInstance.instance;
	}
	
	/**
	 * 创建CuratorFramework客户端
	 * @return
	 */
	public CuratorFramework getCuratorFramework() {
		return curatorFramework;
	}

	/**
	 * Zookeeper连接字符串
	 * @return
	 */
	public String getConnectionString() {
		return connectionString;
	}

	/**
	 * Zookeeper连接失效时间
	 * @return
	 */
	public int getConnectionTimeoutMs() {
		return connectionTimeoutMs;
	}

	/**
	 * 启动当前管理器
	 * 启动CuratorFramework
	 * 创建节点路径
	 */
	public void start(){
		if (this.curatorFramework == null) {
			throw new IllegalArgumentException();
		}
		if (this.curatorFramework != null && (this.curatorFramework.getState().equals(CuratorFrameworkState.STOPPED) || 
						this.curatorFramework.getState().equals(CuratorFrameworkState.LATENT))) {
			this.curatorFramework.start();
		}
		LOG.info("Innodev PDP Zookeeper Start.");
		try {
			Stat stat = this.curatorFramework.checkExists().forPath(Constants.INNODEV_PDP_ZK_CACHE_LOCK_NODE);
			if (stat == null) {
				this.curatorFramework.create().creatingParentsIfNeeded().forPath(Constants.INNODEV_PDP_ZK_CACHE_LOCK_NODE);
				LOG.info("Innodev PDP Zookeeper Create Node : {}" , Constants.INNODEV_PDP_ZK_CACHE_LOCK_NODE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}
	
	/**
	 * 管理当前管理器，并关闭CuratorFramework
	 */
	public void close() {
		if (this.curatorFramework != null) {
			this.curatorFramework.close();
			LOG.info("Zookeeper Closed.");
		}
	}
}
