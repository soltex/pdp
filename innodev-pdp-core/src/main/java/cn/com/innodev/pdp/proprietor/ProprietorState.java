/**
 * 
 */
package cn.com.innodev.pdp.proprietor;

import com.vanstone.business.lang.BaseEnum;

/**
 * 业主状态
 * @author shipeng
 */
public enum ProprietorState implements BaseEnum<Integer> {
	
	Weixin_Wait_Auth("微信首次关注,等待认证",0), 
	Mobile_Email_Auth("手机号/邮件验证通过",1),
	Finished_Auth("全部验证通过",10),
	Cancel_Weixin("取消关注",50);
	
	private String desc;
	private Integer code;
	
	private ProprietorState(String desc, Integer code) {
		this.desc = desc;
		this.code = code;
	}
	
	@Override
	public Integer getCode() {
		return this.code;
	}

	@Override
	public String getDesc() {
		return this.desc;
	}
	
}
