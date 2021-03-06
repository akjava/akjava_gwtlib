package com.akjava.gwt.lib.client.experimental;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nullable;

import com.akjava.gwt.lib.client.CanvasUtils;
import com.akjava.gwt.lib.client.ImageElementUtils;
import com.akjava.lib.common.graphics.IntRect;
import com.akjava.lib.common.graphics.Rect;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;

public class RectCanvasUtils {
private static Canvas sharedCanvas=Canvas.createIfSupported();//TODO delay
	public static Canvas crop(String dataUrl,IntRect rect,@Nullable Canvas retCanvas){
		checkNotNull(dataUrl);
		checkNotNull(rect);
		Canvas imageCanvas=ImageElementUtils.copytoCanvas(dataUrl, sharedCanvas);
		if(retCanvas==null){
			retCanvas=Canvas.createIfSupported();
		}
		CanvasUtils.createCanvas(retCanvas, rect.getWidth(),rect.getHeight());
		retCanvas.getContext2d().drawImage(imageCanvas.getCanvasElement(), -rect.getX(), -rect.getY());
		return retCanvas;
	}
	
	public static Canvas crop(Canvas canvas,IntRect rect,@Nullable Canvas retCanvas){
		checkNotNull(rect);
		return crop(canvas,rect.getX(),rect.getY(),rect.getWidth(),rect.getHeight(),retCanvas);
	}
	public static Canvas crop(Canvas canvas,int x,int y,int w,int h,@Nullable Canvas retCanvas){
		checkNotNull(canvas);
		
		if(retCanvas==null){
			retCanvas=Canvas.createIfSupported();
		}
		//use imagedata style can't crop over pos
		CanvasUtils.createCanvas(retCanvas, w,h);
		//retCanvas.getContext2d().drawImage(canvas.getCanvasElement(), -x, -y);
		retCanvas.getContext2d().drawImage(canvas.getCanvasElement(), x,y,w,h,0,0,w,h);
		return retCanvas;
	}
	
	public static IntRect createRectFromCanvasSize(Canvas canvas){
		return new IntRect(0, 0, canvas.getCoordinateSpaceWidth(), canvas.getCoordinateSpaceHeight());
	}
	
	
	public static Canvas crop(ImageElement image,IntRect rect,@Nullable Canvas retCanvas){
		checkNotNull(image);
		checkNotNull(rect);
		
		if(retCanvas==null){
			retCanvas=Canvas.createIfSupported();
		}
		//use imagedata style can't crop over pos
		CanvasUtils.createCanvas(retCanvas, rect.getWidth(),rect.getHeight());
		retCanvas.getContext2d().drawImage(image, -rect.getX(), -rect.getY());
		return retCanvas;
	}

	public static void fill(IntRect r, Canvas canvas, String style) {
		canvas.getContext2d().setFillStyle(style);
		canvas.getContext2d().fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
	}
	public static void stroke(IntRect r, Canvas canvas, @Nullable String style) {
		if(style!=null){
			canvas.getContext2d().setStrokeStyle(style);
		}
		canvas.getContext2d().strokeRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
	}
	public static void stroke(Rect r, Canvas canvas, @Nullable String style) {
		if(style!=null){
			canvas.getContext2d().setStrokeStyle(style);
		}
		canvas.getContext2d().strokeRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
	}

	public static void fillCircle(IntRect rect,Canvas canvas,boolean inbox){
		fillCircle(canvas,rect.getX(),rect.getY(),rect.getWidth(),rect.getHeight(),inbox);
	}
	

	
	public static void fillCircle(Canvas canvas,int x,int y,int width,int height,boolean inbox){
		drawCircle(canvas,x,y,width,height,inbox,false);
	}
	
	public static void strokeCircle(IntRect rect,Canvas canvas,boolean inbox){
		strokeCircle(canvas,rect.getX(),rect.getY(),rect.getWidth(),rect.getHeight(),inbox);
	}
	
	public static void strokeCircle(Canvas canvas,int x,int y,int width,int height,boolean inbox){
		drawCircle(canvas,x,y,width,height,inbox,true);
	}
	private static void drawCircle(Canvas canvas,int x,int y,int width,int height,boolean inbox,boolean stroke){
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
		
		if(stroke){
			context.stroke();
		}else{
			context.fill();
		}
		
	}
	
	
	public static void strokeOval(IntRect rect,Canvas canvas,boolean inbox){
		strokeOval(canvas,rect.getX(),rect.getY(),rect.getWidth(),rect.getHeight(),inbox);
	}
	public static void strokeOval(Canvas canvas,int x,int y,int width,int height,boolean inbox){
		int cx=x+width/2;
		int cy=y+height/2;
		
		
		double rad;
		double wscale=1;
		double hscale=1;
		if(inbox){
			if(width>height){
				rad=height/2;
				wscale=(double)width/height;
			}else{
				rad=width/2;;
				hscale=(double)height/width;
			}
		}else{
			double longer;
			if(width>height){
				longer=height/2;
				wscale=(double)width/height;
			}else{
				longer=width/2;;
				hscale=(double)height/width;
			}
			
			rad=Math.sqrt(Math.pow(longer, 2)*2);
		}
		
		
		Context2d context=canvas.getContext2d();
		context.save();
		context.translate(cx, cy);
		context.scale(wscale, hscale);
		context.beginPath();
		
		canvas.getContext2d().arc(0, 0, rad, 0, Math.PI*2);
		
		context.closePath();
		context.restore();
		context.stroke();
	}

	public static void fill(Rect rect, Canvas canvas, String rectColor) {
		canvas.getContext2d().setFillStyle(rectColor);
		canvas.getContext2d().fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
	}

	public static void clear(Canvas canvas, Rect r) {
		canvas.getContext2d().clearRect(r.getX(), r.getY(), r.getWidth(),r.getHeight());
		
	}
}
