package com.akjava.gwt.lib.client;

import com.akjava.gwt.html5.client.download.HTML5Download;
import com.akjava.gwt.html5.client.file.Uint8Array;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.ImageData;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Image;

public class CanvasUtils {


	public static Canvas createCanvas(Canvas canvas,int w,int h){
		if(canvas!=null){
			canvas.setCoordinateSpaceWidth(w);
			canvas.setCoordinateSpaceHeight(h);
			canvas.setWidth(w+"px");
			canvas.setHeight(h+"px");
		}
		return canvas;
	}
	
public static Canvas createCanvas(int w,int h){
	Canvas canvas=Canvas.createIfSupported();
	return createCanvas(canvas,w,h);
}

public static void clip(Canvas canvas,int x,int y,int width,int height){
	canvas.getContext2d().beginPath();
	canvas.getContext2d().moveTo(x,y);
	canvas.getContext2d().lineTo(x+width,y);
	canvas.getContext2d().lineTo(x+width,y+height);
	canvas.getContext2d().lineTo(x,y+height);
	canvas.getContext2d().clip();
}

public static String toDataUrl(Canvas canvas,Canvas useCanvas,int x,int y,int width,int height){
	ImageData data=canvas.getContext2d().getImageData(x, y, width, height);
	if(useCanvas==null){
		useCanvas=Canvas.createIfSupported();
	}
	copyTo(data, useCanvas);
	return useCanvas.toDataUrl();
}

public static Canvas createCanvas(Canvas canvas,ImageData data){
	if(canvas==null){
	canvas=Canvas.createIfSupported();
	}
	canvas.setCoordinateSpaceWidth(data.getWidth());
	canvas.setCoordinateSpaceHeight(data.getHeight());
	
	canvas.getContext2d().putImageData(data, 0, 0);
	
	return canvas;
}

public static void disableSelection(Canvas canvas){
	GWTHTMLUtils.disableSelectionStart(canvas.getCanvasElement());
}


public static void drawCenter(Canvas canvas,ImageElement image,int offsetX,int offsetY,double scaleX,double scaleY,double angle,double alpha){
	canvas.getContext2d().save();
	double rx=(canvas.getCoordinateSpaceWidth())/2;
	double ry=(canvas.getCoordinateSpaceHeight())/2;
	
	canvas.getContext2d().translate(rx,ry);//rotate center
	double rotate=(Math.PI / 180)*angle;
	canvas.getContext2d().rotate(rotate);
	canvas.getContext2d().translate(-rx,-ry);//and back
	
	canvas.getContext2d().scale(scaleX,scaleY);
	
	double px=(canvas.getCoordinateSpaceWidth()/scaleX-image.getWidth())/2;
	double py=(canvas.getCoordinateSpaceHeight()/scaleY-image.getHeight())/2;
	
	//LogUtils.log("w="+canvas.getCoordinateSpaceWidth()+",scaled="+(element.getWidth()*scale));
	
	//LogUtils.log("scale:"+scale+",x="+px+",y="+py);
	
	int ox=(int) (offsetX/scaleX);
	int oy=(int) (offsetY/scaleY);
	//canvas.getContext2d().rotate(-rotate);
	
	double x=ox;
	double y=oy;
	
	//offset is effect on angle,but scroll no need do it
	double nx = px+x * Math.cos(-rotate) - y * Math.sin(-rotate);
	double ny = py+x * Math.sin(-rotate) + y * Math.cos(-rotate);
	
	canvas.getContext2d().translate(nx,ny);	
	canvas.getContext2d().setGlobalAlpha(alpha);
	canvas.getContext2d().drawImage(image, 0,0);
	canvas.getContext2d().restore();
}


/*
 * if you need use ImageElementLoader
 */
public static ImageElement toImageElement(Canvas canvas){
	Image img=new Image(canvas.toDataUrl());
	ImageElement imageElement=ImageElement.as(img.getElement());
	return imageElement;
}
public static void clear(Canvas canvas){
	canvas.getContext2d().clearRect(0, 0, canvas.getCoordinateSpaceWidth(), canvas.getCoordinateSpaceHeight());
}
public static void fillRect(Canvas canvas,String style){
	canvas.getContext2d().setFillStyle(style);
	canvas.getContext2d().fillRect(0, 0, canvas.getCoordinateSpaceWidth(), canvas.getCoordinateSpaceHeight());
}

public static String createColorRectImageDataUrl(int r,int g,int b,double opacity,int w,int h){
	Canvas canvas=CanvasUtils.createCanvas(w, h);
	canvas.getContext2d().setFillStyle("rgba("+r+","+g+","+b+","+opacity+")");
	canvas.getContext2d().fillRect(0, 0, w, h);
	String image1=canvas.toDataUrl();
	return image1;
}

public static Canvas createCircleImageCanvas(int r,int g,int b,double opacity,double radius,double lineWidth,boolean stroke){
	double center=radius+lineWidth;
	Canvas canvas=CanvasUtils.createCanvas((int)center*2,(int)center*2);
	if(stroke){
	canvas.getContext2d().setStrokeStyle("rgba("+r+","+g+","+b+","+opacity+")");
	canvas.getContext2d().setLineWidth(lineWidth);
	}else{
	canvas.getContext2d().setFillStyle("rgba("+r+","+g+","+b+","+opacity+")");	
	}
	canvas.getContext2d().beginPath();
	
	canvas.getContext2d().arc(center, center, radius, 0, 360);
	canvas.getContext2d().closePath();
	if(stroke){
		canvas.getContext2d().stroke();
	}else{
		canvas.getContext2d().fill();
	}
	
	return canvas;
}

public static String createCircleImageDataUrl(int r,int g,int b,double opacity,double radius,double lineWidth,boolean stroke){
	double center=radius+lineWidth;
	Canvas canvas=CanvasUtils.createCanvas((int)center*2,(int)center*2);
	if(stroke){
	canvas.getContext2d().setStrokeStyle("rgba("+r+","+g+","+b+","+opacity+")");
	canvas.getContext2d().setLineWidth(lineWidth);
	}else{
	canvas.getContext2d().setFillStyle("rgba("+r+","+g+","+b+","+opacity+")");	
	}
	canvas.getContext2d().beginPath();
	
	canvas.getContext2d().arc(center, center, radius, 0, 360);
	canvas.getContext2d().closePath();
	if(stroke){
		canvas.getContext2d().stroke();
	}else{
		canvas.getContext2d().fill();
	}
	String image1=canvas.toDataUrl();
	return image1;
}
public static void drawImageByCordinate(Canvas target,CanvasElement canvas, int sx1, int sy1,
		int sx2, int sy2,int dx1, int dy1, int dx2, int dy2){

	target.getContext2d().drawImage(canvas, sx1, sy1, sx2-sx1, sy2-sy1, dx1, dy1, dx2-dx1, dy2-dy1);
}

public static Anchor generateDownloadImageLink(Canvas canvas,boolean isPng,String fileName,String anchorLabel,boolean onClickToRemove){
	String mime="image/png";
	if(!isPng){
		mime="image/jpeg";
	}
	return HTML5Download.get().generateBase64DownloadLink(canvas.toDataUrl(mime), mime, fileName, anchorLabel, onClickToRemove);
}

//simple draw center
public static void drawFitCenter(Canvas canvas,ImageElement img){
	drawFitImage(canvas,img,ALIGN_CENTER,VALIGN_MIDDLE);
}

public static void drawExpandCenter(Canvas canvas,ImageElement img){
	drawExpandImage(canvas,img,ALIGN_CENTER,VALIGN_MIDDLE);
}

/**
 * TODO use transform
 * @param canvas
 * @param img
 * @param align
 * @param valign
 */
public static void drawFitImage(Canvas canvas,ImageElement img,int align,int valign){
			int cw=canvas.getCoordinateSpaceWidth();
			int ch=canvas.getCoordinateSpaceHeight();
			
			double[] newImageSize=calcurateFitSize(canvas.getCoordinateSpaceWidth(),canvas.getCoordinateSpaceHeight(),img.getWidth(),img.getHeight());
			
			
			double dx=0;	//ALIGN_LEFT
			double dy=0;
			if(align==ALIGN_CENTER){
			dx=(cw-newImageSize[0])/2;
			}else if(align==ALIGN_RIGHT){
			dx=cw-newImageSize[0];
			}
			if(valign==VALIGN_MIDDLE){
			dy=(ch-newImageSize[1])/2;
			}else if(valign==VALIGN_BOTTOM){
			dy=ch-newImageSize[1];	
			}
			
			
			//log("draw:"+dx+","+dy);
			canvas.getContext2d().drawImage(img, dx, dy, newImageSize[0], newImageSize[1]);
		}

public static void drawExpandImage(Canvas canvas,ImageElement img,int align,int valign){
	int cw=canvas.getCoordinateSpaceWidth();
	int ch=canvas.getCoordinateSpaceHeight();
	
	double[] newImageSize=calcurateExpandSize(canvas.getCoordinateSpaceWidth(),canvas.getCoordinateSpaceHeight(),img.getWidth(),img.getHeight());
	
	
	double dx=0;	//ALIGN_LEFT
	double dy=0;
	if(align==ALIGN_CENTER){
	dx=(cw-newImageSize[0])/2;
	}else if(align==ALIGN_RIGHT){
	dx=cw-newImageSize[0];
	}
	if(valign==VALIGN_MIDDLE){
	dy=(ch-newImageSize[1])/2;
	}else if(valign==VALIGN_BOTTOM){
	dy=ch-newImageSize[1];	
	}
	
	
	//log("draw:"+dx+","+dy);
	canvas.getContext2d().drawImage(img, dx, dy, newImageSize[0], newImageSize[1]);
}

