/**
 * 
 */
package cn.com.innodev.pdp.framework.sms;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author shipeng
 */
public abstract class AbstractSMS implements SMS {

	protected static Logger LOG = LoggerFactory.getLogger(AbstractSMS.class);
	
	private String mobille;
	private String content;
	protected Date createTime;
	
	public AbstractSMS(String mobile, String content) {
		this.mobille = mobile;
		this.content = content;
	}
	
	@Override
	public Date getCreateTime() {
		return this.createTime;
	}

	@Override
	public String getMobile() {
		return this.mobille;
	}

	@Override
	public String getContent() {
		return this.content;
	}

	@Override
	public boolean send(boolean asyn) {
		boolean isok = sendInternal(asyn);
		this.createTime = new Date();
		return isok;
	}
	
	/**
	 * 内部发送
	 * @param asyn
	 * @return
	 */
	protected abstract boolean sendInternal(boolean asyn);
}
