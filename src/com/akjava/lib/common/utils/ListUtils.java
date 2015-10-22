package com.akjava.lib.common.utils;

import java.util.List;

import com.akjava.gwt.lib.client.LogUtils;
import com.akjava.gwt.lib.client.experimental.ArrayTool;
import com.google.common.collect.ImmutableList;

public class ListUtils {
	
	
	/**
	 * 
	 * if undefined error happen maybe list is immutable
	 * 
	 * @param list
	 * @param object
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void top(List list,Object object){
		
		if(list.size()>1){//to move need more
			int index=list.indexOf(object);
			
			if(index!=-1){
				list.remove(object);
				list.add(0,object);
			}
			}
		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void bottom(List list,Object object){
		if(list.size()>1){//to move need more
			int index=list.indexOf(object);
			if(index!=-1){
				list.remove(object);
				list.add(object);
			}
			}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void down(List list,Object object){
		if(list.size()>1){//to move need more
			int index=list.indexOf(object);
			if(index!=-1){
				list.remove(object);
				list.add(Math.min(index+1, list.size()),object);
			}
			}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void up(List list,Object object){
		if(list.size()>1){
			int index=list.indexOf(object);
			if(index!=-1){
				list.remove(object);
				list.add(Math.max(0, index-1),object);
				}
			}
	}
	
	private static ArrayTool<?> arrayTool=null;
	//TODO find way to 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static  List shuffle(List list){
		if(arrayTool==null){
			arrayTool=new ArrayTool();
		}
		return arrayTool.shuffle2(list);
	}
	
	@SuppressWarnings("rawtypes")
	public static  boolean isTop(List list,Object object){
		return list.indexOf(object) ==0 ;
		
	}
	@SuppressWarnings("rawtypes")
	public static  boolean isBottom(List list,Object object){
		return list.indexOf(object) == list.size()-1 ;
		
	}
	
}
