package com.akjava.gwt.lib.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayBoolean;
import com.google.gwt.core.client.JsArrayNumber;
import com.google.gwt.core.client.JsArrayString;

public class JavaScriptUtils {
	//much compatible
	public static native final <T extends JavaScriptObject> JsArray<T> createJSArray()/*-{
	return $wnd.eval("new Array()");
	}-*/;
	
	  	public final static native <T extends JavaScriptObject> JsArray<T> createJSArray( T object) /*-{
		var array= $wnd.eval("new Array()");
		array.push(object);
		return array;
		}-*/;
	  	
	  	public static  final JsArrayNumber createJSArrayNumber(double... values){
	  		JsArrayNumber numbers=createJSArray().cast();
	  		for(double v:values){
	  			numbers.push(v);
	  		}
	  		return numbers;
	  	}

	
	public static List<String> toList(JsArrayString array){
	List<String> list=new ArrayList<String>();
	for(int i=0;i<array.length();i++){
		list.add(array.get(i));
	}
	return list;
	}
	
	public static <T extends JavaScriptObject>  List<T> toList(JsArray<T> array){
	List<T> list=new ArrayList<T>();
	for(int i=0;i<array.length();i++){
		list.add(array.get(i));
	}
	return list;
	}
	
	public static <E extends JavaScriptObject> JsArray<E> toArray(List<E> list){
		JsArray<E> array=JsArray.createArray().cast();
		for(E data:list){
			array.push(data);
		}
		return array;
		}
	
	public static  JsArrayNumber toArray(int[] ints){
		JsArrayNumber array=JsArrayNumber.createArray().cast();
		for(int i=0;i<ints.length;i++){
			array.push(ints[i]);
		}
		return array;
		}
	
	public static  JsArrayNumber toArray(double[] ints){
		JsArrayNumber array=JsArrayNumber.createArray().cast();
		for(int i=0;i<ints.length;i++){
			array.push(ints[i]);
		}
		return array;
		}
	public static  JsArrayBoolean toArray(boolean[] ints){
		JsArrayBoolean array=JsArrayNumber.createArray().cast();
		for(int i=0;i<ints.length;i++){
			array.push(ints[i]);
		}
		return array;
		}
	
	public static  JsArrayString toArray(String[] ints){
		JsArrayString array=JsArrayNumber.createArray().cast();
		for(int i=0;i<ints.length;i++){
			array.push(ints[i]);
		}
		return array;
		}
	
	public final native String getTypeName(JavaScriptObject object)/*-{
	var typeName = typeof object;
	return typeName;
	}-*/;
	
	public final native double toNumber(JavaScriptObject object)/*-{
	return object;
	}-*/;
	
	public final native boolean toBoolean(JavaScriptObject object)/*-{
	return object;
	}-*/;
	
	public final native String toString(JavaScriptObject object)/*-{
	return object;
	}-*/;
	
	//some js code check arg value undefined instead of null.
	public static final native JavaScriptObject createUndefinedValue()/*-{
	return undefined;
	}-*/;
	
	public static final native boolean isUndefined(JavaScriptObject object)/*-{
	return object === undefined;
	}-*/;
}
