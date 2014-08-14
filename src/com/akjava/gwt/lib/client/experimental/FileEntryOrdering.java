package com.akjava.gwt.lib.client.experimental;

import com.akjava.gwt.html5.client.file.webkit.FileEntry;
import com.google.common.base.Strings;
import com.google.common.collect.Ordering;

public class FileEntryOrdering {
private static OrderByPath orderByPath;
	public static class OrderByPath extends Ordering<FileEntry>{
		
		@Override
		public int compare(FileEntry left, FileEntry right) {
			return left.getFullPath().compareTo(right.getFullPath());
		}
		
	}
	public static OrderByPath getOrderByPath(){
		if(orderByPath==null){
			orderByPath=new OrderByPath();
		}
		return orderByPath;
	}
}
