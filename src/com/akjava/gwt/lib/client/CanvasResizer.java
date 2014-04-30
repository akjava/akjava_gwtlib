package com.akjava.gwt.lib.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.ImageData;
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
	
	private boolean useDownScale;
	
	public static CanvasResizer on(Canvas canvas){//need canvas for reduce re-create canvas.
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
		
		return this;
	}
	
	public CanvasResizer downscale(boolean downscale){
		this.useDownScale=downscale;
		return this;
	}
	public CanvasResizer height(int size){
		int w=canvas.getCoordinateSpaceWidth();
		int h=canvas.getCoordinateSpaceHeight();
		double hr=(double)h/size;

		int newWidth=(int)(w/hr);
		canvas.setCoordinateSpaceWidth(newWidth);
		canvas.setCoordinateSpaceHeight(size);
		dx=0;
		dy=0;
		dw=newWidth;
		dh=size;
		
		return this;
	}
	
	private void drawImage(){
		boolean needDownscale=false;
		
		if(useDownScale){
			if(image!=null && image.getWidth()>dw){
				needDownscale=true;
			}
		}
		if(needDownscale){
			double scale=(double)dw/image.getWidth();
			ImageData data=JSDownScale.downScaleCanvas(ImageElementUtils.copytoCanvas(image, null).getCanvasElement(), scale);
			canvas.getContext2d().putImageData(data, dx, dy);
			
			//TODO
			//how to keep alpha?
			//simple draw & get alpha from original and recopy it?
		}else{
			canvas.getContext2d().drawImage(image, dx, dy,dw,dh);
		}
		
	}
	
	public Canvas toCanvas(){
		
		canvas.getContext2d().clearRect(0, 0, canvas.getCoordinateSpaceWidth(),canvas.getCoordinateSpaceHeight());
		drawImage();
		
		return canvas;
	}

	public String toPngDataUrl(){
		
		canvas.getContext2d().clearRect(0, 0, canvas.getCoordinateSpaceWidth(),canvas.getCoordinateSpaceHeight());
		drawImage();
		
		return canvas.toDataUrl();
	}
	public String toJpegDataUrl(){
		
		canvas.getContext2d().clearRect(0, 0, canvas.getCoordinateSpaceWidth(),canvas.getCoordinateSpaceHeight());
		drawImage();
		
		return canvas.toDataUrl("image/jpeg");
	}
	
public String toWebpDataUrl(){
		
		canvas.getContext2d().clearRect(0, 0, canvas.getCoordinateSpaceWidth(),canvas.getCoordinateSpaceHeight());
		drawImage();
		return canvas.toDataUrl("image/webp");
	}
	
}
