package com.akjava.gwt.lib.client.experimental;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
	
	//come from http://stackoverflow.com/questions/10052718/collection-shuffle-not-working-gwt
	public  List<T> shuffle2(List<T> list){
		Random random = new Random(list.size());  

		for(int index = 0; index < list.size(); index += 1) {  
		    Collections.swap(list, index, index + random.nextInt(list.size() - index));  
		}
		return list;
	}
	
	
}