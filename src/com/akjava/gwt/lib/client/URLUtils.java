package com.akjava.gwt.lib.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.URL;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;

public class URLUtils {
	public static String getLocalChangedUrl(String locale){
		return getLocalChangedUrl(locale,null);
	}
	public static String getLocalChangedUrl(String locale,String newtoken){
		Map<String,List<String>> params=Window.Location.getParameterMap();//cant modify
		Map<String,List<String>> changedParams=new HashMap<String,List<String>>();
		for(String key:params.keySet()){
			if(!key.equals("locale")){
				changedParams.put(key, params.get(key));
				//GWT.log("add:"+key);
			}
		}
		
		if(!locale.isEmpty()){
			changedParams.put("locale", Arrays.asList(locale));	
		}
		
		String token=null;
		if(newtoken==null){
		token=History.getToken();
		if(!token.isEmpty()){
			token="#"+token;
		}}else{
			token="#"+newtoken;
		}
		
		return Window.Location.getPath()+createQuery(changedParams)+token;
	}
	

	public static String getCurrentUrl(boolean path,boolean query,boolean hash){
		String url="";
		if(path){
		url+=Window.Location.getPath();	
		}
		if(query){
		url+=Window.Location.getQueryString();
		}
		if(hash){
		url+=Window.Location.getHash();
		}
		
		return url;
	}
	
	public static String getFirstValue(Map<String,List<String>> map,String key){
		if(map.get(key)!=null && map.get(key).size()>0){
			return map.get(key).get(0);
		}else{
			return null;
		}
	}
	
	public static String createUrl(String path,Map<String,List<String>> query,Map<String,List<String>> hash){
		String url="";
		url+=path;
		
		if(query!=null){
		String q=createQuery(query);
		if(q.length()>1){
			url+=q;
		}
		}
		
		if(hash!=null){
			String h=joinMaps(hash,"#",";","=");
			if(h.length()>1){
				url+=h;
			}
			}
		
		return url;
	}
	
	//query ?,&,=
	//favorite token #,;,=
	public static String joinMaps(Map<String,List<String>> params,String head,String joiner,String equaler){
		StringBuilder builder=new StringBuilder();
		
		for(String key:params.keySet()){
			List<String> values=params.get(key);
			
			for(String value:values){
				builder.append(key+equaler+URL.encodeQueryString(value)+joiner);
			}
			if(values.size()==0){//key only
				builder.append(key+joiner);
			}
		}
		if(builder.length()>0){
			builder.delete(builder.length()-1,builder.length() );//chop last joiner;
		}
		return head+builder.toString();
	}
	

	public static String createQuery(Map<String,List<String>> params){
		StringBuilder builder=new StringBuilder();
		
		for(String key:params.keySet()){
			List<String> values=params.get(key);
			
			for(String value:values){
				builder.append(key+"="+URL.encodeQueryString(value)+"&");
			}
		}
		if(builder.length()>0){
			builder.delete(builder.length()-1,builder.length() );//chop
		}
		return joinMaps(params,"?","&","=");
	}
	
	public static void redirectLocale(String locale){
		redirectLocale(locale,null);
	}
	public static void redirectLocale(String locale,String newtoken){
		String current=LocaleInfo.getCurrentLocale().getLocaleName();
		if(current.equals("default")){
			current="";
		}
		if(locale.equals("default")){
			locale="";
		}
		if(!current.equals(locale)){
			Window.open(getLocalChangedUrl(locale,newtoken), "_self", null);
		}
	}
	public static void refresh(){
		//GWT.log("refresh-href:"+Window.Location.getHref());
		Window.open(Window.Location.getHref(), "_self", null);
	}
	
	
	public static Map<String,List<String>> parseToken(String token,boolean ignoreEmpty){
		Map<String,List<String>> params=new HashMap<String,List<String>>();
		String values[]=token.split("&");
		for(String v:values){
			String name_value[]=v.split("=");
			if(name_value.length==2 && !name_value[0].isEmpty()){
				List<String> vs=params.get(name_value[0]);
				if(vs==null){
					vs=new ArrayList<String>();
					params.put(name_value[0], vs);
				}
				vs.add(name_value[1]);
			}else if(name_value.length==1 && !ignoreEmpty){
				params.put(name_value[0], new ArrayList<String>());
			}
		}
		return params;
	}
}
