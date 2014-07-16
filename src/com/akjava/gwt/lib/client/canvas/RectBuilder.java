package com.akjava.gwt.lib.client.canvas;

import com.akjava.gwt.lib.client.LogUtils;
import com.akjava.lib.common.graphics.Rect;

public class RectBuilder {
	private int width;
	private int height;
	
	private int sliceX;
	private int sliceY;
	private RectBuilder(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	public static RectBuilder from(int width,int height){
		return new RectBuilder(width, height);
	}
	
	public RectBuilder slice(int sliceX,int sliceY){
		this.sliceX=sliceX;
		this.sliceY=sliceY;
		return this;
	}
	private int currentX;
	private int currentY;
	private int xspan=0;
	private int yspan=0;
	public RectBuilder at(int x,int y){
		currentX=x;
		currentY=y;
		return this;
	}
	
	public RectBuilder horizontalExpand(int span){
		xspan=span;
		return this;
	}
	
	public RectBuilder verticalExpand(int span){
		yspan=span;
		return this;
	}
	
	public Rect toRect(){
		int perX=width/sliceX;
		int perY=height/sliceY;
		
		int w=perX*(1+Math.abs(xspan));
		int h=perY*(1+Math.abs(yspan));
		int ox=0;
		int oy=0;
		if(w==0){
			w=perX;//need 1 block
		}else if(xspan<0){//minus
			ox=-w+perX;
			
		}
		
		if(h==0){
			h=perY;//need 1 block
		}else if(yspan<0){//minus
			oy=-h+perY;
			
		}
		
		return new Rect(perX*currentX+ox, perY*currentY+oy, w, h);
	}
	
	public RectBuilder topLeft(){
		currentX=0;
		currentY=0;
		return this;
	}
	
	public RectBuilder up(){
		currentY=currentY-1;
		return this;
	}
	public RectBuilder down(){
		currentY=currentY+1;
		return this;
	}
	public RectBuilder right(){
		currentX=currentX+1;
		return this;
	}
	public RectBuilder left(){
		currentX=currentX-1;
		return this;
	}
	
	public RectBuilder parsePostion(String position){
		
		if(position.toLowerCase().equals("lefttop")){
			return topLeft();
		}else if(position.toLowerCase().equals("righttop")){
			return topRight();
		}else if(position.toLowerCase().equals("leftbottom")){
			return bottomLeft();
		}else if(position.toLowerCase().equals("rightbottom")){
			return bottomRight();
		}else if(position.toLowerCase().equals("center")){
			LogUtils.log("position:"+position);
			return middleCenter();
		}
		return this;
	}
	public RectBuilder topRight(){
		if(sliceX!=0){
			currentX=sliceX-1;
		}else{
			currentX=0;
		}
		currentY=0;
		return this;
	}
	public RectBuilder bottomLeft(){
		currentX=0;
		if(sliceY!=0){
			currentY=sliceY-1;
		}else{
			currentY=0;
		}
		return this;
	}
	
	public RectBuilder bottomRight(){
		if(sliceX!=0){
			currentX=sliceX-1;
		}else{
			currentX=0;
		}
		if(sliceY!=0){
			currentY=sliceY-1;
		}else{
			currentY=0;
		}
		return this;
	}
	
	private int getCenter(){
		int c=sliceX/2;
		int m=sliceX%2;
		if(m==0){
			c=c-1;
		}
		return Math.max(0, c);
	}
	
	private int getMiddle(){
		int c=sliceY/2;
		int m=sliceY%2;
		if(m==0){
			c=c-1;
		}
		return Math.max(0, c);
	}
	

	public RectBuilder topCenter(){
		currentX=getCenter();
		currentY=0;
		return this;
	}
	public RectBuilder middleCenter(){
		currentX=getCenter();
		currentY=getMiddle();
		return this;
	}
	
	public RectBuilder bottomCenter(){
		currentX=getCenter();
		if(sliceY!=0){
			currentY=sliceY-1;
		}else{
			currentY=0;
		}
		return this;
	}
	
	public RectBuilder middleRight(){
		if(sliceX!=0){
			currentX=sliceX-1;
		}else{
			currentX=0;
		}
		currentY=getMiddle();
		return this;
	}

	
	public RectBuilder middleLeft(){
		currentX=0;
		currentY=getMiddle();
		return this;
	}
	
	
}
