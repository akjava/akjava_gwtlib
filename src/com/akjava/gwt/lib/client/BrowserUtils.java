package com.akjava.gwt.lib.client;

public class BrowserUtils {
	public static native String getUserAgent() /*-{
	return navigator.userAgent.toLowerCase();
	}-*/;
	
	public static boolean isChrome(){
		return getUserAgent().indexOf("chrome")!=-1;
	}
	public static boolean isFirefox(){
		return getUserAgent().indexOf("firefox")!=-1;
	}
}
