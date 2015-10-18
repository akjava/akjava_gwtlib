package com.akjava.gwt.inpaint.client;

import com.akjava.gwt.html5.client.file.Uint8Array;
import com.akjava.lib.common.utils.ColorUtils;
import com.google.gwt.canvas.dom.client.ImageData;
import com.google.gwt.core.client.JavaScriptObject;



public class InPaint  extends JavaScriptObject{
		protected InPaint(){}
			public static native final boolean exists()/*-{
			if($wnd.InpaintTelea){
				return true;
			}else{
				return false;
			}
			}-*/;
			
			
			public static final int DEFAULT_RADIUS=5;
			
			public static void createImageDataFromMask(ImageData data,Uint8Array mask,int r,int g,int b,int a,boolean doClear){
				for(int y=0;y<data.getHeight();y++){
					for(int x=0;x<data.getWidth();x++){
						if(mask.get(y*data.getWidth()+x)==1){
							data.setAlphaAt(a, x, y);
							data.setRedAt(r, x, y);
							data.setGreenAt(g, x, y);
							data.setBlueAt(b, x, y);
						}else{
							if(doClear){
							data.setAlphaAt(0, x, y);
							data.setRedAt(0, x, y);
							data.setGreenAt(0, x, y);
							data.setBlueAt(0, x, y);
							}
						}
					}
				}
			}
			
			public static void createImageDataFromMaskAsGray(ImageData data,Uint8Array mask){
				for(int y=0;y<data.getHeight();y++){
					for(int x=0;x<data.getWidth();x++){
						int v=mask.get(y*data.getWidth()+x);
						data.setAlphaAt(255, x, y);
						data.setRedAt(v, x, y);
						data.setGreenAt(v, x, y);
						data.setBlueAt(v, x, y);
					}
				}
			}
			public static Uint8Array createMaskByColor(ImageData data,int r,int g,int b){
				return createMaskByColor(data, r, g, b,true);
			}
			
			public static Uint8Array createMaskBySimilarColor(ImageData data,int r,int g,int b,int allowLength){
				Uint8Array bytes=Uint8Array.createUint8(data.getWidth()*data.getHeight());
				for(int y=0;y<data.getHeight();y++){
					for(int x=0;x<data.getWidth();x++){
						int imgR=data.getRedAt(x, y);
						int imgG=data.getGreenAt(x, y);
						int imgB=data.getBlueAt(x, y);
						
						double length=ColorUtils.getColorLength(r, g, b, imgR, imgG, imgB);
						if(length<allowLength){
							bytes.set(y*data.getWidth()+x, 1);
						}
					}
				}
				return bytes;
			}
			
			public static Uint8Array createMaskByColor(ImageData data,int r,int g,int b,boolean setSameColorCase){
				Uint8Array bytes=Uint8Array.createUint8(data.getWidth()*data.getHeight());
				for(int y=0;y<data.getHeight();y++){
					for(int x=0;x<data.getWidth();x++){
						int imgR=data.getRedAt(x, y);
						int imgG=data.getGreenAt(x, y);
						int imgB=data.getBlueAt(x, y);
						if(r==imgR && g==imgG&&b==imgB){
							if(setSameColorCase){
							bytes.set(y*data.getWidth()+x, 1);
							}
						}else{
							if(!setSameColorCase){
							bytes.set(y*data.getWidth()+x, 1);
							}
						}
					}
				}
				return bytes;
			}
			
			
			public final  static native void inpaint(ImageData blah,Uint8Array mask_u8,int radius)/*-{
			var width = blah.width;
			var height = blah.height;
			
			for(var channel = 0; channel < 3; channel++){
		var img_u8 = new Uint8Array(width * height)
		for(var n = 0; n < blah.data.length; n+=4){
			img_u8[n / 4] = blah.data[n + channel]
		}
		$wnd.InpaintTelea(width, height, img_u8, mask_u8,radius)
		for(var i = 0; i < img_u8.length; i++){
			blah.data[4 * i + channel] = img_u8[i]
		}	
	}

	
	// render result back to canvas
	for(var i = 0; i < img_u8.length; i++){
		blah.data[4 * i + 3] = 255;
	}
		}-*/;

			public final  native void inpaintTelea(int width,int height,Uint8Array imageChannel,Uint8Array mask,int radius)/*-{
				$wnd.InpaintTelea(width,height,imageChannel,mask,radius);
			}-*/;
			
			public static Uint8Array expandMaskByte(Uint8Array bytes,int width,int rad){
				int height=bytes.length()/width;
				Uint8Array copied=Uint8Array.createUint8(width*height);
				for(int y=0;y<height;y++){
					for(int x=0;x<width;x++){
						int value=bytes.get(y*width+x);
						if(value==1){
							for(int dx = -rad; dx <= rad; dx++){
								for(int dy = -rad; dy <= rad; dy++){
									if(dx * dx + dy * dy <= rad * rad){
										int sx=x+dx;
										int sy=y+dy;
										if(sx>=0 && sx<width && sy>=0 &&sy<height){//valid in
											int at=(y+dy)*width+x+dx;
											copied.set(at,1);
										}
									}
								}
							}
						}
					}
				}
				return copied;		
			}
			
			public static Uint8Array createMaskByAlpha(ImageData data) {
				Uint8Array bytes=Uint8Array.createUint8(data.getWidth()*data.getHeight());
				for(int y=0;y<data.getHeight();y++){
					for(int x=0;x<data.getWidth();x++){
						int alpha=data.getAlphaAt(x, y);
						
						//if(alpha==0){//this means only 0 
							//bytes.set((y)*data.getWidth()+x,1);
						//}
						if(alpha!=255){//need option
							bytes.set((y)*data.getWidth()+x,1);
						}
					}
				}
				
				
				
				return bytes;
			}
			//return 0-255 value
			public static Uint8Array expandMaskByteAsGray(Uint8Array bytes,int width,int rad) {
				int height=bytes.length()/width;
				double perv=256.0/rad;
				Uint8Array copied=Uint8Array.createUint8(width*height);
				for(int y=0;y<height;y++){
					for(int x=0;x<width;x++){
						int value=bytes.get(y*width+x);
						if(value==1){
							for(int dx = -rad; dx <= rad; dx++){
								for(int dy = -rad; dy <= rad; dy++){
									if(dx * dx + dy * dy <= rad * rad){
										
										double length=Math.sqrt(Math.pow(Math.abs(dx),2)+Math.pow(Math.abs(dy),2));
										int v=255-((int) Math.min(255,(int)(perv*length)));
										
										
										int sx=x+dx;
										int sy=y+dy;
										if(sx>=0 && sx<width && sy>=0 &&sy<height){//valid in
											int at=(y+dy)*width+x+dx;
											if(copied.get(at)<v){
												copied.set(at, v);
											}
										}
										
										
										
										
										
									}
								}
							}
						}
					}
				}
				return copied;
			}
			
}
