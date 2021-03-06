package com.akjava.lib.common.graphics;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.akjava.gwt.lib.client.game.PointD;
import com.akjava.lib.common.utils.ValuesUtils;
import com.google.common.collect.Lists;

public class Rect {
private double x;
private double y;
private double width;
public Rect() {
	this(0,0,0,0);
}
public boolean hasWidthAndHeight(){
	return width>0 && height>0;
}
public boolean contains(double px,double py){
	return px>=x && px<=x+width && py>=y && py<=y+height;
}

public boolean contains(Rect r){
	return contains(r.x,r.y) && contains(r.x+r.getWidth(),r.y+r.getHeight());
}

public Rect(double x, double y, double width, double height) {
	super();
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
}
public Point getXY(){
	return new Point(x,y);
}
public Point getWidthHeight(){
	return new Point(width,height);
}
public Rect(Rect rect) {
	this(rect.x,rect.y,rect.width,rect.height);
}
public void clear(){
	set(0,0,0,0);
}
public double getRightPos(){
	return x+width;
}
public double getBottomPos(){
	return y+height;
}

public Rect collisionRect(Rect targetRect){
	if(!collision(targetRect)){
		return new Rect();
	}else{
		double sx=Math.max(x, targetRect.x);
		double sy=Math.max(y, targetRect.y);
		
		double ex=Math.min(x+width, targetRect.x+targetRect.width);
		double ey=Math.min(y+height, targetRect.y+targetRect.height);
		
		double w=ex-sx;
		double h=ey-sy;
		if(w<0 || h<0){
			//LogUtils.log("invalid-size:"+toString()+","+targetRect.toString());
		}
		return new Rect(sx,sy,w,h);
	}
}

public boolean collision(Rect targetRect){
	if (this.x < targetRect.x + targetRect.width &&
			this.x + this.width > targetRect.x &&
			this.y < targetRect.y + targetRect.height &&
			this.height + this.y > targetRect.y) {
			    // collision detected!
		return true;
			}
	return false;
}

public static Rect fromCenterPoint(double cx,double cy,double hw,double hh){
	return new Rect(cx-hw,cy-hh,hw*2,hh*2);
}
public static Rect fromString(String kanmaValues){
	checkNotNull(kanmaValues);
	String[] vs=kanmaValues.split(",");
	//checkArgument(vs.length==4,"invalid rect format:%s",kanmaValues);
	if(vs.length!=4){
		return new Rect();//
	}
	int x=ValuesUtils.toInt(vs[0], 0);
	int y=ValuesUtils.toInt(vs[1], 0);
	int w=ValuesUtils.toInt(vs[2], 0);
	int h=ValuesUtils.toInt(vs[3], 0);
	return new Rect(x,y,w,h);
}
public void set(double x,double y,double width,double height){
	this.x=x;
	this.y=y;
	this.width=width;
	this.height=height;
}

/**
 * create expaded rect
 * @param w
 * @param h
 * @return
 */
public Rect expand(double w,double h){
	return new Rect(x-w/2,y-h/2,width+w,height+h);
}

public Rect expandSelf(double w,double h){
	set(x-w/2,y-h/2,width+w,height+h);
	return this;
}

public Rect expandIn(int w,int h,int maxW,int maxH){
	Rect r= expand(w,h);
	if(r.getX()<0){
		r.setWidth(r.getWidth()+r.getX());
		r.setX(0);
	}
	if(r.getY()<0){
		r.setHeight(r.getHeight()+r.getY());
		r.setX(y);
	}
	if(r.getRightPos()>maxW){
		r.setWidth(maxW-r.getX());
	}
	
	if(r.getBottomPos()>maxH){
		r.setHeight(maxH-r.getY());
	}
	return r;
}

public static Rect fromPoints(List<Point> points){
	Rect rect=new Rect();
	double minX=Double.MAX_VALUE;double minY=Double.MAX_VALUE;double maxX=Double.MIN_VALUE;double maxY=Double.MIN_VALUE;
	
	for(Point pt:points){
		if(pt.getX()<minX){
			minX=pt.getX();
		}
		if(pt.getY()<minY){
			minY=pt.getY();
		}
		if(pt.getX()>maxX){
			maxX=pt.getX();
		}
		if(pt.getY()>maxY){
			maxY=pt.getY();
		}
	}
	double w=maxX-minX;
	double h= maxY-minY;
	
	rect.set(minX, minY, w,h);
	return rect;
}



public Rect rightTop(int size){
	return new Rect(x+width-size,y,size,size);
}
public Rect rightBottom(int size){
	return new Rect(x+width-size,y+height-size,size,size);
}
public Rect right(int size){
	return new Rect(x+width-size,y,size,height);
}
public Rect left(int size){
	return new Rect(x,y,size,height);
}
public Rect top(int size){
	return new Rect(x,y,width,size);
}
public Rect bottom(int size){
	return new Rect(x,y+height-size,width,size);
}

public List<Point> toPoints(){
	List<Point> points=Lists.newArrayList();
	
	points.add(new Point(x,y));
	points.add(new Point(x+width,y));
	points.add(new Point(x+width,y+height));
	points.add(new Point(x,y+height));
	
	return points;
}

public Rect leftTop(int size){
	return new Rect(x,y,size,size);
}
public Rect leftBottom(int size){
	return new Rect(x,y+height-size,size,size);
}

public int getIntX(){
	return (int)x;
}
public int getIntY(){
	return (int)y;
}
public int getIntWidth(){
	return (int)width;
}
public int getIntHeight(){
	return (int)height;
}


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
public double getWidth() {
	return width;
}
public void setWidth(double width) {
	this.width = width;
}
public double getHeight() {
	return height;
}
public void setHeight(double height) {
	this.height = height;
}
private double height;

/* stop ,maybe some problem would happen
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + height;
	result = prime * result + width;
	result = prime * result + x;
	result = prime * result + y;
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Rect other = (Rect) obj;
	if (height != other.height)
		return false;
	if (width != other.width)
		return false;
	if (x != other.x)
		return false;
	if (y != other.y)
		return false;
	return true;
}
*/
public Rect copy(){
	return new Rect(x,y,width,height);
}
public void copyTo(Rect rect){
	rect.x=x;
	rect.y=y;
	rect.width=width;
	rect.height=height;
}
@Override
public String toString() {
	return "Rect [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]";
}

public String toKanmaString(){
	return x+","+y+","+width+","+height;
}
public void expandLeft(int vector) {
	x-=vector;
	width+=vector;
}

public void expandTop(int vector) {
	y-=vector;
	height+=vector;
}
public void expandBottom(int vector) {
	height+=vector;
}
public void expandRight(int vectorX) {
	width+=vectorX;
}
public PointD getCenterPoint() {
	return new PointD(x+(double)width/2,y+(double)height/2);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Rect other = (Rect) obj;
	if (Double.doubleToLongBits(height) != Double.doubleToLongBits(other.height))
		return false;
	if (Double.doubleToLongBits(width) != Double.doubleToLongBits(other.width))
		return false;
	if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
		return false;
	if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
		return false;
	return true;
}



}
