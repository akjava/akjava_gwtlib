package com.akjava.gwt.lib.client;

import com.google.common.base.Ascii;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Navigator;

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
/**
 * via http://stackoverflow.com/questions/9038625/detect-if-device-is-ios
 * @return
 */
public final native static boolean isIOS()/*-{
return navigator.userAgent.match(/(iPad|iPhone|iPod)/g) ? true : false ;
}-*/;

public static boolean isIE(){
	String agent=Window.Navigator.getUserAgent().toLowerCase();
	return agent.indexOf("msie")!=-1 || agent.indexOf("trident")!=-1;
}

public static boolean isIAndroid(){
	String agent=Navigator.getUserAgent();
	if(Ascii.toLowerCase(agent).indexOf("android")!=-1){
		return true;
	}else{
		return false;
	}
}


}
