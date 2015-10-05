package com.akjava.gwt.lib.client.graphics;

import java.util.Collection;

import com.akjava.gwt.lib.client.CanvasUtils;
import com.akjava.lib.common.graphics.Point;
import com.akjava.lib.common.graphics.Rect;
import com.akjava.lib.common.io.FileType;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.ImageData;
import com.google.gwt.dom.client.ImageElement;

public class CanvasGraphics {
	
	private Canvas canvas;
	
public CanvasGraphics(Canvas canvas) {
		super();
		this.canvas = canvas;
	}

public CanvasGraphics fill(double  x,double y){	
		return this;
	}

	public CanvasGraphics fill(Point pt){
		
		return this;
	}
	
	public CanvasGraphics fill(Rect rect){
		
		return this;
	}
	
	public CanvasGraphics fill(Collection<Point> rect){
		
		return this;
	}
	
	public CanvasGraphics filltyle(String style){
		
		return this;
	}
	
	public CanvasGraphics stroke(Point pt1,Point pt2){
		
		return this;
	}
	
	public CanvasGraphics stroke(double x1,double y1,double x2,double y2){
		
		return this;
	}

	public CanvasGraphics strokeStyle(String style){
		
		return this;
	}
	public CanvasGraphics lineWidth(double width){
		
		return this;
	}
	
	public Canvas copyTo(Canvas canvas){
		return null;
	}
	public Canvas copyTo(){
		return null;
	}
	
	public void copyToSizeOnly(Canvas target){
		
	}
	public String toDataUrl(FileType fileType){
		
		return null;
	}
	
	
	
	public ImageElement toImageElement(){
		
		return null;
	}
	public ImageData toImageData(){
		
		return null;
	}

	public CanvasGraphics copyToSizeOnly(Rect rect) {
		CanvasUtils.setSize(canvas, (int)rect.getWidth(), (int)rect.getHeight());
		return this;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void drawTo(Canvas resultCanvas,double x,double y) {
		resultCanvas.getContext2d().drawImage(canvas.getCanvasElement(), x, y);
	}
	public void drawTo(Canvas resultCanvas) {
		drawTo(resultCanvas,0,0);
	}
}
