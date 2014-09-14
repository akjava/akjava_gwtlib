package com.akjava.gwt.lib.client.experimental;

import com.akjava.gwt.lib.client.CanvasUtils;
import com.akjava.gwt.lib.client.ImageElementUtils;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.ImageData;
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
public static ImageData create(int w,int h){
	return create(null,w,h);
	}

	public static ImageData create(Canvas canvas,int w,int h){
		if(canvas==null){
			canvas=getSharedCanvas();
		}
		return canvas.getContext2d().createImageData(w, h);
		}
	

public static ImageData create(Canvas canvas,ImageElement element){
	if(canvas==null){
		canvas=Canvas.createIfSupported();
	}
	ImageElementUtils.copytoCanvas(element, canvas, true);
	return CanvasUtils.getImageData(canvas, true);
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

public static ImageData setGrayscale(ImageData imageData,Uint8ArrayNative nativeArray,boolean setRGBA){
	int w=imageData.getWidth();
	for(int i=0;i<nativeArray.length();i++){
		int x=i%w;
		int y=i/w;
		imageData.setRedAt(nativeArray.get(i), x, y);
	}
	return imageData;
	}

/**
 *  var dst = ctx.createImageData(src.width, src.height);
    dst.data.set(src.data);
    return dst;
 * 
 */


}
