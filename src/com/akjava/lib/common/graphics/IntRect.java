package com.akjava.lib.common.graphics;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.akjava.gwt.lib.client.game.PointD;
import com.akjava.gwt.lib.client.game.PointXY;
import com.akjava.lib.common.utils.ValuesUtils;

/**
 * @deprecated for compatible
 * use Rect
 * @author aki
 *
 */
public class IntRect {
private int x;
private int y;
private int width;
public IntRect() {
	this(0,0,0,0);
}
public boolean hasWidthAndHeight(){
	return width>0 && height>0;
}
public boolean contains(int px,int py){
	return px>=x && px<=x+width && py>=y && py<=y+height;
}

public boolean contains(IntRect r){
	return contains(r.x,r.y) && contains(r.x+r.getWidth(),r.y+r.getHeight());
}

public IntRect(int x, int y, int width, int height) {
	super();
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
}
public IntRect(IntRect rect) {
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
public Rect toRect(){
	return new Rect(x,y,width,height);
}

public IntRect collisionRect(IntRect targetRect){
	if(!collision(targetRect)){
		return new IntRect();
	}else{
		int sx=Math.max(x, targetRect.x);
		int sy=Math.max(y, targetRect.y);
		
		int ex=Math.min(x+width, targetRect.x+targetRect.width);
		int ey=Math.min(y+height, targetRect.y+targetRect.height);
		
		int w=ex-sx;
		int h=ey-sy;
		if(w<0 || h<0){
			//LogUtils.log("invalid-size:"+toString()+","+targetRect.toString());
		}
		return new IntRect(sx,sy,w,h);
	}
}

public boolean collision(IntRect targetRect){
	if (this.x < targetRect.x + targetRect.width &&
			this.x + this.width > targetRect.x &&
			this.y < targetRect.y + targetRect.height &&
			this.height + this.y > targetRect.y) {
			    // collision detected!
		return true;
			}
	return false;
}

public static IntRect fromCenterPoint(int cx,int cy,int hw,int hh){
	return new IntRect(cx-hw,cy-hh,hw*2,hh*2);
}
public static IntRect fromString(String kanmaValues){
	checkNotNull(kanmaValues);
	String[] vs=kanmaValues.split(",");
	//checkArgument(vs.length==4,"invalid rect format:%s",kanmaValues);
	if(vs.length!=4){
		return new IntRect();//
	}
	int x=ValuesUtils.toInt(vs[0], 0);
	int y=ValuesUtils.toInt(vs[1], 0);
	int w=ValuesUtils.toInt(vs[2], 0);
	int h=ValuesUtils.toInt(vs[3], 0);
	return new IntRect(x,y,w,h);
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
public IntRect expand(int w,int h){
	return new IntRect(x-w/2,y-h/2,width+w,height+h);
}

public IntRect expandSelf(int w,int h){
	set(x-w/2,y-h/2,width+w,height+h);
	return this;
}

public IntRect expandIn(int w,int h,int maxW,int maxH){
	IntRect r= expand(w,h);
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

public static IntRect fromPoints(List<PointXY> points){
	IntRect rect=new IntRect();
	int minX=Integer.MAX_VALUE;int minY=Integer.MAX_VALUE;int maxX=Integer.MIN_VALUE;int maxY=Integer.MIN_VALUE;
	
	for(PointXY pt:points){
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
	int w=maxX-minX;
	int h= maxY-minY;
	
	rect.set(minX, minY, w,h);
	return rect;
}


public IntRect rightTop(int size){
	return new IntRect(x+width-size,y,size,size);
}
public IntRect rightBottom(int size){
	return new IntRect(x+width-size,y+height-size,size,size);
}
public IntRect right(int size){
	return new IntRect(x+width-size,y,size,height);
}
public IntRect left(int size){
	return new IntRect(x,y,size,height);
}
public IntRect top(int size){
	return new IntRect(x,y,width,size);
}
public IntRect bottom(int size){
	return new IntRect(x,y+height-size,width,size);
}


public IntRect leftTop(int size){
	return new IntRect(x,y,size,size);
}
public IntRect leftBottom(int size){
	return new IntRect(x,y+height-size,size,size);
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
public IntRect copy(){
	return new IntRect(x,y,width,height);
}
public void copyTo(IntRect rect){
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

}
