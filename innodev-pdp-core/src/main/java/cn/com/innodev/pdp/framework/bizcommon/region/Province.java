/**
 * 
 */
package cn.com.innodev.pdp.framework.bizcommon.region;

import com.vanstone.business.def.AbstractBusinessObject;

/**
 * 省份
 * @author shipeng
 */
public class Province extends AbstractBusinessObject implements Region {
	
	private static final long serialVersionUID = 7922948206056005003L;
	
	private Integer id;
	private String title;
	private Character firstLetter;
	private int cityCount;
	
	public Province() {}
	
	public Province(int cityCount) {
		this.cityCount = cityCount;
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

	public void setId(Integer id) {
		this.id = id;
	}

	public Character getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(Character firstLetter) {
		this.firstLetter = firstLetter;
	}

	public int getCityCount() {
		return cityCount;
	}

}
