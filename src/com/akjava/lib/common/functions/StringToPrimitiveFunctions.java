package com.akjava.lib.common.functions;

import com.google.common.base.Function;
import com.google.common.base.MoreObjects;

public class StringToPrimitiveFunctions {

	public static ToBoolean toBoolean(){
		return ToBoolean.INSTANCE;
	}
	public enum ToBoolean implements Function<String,Boolean>{
		INSTANCE ;
		@Override
		public Boolean apply(String input) {
			return Boolean.valueOf(input);
		}
	}
	
	//watch out default is 0
	public static ToInteger toInteger(){
		return ToInteger.INSTANCE;
	}
	public enum ToInteger implements Function<String,Integer>{
		INSTANCE ;
		@Override
		public Integer apply(String input) {
			return Integer.valueOf(MoreObjects.firstNonNull(input, "0"));
		}
	}
	
	//watch out default is 0
		public static ToDouble toDouble(){
			return ToDouble.INSTANCE;
		}
		public enum ToDouble implements Function<String,Double>{
			INSTANCE ;
			@Override
			public Double apply(String input) {
				return Double.valueOf(MoreObjects.firstNonNull(input, "0"));
			}
		}
		
}
