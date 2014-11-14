/**
 * 
 */
package cn.com.innodev.pdp.framework.mail;

import java.util.Collection;

import com.vanstone.fs.FSFile;
import com.vanstone.mail.MailObject;

/**
 * @author shipeng
 */
public interface MailManager {
	
	/**
	 * 发送邮件
	 * @param mailObject
	 */
	void send(MailObject mailObject);
	
	/**
	 * 发送邮件
	 * @parm toAddress
	 * @param title
	 * @param htmlContent
	 */
	void send(String toAddress, String title, String htmlContent);
	
	/**
	 * 发送邮件
	 * @param tilte
	 * @param htmlContent
	 * @param attachments
	 */
	void send(String toAddress, String title, String htmlContent, Collection<Attachment> attachments);
	
	/**
	 * 邮件附件信息
	 * @author shipeng
	 */
	public static class Attachment {
		
		private FSFile fsFile;
		private String name;
		private String description;
		
		public Attachment(FSFile fsFile, String name, String description) {
			this.fsFile = fsFile;
			this.name = name;
			this.description = description;
		}
		
		public FSFile getFsFile() {
			return fsFile;
		}

		public String getName() {
			return name;
		}
		
		public String getDescription() {
			return description;
		}
		
	}
}
