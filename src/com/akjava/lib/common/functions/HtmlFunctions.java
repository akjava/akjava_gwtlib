package com.akjava.lib.common.functions;

import com.google.common.base.Function;

public class HtmlFunctions {
	//utility
	public static class StringToPreFixAndSuffix implements Function<String,String>{
		private String prefix;
		private String suffix;
		public StringToPreFixAndSuffix(String prefix,String suffix){
			this.prefix=prefix;
			this.suffix=suffix;
		}
		@Override
		public String apply(String value) {
			return prefix+value+suffix;
		}
	}
	
	public StringToTHFunction getStringToTHFunction(){
		return StringToTHFunction.INSTANCE;
	}
	public enum  StringToTHFunction implements Function<String,String >{
		INSTANCE;
		@Override
		public String apply(String value) {
			return "<th>"+value+"</th>";
		}
	}
	
	public StringToTDFunction getStringToTDFunction(){
		return StringToTDFunction.INSTANCE;
	}
	public enum  StringToTDFunction implements Function<String,String >{
		INSTANCE;
		@Override
		public String apply(String value) {
			return "<td>"+value+"</td>";
		}
	}
}
