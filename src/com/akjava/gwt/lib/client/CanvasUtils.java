package com.akjava.gwt.lib.client;

import com.google.gwt.canvas.client.Canvas;
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
/*
 * if you need use ImageElementLoader
 */
public static ImageElement toImageElement(Canvas canvas){
	Image img=new Image(canvas.toDataUrl());
	ImageElement imageElement=ImageElement.as(img.getElement());
	return imageElement;
}

public static String createColorImageDataUrl(int r,int g,int b,double opacity,int w,int h){
	Canvas canvas=CanvasUtils.createCanvas(w, h);
	canvas.getContext2d().setFillStyle("rgba("+r+","+g+","+b+","+opacity+")");
	canvas.getContext2d().fillRect(0, 0, w, h);
	String image1=canvas.toDataUrl();
	return image1;
}
}
