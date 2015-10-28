package com.akjava.gwt.lib.client.experimental;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nullable;

import com.akjava.gwt.lib.client.CanvasUtils;
import com.akjava.gwt.lib.client.ImageElementUtils;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.ImageData;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.typedarrays.client.Uint8ArrayNative;

public class ImageDataUtils {
public static Canvas sharedCanvas;
private static Canvas getSharedCanvas(){
	if(sharedCanvas==null){
		sharedCanvas=Canvas.createIfSupported();
	}
	return sharedCanvas;
}

public static ImageData convertToGrayScale(ImageData data,boolean firstChannelOnly) {
	for(int y=0;y<data.getHeight();y++){
		for(int x=0;x<data.getWidth();x++){
			int value=(int) (0.299*data.getRedAt(x, y) + 0.587*data.getGreenAt(x, y) + 0.114*data.getBlueAt(x, y));
			data.setRedAt(value, x, y);
			if(firstChannelOnly){
			data.setGreenAt(value, x, y);
			data.setBlueAt(value, x, y);
			}
		}
	}
	return data;
}

public static ImageData createWithSharedCanvas(int w,int h){
	return createNoCopyWith(null,w,h);
	}

public static ImageData copyFrom(Canvas canvas){
	return canvas.getContext2d().getImageData(0, 0, canvas.getCoordinateSpaceWidth(), canvas.getCoordinateSpaceHeight());
	}

	public static ImageData createNoCopyWith(Canvas canvas,int w,int h){
		if(canvas==null){
			canvas=getSharedCanvas();
		}
		return canvas.getContext2d().createImageData(w, h);
		}
	
	public static ImageData copySizeOnly(Canvas canvas){
		
		return canvas.getContext2d().createImageData(canvas.getCoordinateSpaceWidth(), canvas.getCoordinateSpaceHeight());
		}
	

public static ImageData create(@Nullable Canvas canvas,ImageElement element){
	if(canvas==null){
		canvas=Canvas.createIfSupported();
	}
	ImageElementUtils.copytoCanvas(element, canvas, true);
	return CanvasUtils.getImageData(canvas, true);
	}

public static void setAlphaAll(ImageData imageData,int alpha){
	for(int x=0;x<imageData.getWidth();x++){
		for(int y=0;y<imageData.getHeight();y++){
			imageData.setAlphaAt(alpha, x, y);
		}
	}
}

	public static Uint8ArrayNative crop(ImageData data,int x,int y,int width,int height){
		// array=Uint8ArrayNative.create(width*height*4);
		int values[]=new int[width*height*4];
		//LogUtils.log(values.length);
		for(int i=x;i<x+width;i++){
			for(int j=y;j<y+height;j++){
				
				
				
				int red=data.getRedAt(i, j);
				int green=data.getGreenAt(i, j);
				int blue=data.getBlueAt(i, j);
				int alpha=data.getAlphaAt(i, j);
				
				int offset=((j-y)*width+(i-x))*4;
				
				values[offset]=red;
				values[offset+1]=green;
				values[offset+2]=blue;
				values[offset+3]=alpha;
				
				/*
				LogUtils.log(i+"x"+j+"="+red+","+green+","+blue+","+alpha);
				array.set(offset,red);
				array.set(offset+1,green);
				array.set(offset+2,blue);
				array.set(offset+3,alpha);
				*/
			}
		}
		
		return Uint8ArrayNative.create(values);
		//return values;
	}
	
	public static Uint8ArrayNative cropRedOnly(ImageData data,int x,int y,int width,int height){
		// array=Uint8ArrayNative.create(width*height*4);
		int values[]=new int[width*height*4];
		//LogUtils.log(values.length);
		for(int i=x;i<x+width;i++){
			for(int j=y;j<y+height;j++){
				
				
				
				int red=data.getRedAt(i, j);
				//int green=data.getGreenAt(i, j);
				//int blue=data.getBlueAt(i, j);
				//int alpha=data.getAlphaAt(i, j);
				
				int offset=((j-y)*width+(i-x))*4;
				
				values[offset]=red;
				//values[offset+1]=green;
				//values[offset+2]=blue;
				//values[offset+3]=alpha;
				
				/*
				LogUtils.log(i+"x"+j+"="+red+","+green+","+blue+","+alpha);
				array.set(offset,red);
				array.set(offset+1,green);
				array.set(offset+2,blue);
				array.set(offset+3,alpha);
				*/
			}
		}
		
		return Uint8ArrayNative.create(values);
		//return values;
	}
	
