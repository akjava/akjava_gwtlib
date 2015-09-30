package com.akjava.lib.common.graphics;

import com.akjava.gwt.lib.client.game.PointXY;

/*
 * Simple Point
 */
public class Point {
public double x;
public double getX() {
	return x;
}
public int getIntX(){
	return (int)x;
}
public int getIntY(){
	return (int)x;
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
public double length(Point pt){
	return length(pt.x,pt.y);
}
public double length(double x,double y){
	double dx=Math.abs(x-this.x);
	double dy=Math.abs(y-this.y);
	return dx+dy;
}

public static Point turnedAngle(Point center,Point point,double angle){
	return turnedAngle(center.x,center.y,point.x,point.y,angle);
}

public static Point turnedAngle(double centerx,double centery,double px,double py,double angle){
	double x=px-centerx;
	double y=py-centery;
	double radian=Math.toRadians(angle);
	double turnedX=Math.cos(radian)*x-Math.sin(radian)*y;
	double turnedY=Math.cos(radian)*y+Math.sin(radian)*x;
	return new Point(turnedX+centerx, turnedY+centery);
}

public Point createOffsetedPoint(double offx,double offy){
	return new Point(x+offx,y+offy);
}

public double distance(Point pt){
	double v=Math.sqrt((pt.x-x)*(pt.x-x)+(pt.y-y)*(pt.y-y));
	return v;
}

public double y ;
	public Point(double x,double y){
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
		if(object instanceof Point){
			return ((Point)object).x==this.x && ((Point)object).y==this.y;
		}
		return false;
	}
	public Point inverse() {
		return new Point(-x,-y);
	}
	public Point incrementXY(double x,double y){
		this.x+=x;
		this.y+=y;
		return this;
	}
}
