package com.akjava.lib.common.predicates;

import java.util.List;

import com.google.common.base.Predicate;

public class StringPredicates {
	public static NotEmpty getNotEmpty(){
		return NotEmpty.INSTANCE;
	}
	
	public enum NotEmpty implements Predicate<String>{
		INSTANCE;
		@Override
		public boolean apply(String value) {
			return value!=null && !value.isEmpty();
		}
	}
	public  static IndexOfList getIndexOfList(List<String> values){
		return new IndexOfList(values);
	}
	
	public static class IndexOfList implements Predicate<String>{
		List<String> values;
		public IndexOfList(List<String> values) {
			super();
			this.values = values;
		}
		@Override
		public boolean apply(String input) {
			for(String v:values){
				if(input.indexOf(v)!=-1){
					return true;
				}
			}
			return false;
		}
		
	}
}
