package com.akjava.gwt.lib.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.dom.client.ImageElement;

public class CanvasResizer {
	private CanvasResizer(Canvas canvas) {
		super();
		this.canvas = canvas;
	}
	private Canvas canvas;
	private ImageElement image;

	private int dx;
	private int dy;
	private int dw;
	private int dh;
	public static CanvasResizer on(Canvas canvas){
		return new CanvasResizer(canvas);
	}
	
	public CanvasResizer image(ImageElement element){
		canvas.setCoordinateSpaceWidth(element.getWidth());
		canvas.setCoordinateSpaceHeight(element.getHeight());
		
		//canvas.getContext2d().clearRect(0, 0, canvas.getCoordinateSpaceWidth(),canvas.getCoordinateSpaceHeight());
		//canvas.getContext2d().drawImage(element, 0, 0);//TODO fix draw twice
		
		this.image=element;
		return this;
	}
	
	public CanvasResizer width(int width){
		int w=canvas.getCoordinateSpaceWidth();
		int h=canvas.getCoordinateSpaceHeight();
		double wr=(double)w/width;

		int newHeight=(int)(h/wr);
		canvas.setCoordinateSpaceWidth(width);
		canvas.setCoordinateSpaceHeight(newHeight);
		dx=0;
		dy=0;
		dw=width;
		dh=newHeight;
		//canvas.getContext2d().drawImage(image, 0, 0,width,newHeight);
		return this;
	}
	public String toPngDataUrl(){
		
		canvas.getContext2d().clearRect(0, 0, canvas.getCoordinateSpaceWidth(),canvas.getCoordinateSpaceHeight());
		canvas.getContext2d().drawImage(image, dx, dy,dw,dh);
		
		return canvas.toDataUrl();
	}
	
}
