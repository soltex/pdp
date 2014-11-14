/**
 * 
 */
package cn.com.innodev.pdp.framework.mail;

import java.util.Collection;
import java.util.concurrent.Callable;

import org.apache.commons.mail.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.innodev.pdp.framework.mail.hostconf.MailHostConf;
import cn.com.innodev.pdp.framework.systask.SysTaskManager;

import com.vanstone.centralserver.common.MyAssert;
import com.vanstone.mail.MailObject;

/**
 * @author shipeng
 */
@Service("mailManager")
public class DefaultMailManagerImpl implements MailManager {

	private static Logger LOG = LoggerFactory.getLogger(DefaultMailManagerImpl.class);
	
	@Autowired
	private SysTaskManager sysTaskManager;
	
	@Override
	public void send(final MailObject mailObject) {
		MyAssert.notNull(mailObject);
		sysTaskManager.executeTask(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				try {
					mailObject.send();
				} catch (EmailException e) {
					e.printStackTrace();
					LOG.error("Send Mail Fail.");
				}
				return null;
			}
		}, true);
	}

	@Override
	public void send(String toAddress, String title, String htmlContent) {
		MyAssert.hasText(title);
		MyAssert.hasText(htmlContent);
		MailObject mailObject = new MailObject(MailHostConf.getMailHostConf());
		mailObject.setSubject(title);
		try {
			mailObject.addTo(toAddress);
			mailObject.setHtmlMsg(htmlContent);
		} catch (EmailException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
		this.send(mailObject);
	}

	@Override
	public void send(String toAddress, String title, String htmlContent, Collection<Attachment> attachments) {
		MyAssert.hasText(title);
		MyAssert.hasText(htmlContent);
		MailObject mailObject = new MailObject(MailHostConf.getMailHostConf());
		mailObject.setSubject(title);
		try {
			mailObject.addTo(toAddress);
			mailObject.setHtmlMsg(htmlContent);
		} catch (EmailException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
		if (attachments != null && attachments.size() >0) {
			for (Attachment attachment : attachments) {
				try {
					mailObject.attach(attachment.getFsFile().getPhycicalpath(), attachment.getName(), attachment.getDescription());
				} catch (EmailException e) {
					e.printStackTrace();
					throw new IllegalArgumentException(e);
				}
			}
		}
		this.send(mailObject);
	}
	
}
