package com.akjava.gwt.lib.client;

public class GWTStringUtils {
	public static native String reverse(String value) /*-{
	return new StringBuilder(value).reverse().toString();
	}-*/;  
}
