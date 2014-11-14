/**
 * 
 */
package cn.com.innodev.pdp.gson.app;

import cn.com.innodev.pdp.gson.SystemInfo;
import cn.com.innodev.pdp.gson.UserInfo;

import com.google.gson.Gson;
import com.vanstone.business.serialize.GsonCreator;

/**
 * @author shipeng
 *
 */
public class GsonMainApp {
	
	public static void main(String[] args) {
		UserInfo userInfo = new UserInfo("shipeng");
		Gson gson = GsonCreator.create();
		System.out.println(gson.toJson(userInfo));
		String json = gson.toJson(userInfo);
		
		Gson gson1 = GsonCreator.create();
		
		UserInfo userInfo2 = gson1.fromJson(json, UserInfo.class);
		System.out.println(userInfo2.getId());
		
		Gson gson3 = GsonCreator.create();
		SystemInfo systemInfo = new SystemInfo(userInfo);
		systemInfo.setContent("呵呵呵呵呵呵呵");
		String systemInfoJson = gson3.toJson(systemInfo);
		
		Gson gson4 = GsonCreator.create();
		SystemInfo systemInfo2 = gson4.fromJson(systemInfoJson, SystemInfo.class);
		System.out.println(systemInfo2.getContent());
		System.out.println(systemInfo2.getUserInfo().getUserName());
	}
	
}
