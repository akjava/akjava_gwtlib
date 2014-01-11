package com.akjava.lib.common.functions;

import java.util.List;

import com.google.common.base.Function;

public class HtmlFunctions {
	//utility
	
	public static StringToTHFunction getStringToTHFunction(){
		return StringToTHFunction.INSTANCE;
	}
	public enum  StringToTHFunction implements Function<String,String >{
		INSTANCE;
		@Override
		public String apply(String value) {
			return "<th>"+value+"</th>";
		}
	}
	
	public static StringToTDFunction getStringToTDFunction(){
		return StringToTDFunction.INSTANCE;
	}
	public enum  StringToTDFunction implements Function<String,String >{
		INSTANCE;
		@Override
		public String apply(String value) {
			return "<td>"+value+"</td>";
		}
	}
	
	public static StringToTRTDFunction getStringToTRTDFunction(){
		return StringToTRTDFunction.INSTANCE;
	}
	public enum  StringToTRTDFunction implements Function<List<List<String>>,String >{
		INSTANCE;
		@Override
		public String apply(List<List<String>> value) {
			if(value.size()==0){
				return "";
			}
			List<String> first=value.get(0);
			int rowCount=first.size();
			if(rowCount==0){
				return "";
			}
			StringBuffer buffer=new StringBuffer();
			//all list must be same size
			int columnCount=value.size();
			for(int j=0;j<rowCount;j++){
				buffer.append("<tr>\n");
			for(int i=0;i<columnCount;i++){
				buffer.append("<td>\n");
				buffer.append(value.get(i).get(j));
				buffer.append("</td>\n");
			}
			buffer.append("</tr>\n");
			}
			return buffer.toString();
		}
	}
	

}
