package com.akjava.gwt.lib.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.ui.Image;

public class CanvasUtils {
public static Canvas createCanvas(int w,int h){
	Canvas canvas=Canvas.createIfSupported();
	if(canvas!=null){
		canvas.setCoordinateSpaceWidth(w);
		canvas.setCoordinateSpaceHeight(h);
		canvas.setWidth(w+"px");
		canvas.setHeight(h+"px");
	}
	return canvas;
}
public static void disableSelection(Canvas canvas){
	GWTHTMLUtils.disableSelectionStart(canvas.getCanvasElement());
}
/*
 * if you need use ImageElementLoader
 */
public static ImageElement toImageElement(Canvas canvas){
	Image img=new Image(canvas.toDataUrl());
	ImageElement imageElement=ImageElement.as(img.getElement());
	return imageElement;
}

public static String createColorRectImageDataUrl(int r,int g,int b,double opacity,int w,int h){
	Canvas canvas=CanvasUtils.createCanvas(w, h);
	canvas.getContext2d().setFillStyle("rgba("+r+","+g+","+b+","+opacity+")");
	canvas.getContext2d().fillRect(0, 0, w, h);
	String image1=canvas.toDataUrl();
	return image1;
}

public static Canvas createCircleImageCanvas(int r,int g,int b,double opacity,double radius,double lineWidth,boolean stroke){
	double center=radius+lineWidth;
	Canvas canvas=CanvasUtils.createCanvas((int)center*2,(int)center*2);
	if(stroke){
	canvas.getContext2d().setStrokeStyle("rgba("+r+","+g+","+b+","+opacity+")");
	canvas.getContext2d().setLineWidth(lineWidth);
	}else{
	canvas.getContext2d().setFillStyle("rgba("+r+","+g+","+b+","+opacity+")");	
	}
	canvas.getContext2d().beginPath();
	
	canvas.getContext2d().arc(center, center, radius, 0, 360);
	canvas.getContext2d().closePath();
	if(stroke){
		canvas.getContext2d().stroke();
	}else{
		canvas.getContext2d().fill();
	}
	
	return canvas;
}

public static String createCircleImageDataUrl(int r,int g,int b,double opacity,double radius,double lineWidth,boolean stroke){
	double center=radius+lineWidth;
	Canvas canvas=CanvasUtils.createCanvas((int)center*2,(int)center*2);
	if(stroke){
	canvas.getContext2d().setStrokeStyle("rgba("+r+","+g+","+b+","+opacity+")");
	canvas.getContext2d().setLineWidth(lineWidth);
	}else{
	canvas.getContext2d().setFillStyle("rgba("+r+","+g+","+b+","+opacity+")");	
	}
	canvas.getContext2d().beginPath();
	
	canvas.getContext2d().arc(center, center, radius, 0, 360);
	canvas.getContext2d().closePath();
	if(stroke){
		canvas.getContext2d().stroke();
	}else{
		canvas.getContext2d().fill();
	}
	String image1=canvas.toDataUrl();
	return image1;
}
public static void drawImageByCordinate(Canvas target,CanvasElement canvas, int sx1, int sy1,
		int sx2, int sy2,int dx1, int dy1, int dx2, int dy2){

	target.getContext2d().drawImage(canvas, sx1, sy1, sx2-sx1, sy2-sy1, dx1, dy1, dx2-dx1, dy2-dy1);
}

}
