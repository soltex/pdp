/**
 * 
 */
package cn.com.innodev.pdp.proprietor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import cn.com.innodev.pdp.community.Community;
import cn.com.innodev.pdp.community.Room;
import cn.com.innodev.pdp.framework.bizcommon.Gender;
import cn.com.innodev.pdp.framework.bizcommon.ImageBean;

import com.vanstone.business.def.AbstractBusinessObject;

/**
 * @author shipeng
 */
public class Proprietor extends AbstractBusinessObject {

	/** */
	private static final long serialVersionUID = 510538662997620318L;

	private String id;
	/** 唯一手机号*/
	private String mobile;
	/** 唯一邮件地址*/
	private String email;
	/** 真实姓名*/
	private String fullName;
	/** 昵称*/
	private String nickName;
	/** 密码*/
	private String pwd;
	/** 微信OPENID*/
	private String weixinOpenid;
	/** 性别*/
	private Gender gender;
	/** 职业*/
	private String profession;
	/** 头像*/
	private ImageBean headImageBean;
	/** 业主状态*/
	private ProprietorState proprietorState = ProprietorState.Weixin_Wait_Auth;
	/** 系统写入时间*/
	private Date sysInsertTime;
	/** 系统更新时间*/
	private Date sysUpdateTime;
	/** 兴趣爱好*/
	private String interest;
	/** 房间号*/
	private Collection<Room> rooms = new ArrayList<Room>();
	/** 社区*/
	private Community community;
	
	public Proprietor(Community community) {
		this.community = community;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vanstone.business.def.AbstractBusinessObject#getId()
	 */
	@Override
	public String getId() {
		return this.id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getWeixinOpenid() {
		return weixinOpenid;
	}

	public void setWeixinOpenid(String weixinOpenid) {
		this.weixinOpenid = weixinOpenid;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public ImageBean getHeadImageBean() {
		return headImageBean;
	}

	public void setHeadImageBean(ImageBean headImageBean) {
		this.headImageBean = headImageBean;
	}

	public ProprietorState getProprietorState() {
		return proprietorState;
	}

	public void setProprietorState(ProprietorState proprietorState) {
		this.proprietorState = proprietorState;
	}

	public Date getSysInsertTime() {
		return sysInsertTime;
	}

	public void setSysInsertTime(Date sysInsertTime) {
		this.sysInsertTime = sysInsertTime;
	}

	public Date getSysUpdateTime() {
		return sysUpdateTime;
	}

	public void setSysUpdateTime(Date sysUpdateTime) {
		this.sysUpdateTime = sysUpdateTime;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public Collection<Room> getRooms() {
		return rooms;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void addRoom(Room room) {
		this.rooms.add(room);
	}
	
	public void addRooms(Collection<Room> rooms) {
		this.rooms.addAll(rooms);
	}
	
	public void clearRooms() {
		this.rooms.clear();
	}
	
	public Community getCommunity() {
		return community;
	}
	
}
