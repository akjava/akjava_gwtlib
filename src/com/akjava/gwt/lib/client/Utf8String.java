package com.akjava.gwt.lib.client;

import java.io.UnsupportedEncodingException;

/**
 * for GWT way ,gwt not support below way.
 * new String(bytes,Charsets.UTF_8)
 * @author aki
 *
 */
public class Utf8String {

	public static final String create(byte[] bytes){
		try {
			return new String(bytes,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
