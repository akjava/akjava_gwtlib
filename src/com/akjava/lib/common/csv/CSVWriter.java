package com.akjava.lib.common.csv;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.FluentIterable;

public class CSVWriter {
	private CSVJoiner joiner;
	private char separator;
	public CSVWriter(char separator){
		this.separator=separator;
		joiner=new CSVJoiner(separator);
	}
	public String listToCsvLine(List<String> csv){
		return joiner.apply(csv);
	}
	
	public String toCsv(List<List<String>> csvs,String lineSeparator){
		return Joiner.on(lineSeparator).join(FluentIterable.from(csvs).transform(joiner));
	}
	

	//quote is fixed
	public static String toCsvString(String text,char separator,char quote,String quoteEscaped){
		boolean containLineSeparator=text.indexOf("\n")!=-1;
		boolean containSeparator=text.indexOf(separator)!=-1;
		
		if(containLineSeparator || containSeparator || text.startsWith(""+quote)){
			text=text.replace(""+quote, quoteEscaped);//escaped
			return quote+text+quote;
		}else{//
			return text;
		}
	}
	
	public static class CSVJoiner implements Function<List<String>,String>{
		private Joiner joiner;
		private char separator;
		public CSVJoiner(char separator) {
			super();
			this.separator=separator;
			joiner=Joiner.on(separator);
		}
		@Override
		public String apply(List<String> input) {
			List<String> converted=FluentIterable.from(input).transform(new ToCSVString(separator,'"',"\"\"")).toList();
			return joiner.join(converted);
		}
		
	}
	
	
	public static class ToCSVString implements Function<String,String>{
		private char separator;
		private char quote;
		String quoteEscaped;
		public ToCSVString(char separator,char quote,String quoteEscaped) {
			super();
			this.separator = separator;
			this.quote=quote;
			this.quoteEscaped=quoteEscaped;
		}
		@Override
		public String apply(String input) {
			return toCsvString(input,separator,quote,quoteEscaped);
		}
	}
	
	public static class ListToArray implements Function<List<String>,String[]>{
		@Override
		public String[] apply(List<String> input) {
			return input.toArray(new String[0]);
		}
	}
}
