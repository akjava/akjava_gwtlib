package com.akjava.gwt.lib.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @deprecated
 * @author aki
 *
 */
public class ValueUtils {
	private ValueUtils(){}
	
	/**
	 * use Values Utils.toDouble
	 * @param bool
	 * @param defaultValue
	 * @return
	 */
public static boolean getBoolean(String bool,boolean defaultValue){
	if(bool==null || bool.equals("")){
		return defaultValue;
	}
	boolean ret=defaultValue;
	if( bool.toLowerCase().equals("true")){
		ret=true;
	}else if( bool.toLowerCase().equals("false")){
		ret=false;
	}
	return ret;
}
/**
 * @deprecated
 * use CSVUtils
 */
public static String toNLineSeparator(String text){
	String ret=text.replace("\r\n", "\n");
	ret= ret.replace("\r", "\n");
	return ret;
}
/**
 * @deprecated
 * use CSVUtils
 */
public static String[] splitLines(String text){
    text=toNLineSeparator(text);
    String[] lines=text.split("\n");
    return lines;
}
public static String[][] csvToArray(String line,char separator){
	line=ValueUtils.toNLineSeparator(line);
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



public static List<String[]> csvToArrayList(String line,char separator){
	line=ValueUtils.toNLineSeparator(line);
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
	return csvToArray(line,',');
}



public static String removeEmptyLine(String line){
	line=ValueUtils.toNLineSeparator(line);
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

/**
 * use GWTHTMLUtils
 * @deprecated
 * 
 * @param id
 * @param defaultValue
 * @return
 */
public static String getFormValueById(String id,String defaultValue){
	String ret=defaultValue;
	RootPanel panel=RootPanel.get(id);
	if(panel!=null){
		String data=panel.getElement().getAttribute("value");
		if(data!=null && !data.equals("") && !data.equals("None")){
			ret=data;
		}
	}
	   return ret;
}


public static String tabbed(Iterable<String> iter){
	StringBuilder builder=new StringBuilder();
	Iterator<String> itor=iter.iterator();
	while(itor.hasNext()){
		builder.append(itor.next());
		if(itor.hasNext()){
			builder.append("\t");
		}
	}
	return builder.toString();
}

public static String[] untabbed(String line){
	return line.split("\t");
}

public static Map<String,String> loadCsvAsMap(String text){
	return loadCsvAsMap(text,",");
}
public static Map<String,String> loadCsvAsMap(String text,String separator){
	Map<String,String> map=new LinkedHashMap<String,String>();
	String[] lines=splitLines(text);
for(String line:lines){
	if(line.isEmpty()){
		continue;
	}
	int sp=line.indexOf(separator);
	if(sp!=-1){
		map.put(line.substring(0,sp),line.substring(sp+separator.length()));
	}else{
		map.put(line, "");
	}
}
	return map;
}


public static native String reverse(String value) /*-{
return value.split('').reverse().join('');
}-*/;  


/**
 * Use ArrayUtils
 * for gwt convert
 * @param vs
 * @return
 */
public static String[] iterableToArray(Iterable<String> vs){
	Collection<String> collection =  Lists.newArrayList(vs);
    String[] array = new String[collection.size()];
    return collection.toArray(array);
}
}
