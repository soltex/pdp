/**
 * 
 */
package cn.com.innodev.example.common;

import com.vanstone.business.def.AbstractBusinessObject;

/**
 * @author shipeng
 */
public class Province extends AbstractBusinessObject {
	
	private static final long serialVersionUID = 7922948206056005003L;
	
	private Integer id;
	private String title;
	private char firstLetter;
	
	@Override
	public Integer getId() {
		return this.id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public char getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(char firstLetter) {
		this.firstLetter = firstLetter;
	}
	
}
