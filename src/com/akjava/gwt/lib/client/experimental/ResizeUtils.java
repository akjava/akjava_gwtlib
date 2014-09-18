package com.akjava.gwt.lib.client.experimental;

import com.akjava.gwt.lib.client.LogUtils;
import com.google.gwt.canvas.dom.client.ImageData;
import com.google.gwt.typedarrays.client.Uint8ArrayNative;

public class ResizeUtils {
private ResizeUtils(){}
//from http://tech-algorithm.com/articles/bilinear-image-scaling/
public static Uint8ArrayNative resizeBilinear(Uint8ArrayNative array, int w, int h, int w2, int h2) {
	Uint8ArrayNative result = Uint8ArrayNative.create(w2*h2*4) ;
    int a, b, c, d, x, y, index ;
    float x_ratio = ((float)(w-1))/w2 ;
    float y_ratio = ((float)(h-1))/h2 ;
    float x_diff, y_diff, value ;
    int offset = 0 ;
    for (int i=0;i<h2;i++) {
        for (int j=0;j<w2;j++) {
            x = (int)(x_ratio * j) ;
            y = (int)(y_ratio * i) ;
            x_diff = (x_ratio * j) - x ;
            y_diff = (y_ratio * i) - y ;
            index = (y*w+x) ;
            
            for(int k=0;k<4;k++){
            	a = array.get((index)*4+k) ;
  	            b =array.get((index+1)*4+k) ;
  	            c = array.get((index+w)*4+k) ;
  	            d = array.get((index+w+1)*4+k) ;
  	            
  	          value = (a&0xff)*(1-x_diff)*(1-y_diff) + (b&0xff)*(x_diff)*(1-y_diff) +
	                   (c&0xff)*(y_diff)*(1-x_diff)   + (d&0xff)*(x_diff*y_diff);
  	          
  	        result.set(offset*4+k,(int) value); 
            }
          
            offset++;
            
        }
    }
    return result;
}

public static Uint8ArrayNative resizeBilinearRedOnly(Uint8ArrayNative array, int w, int h, int w2, int h2) {
	Uint8ArrayNative result = Uint8ArrayNative.create(w2*h2*4) ;
    int a, b, c, d, x, y, index ;
    float x_ratio = ((float)(w-1))/w2 ;
    float y_ratio = ((float)(h-1))/h2 ;
    float x_diff, y_diff, value ;
    int offset = 0 ;
    for (int i=0;i<h2;i++) {
        for (int j=0;j<w2;j++) {
            x = (int)(x_ratio * j) ;
            y = (int)(y_ratio * i) ;
            x_diff = (x_ratio * j) - x ;
            y_diff = (y_ratio * i) - y ;
            index = (y*w+x) ;
            
            	a = array.get((index)*4) ;
	            b =array.get((index+1)*4) ;
	            c = array.get((index+w)*4) ;
	            d = array.get((index+w+1)*4) ;
	            
	          value = (a&0xff)*(1-x_diff)*(1-y_diff) + (b&0xff)*(x_diff)*(1-y_diff) +
                   (c&0xff)*(y_diff)*(1-x_diff)   + (d&0xff)*(x_diff*y_diff);
	          
	        result.set(offset*4,(int) value); 
          
            offset++;
            
        }
    }
    return result;
}

/**
 * 
 * @param array must be packed
 * @param w
 * @param h
 * @param w2
 * @param h2
 * @return
 */
public static Uint8ArrayNative resizeBilinearRedOnlyPacked(Uint8ArrayNative array, int w, int h, int w2, int h2) {
	Uint8ArrayNative result = Uint8ArrayNative.create(w2*h2) ;
    int a, b, c, d, x, y, index ;
    float x_ratio = ((float)(w-1))/w2 ;
    float y_ratio = ((float)(h-1))/h2 ;
    float x_diff, y_diff, value ;
    int offset = 0 ;
    for (int i=0;i<h2;i++) {
        for (int j=0;j<w2;j++) {
            x = (int)(x_ratio * j) ;
            y = (int)(y_ratio * i) ;
            x_diff = (x_ratio * j) - x ;
            y_diff = (y_ratio * i) - y ;
            index = (y*w+x) ;
            
            	a = array.get((index)) ;
	            b =array.get((index+1)) ;
	            c = array.get((index+w)) ;
	            d = array.get((index+w+1)) ;
	            
	          value = (a&0xff)*(1-x_diff)*(1-y_diff) + (b&0xff)*(x_diff)*(1-y_diff) +
                   (c&0xff)*(y_diff)*(1-x_diff)   + (d&0xff)*(x_diff*y_diff);
	          
	        result.set(offset,(int) value); 
          
            offset++;
            
        }
    }
    return result;
}

//http://tech-algorithm.com/articles/nearest-neighbor-image-scaling/
public static Uint8ArrayNative resizePixels(Uint8ArrayNative array,int w1,int h1,int w2,int h2) {
    int[] temp = new int[w2*h2*4] ;//rgba
	
	// EDIT: added +1 to account for an early rounding problem
    int x_ratio = (int)((w1<<16)/w2) +1;
    int y_ratio = (int)((h1<<16)/h2) +1;
    //int x_ratio = (int)((w1<<16)/w2) ;
    //int y_ratio = (int)((h1<<16)/h2) ;
    int x2, y2 ;
    for (int i=0;i<h2;i++) {
        for (int j=0;j<w2;j++) {
            x2 = ((j*x_ratio)>>16) ;
            y2 = ((i*y_ratio)>>16) ;
            int tempOff=((i*w2)+j)*4;
            int arrayOff=((y2*w1)+x2)*4;
            for(int k=0;k<4;k++){
            	temp[tempOff+k] = array.get(arrayOff+k) ;
            }
            
        }                
    }                
    return Uint8ArrayNative.create(temp);
}

public final  static native Uint8ArrayNative getUint8Array(ImageData data) /*-{
return data.data;
}-*/;

public final static  void setUint8Array(ImageData data,int[] array){
	int w=data.getWidth();
	LogUtils.log(array.length+",data="+data.getWidth()+"x"+data.getHeight());
	for(int i=0;i<array.length;i+=4){
		int x=i%(w*4);
		int y=i/(w*4);
		data.setRedAt(array[i], x, y);
		data.setGreenAt(array[i+1], x, y);
		data.setBlueAt(array[i+2], x, y);
		data.setAlphaAt(array[i+3], x, y);
		//LogUtils.log(x+"x"+y+","+array[i]+"/"+array[i+3]);
	}
}

public final static native void setUint8Array(ImageData data,Uint8ArrayNative array) /*-{
data.data.set(array);
}-*/;
}
