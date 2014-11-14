/**
 * 
 */
package cn.com.innodev.pdp.weixin;

import com.vanstone.centralserver.common.weixin.WeixinException;
import com.vanstone.centralserver.common.weixin.wrap.msg.CCMsg4Text;
import com.vanstone.weixin.client.IWeixinAPIManager;
import com.vanstone.weixin.client.WeixinClientFactory;

/**
 * @author shipeng
 */
public class WeixinTest {
	
	public static void main(String[] args) {
		IWeixinAPIManager weixinAPIManager = WeixinClientFactory.getWeixinAPIManager();
		
		CCMsg4Text text = new CCMsg4Text();
		text.setContent("呵呵呵呵");
		text.setTouser("oE9mFuDKRV09gLRjZL5a6UkIB4UY");
		try {
			weixinAPIManager.sendCCMsgText("jiujuyayuan", text);
		} catch (WeixinException e) {
			e.printStackTrace();
		}
	}
	
}
