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
	 
	 public static int countStartWith(String text,char ch){
		 if(text==null){
			 return 0;
		 }
		 int match=0;
		 for(int i=0;i<text.length();i++){
			 if(text.charAt(i)==ch){
				 match++;
			 }else{
				 break;
			 }
		 }
		 return match;
	 }
}
