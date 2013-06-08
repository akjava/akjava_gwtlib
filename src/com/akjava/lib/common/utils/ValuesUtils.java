package com.akjava.lib.common.utils;

import java.util.ArrayList;
import java.util.List;

import com.akjava.gwt.lib.client.ValueUtils;


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
	
	public static String[] toArrayLines(String text){
		text=ValueUtils.toNLineSeparator(text);
		return text.split("\n");//this is slow
	}
	public static List<String> toListLines(String text){
		text=ValueUtils.toNLineSeparator(text);
		List<String> values=new ArrayList<String>();
		for(String v:text.split("\n")){
			values.add(v);
		}
		return values;
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
	
	public static String removeSuffix(String name,String suffix){
		if(name==null || suffix==null || !name.endsWith(suffix)){
			return name;
		}else{
			return name.substring(0,name.length()-suffix.length());
		}
	}
	public static String removePrefix(String name,String prefix){
		if(name==null || prefix==null || !name.startsWith(prefix)){
			return name;
		}else{
			return name.substring(prefix.length());
		}
	}
}
