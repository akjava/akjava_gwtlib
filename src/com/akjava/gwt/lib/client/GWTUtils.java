package com.akjava.gwt.lib.client;

public class GWTUtils {
public static String getSimpleName(Class clazz){
	String name=clazz.getName();
	int dot=name.lastIndexOf(".");
	if(dot!=-1){
		return name.substring(dot+1);
	}else{
		return name;
	}
}
}
