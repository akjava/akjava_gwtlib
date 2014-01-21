package com.akjava.lib.common.csv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.io.LineProcessor;

public class CSVProcessor implements LineProcessor<List<List<String>>> {

	boolean inQuote=false;
	boolean inText=false;
	
	
	private char separator;
	private char quotechar;
	public CSVProcessor(){
		this('\t','"');
	}
	public CSVProcessor(char separator, char quotechar){
	
		Preconditions.checkArgument(separator!=quotechar);
		Preconditions.checkArgument(separator!='\r');
		Preconditions.checkArgument(separator!='\n');
		Preconditions.checkArgument(quotechar!='\r');
		Preconditions.checkArgument(quotechar!='\n');

		this.separator=separator;
		this.quotechar=quotechar;
		
	}
	public CSVProcessor(char separator) {
		this(separator,'"');
	}
	
	public void reset(){
		inQuote=false;
		inText=false;
		resultCalled=false;
		values="";
		columns=new ArrayList<String>();
		result=new ArrayList<List<String>>();
	}
	private boolean resultCalled;
	List<List<String>> result=new ArrayList<List<String>>();
	List<String> columns=new ArrayList<String>();
	String values="";
	@Override
	public List<List<String>> getResult() {
		
		if(!resultCalled){
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
		resultCalled=true;
		}
		
		return result;
	}

	@Override
	public boolean processLine(String line) throws IOException {
		if(resultCalled){
			throw new IOException("need reset()");
		}

		for(int i=0;i<line.length();i++){
			char ch=line.charAt(i);
			if(ch==quotechar){
				if(inQuote){
					//check escaped
					if(i+1<line.length()){
						char next=line.charAt(i+1);
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
			}else{
				values+=ch;
				if(!inQuote){
					inText=true;//this csv parser can contain quote
				}
			}
		}
		
		//do something end of line
		if(inQuote){//inQuote line separator ignored
			values+="\n";//add line separator,but this is somekind bug,not added original line separator.
		}else{
			//end line is usually end of row,refresh it
			columns.add(values);
			values="";
			result.add(columns);
			columns=new ArrayList<String>();
			inText=false;
		}
		
		return true;
	}

}
