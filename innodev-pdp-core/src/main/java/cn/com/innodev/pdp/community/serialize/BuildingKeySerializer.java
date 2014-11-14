/**
 * 
 */
package cn.com.innodev.pdp.community.serialize;

import java.lang.reflect.Type;

import cn.com.innodev.pdp.community.Building;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * Building key 序列化以及反序列化
 * @author shipeng
 */
public class BuildingKeySerializer implements JsonDeserializer<Building> {

	@Override
	public Building deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		if (json == null) {
			return null;
		}
		System.out.println(json.toString());
		Building building = new Building();
		JsonObject buildingJsonObject = json.getAsJsonObject();
		Integer id = buildingJsonObject.get("id") != null ? buildingJsonObject.get("id").getAsInt() : null;
		String buildingNo = buildingJsonObject.get("buildingNo") != null ? buildingJsonObject.get("buildingNo").getAsString() : null;
		String content = buildingJsonObject.get("content") != null ? buildingJsonObject.get("content").getAsString() : null ;
		building.setId(id);
		building.setBuildingNo(buildingNo);
		building.setContent(content);
		return building;
	}
	
}
