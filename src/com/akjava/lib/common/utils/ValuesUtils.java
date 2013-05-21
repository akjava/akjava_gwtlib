package com.akjava.lib.common.utils;


public class ValuesUtils {

	
	public static int toInt(String text,int defaultValue) {
		if(text==null || text.isEmpty()){
			return defaultValue;
		}
		int ret;
		try{
			ret=(int) Double.parseDouble(text);
		}catch(Exception e){
			return defaultValue;
		}
		return ret;
	}
	
	public static double toDouble(String text,double defaultValue) {
		if(text==null || text.isEmpty()){
			return defaultValue;
		}
		double ret;
		try{
			ret=Double.parseDouble(text);
		}catch(Exception e){
			return defaultValue;
		}
		return ret;
	}
	
	public static long toLong(String text,long defaultValue) {
		if(text==null || text.isEmpty()){
			return defaultValue;
		}
		long ret;
		try{
			ret=(long) Double.parseDouble(text);
		}catch(Exception e){
			return defaultValue;
		}
		return ret;
	}
	
	public static boolean toBoolean(String text,boolean defaultValue) {
		if(text==null || text.isEmpty()){
			return defaultValue;
		}
		boolean ret;
		try{
			ret= Boolean.parseBoolean(text);
		}catch(Exception e){
			return defaultValue;
		}
		return ret;
	}
	/*
	 * for the case without Guava
	 */
	public static String toUpperCamel(String text){
		if(text==null || text.length()==0){
			return text;
		}
		if(Character.isLowerCase(text.charAt(0))){
			return Character.toUpperCase(text.charAt(0))+text.substring(1);
		}else{
			return text;
		}
	}
	
	public static String toUnderBarToUpperCamel(String text){
		if(text==null || text.length()==0){
			return text;
		}
		String vs[]=text.split("_");
		if(vs.length==1){
			return text;
		}else{
			String result=vs[0];
			for(int i=1;i<vs.length;i++){
				result+=toUpperCamel(vs[i]);
			}
			return result;
		}
		
	}
	
	/*
	 * for the case without Guava
	 */
	public static String toLowerCamel(String text){
		if(text==null || text.length()==0){
			return text;
		}
		if(Character.isUpperCase(text.charAt(0))){
			return Character.toLowerCase(text.charAt(0))+text.substring(1);
		}else{
			return text;
		}
	}
}
