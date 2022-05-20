package com.autumn.system.tools;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

/**
 * json转换工具
 * 
 * @param <T>
 *            实体类型
 */

@Component
public class JsonUtil<T> {

	/**
	 * 实体转json字符串
	 * 
	 * @param t
	 * @return
	 */
	public String objectToJSON(T t) {
		Gson gson = new Gson();
		return gson.toJson(t);
	}

	/**
	 * 实体集合转json字符串
	 * 
	 * @param objs
	 * @return
	 */
	public String objectsToJSON(List<T> objs) {
		Gson gson = new Gson();
		return gson.toJson(objs);
	}

	/**
	 * json字符串转实体
	 * 
	 * @param cla
	 *            实体原型 {"name":"lidebao","age":32}
	 */
	public T jsonToObject(String json, Class<T> cla) {
		Gson gson = new Gson();
		T t = (T) gson.fromJson(json, cla);
		return t;
	}

	/**
	 * json数组[{"age":32}]
	 * 
	 * @param json
	 * @return
	 */
	public List<T> jsonToObjects(String json) {
		Gson gson = new Gson();
		Type listType = new TypeToken<LinkedList<T>>() {
		}.getType();
		LinkedList<T> objs = gson.fromJson(json, listType);
		return objs;
	}

	public List<Map<String, String>> jsonArrToMap(String jsonArr) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			JsonReader reader = new JsonReader(new StringReader(jsonArr));
			reader.beginArray();
			while (reader.hasNext()) {
				reader.beginObject();
				Map<String, String> map = new HashMap<String, String>();
				while (reader.hasNext()) {
					String tagName = reader.nextName();
					String val = reader.nextString();
					map.put(tagName, val);
				}
				list.add(map);
				reader.endObject();
			}
			reader.endArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}