package com.akjava.gwt.lib.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class JsonValueUtils {
	
	public static native final String stringify(JavaScriptObject json)/*-{
	return $wnd.JSON.stringify(json,null,2);
	}-*/;
	
	public static String getString(JSONObject object,String key,String defaultValue){
		if(object==null){
			return defaultValue;
		}
		
		JSONValue value=object.get(key);
		if(value==null){
			return defaultValue;
		}
		
		JSONString jsonString=value.isString();
		if(jsonString==null){
			return defaultValue;
		}
		return jsonString.stringValue();
	}
	
	public static JSONObject getObject(JSONObject object,String key,JSONObject defaultValue){
		if(object==null){
			return defaultValue;
		}
		
		JSONValue value=object.get(key);
		if(value==null){
			return defaultValue;
		}
		
		JSONObject jsonString=value.isObject();
		if(jsonString==null){
			return defaultValue;
		}
		return jsonString;
	}
	
	public static double getNumber(JSONObject object,String key,double defaultValue){
		if(object==null){
			return defaultValue;
		}
		JSONValue value=object.get(key);
		if(value==null){
			return defaultValue;
		}
		
		JSONNumber jsonString=value.isNumber();
		if(jsonString==null){
			return defaultValue;
		}
		return jsonString.doubleValue();
	}
	
	public static boolean getBoolean(JSONObject object,String key,boolean defaultValue){
		if(object==null){
			return defaultValue;
		}
		
		JSONValue value=object.get(key);
		if(value==null){
			return defaultValue;
		}
		
		JSONBoolean jsonString=value.isBoolean();
		if(jsonString==null){
			return defaultValue;
		}
		return jsonString.booleanValue();
	}
	public static JSONObject convertToJson(Map<String,String> map){
		
		JSONObject object=new JSONObject();
		for(String key:map.keySet()){
			JSONString valueString=new JSONString(map.get(key));
			object.put(key, valueString);
		}
		return object;
	}
	
	public static Map<String,String> createMap(JSONValue json){
		HashMap<String,String> map=new HashMap<String, String>();
		JSONObject obj=json.isObject();
		if(obj!=null){
			for(String key:obj.keySet()){
				JSONValue value=obj.get(key);
				JSONString valueJson=value.isString();
				if(valueJson!=null){
					map.put(key, valueJson.stringValue());
				}
			}
		}
		return map;
	}
	
}