		public static double[] calcurateFitSize(int canvasWidth,int canvasHeight,int imageWidth,int imageHeight){	
			double rw=(double)canvasWidth/imageWidth;
			double rh=(double)canvasHeight/imageHeight;
			
			
			
			double[] result=new double[2];
			if(rw<rh){
				result[0]=canvasWidth;
				result[1]=rw*imageHeight;
			}else{
				result[0]=rh*imageWidth;
				result[1]=canvasHeight;
			}

			return result;
		}
		
		public static double[] calcurateExpandSize(int canvasWidth,int canvasHeight,int imageWidth,int imageHeight){	
			double rw=(double)canvasWidth/imageWidth;
			double rh=(double)canvasHeight/imageHeight;
			
			
			
			double[] result=new double[2];
			if(rw<rh){
				result[0]=rh*imageWidth;
				result[1]=canvasHeight;
				
			}else{
				result[0]=canvasWidth;
				result[1]=rw*imageHeight;
			}

			return result;
		}
		//TODO merge or change halign
		public static int ALIGN_CENTER=0;
		public static int ALIGN_LEFT=1;
		public static int ALIGN_RIGHT=2;
		
		public static int VALIGN_MIDDLE=0;
		public static int VALIGN_TOP=1;
		public static int VALIGN_BOTTOM=2;

	
	//simple draw center,no resize
public static void drawCenter(Canvas canvas,ImageElement img){
	int cw=canvas.getCoordinateSpaceWidth();
	int ch=canvas.getCoordinateSpaceHeight();
	int dx=(cw-img.getWidth())/2;
	int dy=(ch-img.getHeight())/2;
	//log("draw:"+dx+","+dy);
	canvas.getContext2d().drawImage(img, dx, dy, img.getWidth(), img.getHeight());
	}

public static ImageData getImageData(Canvas canvas,boolean copy) {
	if(copy){
	return canvas.getContext2d().getImageData(0, 0, canvas.getCoordinateSpaceWidth(), canvas.getCoordinateSpaceHeight());
	}else{
	return canvas.getContext2d().createImageData(canvas.getCoordinateSpaceWidth(), canvas.getCoordinateSpaceHeight());
	}
}

public static Canvas copyTo(ImageData imageData,Canvas canvas) {
	if(canvas==null){
		canvas=Canvas.createIfSupported();
	}
	canvas.setCoordinateSpaceWidth(imageData.getWidth());
	canvas.setCoordinateSpaceHeight(imageData.getHeight());
	canvas.getContext2d().putImageData(imageData, 0, 0);
	return canvas;
}

public static void drawImage(Canvas sharedCanvas,ImageElement element) {
	sharedCanvas.getContext2d().drawImage(element, 0,0);
}
public static void drawImage(Canvas sharedCanvas,ImageElement element,int x,int y) {
	sharedCanvas.getContext2d().drawImage(element, x,y);
}

public static void copyAlpha(ImageData paintedData, Uint8Array grayByte) {
	for(int y=0;y<paintedData.getHeight();y++){
		for(int x=0;x<paintedData.getWidth();x++){
			paintedData.setAlphaAt(grayByte.get(y*paintedData.getWidth()+x), x, y);
		}
	}
}

public static void drawImage(Canvas sharedCanvas, Canvas imageCanvas) {
	sharedCanvas.getContext2d().drawImage(imageCanvas.getCanvasElement(), 0,0);
}

public static void drawLine(Canvas canvas, double x1, double y1, double x2, double y2) {
	Context2d c2d=canvas.getContext2d();
	c2d.beginPath();
	c2d.moveTo(x1, y1);
	c2d.lineTo(x2,y2);
	c2d.closePath();
	c2d.stroke();
}

public static void setSize(Canvas canvas, int w, int h) {
	if(canvas!=null){
		canvas.setCoordinateSpaceWidth(w);
		canvas.setCoordinateSpaceHeight(h);
		canvas.setWidth(w+"px");
		canvas.setHeight(h+"px");
	}
}




}
