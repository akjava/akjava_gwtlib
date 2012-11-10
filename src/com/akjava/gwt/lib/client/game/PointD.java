package com.akjava.gwt.lib.client.game;

/*
 * Simple Point
 */
public class PointD {
public double x;
public double getX() {
	return x;
}
public void setX(double x) {
	this.x = x;
}
public double getY() {
	return y;
}
public void setY(double y) {
	this.y = y;
}
public double length(PointD pt){
	return length(pt.x,pt.y);
}
public double length(double x,double y){
	double dx=Math.abs(x-this.x);
	double dy=Math.abs(y-this.y);
	return dx+dy;
}

public PointD createOffsetedPoint(double offx,double offy){
	return new PointD(x+offx,y+offy);
}

public double y ;
	public PointD(double x,double y){
		this.x=x;
		this.y=y;
	}
	public void set(double x,double y){
		this.x=x;
		this.y=y;
	}
	public String toString(){
		return x+"x"+y;
	}
	@Override
	public boolean equals(Object object){
		if(object instanceof PointD){
			return ((PointD)object).x==this.x && ((PointD)object).y==this.y;
		}
		return false;
	}
}
