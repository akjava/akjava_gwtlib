package com.akjava.lib.common.utils;

public class ColorUtils {
	private ColorUtils(){}
public static int[] toRGB(int value){
	int[]rgb=new int[3];
	rgb[0]=value>>16&0xff;
	rgb[1]=value>>8&0xff;
	rgb[2]=value&0xff;
	return rgb;
}


/*
 * not so good
 */
public static double getColorLength(int r1,int g1,int b1,int r2,int g2,int b2){
	return Math.sqrt(Math.pow(r2-r1,2)+Math.pow(g2-g1,2)+Math.pow(b2-b1,2));
}

public static int[] toRGB(String hexString){
	if(hexString.startsWith("0x")){
		hexString=hexString.substring(2);
	}else if(hexString.startsWith("#")){
		hexString=hexString.substring(1);
	}
	
	int value=Integer.parseInt(hexString,16);
	return toRGB(value);
}
public static String toCssColor(int r,int g,int b){
	int rgb=(r<<16) | (g<<8) | b;
	return toCssColor(rgb);
}
public static int toColor(int[] rgb){
	int r=rgb[0];
	int g=rgb[1];
	int b=rgb[2];
	return (r<<16) | (g<<8) | b;
}	

public static int toColor(int r,int g,int b){
	return (r<<16) | (g<<8) | b;
}	


public static String toCssColor(int rgb){
	String ret=Integer.toHexString(rgb);
	while(ret.length()<6){
		ret="0"+ret;
	}
	return "#"+ret;
}	
}
