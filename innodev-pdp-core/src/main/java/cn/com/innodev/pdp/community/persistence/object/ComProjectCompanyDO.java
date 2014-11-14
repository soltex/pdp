package cn.com.innodev.pdp.community.persistence.object;

public class ComProjectCompanyDO {
	private String communityId;

	private String logoFileId;

	private String logoFileExt;

	private Integer logoFileWidth;

	private Integer logoFileHeight;

	private String companyName;

	private String companyEmail;

	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}

	public String getLogoFileId() {
		return logoFileId;
	}

	public void setLogoFileId(String logoFileId) {
		this.logoFileId = logoFileId;
	}

	public String getLogoFileExt() {
		return logoFileExt;
	}

	public void setLogoFileExt(String logoFileExt) {
		this.logoFileExt = logoFileExt;
	}

	public Integer getLogoFileWidth() {
		return logoFileWidth;
	}

	public void setLogoFileWidth(Integer logoFileWidth) {
		this.logoFileWidth = logoFileWidth;
	}

	public Integer getLogoFileHeight() {
		return logoFileHeight;
	}

	public void setLogoFileHeight(Integer logoFileHeight) {
		this.logoFileHeight = logoFileHeight;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}
}