package com.akjava.lib.common.utils;

import java.util.HashMap;
import java.util.Map;

public class TemplateUtils {
	/**
	 * very simple way just replace ${key} to value;
	 * @param template
	 * @param map
	 * @return
	 */
	public static String createText(String template,Map<String,String> map){
		if(map==null){
			throw new RuntimeException("null map");
		}
		if(template==null){
			throw new RuntimeException("null template");
		}
		String result=template;
		for(String key:map.keySet()){
			if(map.get(key)!=null){
			result=result.replace("${"+key+"}", map.get(key));
			}else{
				System.out.println("TemplateUtils.createText()#null value:"+key+" skipped");
			}
		}
		return result;
	}
	/**
	 * please dont insert null-key
	 * support upper-camel,lower-camel,upper-case,lower-case,filename-only,file-extension-name-only
	 * @param template
	 * @param map
	 * @return
	 */
	public static String createAdvancedText(String template,Map<String,String> map){
		if(map==null){
			throw new RuntimeException("null map");
		}
		if(template==null){
			throw new RuntimeException("null template");
		}
		String result=template;
		for(String key:map.keySet()){
			if(map.get(key)!=null){
			String value=map.get(key);
			result=result.replace("${"+key+"}",value);
			result=result.replace("${u+"+key+"}", ValuesUtils.toUpperCamel(value));
			result=result.replace("${l+"+key+"}", ValuesUtils.toLowerCamel(value));
			result=result.replace("${U+"+key+"}", value.toUpperCase());
			result=result.replace("${L+"+key+"}", value.toLowerCase());
			result=result.replace("${_+"+key+"}", value.replace("-","_").replace(" ", "_"));
			int mutch=value.lastIndexOf(".");
			if(mutch!=-1){
				result=result.replace("${name+"+key+"}",value.substring(0,mutch));
				result=result.replace("${ext+"+key+"}",value.substring(mutch+1));
			}else{//is this make slow?
				result=result.replace("${name+"+key+"}",value);
				result=result.replace("${ext+"+key+"}","");
				}
			}else{
				System.out.println("TemplateUtils.createText()#null value:"+key+" skipped");
			}
		}
		return result;
	}
	
	public static String createText(String template,String value){
		Map<String,String> map=new HashMap<String, String>();
		map.put("value", value);
		return createText(template,map);
	}
	
	
}