	public static Uint8ArrayNative cropRedOnlyPacked(ImageData data,int x,int y,int width,int height){
		// array=Uint8ArrayNative.create(width*height*4);
		int values[]=new int[width*height];
		//LogUtils.log(values.length);
		for(int i=x;i<x+width;i++){
			for(int j=y;j<y+height;j++){
				
				
				
				int red=data.getRedAt(i, j);
				//int green=data.getGreenAt(i, j);
				//int blue=data.getBlueAt(i, j);
				//int alpha=data.getAlphaAt(i, j);
				
				int offset=((j-y)*width+(i-x));
				
				values[offset]=red;
				//values[offset+1]=green;
				//values[offset+2]=blue;
				//values[offset+3]=alpha;
				
				/*
				LogUtils.log(i+"x"+j+"="+red+","+green+","+blue+","+alpha);
				array.set(offset,red);
				array.set(offset+1,green);
				array.set(offset+2,blue);
				array.set(offset+3,alpha);
				*/
			}
		}
		
		return Uint8ArrayNative.create(values);
		//return values;
	}
	
	

public static ImageData copy(Canvas canvas,ImageData data){
	return copy(canvas.getContext2d(),data);
	}


public static ImageData copy(Context2d ctx,ImageData data){
	ImageData newData=ctx.createImageData(data);
	for(int i=0;i<data.getData().getLength();i++){
		newData.getData().set(i, data.getData().get(i));
	}
	return newData;
	}
public static ImageData copyFast(Canvas canvas,ImageData data){
	return copyFast(canvas.getContext2d(),data);
	}	

public  static  native final ImageData copyFast(Context2d ctx,ImageData src)/*-{
var dst = ctx.createImageData(src.width, src.height);
dst.data.set(src.data);
return dst;
}-*/;

public  static  native final Uint8ArrayNative getUint8(ImageData data)/*-{
return data.data;
}-*/;

public  static  native final JavaScriptObject getArrayBuffer(ImageData data)/*-{
return data.data.buffer;
}-*/;

public static ImageData setGrayscale(ImageData imageData,Uint8ArrayNative nativeArray,boolean setRGBA){
	int w=imageData.getWidth();
	for(int i=0;i<nativeArray.length();i++){
		int x=i%w;
		int y=i/w;
		imageData.setRedAt(nativeArray.get(i), x, y);
		if(setRGBA){
			imageData.setGreenAt(nativeArray.get(i), x, y);
			imageData.setBlueAt(nativeArray.get(i), x, y);
			imageData.setAlphaAt(255, x, y);
		}
	}
	return imageData;
	}

public static boolean isSameData(ImageData data1,ImageData data2){
	if(data1==data2){
		return true;
	}
	if(data1.getWidth()!=data2.getWidth() || data1.getHeight()!=data2.getHeight()){
		return false;
	}
	for(int x=0;x<data1.getWidth();x++){
		for(int y=0;y<data1.getHeight();y++){
			
			if(data1.getRedAt(x, y)!=data2.getRedAt(x, y)){
				return false;
			}
			
			if(data1.getGreenAt(x, y)!=data2.getGreenAt(x, y)){
				return false;
			}
			
			if(data1.getBlueAt(x, y)!=data2.getBlueAt(x, y)){
				return false;
			}
			
			if(data1.getAlphaAt(x, y)!=data2.getAlphaAt(x, y)){
				return false;
			}
			
			
		}
	}
	
	return true;
}

public static void rezieAndImageData(ImageData data,Canvas canvas) {
	checkNotNull(data,"putImageData:need data");
	checkNotNull(canvas,"putImageData:need canvas");
	CanvasUtils.setSize(canvas, data.getWidth(), data.getHeight());
	putImageData(data, canvas);
}

public static void putImageData(ImageData data,Canvas canvas) {
	checkNotNull(data,"putImageData:need data");
	checkNotNull(canvas,"putImageData:need canvas");
	canvas.getContext2d().putImageData(data, 0, 0);
}

/**
 *  var dst = ctx.createImageData(src.width, src.height);
    dst.data.set(src.data);
    return dst;
 * 
 */


}
