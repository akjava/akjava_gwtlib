package com.akjava.lib.common.form.page;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

//import javax.servlet.http.HttpServletRequest;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;


public class QueryUtils {
	public static List<String> indexOf(String key,Collection<String> values){
		return Lists.newArrayList(Iterables.transform(values, new IndexOfAll(key,true)));
	}
	
	public static List<String> notIndexOf(String key,Collection<String> values){
		return Lists.newArrayList(Iterables.transform(values, new IndexOfAll(key,false)));
	}
	
	public static class IndexOfAll implements Function<String,String>{
		private String key;
		private boolean contain;
		private String quote;
		public IndexOfAll(String key,boolean contain){
			this(key,contain,"'");
		}
		public IndexOfAll(String key,boolean contain,String quote){
			this.key=key;
			this.contain=contain;
			this.quote=quote;
		}
		
		@Override
		public String apply(String value) {
			if(contain){
				return "this."+key+".indexOf("+quote+value+quote+")!=-1";
			}else{
				return "this."+key+".indexOf("+quote+value+quote+")==-1";
			}
		}
		
	}
	
	public static class Equal implements Function<String,String>{
		private String key;

		private String quote;
		public Equal(String key){
			this(key,"'");
		}
		public Equal(String key,String quote){
			this.key=key;

			this.quote=quote;
		}
		
		@Override
		public String apply(String value) {
			return "this."+key+" == "+quote+value+quote+"";
		}
		
	}
	
	/*
	 * queryの値を置き換える
	 * request.getQueryStringで取り出した(文字コードとか同じの方が楽)値を置き換えよう
	 */
	public static String replace(String origin,Map<String,String> replaced){
		if(origin==null){
			origin="";
		}
		List<String> keyValueList=new ArrayList<String>();
		String[] key_values=origin.split("&");
		for (String key_value:key_values) {
			String[] k_v=key_value.split("=");
			if(k_v.length>1){
			String value=replaced.get(k_v[0]);
			if(value!=null){
				k_v[1]=value;
				replaced.remove(k_v[0]);//not need
			}
			keyValueList.add(k_v[0]+"="+k_v[1]);
			}
			
		}
		
		//追加
		for(String key:replaced.keySet()){
			keyValueList.add(key+"="+replaced.get(key));
		}
		
		String qwery=Joiner.on('&').join(keyValueList);
		
		return qwery;
	}
	
}
