package com.akjava.gwt.lib.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayString;

public class JavaScriptUtils {
	//much compatible
	public static native final JsArray createJSArray()/*-{
	return $wnd.eval("new Array()");
	}-*/;
	
	public static List<String> toList(JsArrayString array){
	List<String> list=new ArrayList<String>();
	for(int i=0;i<array.length();i++){
		list.add(array.get(i));
	}
	return list;
	}
	
	public static <T>  List<T> toList(JsArray array){
	List list=new ArrayList();
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
}
