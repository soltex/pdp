/**
 * 
 */
package cn.com.innodev.pdp.qr;

import java.io.File;

import cn.com.innodev.pdp.framework.Constants;

import com.google.gson.Gson;
import com.vanstone.business.serialize.GsonCreator;
import com.vanstone.centralserver.common.weixin.WeixinException;
import com.vanstone.centralserver.common.weixin.wrap.qrcode.Ticket;
import com.vanstone.weixin.client.IWeixinAPIManager;
import com.vanstone.weixin.client.WeixinClientFactory;

/**
 * 
 * @author shipeng
 */
public class QRMainApp {
	
	public static void main(String[] args) {
		IWeixinAPIManager weixinAPIManager = WeixinClientFactory.getWeixinAPIManager();
		try {
			Ticket ticket = weixinAPIManager.getTmpQCTicket(Constants.WEIXIN_SERVICE_NUM, 1800, -2147483648);
			Gson gson = GsonCreator.createPretty();
			System.out.println(gson.toJson(ticket));
			weixinAPIManager.downloadQCImage(ticket.getTicket(), new File("d:/aaa.jpg"));
		} catch (WeixinException e) {
			System.out.println(e.getErrorCode().getDesc() + " -- " + e.getErrorCode().getCode());
			e.printStackTrace();
		}
		weixinAPIManager.close();
	}
	
}
