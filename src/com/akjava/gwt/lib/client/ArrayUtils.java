package com.akjava.gwt.lib.client;

import java.util.Collection;

import com.google.common.collect.Lists;


/**
 * 
 *  * http://docs.oracle.com/javase/6/docs/api/java/util/Arrays.html
	* https://github.com/google/guava/wiki/PrimitivesExplained
 * 
 * should use com.google.common.collect.Table
 * but toArray not support on GWT,so still need these old style class
 * @author aki
 *
 */
public class ArrayUtils {

	/**
	 * see
	 *
	
	 * 
	 * sadly FluentIterable.from(vs).toArray(String.class) not work on GWT
	 * @param vs
	 * @return
	 */
	public static String[] iterableToArray(Iterable<String> vs){
		
		Collection<String> collection =  Lists.newArrayList(vs);
	    String[] array = new String[collection.size()];
	    return collection.toArray(array);
	   
	}
	
	public static int[][] createIntYXArray(int x,int y,int defaultValue){
	int[][] ret=new int[y][x];
	if(defaultValue!=0){
		for(int i=0;i<y;i++){
			for(int j=0;j<x;j++){
				ret[i][j]=defaultValue;
			}
		}
	}
	return ret;
	}
	
	public static int[][] copy(int[][] data){
		int[][] newret=new int[data.length][];
		for(int y=0;y<data.length;y++){
			newret[y]=new int[data[y].length];
			for(int x=0;x<data[y].length;x++){
				newret[y][x]=data[y][x];
			}
		}
		return newret;
	}
	public static int[][] inverse(int[][] data){
		int[][] newret=new int[data.length][];
		for(int y=0;y<data.length;y++){
			newret[y]=new int[data[y].length];
			for(int x=0;x<data[y].length;x++){
				newret[y][x]=data[y][x]*-1;
			}
		}
		return newret;
	}
	
	public static void clear(int[][] array,int value){
			for(int i=0;i<array.length;i++){
				for(int j=0;j<array[i].length;j++){
					array[i][j]=value;
				}
			}
		}
	
	public static String toString(int[][] array){
		String ret="";
		for(int i=0;i<array.length;i++){
			for(int j=0;j<array[i].length;j++){
				ret+=""+array[i][j];
				if(j!=array[i].length-1){
					ret+=",";
				}
			}
			if(i!=array.length-1){
				ret+="\n";
			}
		}
		return ret;
	}
	
	/*
	 * 
	 */
	public static class ArrayIntGrid{
		private int[][] data;
		private int rows;
		private int cols;
		public int[][] getData(){
			return data;
		}
		public int getRows() {
			return rows;
		}
		public int getCols() {
			return cols;
		}
		public ArrayIntGrid(int cols,int rows){
			data=createIntYXArray(cols, rows, 0);
			this.rows=rows;
			this.cols=cols;
		}
		public int get(int x,int y){
			return data[y][x];
		}
		public void set(int x,int y,int value){
			data[y][x]=value;
		}
		
	}

}
