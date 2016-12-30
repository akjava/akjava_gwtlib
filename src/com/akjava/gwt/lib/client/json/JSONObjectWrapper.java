package com.akjava.gwt.lib.client.json;

import com.akjava.gwt.lib.client.LogUtils;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayBoolean;
import com.google.gwt.core.client.JsArrayNumber;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

public  class JSONObjectWrapper{
	private JSONObject jsonObject;

	public JSONObject jsonObject(){
		return jsonObject;
	}
	public JSONObjectWrapper(JSONObject object) {
		super();
		this.jsonObject = object;
	}
	
	public void setArrayBoolean(String key,JsArrayBoolean value){
		jsonObject.put(key, new JSONArray(value));
	}
	public JsArrayBoolean getArrayBoolean(String key){
		if(!jsonObject.containsKey(key)){
			return null;
		}
		JSONArray value=jsonObject.get(key).isArray();
		if(value==null){
			LogUtils.log("not array");
			return null;
		}
		
		return value.getJavaScriptObject().cast();
	}
	
	public void setArrayNumber(String key,JsArrayNumber value){
		jsonObject.put(key, new JSONArray(value));
	}
	
	public void setArray(String key,@SuppressWarnings("rawtypes") JsArray value){
		jsonObject.put(key, new JSONArray(value));
	}
	
	
	
	public void setObject(String key,JSONObject value){
		jsonObject.put(key, value);
	}
	
	public void setInt(String key,int value){
		jsonObject.put(key, new JSONNumber(value));
	}
	public void setDouble(String key,double value){
		jsonObject.put(key, new JSONNumber(value));
	}
	public void setBoolean(String key,boolean value){
		jsonObject.put(key, JSONBoolean.getInstance(value));
	}
	public void setString(String key,String value){
		jsonObject.put(key, new JSONString(value));
	}
	
	public JSONObjectWrapper getObject(String key){
		if(!jsonObject.containsKey(key)){
			return null;
		}
		JSONObject value=jsonObject.get(key).isObject();
		if(value==null){
			LogUtils.log("not object");
			return null;
		}
		return new JSONObjectWrapper(value);
	}
	
	public JsArray<JavaScriptObject> getArray(String key){
		if(!jsonObject.containsKey(key)){
			return null;
		}
		JSONArray value=jsonObject.get(key).isArray();
		if(value==null){
			LogUtils.log("not array");
			return null;
		}
		return value.getJavaScriptObject().cast();
	}
	
	public JsArrayNumber getArrayNumber(String key){
		if(!jsonObject.containsKey(key)){
			return null;
		}
		JSONArray value=jsonObject.get(key).isArray();
		if(value==null){
			LogUtils.log("not array");
			return null;
		}
		
		return value.getJavaScriptObject().cast();
	}
	
	public double getDouble(String key,Double defaultValue){
		if(!jsonObject.containsKey(key)){
			return defaultValue;
		}
		JSONNumber value=jsonObject.get(key).isNumber();
		if(value==null){
			LogUtils.log("not number");
			return defaultValue;
		}
		
		return value.doubleValue();
	}
	public int getInt(String key,Integer defaultValue){
		if(!jsonObject.containsKey(key)){
			return defaultValue;
		}
		JSONNumber value=jsonObject.get(key).isNumber();
		if(value==null){
			LogUtils.log("not number");
			return defaultValue;
		}
		
		return (int)value.doubleValue();
	}
	public String getString(String key,String defaultValue){
		if(!jsonObject.containsKey(key)){
			return defaultValue;
		}
		JSONString value=jsonObject.get(key).isString();
		if(value==null){
			LogUtils.log("not string");
			return defaultValue;
		}
		return value.stringValue();
	}
	public boolean getBoolean(String key,Boolean defaultValue){
		if(!jsonObject.containsKey(key)){
			return defaultValue;
		}
		JSONBoolean number=jsonObject.get(key).isBoolean();
		if(number==null){
			LogUtils.log("not boolean");
			return defaultValue;
		}
		return number.booleanValue();
	}
}