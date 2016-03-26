package com.akjava.lib.common.utils;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.akjava.gwt.lib.client.LogUtils;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

public class TemplateUtils {
	public final static String TEMPLATE_VALUE_END_STRING="${";
	public final  static String TEMPLATE_VALUE_START_STRING="}";

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
	public static String createAdvancedText(String template,String value){
		Map<String,String> map=Maps.newHashMap();
		map.put("value", value);
		return createAdvancedText(template, map);
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
			
			//test
			result=result.replace("${C+"+key+"}", ValuesUtils.toUnderBarToUpperCamel(value));
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
	
	public static Set<String> findTemplateKeys(String text){
		Set<String> keys=new HashSet<String>();
		if(Strings.isNullOrEmpty(text)){
			return keys;
		}
		
		int start=0;
		while(start!=-1){
			start=text.indexOf(TEMPLATE_VALUE_END_STRING, start);
			if(start!=-1){
				int end=text.indexOf(TEMPLATE_VALUE_START_STRING,start+TEMPLATE_VALUE_END_STRING.length());
				if(end==-1){
					break;
				}else{
					String key=text.substring(start+TEMPLATE_VALUE_END_STRING.length(),end);
					keys.add(key);
					LogUtils.log("add:"+key);
				}
				start=end+TEMPLATE_VALUE_END_STRING.length();
			}
		}
		return keys;
	}
	
	public static TemplateKey labelToTemplateKey(String label){
		checkNotNull(label);
		List<String> vs=Splitter.on("+").splitToList(label);
		
		if(vs.size()>1){
			return new TemplateKey(vs.get(1),vs.get(0));
		}else{
			return new TemplateKey(label);
		}
	}
	public static class TemplateKey{
		private String key;
		private String option;
		public TemplateKey(String key) {
			this(key,null);
		}
		public TemplateKey(String key, String option) {
			super();
			this.key = key;
			this.option = option;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getOption() {
			return option;
		}
		public void setOption(String option) {
			this.option = option;
		}
		
		public String toString(){
			String header="";
			if(!Strings.isNullOrEmpty(option)){
				header=option+"+";
			}
			return TEMPLATE_VALUE_START_STRING+header+key+TEMPLATE_VALUE_END_STRING;
		}
		
	}
}
