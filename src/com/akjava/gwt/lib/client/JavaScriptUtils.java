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
	  	
	  	
	  	@SuppressWarnings({ "rawtypes", "unchecked" })
		public static  final  JsArray createJSArray(JavaScriptObject... values){
	  		JsArray numbers=createJSArray().cast();
	  		for(JavaScriptObject v:values){
	  			numbers.push(v);
	  		}
	  		return numbers;
	  	}

	  	
	public static void concat(JsArrayNumber target,JsArrayNumber values){
			for(int i=0;i<values.length();i++){
				target.push(values.get(i));
			}
	}
		
	
	public static List<String> toList(JsArrayString array){
		if(array==null){
			return null;
		}
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
	
	public static List<Double> toList(JsArrayNumber array){
		if(array==null){
			return null;
		}
	List<Double> list=new ArrayList<Double>();
	for(int i=0;i<array.length();i++){
		list.add(array.get(i));
	}
	return list;
	}
	public static List<Boolean> toList(JsArrayBoolean array){
		if(array==null){
			return null;
		}
		List<Boolean> list=new ArrayList<Boolean>();
		for(int i=0;i<array.length();i++){
			list.add(array.get(i));
		}
		return list;
		}
	
	public static JsArrayNumber toArrayNumber(List<Double> list){
		JsArrayNumber array=JsArray.createArray().cast();
		for(Double data:list){
			array.push(data);
		}
		return array;
		}
	
	public static JsArrayBoolean toArrayBoolean(List<Boolean> list){
		JsArrayBoolean array=JsArray.createArray().cast();
		for(Boolean data:list){
			array.push(data);
		}
		return array;
		}
	
	public static <E extends JavaScriptObject> JsArray<E> toArray(Iterable<E> list){
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
	
	
	public static final native void remove(JsArrayNumber array,int index)/*-{
	array.splice(index,1);
	}-*/;
	
	
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
	public static final native boolean isNaN(double object)/*-{
	return $wnd.isNaN(object);
	}-*/;

	public static JsArrayNumber clone(JsArrayNumber pos) {
		JsArrayNumber number=createJSArray().cast();
		for(int i=0;i<pos.length();i++){
			number.set(i, pos.get(i));
		}
		return number;
	}
	
	public final static double fixNumber(int v,double number){
		String fixed=toFixed(number, v);
		return Double.valueOf(fixed);
	}
	
	  private  final static native String toFixed(double number, int n) /*-{
	    return number.toFixed(n);
	  }-*/;
	
}
