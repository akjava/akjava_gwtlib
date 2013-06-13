package com.akjava.gwt.lib.client;

import java.util.ArrayList;
import java.util.List;

/*
 * quote not supported
 * TODO merge csvUtils
 */
public class SimpleCSVUtils {

	public static List<String[]> csvToArrayList(String text,char separator){
		return csvToArrayList(text,separator,"\n",false);
	}
	
	public static List<String[]> csvToArrayList(String text,char separator,String lineSeparator){
		if(text.length()==0){
			return new ArrayList<String[]>();
		}
		
		String[] lines=text.split(lineSeparator);
		return csvToArrayList(lines,separator,false);
	}
	
	public static List<String[]> csvToArrayList(String text,char separator,boolean skipEmpty){
		if(text.length()==0){
			return new ArrayList<String[]>();
		}
		
		text=ValueUtils.toNLineSeparator(text);
		return csvToArrayList(text,separator,"\n",skipEmpty);
	}
	
	public static List<String[]> csvToArrayList(String text,char separator,String lineSeparator,boolean skipEmpty){
		if(text.length()==0){
			return new ArrayList<String[]>();
		}
		String[] lines=text.split(lineSeparator);
		return csvToArrayList(lines,separator,skipEmpty);
	}
	
	public static List<String[]> csvToArrayList(String[] lines,char separator,boolean skipEmpty){
		List<String[]> csv=new ArrayList<String[]>();
		
		for (int i = 0; i < lines.length; i++) {
			if(skipEmpty &&lines[i].equals("")){
				
			}else{
			
			csv.add(lines[i].split(""+separator));
			}
		}
		return csv;
	}
	/*
	 * not copy
	 */
	public static String[][] listToArray( List<String[]> list){
		String[][] ret=new String[list.size()][];
		for(int i=0;i<list.size();i++){
			ret[i]=list.get(i);
		}
		return ret;
	}
	public static int[][] stringArrayToInt(String[][] values){
		int vs[][]=new int[values.length][];
		for(int i=0;i<values.length;i++){
			vs[i]=new int[values[i].length];
			for(int j=0;j<values[i].length;j++){
				try{
				vs[i][j]=Integer.parseInt(values[i][j]);
				}catch(Exception e){
					
				}
			}
		}
		return vs;
	}
	public static String toCSV(int[][] values){
		String vs[][]=new String[values.length][];
		for(int i=0;i<values.length;i++){
			vs[i]=new String[values[i].length];
			for(int j=0;j<values[i].length;j++){
				vs[i][j]=""+values[i][j];
			}
		}
		return toCSV(vs);
	}
	/**
	 * 
	 * @param values
	 * @return
	 */
	public static String toCSV(String[][] values){
		StringBuilder builder=new StringBuilder();
		for(int y=0;y<values.length;y++){
			for(int x=0;x<values[y].length;x++){
				builder.append(values[y][x]);
				if(x!=values[y].length-1){
					builder.append(",");
				}
			}
			if(y!=values.length-1){
				builder.append("\n");
			}
		}
		return builder.toString();
	}
	
	public static String toCSV(List<String[]> values,char separator){
		StringBuilder builder=new StringBuilder();
		for(int y=0;y<values.size();y++){
			for(int x=0;x<values.get(y).length;x++){
				builder.append(values.get(y)[x]);
				if(x!=values.get(y).length-1){
					builder.append(separator);
				}
			}
			if(y!=values.size()-1){
				builder.append("\n");
			}
		}
		return builder.toString();
	}
	
	public static String toCSV(String[] values,char separator){
		StringBuilder builder=new StringBuilder();
		
			for(int x=0;x<values.length;x++){
				builder.append(values[x]);
				if(x!=values.length-1){
					builder.append(separator);
				}
			}
		return builder.toString();
	}
}
