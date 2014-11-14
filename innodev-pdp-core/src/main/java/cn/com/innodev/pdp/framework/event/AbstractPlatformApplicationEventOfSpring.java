/**
 * 
 */
package cn.com.innodev.pdp.framework.event;

import java.util.Date;

import org.springframework.context.ApplicationEvent;

import com.vanstone.framework.context.SpringContextHolder;

/**
 * @author shipeng
 *
 */
public abstract class AbstractPlatformApplicationEventOfSpring extends ApplicationEvent {

	/** */
	private static final long serialVersionUID = -6269901137226794108L;
	
	private Date createEventTime;
	
	public AbstractPlatformApplicationEventOfSpring(Object source) {
		super(source);
		this.createEventTime = new Date();
	}
	
	public Date getCreateEventTime() {
		return createEventTime;
	}
	
	/**
	 * 发布事件
	 */
	public void pushEvent() {
		SpringContextHolder.getApplicationContext().publishEvent(this);
	}
	
}
