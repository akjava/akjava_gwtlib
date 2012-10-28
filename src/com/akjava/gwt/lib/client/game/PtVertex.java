/**
 * 
 */
package com.akjava.gwt.lib.client.game;

import com.akjava.lib.common.repackaged.org.apache.xmlgraphics.util.Vertex;



public class PtVertex   implements Vertex{
	/**
	 * 
	 */
	
	public PtVertex(int x,int y){
		this.x=x;
		this.y=y;
	}
	public int x;
	public int getX() {
		return x;
	}
	/*
	public void setX(int x) {
		this.x = x;
	}
	*/
	public int getY() {
		return y;
	}
	/*
	public void setY(int y) {
		this.y = y;
	}*/
	public int y;
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof PtVertex)){
			return false;
		}
		PtVertex p=(PtVertex)obj;
		return p.x==x && p.y == y;
	}
	@Override
	public String toString(){
		
		return x+"x"+y;
	}
	
	public static final String X="x";
	
	@Override
	public int compareTo(Object p) {
		if(!(p instanceof PtVertex)){
			return 0;
		}
		PtVertex pt=(PtVertex)p;
		if(pt.x!=x){
			return pt.x-x;
		}else{
			return pt.y-y;
		}
	}
}