package com.akjava.gwt.lib.client.experimental;

import java.util.ArrayList;
import java.util.List;

public class ArrayTool<T> {

	public  List<T> shuffle(List<T> list){
		ArrayList<T> newList=new ArrayList<T>();
		
		if(list==null || list.size()==0){
			return newList;
		}
		
		ArrayList<T> tmpList=new ArrayList<T>();
		for(int i=0;i<list.size();i++){
			tmpList.add(list.get(i));
		}
		
		while(tmpList.size()>0){
			int index=(int) (Math.random()*tmpList.size());
			newList.add(tmpList.remove(index));
		}
		return newList;
	}
}