package com.akjava.gwt.lib.client;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nullable;

import com.akjava.gwt.html5.client.download.DownloadURL;
import com.akjava.gwt.html5.client.file.Blob;
import com.akjava.gwt.html5.client.file.File;
import com.akjava.gwt.html5.client.file.webkit.FileCallback;
import com.akjava.gwt.html5.client.file.webkit.FileEntry;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.ImageData;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ImageElement;

public class ImageElementUtils {
private ImageElementUtils(){}

/**
 * 
 * watch out!
 * sometime return 0x0 withxheight on IE11 and chrome
 * use new ImageElementLoader().load


 * @deprecated
 * @param url
 * @return
 * 
 * 
 * 
 
 //one of replace case
 ImageElementUtils.createWithLoader(text,new ImageElementListener() {
					
					@Override
					public void onLoad(ImageElement element) {
						
					}
					
					@Override
					public void onError(String url, ErrorEvent event) {
						LogUtils.log(event.getNativeEvent());
					}
				});
 
 **/
public static ImageElement create(String url){
	checkNotNull(url,"ImageElementUtils:imageElement need url");
	ImageElement element=Document.get().createImageElement();
	element.setSrc(url);
	return element;
}

public static ImageElement createNotLoadedImage(String url){
	checkNotNull(url,"ImageElementUtils:imageElement need url");
	ImageElement element=Document.get().createImageElement();
	element.setSrc(url);
	return element;
}

/*
 * url must be loaded url;
 */
public static ImageElement createFromLoadedUrl(String url){
	checkNotNull(url,"ImageElementUtils:imageElement need url");
	ImageElement element=Document.get().createImageElement();
	element.setSrc(url);
	if(element.getWidth()==0 || element.getHeight()==0){
		LogUtils.log("createFromLoadedUrl:possible not loaded");
	}
	return element;
}


public static ImageElementLoader createWithLoader(String url,ImageElementListener listener){
	ImageElementLoader loader=new ImageElementLoader();
	loader.load(url, listener);
	return loader;
}



public static void fileEntryToImage(FileEntry fileEntry,final ImageElementListener listener){
	checkNotNull(fileEntry,"file is null");
	checkNotNull(listener,"listener is null");
	fileEntry.file(new FileCallback() {
		
		@Override
		public void callback(File file) {
			fileToImage(file,listener);
		}
	});
}

public static void fileToImage(Blob file,ImageElementListener listener){
	checkNotNull(file,"file is null");
	checkNotNull(listener,"listener is null");
	new ImageElementLoader().load(DownloadURL.get().createObjectURL(file), listener);
}


public static Canvas copytoCanvas(String dataUrl,Canvas canvas){
	return copytoCanvas(create(dataUrl), canvas,true);
}

public static Canvas flip(ImageElement image,boolean horizontal,boolean vertical,@Nullable Canvas workingCanvas){
	
	if(workingCanvas==null){
		workingCanvas=Canvas.createIfSupported();
	}
	
	int w=image.getWidth();
	int h=image.getHeight();
	//set size
	CanvasUtils.setSize(workingCanvas, w,h);
	workingCanvas.getContext2d().save();
	int x=horizontal?w:0;
	int y=vertical?h:0;
	int scaleX=horizontal?-1:1;
	int scaleY=vertical?-1:1;
	workingCanvas.getContext2d().save();
	workingCanvas.getContext2d().translate(x, y);
	workingCanvas.getContext2d().scale(scaleX, scaleY);
	workingCanvas.getContext2d().drawImage(image, 0, 0);
	workingCanvas.getContext2d().restore();
	workingCanvas.getContext2d().restore();
	return workingCanvas;
}


public static Canvas copytoCanvas(ImageElement element,Canvas canvas){
	return copytoCanvas(element, canvas,true);
}
public static Canvas copytoCanvas(ImageElement element,Canvas canvas,boolean drawImage){
	if(canvas==null){
		canvas=Canvas.createIfSupported();
	}
	checkArgument(element.getWidth()!=0 && element.getHeight()!=0,"copytoCanvas:0 size width or height element,need load image");
	canvas.setWidth(element.getWidth()+"px");
	canvas.setHeight(element.getHeight()+"px");
	canvas.setCoordinateSpaceWidth(element.getWidth());
	canvas.setCoordinateSpaceHeight(element.getHeight());
	if(drawImage){
		canvas.getContext2d().drawImage(element, 0, 0);
	}
	return canvas;
}




public static void inPaintMargin(Canvas canvas,int margin){

	//up,down,left,right
	int originW=canvas.getCoordinateSpaceWidth()-margin*2;
	int originH=canvas.getCoordinateSpaceHeight()-margin*2;
	
	//top-area
	ImageData data=null;
	data=canvas.getContext2d().getImageData(margin, 0, originW, margin+1);
	for(int y=margin-1;y>=0;y--){
		for(int x=0;x<originW;x++){
			copyImageData(data,x,margin,x,y);
			//LogUtils.log("from "+x+","+margin+",to "+x+","+y);
		}
	}
	canvas.getContext2d().putImageData(data,margin, 0);
	//bottom
	data=canvas.getContext2d().getImageData(margin, originH+margin-1, originW, margin+1);
	for(int y=1;y<margin+1;y++){
		for(int x=0;x<originW;x++){
			copyImageData(data,x,0,x,y);
		}
	}
	canvas.getContext2d().putImageData(data,margin, originH+margin-1);
	//left
	data=canvas.getContext2d().getImageData(0, margin, margin+1, originH);
	for(int y=0;y<originH;y++){
		for(int x=margin-1;x>=0;x--){
			copyImageData(data,margin,y,x,y);
		}
	}
	canvas.getContext2d().putImageData(data,0, margin);
	//right
	data=canvas.getContext2d().getImageData(originW+margin-1, margin, margin+1, originH);
	for(int y=0;y<originH;y++){
		for(int x=1;x<margin+1;x++){
			copyImageData(data,0,y,x,y);
		}
	}
	canvas.getContext2d().putImageData(data,originW+margin-1, margin);
	
	//left-top
	data=canvas.getContext2d().getImageData(0,0, margin+1, margin+1);
	for(int y=margin-1;y>=0;y--){
		for(int x=margin-1;x>=0;x--){
			copyImageData(data,x,y+1,x+1,y,x,y);
		}
	}
	canvas.getContext2d().putImageData(data,0,0);
	//right-top
	data=canvas.getContext2d().getImageData(originW+margin-1,0, margin+1, margin+1);
	for(int y=margin-1;y>=0;y--){
		for(int x=1;x<margin+1;x++){
			copyImageData(data,x-1,y,x,y+1,x,y);
		}
	}
	canvas.getContext2d().putImageData(data,originW+margin-1,0);
	//left-bottom
	data=canvas.getContext2d().getImageData(0,originH+margin-1, margin+1, margin+1);
	for(int y=1;y<margin+1;y++){
		for(int x=margin-1;x>=0;x--){
			copyImageData(data,x,y-1,x+1,y,x,y);
		}
	}
	canvas.getContext2d().putImageData(data,0,originH+margin-1);
	//right-bottom
	data=canvas.getContext2d().getImageData(originW+margin-1,originH+margin-1, margin+1, margin+1);
	for(int y=1;y<margin+1;y++){
		for(int x=1;x<margin+1;x++){
			copyImageData(data,x-1,y,x,y-1,x,y);
		}
	}
	canvas.getContext2d().putImageData(data,originW+margin-1,originH+margin-1);
}
public static Canvas copytoCanvasWithMargin(ImageElement element,Canvas canvas,boolean drawImage,int margin,boolean fillMargin){
	if(canvas==null){
		canvas=Canvas.createIfSupported();
	}
	int originW=element.getWidth();
	int originH=element.getHeight();
	
	checkArgument(originH!=0 && originH!=0,"copytoCanvasWithMargin 0 width or height,maybe need image-loading");
	
	int w=originW+margin*2;
	int h=originH+margin*2;
	canvas.setWidth(w+"px");
	canvas.setHeight(h+"px");
	canvas.setCoordinateSpaceWidth(w);
	canvas.setCoordinateSpaceHeight(h);
	if(drawImage){
		canvas.getContext2d().drawImage(element, margin, margin);
		
		if(fillMargin){
			inPaintMargin(canvas,margin);
		}
		
	}
	return canvas;
}

//move to canvas?
public static void copyImageData(ImageData data,int x1,int y1,int x2,int y2){
	data.setAlphaAt(data.getAlphaAt(x1, y1), x2, y2);
	data.setRedAt(data.getRedAt(x1, y1), x2, y2);
	data.setGreenAt(data.getGreenAt(x1, y1), x2, y2);
	data.setBlueAt(data.getBlueAt(x1, y1), x2, y2);
}

public static void copyImageData(ImageData data,int x1,int y1,int x2,int y2,int x3,int y3){
	data.setAlphaAt((data.getAlphaAt(x1, y1)+data.getAlphaAt(x2, y2))/2, x3, y3);
	data.setRedAt((data.getRedAt(x1, y1)+data.getRedAt(x2, y2))/2, x3, y3);
	data.setGreenAt((data.getGreenAt(x1, y1)+data.getGreenAt(x2, y2))/2, x3, y3);
	data.setBlueAt((data.getBlueAt(x1, y1)+data.getBlueAt(x2, y2))/2, x3, y3);
}

public static boolean isSameSize(ImageElement image, @Nullable ImageElement image2) {
	checkNotNull(image,"isSameSize:image is null");
	if(image2==null){
		return false;
	}
	
	return image.getWidth()==image2.getWidth() && image.getHeight()==image2.getHeight();
}
/**
 * 
 * @param imageElement
 * @return possible null if input is null
 */
public static ImageElement copy(@Nullable ImageElement imageElement) {
	if(imageElement==null){
		return null;
	}
	return create(imageElement.getSrc());
}


}
