package com.akjava.lib.common.utils;

import java.util.List;

import com.akjava.gwt.lib.client.experimental.ArrayTool;

public class ListUtils {
	
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
	//TODO find way to uncheck
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static  List shuffle(List list){
		return new ArrayTool().shuffle(list);
	}
	
}
