package com.akjava.lib.common.utils;

import com.akjava.lib.common.functions.ColorFunctions;
import com.google.common.base.Equivalence;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public class ColorUtils {
	private ColorUtils(){}
public static int[] toRGB(int value){
	int[]rgb=new int[3];
	rgb[0]=value>>16&0xff;
	rgb[1]=value>>8&0xff;
	rgb[2]=value&0xff;
	return rgb;
}




/**
 * I'm not sure ,are these cut down initialize cost?
 * 
 * can compare #ff0000 , #fff ,rgb(0,0,0) and rgba(0,0,0,1)
 * 
 * @return
 */
public static Equivalence<String> getColorEquivalance(){
    return colorMemoize.get();
    }

private static Supplier<Equivalence<String>> colorSupplier=new Supplier<Equivalence<String>>(){
    public Equivalence<String> get(){
    	return Equivalence.equals().onResultOf(ColorFunctions.getStringToRGBAFunction());
    }
};

private static volatile Supplier<Equivalence<String>> colorMemoize = Suppliers.memoize(colorSupplier);


/*
 * not so good
 */
public static double getColorLength(double r1,double g1,double b1,double r2,double g2,double b2){
	return Math.sqrt(Math.pow(r2-r1,2)+Math.pow(g2-g1,2)+Math.pow(b2-b1,2));
}
/* use-less
public static double getColorLength(double r1,double g1,double b1){
	return Math.sqrt(Math.pow(r1,2)+Math.pow(g1,2)+Math.pow(b1,2));
}
*/

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

/**
 * 0xfffff,#ffffff
 * @param hexString
 * @return
 */
public static int toColor(String hexString){
	return toColor(toRGB(hexString));
}	


public static int toColor(int[] rgb){
	int r=rgb[0];
	int g=rgb[1];
	int b=rgb[2];
	return (r<<16) | (g<<8) | b;
}	

public static int toGrayColor(int gray){
	return toColor(gray,gray,gray);
}

public static int toColor(int r,int g,int b){
	return (r<<16) | (g<<8) | b;
}

public static int toGrayscale(int r,int g,int b){
	return (int) (0.299*r + 0.587*g + 0.114*b);
}	

public static String toCssGrayColor(int r){
	int rgb=toGrayColor(r);
	String ret=Integer.toHexString(rgb);
	while(ret.length()<6){
		ret="0"+ret;
	}
	return "#"+ret;
}

public static String toCssColor(int rgb){
	String ret=Integer.toHexString(rgb);
	while(ret.length()<6){
		ret="0"+ret;
	}
	return "#"+ret;
}
public static String toCssColor(int r,int g,int b,double alpha){
	return "rgba("+r+","+g+","+b+","+alpha+")";
}
public static String toCssColor(String color, double alpha) {
	int[] rgb=toRGB(color);
	return toCssColor(rgb[0], rgb[1], rgb[2], alpha);
}	
}
