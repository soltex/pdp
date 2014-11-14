package cn.com.innodev.pdp.community.serialize;

import java.util.Date;

import cn.com.innodev.pdp.community.Building;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vanstone.business.serialize.DateSerializer;
import com.vanstone.business.serialize.GsonCreator;

/**
 * 社区Json转换器
 * @author shipeng
 */
public abstract class GsonCreatorOfCommunity extends GsonCreator {
	
	/**
	 * 创建Community的Gson
	 * @return
	 */
	public static Gson createCommunityGson() {
		Gson gson = new GsonBuilder().
				registerTypeAdapter(Building.class, new BuildingKeySerializer()).
				registerTypeAdapter(Date.class, new DateSerializer()).
				create();
		return gson;
	}
}
