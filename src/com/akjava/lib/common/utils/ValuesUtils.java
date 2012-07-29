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
}
