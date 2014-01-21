package com.akjava.lib.common.csv;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;

public class NewCSVReader {
	private String lines;
	private char separator;
	private char quotechar;
	public NewCSVReader(String lines){
		this(lines,'\t','"');
	}
	public NewCSVReader(String lines,char separator, char quotechar){
		Preconditions.checkNotNull(lines);
		Preconditions.checkArgument(separator!=quotechar);
		Preconditions.checkArgument(separator!='\r');
		Preconditions.checkArgument(separator!='\n');
		Preconditions.checkArgument(quotechar!='\r');
		Preconditions.checkArgument(quotechar!='\n');
		this.lines=lines;
		this.separator=separator;
		this.quotechar=quotechar;
	}
	public NewCSVReader(String lines, char separator) {
		this(lines,separator,'"');
	}
	public List<List<String>> readAllAsList(){
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
