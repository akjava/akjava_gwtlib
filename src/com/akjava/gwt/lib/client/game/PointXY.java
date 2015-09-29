package com.akjava.gwt.lib.client.game;

import com.akjava.lib.common.utils.ValuesUtils;

/*
 * Simple Point
 */
public class PointXY {
public int x;
public int getX() {
	return x;
}
public void setX(int x) {
	this.x = x;
}
public int getY() {
	return y;
}
public void setY(int y) {
	this.y = y;
}
public int length(PointXY pt){
	return length(pt.x,pt.y);
}
public int length(int x,int y){
	int dx=Math.abs(x-this.x);
	int dy=Math.abs(y-this.y);
	return dx+dy;
}

public void incrementX(int x){
	this.x+=x;
}

public void incrementY(int y){
	this.y+=y;
}
public PointXY copy(){
	return new PointXY(x,y);
}

public PointXY incrementXY(int x,int y){
	this.x+=x;
	this.y+=y;
	return this;
}

public PointD toPointD(){
	return new PointD(x,y);
}
public PointXY between(PointXY pt){
	int diffX=pt.x-x;
	int diffY=pt.y-y;
	return new PointXY(x+diffX/2,y+diffY/2);
}

public static PointXY fromString(String text){
	if(text==null || text.isEmpty()){
		return new PointXY(0, 0);
	}
	
	int index=text.indexOf("x");
	if(index==-1){
		return new PointXY(0, 0);
	}
	int x=ValuesUtils.toInt(text.substring(0,index), 0);
	int y=ValuesUtils.toInt(text.substring(index+1), 0);
	
	return new PointXY(x,y);
}

public int y ;
	public PointXY(int x,int y){
		this.x=x;
		this.y=y;
	}
	public void set(int x,int y){
		this.x=x;
		this.y=y;
	}
	public String toString(){
		return x+"x"+y;
	}
	@Override
	public boolean equals(Object object){
		if(object instanceof PointXY){
			return ((PointXY)object).x==this.x && ((PointXY)object).y==this.y;
		}
		return false;
	}
}
