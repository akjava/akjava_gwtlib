package com.akjava.lib.common.graphics;

import static com.google.common.base.Preconditions.checkNotNull;

import com.akjava.lib.common.utils.ValuesUtils;

public class Rect {
private int x;
private int y;
private int width;
public Rect() {
	this(0,0,0,0);
}
public boolean hasWidthAndHeight(){
	return width>0 && height>0;
}
public boolean contains(int px,int py){
	return px>=x && px<=x+width && py>=y && py<=y+height;
}

public boolean contains(Rect r){
	return contains(r.x,r.y) && contains(r.x+r.getWidth(),r.y+r.getHeight());
}

public Rect(int x, int y, int width, int height) {
	super();
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
}
public Rect(Rect rect) {
	this(rect.x,rect.y,rect.width,rect.height);
}
public void clear(){
	set(0,0,0,0);
}
public int getRightPos(){
	return x+width;
}
public int getBottomPos(){
	return y+height;
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
public void set(int x,int y,int width,int height){
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
public Rect expand(int w,int h){
	return new Rect(x-w/2,y-h/2,width+w,height+h);
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


public Rect leftTop(int size){
	return new Rect(x,y,size,size);
}
public Rect leftBottom(int size){
	return new Rect(x,y+height-size,size,size);
}



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
public int getWidth() {
	return width;
}
public void setWidth(int width) {
	this.width = width;
}
public int getHeight() {
	return height;
}
public void setHeight(int height) {
	this.height = height;
}
private int height;

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

}
