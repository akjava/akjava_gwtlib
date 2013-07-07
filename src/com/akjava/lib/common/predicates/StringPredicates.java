package com.akjava.lib.common.predicates;

import com.google.common.base.Predicate;

public class StringPredicates {
	public static NotEmpty getNotEmpty(){
		return NotEmpty.INSTANCE;
	}
	//TODO create StringPredicates
	public enum NotEmpty implements Predicate<String>{
		INSTANCE;
		@Override
		public boolean apply(String value) {
			return value!=null && !value.isEmpty();
		}
	}
}
