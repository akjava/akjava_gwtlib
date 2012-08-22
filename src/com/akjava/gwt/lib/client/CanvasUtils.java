package com.akjava.gwt.lib.client;

import com.google.gwt.canvas.client.Canvas;

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
}
