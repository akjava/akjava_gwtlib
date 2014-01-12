package com.akjava.lib.common.utils;

import java.util.List;

public class StringUtils {
	private StringUtils(){}
	
	//technically this is for GWT
	 public static boolean isWhitespace(String text){
	    	return text.equals(" ") || text.equals("\t");
	    }
	 
	 public static String replaceStrings(String text,List<List<String>> values){
		 String result=text;
		 for(List<String> value:values){
		 if(value.size()>1){
			 result=result.replace(value.get(0), value.get(1));
		 	}
		 }
		 return result;
	 }
}
