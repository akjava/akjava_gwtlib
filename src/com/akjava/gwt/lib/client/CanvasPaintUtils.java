package com.akjava.gwt.lib.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;

/**
 * @deprecated move to rectcanvas
 * @author aki
 *
 */
public class CanvasPaintUtils {

	public static void drawCircleInRect(Canvas canvas,int x,int y,int width,int height,boolean inbox,boolean fill){
		int cx=x+width/2;
		int cy=y+height/2;
		
		
		double rad;
		
		if(inbox){
			rad=width>height?height/2:width/2;
		}else{
			double longer=width>height?width/2:height/2;
			rad=Math.sqrt(Math.pow(longer, 2)*2);
		}
		
		Context2d context=canvas.getContext2d();
		context.beginPath();
		
		canvas.getContext2d().arc(cx, cy, rad, 0, Math.PI*2);
		
		context.closePath();
		if(fill){
			context.fill();
		}else{
			context.stroke();
		}
	}
}
