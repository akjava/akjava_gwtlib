package com.akjava.gwt.lib.client.graphics;

import com.google.gwt.canvas.client.Canvas;

public class Graphics {
private Graphics(){}

	public static CanvasGraphics from(Canvas canvas){
		return new CanvasGraphics(canvas);
	}
	public static CanvasGraphics createCanvas(){
		return new CanvasGraphics(Canvas.createIfSupported());
	}
	
	
	//canvas graphics
	
	
	
}
