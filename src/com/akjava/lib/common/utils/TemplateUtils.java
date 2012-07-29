package com.akjava.lib.common.utils;

import java.util.Map;

public class TemplateUtils {
	/**
	 * very simple way just replace ${key} to value;
	 * @param template
	 * @param map
	 * @return
	 */
	public static String createText(String template,Map<String,String> map){
		String result=template;
		for(String key:map.keySet()){
			result=result.replace("${"+key+"}", map.get(key));
		}
		return result;
	}
}
