/**
 * 
 */
package cn.com.innodev.pdp.framework.mail.hostconf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.innodev.pdp.framework.Constants;

import com.vanstone.configuration.client.ConfigurationFactory;
import com.vanstone.configuration.client.IConfigurationManager;
import com.vanstone.mail.IHostConf;

/**
 * 当前邮件主机配置信息配置常量内容来自 Configuration Server
 * @author shipeng
 */
public class MailHostConf implements IHostConf {

	private static class MainHostConfInstance {
		private static MailHostConf instance = new MailHostConf();
	}
	
	private MailHostConf() {}
	
	public static MailHostConf getMailHostConf() {
		return MainHostConfInstance.instance;
	}
	
	private static Logger LOG = LoggerFactory.getLogger(MailHostConf.class);
	
	private IConfigurationManager configurationManager = ConfigurationFactory.getConfigurationManager();
	
	/* (non-Javadoc)
	 * @see com.vanstone.mail.IHostConf#getSmtp()
	 */
	@Override
	public String getSmtp() {
		return configurationManager.getValue(Constants.INNODEV_PDP_MAIL_GROUP, Constants.MailConf.Smtp.getCode());
	}
	
	/* (non-Javadoc)
	 * @see com.vanstone.mail.IHostConf#getSmtpPort()
	 */
	@Override
	public int getSmtpPort() {
		return configurationManager.getIntegerValue(Constants.INNODEV_PDP_MAIL_GROUP, Constants.MailConf.SmtpPort.getCode());
	}
	
	/* (non-Javadoc)
	 * @see com.vanstone.mail.IHostConf#getFromEmailAddress()
	 */
	@Override
	public String getFromEmailAddress() {
		return configurationManager.getValue(Constants.INNODEV_PDP_MAIL_GROUP, Constants.MailConf.FromEmailAddress.getCode());
	}
	
	/* (non-Javadoc)
	 * @see com.vanstone.mail.IHostConf#getEmailUsername()
	 */
	@Override
	public String getEmailUsername() {
		return configurationManager.getValue(Constants.INNODEV_PDP_MAIL_GROUP, Constants.MailConf.EmailUsername.getCode());
	}

	/* (non-Javadoc)
	 * @see com.vanstone.mail.IHostConf#getEmailUserPwd()
	 */
	@Override
	public String getEmailUserPwd() {
		return configurationManager.getValue(Constants.INNODEV_PDP_MAIL_GROUP, Constants.MailConf.EmailUserPwd.getCode());
	}
	
	/* (non-Javadoc)
	 * @see com.vanstone.mail.IHostConf#isSecurityable()
	 */
	@Override
	public boolean isSecurityable() {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.vanstone.mail.IHostConf#isDebugable()
	 */
	@Override
	public boolean isDebugable() {
		if (LOG.isDebugEnabled()) {
			return true;
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see com.vanstone.mail.IHostConf#getCharset()
	 */
	@Override
	public String getCharset() {
		return Constants.SYS_CHARSET_STRING;
	}
	
}
