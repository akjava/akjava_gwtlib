package com.akjava.gwt.lib.client;

import com.google.common.io.BaseEncoding;
/**
 * @author aki
 *
 */
public class Base64Utils {
	private Base64Utils(){}
public static final String DATA_BASE64_KEY=";base64,";
	public static String getDataUrlHeader(String mimeType){
		return "data:"+mimeType+DATA_BASE64_KEY;
	}
	
	public static String toDataUrl(String mimeType,String base64){
		return getDataUrlHeader(mimeType)+base64;
	}
	public static String toDataUrl(String mimeType,byte[] data){
		return getDataUrlHeader(mimeType)+encode(data);
	}
	public static String encode(byte[] data){
		return BaseEncoding.base64().encode(data);
	}
	/**
	 * don't use dataurl that contain header,use fromDataUrl();
	 * @param data
	 * @return
	 */
	public static byte[] decode(String data){
		
		return BaseEncoding.base64().decode(data);
	}
	//TODO be careful
	public static byte[] fromDataUrl(String url){
		int index=url.indexOf(DATA_BASE64_KEY);
		if(index!=-1){
			
			return BaseEncoding.base64Url().decode(url.substring(DATA_BASE64_KEY.length()));
		}else{
			return BaseEncoding.base64Url().decode(url);
		}
	}
	public static String cutHeader(String url){
		int index=url.indexOf(DATA_BASE64_KEY);
		if(index!=-1){
			return url.substring(index+DATA_BASE64_KEY.length());
		}else{
			return url;
		}
	}
}
