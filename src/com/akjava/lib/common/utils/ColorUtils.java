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

public static String toCssColor(int rgb){
	String ret=Integer.toHexString(rgb);
	while(ret.length()<6){
		ret="0"+ret;
	}
	return "#"+ret;
}	
}
