package com.akjava.lib.common.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.akjava.gwt.lib.client.ValueUtils;

public class CSVUtils {
private CSVUtils(){}
public static String toNLineSeparator(String text){
	String ret=text.replace("\r\n", "\n");
	ret= ret.replace("\r", "\n");
	return ret;
}

public static String[] splitLines(String text){
    text=toNLineSeparator(text);
    String[] lines=text.split("\n");
    return lines;
}

public static String[][] csvTextToArray(String line,char separator){
	line=toNLineSeparator(line);
	String[] lines=line.split("\n");
	String ret[][]=new String[lines.length][0];
	for (int i = 0; i < lines.length; i++) {
		String csv[]=lines[i].split(""+separator);
		ret[i]=new String[csv.length];
		for (int j = 0; j < csv.length; j++) {
			ret[i][j]=csv[j];
		}
	}
	return ret;
}



public static List<String[]> csvTextToArrayList(String line,char separator){
	line=toNLineSeparator(line);
	String[] lines=line.split("\n");
	
	List<String[]> ret=new ArrayList<String[]>();
	for (int i = 0; i < lines.length; i++) {
		String csv[]=lines[i].split(""+separator);
		ret.add(csv);
	}
	return ret;
}

public static String joinOn(Iterable<String> values,String separator){
	StringBuilder builder=new StringBuilder();
	
	Iterator<String> itor=values.iterator();
	while(itor.hasNext()){
		builder.append(itor.next());
		if(itor.hasNext()){
			builder.append(separator);
		}
	}
	
	return builder.toString();
}

public static String[][] csvToArray(String line){
	return csvTextToArray(line,',');
}

public static Map<String,String> csvTextToMap(char separator,String text){
	Map<String,String> map=new LinkedHashMap<String,String>();
	String[] lines=splitLines(text);
for(String line:lines){
	if(line.isEmpty()){
		continue;
	}
	int sp=line.indexOf(separator);
	if(sp!=-1){
		map.put(line.substring(0,sp),line.substring(sp+1));
	}else{
		map.put(line, "");
	}
}
	return map;
}

public static String removeEmptyLine(String line){
	line=toNLineSeparator(line);
	String[] lines=line.split("\n");
	String ret="";
	for (int i = 0; i < lines.length; i++) {
		if(!lines[i].equals("")){
			ret+=lines[i];
			if(i!=lines.length-1){
				ret+="\n";
			}else{
				ret+="\n";
			}
		}
	}
	return ret;
}
}