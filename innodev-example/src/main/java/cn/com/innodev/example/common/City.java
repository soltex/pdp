/**
 * 
 */
package cn.com.innodev.example.common;

import com.vanstone.business.def.AbstractBusinessObject;

/**
 * @author shipeng
 *
 */
public class City extends AbstractBusinessObject {
	
	private static final long serialVersionUID = -5941512095915836883L;
	
	private Integer id;
	private String title;
	private char firstLetter;
	private Province province;
	
	public City(Province province) {
		this.province = province;
	}
	
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

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
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