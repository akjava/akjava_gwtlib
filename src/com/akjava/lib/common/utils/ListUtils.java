package com.akjava.lib.common.utils;

import java.util.List;

public class ListUtils {
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
}
