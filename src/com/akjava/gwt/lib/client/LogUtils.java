package com.akjava.gwt.lib.client;

import com.google.gwt.core.client.JavaScriptObject;

public class LogUtils {
	public static final native void log(JavaScriptObject object)/*-{
		if (navigator.appName == 'Microsoft Internet Explorer'){
			return;
		}
	if(console){
		console.log(object);
	}
	}-*/;
	public static final  void log(Object value){
		log(value.toString());
	}
	public static final  void log(double value){
		log(""+value);
	}
	public static final  void log(long value){
		log(""+value);
	}
	public static final  void log(int value){
		log(""+value);
	}
	public static final native void log(String object)/*-{
		if (navigator.appName == 'Microsoft Internet Explorer'){
			return;
		}
	if(console){
		console.log(object);
	}
	}-*/;
}
