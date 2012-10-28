package com.akjava.gwt.lib.client.game;

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


public PointD toPointD(){
	return new PointD(x,y);
}
public PointXY between(PointXY pt){
	int diffX=pt.x-x;
	int diffY=pt.y-y;
	return new PointXY(x+diffX/2,y+diffY/2);
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
