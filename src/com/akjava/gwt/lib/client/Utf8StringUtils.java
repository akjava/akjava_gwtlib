package com.akjava.gwt.lib.client;

import java.io.UnsupportedEncodingException;

/**
 * for GWT way ,gwt not support below way.
 * new String(bytes,Charsets.UTF_8)
 * @author aki
 *
 */
public class Utf8StringUtils {
private Utf8StringUtils(){}
	//should i rename to String
	public static  String toString(byte[] bytes){
		try {
			return new String(bytes,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static byte[] toByte(String text){
		try {
			return text.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
