package com.akjava.lib.common.csv;

import java.util.ArrayList;
import java.util.List;

import com.akjava.lib.common.csv.CSVLibUtils.CSVJoiner;
import com.google.common.base.Converter;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.FluentIterable;

public class CSVConverter extends Converter<String,List<List<String>>> {
	public static final char SEPARATOR_COMMA=',';
	public static final char SEPARATOR_TAB='\t';
	private char separator;
	private char quotechar;
	private String lineSeparator="\n";
	public CSVConverter(char separator){
		this(separator,'"');
	}
	
	public CSVConverter(char separator, char quotechar){
		Preconditions.checkArgument(separator!=quotechar);
		Preconditions.checkArgument(separator!='\r');
		Preconditions.checkArgument(separator!='\n');
		Preconditions.checkArgument(quotechar!='\r');
		Preconditions.checkArgument(quotechar!='\n');
		this.separator=separator;
		this.quotechar=quotechar;
	}
	
	@Override
	protected String doBackward(List<List<String>> csvs) {
		CSVJoiner joiner=new CSVJoiner(separator,quotechar);
		// TODO Auto-generated method stub
		return Joiner.on(lineSeparator).join(FluentIterable.from(csvs).transform(joiner));
	}

	/**
	 * came from NewCSVReader,TODO based Processor
	 */
	@Override
	protected List<List<String>> doForward(String lines) {
		List<List<String>> result=new ArrayList<List<String>>();
		List<String> columns=new ArrayList<String>();
		String values="";
		boolean inQuote=false;
		boolean inText=false;
		for(int i=0;i<lines.length();i++){
			char ch=lines.charAt(i);
			if(ch==quotechar){
				if(inQuote){
					//check escaped
					if(i+1<lines.length()){
						char next=lines.charAt(i+1);
						if(next==quotechar){//escaped 
							values+=ch;
							//values+=next;
							++i;//skip
							//System.out.println("inQuote but next is not quote");
							continue;
						}else{
							//System.out.println("inQuote but next is not quote:"+next);
						}
					}
					//System.out.println("set inQuote=false");
					inQuote=false;
				}else{
					//skip
					if(inText){
						System.out.println("inText");
					values+=ch;	
					}else{
					//	System.out.println("set inQuote=true");
					inQuote=true;
					}
				}
			}else if(ch==separator){
				if(inQuote){//inQuote separator ignored
					values+=ch;
				}else{
					//value break
					columns.add(values);
					values="";
					inText=false;
				}
			}else if(ch=='\r'){
				if(inQuote){//inQuote separator ignored
					values+=ch;
				}else{
					if(i+1<lines.length()){
						char next=lines.charAt(i+1);
						if(next=='n'){//line-break
							columns.add(values);
							values="";
							result.add(columns);
							columns=new ArrayList<String>();
							continue;
						}
					}
					values+=ch;
				}
			}else if(ch=='\n'){//line break
				if(inQuote){//inQuote separator ignored
					values+=ch;
				}else{
					columns.add(values);
					values="";
					result.add(columns);
					columns=new ArrayList<String>();
					inText=false;
				}
			}else{
				values+=ch;
				if(!inQuote){
					inText=true;
				}
			}
		}
		
		if(!values.isEmpty()){
			columns.add(values);
		}
		
		if(!columns.isEmpty()){
			result.add(columns);
		}else{
			//line end
			columns.add("");//at least one
			result.add(columns);
		}
		
		return result;
	}

}
