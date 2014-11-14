/**
 * 
 */
package cn.com.innodev.pdp.proprietor;

import com.vanstone.business.lang.BaseEnum;

/**
 * 
 * @author shipeng
 */
public enum VisitorQRState implements BaseEnum<Integer> {
	
	Apply_QR("申请访客二维码",0), Completed_QR_Info("完善二维码信息",10)
	
	//, Validated_QR("验证通过访客二维码信息", 20)
	;
	
	private String desc;
	private Integer code;
	
	private VisitorQRState(String desc, Integer code) {
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
