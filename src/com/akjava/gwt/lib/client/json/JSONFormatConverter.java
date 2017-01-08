package com.akjava.gwt.lib.client.json;

import java.util.List;

import com.akjava.gwt.lib.client.LogUtils;
import com.google.common.base.Converter;
import com.google.common.collect.Lists;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

/*
 * try to similar Three.js JSON Geometry format 4
 */
public class JSONFormatConverter extends Converter<String,JSONValue>{
private String name="JSONFormatConverter";
public JSONFormatConverter(String name, String type, double version, String generator) {
	super();
	this.name = name;
	this.type = type;
	this.version = version;
	this.generator = generator;
}
public JSONFormatConverter(String name, String type){
	this(name,type,1,"JSONFormatConverter");
}

private String type;
private double version;
private String generator="JSONFormatConverter";

/*
 * return null if parse faild or not exist
 */
public static String parseDataType(String json){
	JSONValue value=null;
	try{
	value=JSONParser.parseStrict(json);
	}catch (Exception e) {
		LogUtils.log("json parse-faild:"+e.getMessage()+"\n"+json);
		return null;
	}
	if(value==null){
		LogUtils.log("getDataType"+":parse json faild.not json "+json);
		return null;
	}
	JSONObject object=value.isObject();
	if(object==null){
		LogUtils.log("getDataType"+":root is not json object:"+json);
		return null;
	}
	
	
		if(object.get("metadata")==null){
			LogUtils.log("getDataType"+":has no meta attribute:"+object.toString());
			return null;
		}
		
		JSONObject metaObject=object.get("metadata").isObject();
		if(metaObject==null){
			LogUtils.log("getDataType"+":meta is not object:"+object.toString());
			return null;
		}
		
		if(metaObject.get("type")==null){
			LogUtils.log("getDataType"+":has no type attribute:"+metaObject.toString());
			return null;
		}
		
		JSONString typeJSONString=metaObject.get("type").isString();
		if(typeJSONString==null){
			LogUtils.log("getDataType"+":has type,but not string:"+metaObject.toString());
			return null;
		}
		
		String typeString=typeJSONString.stringValue();
		return typeString;
	
}

	@Override
	protected JSONValue doForward(String json) {
		JSONValue value=null;
		try{
		value=JSONParser.parseStrict(json);
		}catch (Exception e) {
			LogUtils.log("json parse-faild:"+e.getMessage()+"\n"+json);
			return null;
		}
		if(value==null){
			LogUtils.log(name+":parse json faild.not json "+json);
			return null;
		}
		JSONObject object=value.isObject();
		if(object==null){
			LogUtils.log(name+":root is not json object:"+json);
			return null;
		}
		
		//meta-check
		if(type!=null){
			if(object.get("metadata")==null){
				LogUtils.log(name+":has no meta attribute:"+object.toString());
				return null;
			}
			
			JSONObject metaObject=object.get("metadata").isObject();
			if(metaObject==null){
				LogUtils.log(name+":meta is not object:"+object.toString());
				return null;
			}
			
			if(metaObject.get("type")==null){
				LogUtils.log(name+":has no type attribute:"+metaObject.toString());
				return null;
			}
			
			JSONString typeJSONString=metaObject.get("type").isString();
			if(typeJSONString==null){
				LogUtils.log(name+":has type,but not string:"+metaObject.toString());
				return null;
			}
			
			String typeString=typeJSONString.stringValue();
			if(!type.equals(typeString)){
				LogUtils.log(name+":must be "+type+"but json type is difference "+typeString);
				return null;
			}
		}
		
		JSONValue datasValue=object.get("data");
		if(datasValue==null){
			LogUtils.log(name+":no data:");
			return null;
		}
		
		return datasValue;
		
		
	}
	
	public List<JSONObject> toJsonObjectList(JSONValue datasValue){
		JSONArray array=datasValue.isArray();
		if(array==null){
			LogUtils.log(name+":data is not  jsonarray:");
			return null;
		}
		List<JSONObject> jsonObjects=Lists.newArrayList();
		for(int i=0;i<array.size();i++){
			JSONValue arrayValue=array.get(i);
			JSONObject arrayObject=arrayValue.isObject();
			if(arrayObject==null){
				LogUtils.log(name+":contain invalid data:"+i+","+arrayValue);
				return null;
			}
			jsonObjects.add(arrayObject);
		}
		return jsonObjects;
	}
	
	public JSONValue fromJsonObjectList(Iterable<JSONObject> datas){
		int index=0;
		JSONArray array=new JSONArray();
		for(JSONObject data:datas){
			array.set(index, data);
			index++;
		}
		return array;
	}

	@Override
	protected String doBackward(JSONValue data) {
		JSONObject root=new JSONObject();
		
		JSONObject meta=new JSONObject();
		meta.put("generator", new JSONString(generator));
		meta.put("version", new JSONNumber(version));
		meta.put("type", new JSONString(type));
		root.put("metadata", meta);
		
		root.put("data", data);
		return root.toString();
	}

}
