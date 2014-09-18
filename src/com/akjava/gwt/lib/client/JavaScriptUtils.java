package com.akjava.gwt.lib.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayNumber;
import com.google.gwt.core.client.JsArrayString;

public class JavaScriptUtils {
	//much compatible
	public static native final JsArray<JavaScriptObject> createJSArray()/*-{
	return $wnd.eval("new Array()");
	}-*/;
	
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
}
