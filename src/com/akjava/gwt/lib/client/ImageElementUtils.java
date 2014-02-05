package com.akjava.gwt.lib.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.ImageData;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ImageElement;

public class ImageElementUtils {
private ImageElementUtils(){}

/**
 * watch out!
 * sometime return 0x0 withxheight on IE11
 * use new ImageElementLoader().load
 * @param url
 * @return
 */
public static ImageElement create(String url){
	ImageElement element=Document.get().createImageElement();
	element.setSrc(url);
	return element;
}
public static Canvas copytoCanvas(ImageElement element,Canvas canvas){
	return copytoCanvas(element, canvas,true);
}
public static Canvas copytoCanvas(ImageElement element,Canvas canvas,boolean drawImage){
	if(canvas==null){
		canvas=Canvas.createIfSupported();
	}
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


}
