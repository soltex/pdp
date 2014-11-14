/**
 * 
 */
package cn.com.innodev.pdp.admin.webapp.example;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @author shipeng
 */
public class ExampleForm {
	
	private ExampleState exampleState;
	private Date releaseTime;
	
	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public ExampleState getExampleState() {
		return exampleState;
	}

	public void setExampleState(ExampleState exampleState) {
		this.exampleState = exampleState;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
