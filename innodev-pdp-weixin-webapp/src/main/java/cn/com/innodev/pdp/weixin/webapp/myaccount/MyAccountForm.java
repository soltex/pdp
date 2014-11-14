/**
 * 
 */
package cn.com.innodev.pdp.weixin.webapp.myaccount;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author shipeng
 */
public class MyAccountForm {
	
	/////////////////////////////////////////
	private String mobile;
	private String validatecode;
	private String openId;
	/////////////////////////////////////////
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getValidatecode() {
		return validatecode;
	}

	public void setValidatecode(String validatecode) {
		this.validatecode = validatecode;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	/////////////////////////////////////////
	private List<Integer> roomIds = new ArrayList<Integer>();
	/////////////////////////////////////////
	
	public List<Integer> getRoomIds() {
		return roomIds;
	}

	public void setRoomIds(List<Integer> roomIds) {
		this.roomIds = roomIds;
	}
	
	/////////////////////////////////////////
	private String email;
	private String fullName;
	private String nickName;
	private Integer gender;
	private String profession;
	private String interest;
	/////////////////////////////////////////
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}
	
	/////////////////////////////////////////
	private MultipartFile headerMultipartFile;
	/////////////////////////////////////////

	public MultipartFile getHeaderMultipartFile() {
		return headerMultipartFile;
	}

	public void setHeaderMultipartFile(MultipartFile headerMultipartFile) {
		this.headerMultipartFile = headerMultipartFile;
	}
	
}
