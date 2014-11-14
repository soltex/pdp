/**
 * 
 */
package cn.com.innodev.pdp.framework.bizcommon.region;

import com.vanstone.business.def.AbstractBusinessObject;

/**
 * 城市
 * @author shipeng
 */
public class City extends AbstractBusinessObject implements Region {
	
	private static final long serialVersionUID = -5941512095915836883L;
	
	private Integer id;
	private String title;
	private Character firstLetter;
	private Province province;
	private int communityCount = 0;
	
	/**
	 * 仅用于缓冲对象序列化
	 */
	//public City() {}
	
	public City(Province province) {
		this.province = province;
	}
	
	public City(Province province, int communityCount) {
		this.province = province;
		this.communityCount = communityCount;
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

	public Character getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(Character firstLetter) {
		this.firstLetter = firstLetter;
	}

	public int getCommunityCount() {
		return communityCount;
	}
	
}