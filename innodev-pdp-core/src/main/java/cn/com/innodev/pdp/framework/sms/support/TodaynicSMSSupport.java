/**
 * 
 */
package cn.com.innodev.pdp.framework.sms.support;

import java.util.Hashtable;
import java.util.concurrent.Callable;

import cn.com.innodev.pdp.framework.Constants;
import cn.com.innodev.pdp.framework.sms.AbstractSMS;
import cn.com.innodev.pdp.framework.systask.SysTaskManager;

import com.todaynic.client.mobile.SMS;
import com.vanstone.configuration.client.ConfigurationFactory;
import com.vanstone.configuration.client.IConfigurationManager;
import com.vanstone.framework.business.ServiceManagerFactory;

/**
 * 
 * @author shipeng
 */
public class TodaynicSMSSupport extends AbstractSMS {
	
	public TodaynicSMSSupport(String mobile, String content) {
		super(mobile, content);
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.framework.sms.AbstractSMS#sendInternal(boolean)
	 */
	@Override
	protected boolean sendInternal(boolean asyn) {
		SysTaskManager sysTaskManager = ServiceManagerFactory.getInstance().getService(SysTaskManager.SERVICE);
		Boolean isok = sysTaskManager.executeTask(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				Hashtable<String,String> configTable=new Hashtable<String,String>();
				IConfigurationManager configurationManager = ConfigurationFactory.getConfigurationManager();
				configTable.put("VCPSERVER",configurationManager.getValue(Constants.INNODEV_PDP_SMS_GROUP, Constants.SMS_Conf.VCPSERVER.getCode()));
				configTable.put("VCPSVPORT",configurationManager.getValue(Constants.INNODEV_PDP_SMS_GROUP, Constants.SMS_Conf.VCPSVPORT.getCode()));
				configTable.put("VCPUSERID",configurationManager.getValue(Constants.INNODEV_PDP_SMS_GROUP, Constants.SMS_Conf.VCPUSERID.getCode()));
				configTable.put("VCPPASSWD",configurationManager.getValue(Constants.INNODEV_PDP_SMS_GROUP, Constants.SMS_Conf.VCPPASSWD.getCode()));
				SMS sms = new SMS(configTable);
				try {
					boolean isok = sms.sendSMS(getMobile(),getContent(),"0","3");
					if (LOG.isDebugEnabled()) {
						String receiveXml = sms.getSendXml();
						String code = sms.getCode();
						String msg = sms.getMsg();
						System.out.println(isok);
						System.out.println(receiveXml);
						System.out.println(code);
						System.out.println(msg);
					}
					return isok;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
		}, asyn);
		if (asyn) {
			return true;
		}
		return isok;
	}
}
