package com.akjava.gwt.inpaint.client;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import com.akjava.gwt.html5.client.file.Uint8Array;
import com.akjava.gwt.inpaint.client.InPaint;
import com.akjava.gwt.lib.client.CanvasUtils;
import com.akjava.gwt.lib.client.ImageElementUtils;
import com.akjava.gwt.lib.client.LogUtils;
import com.akjava.lib.common.utils.Benchmark;
import com.akjava.lib.common.utils.ColorUtils;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.ImageData;
import com.google.gwt.dom.client.ImageElement;

public class InpaintEngine {

	//do async by yourself
	
	private Canvas sharedCanvas;
	
	public InpaintEngine(){
		sharedCanvas=Canvas.createIfSupported();
	}
	
	public static interface InpaintListener{
		public void createMixedImage(String dataUrl);
		public void createInpaintImage(String dataUrl);//cutted mask
		public void createGreyScaleMaks(String dataUrl);
		public void createInpainteMaks(String dataUrl);
	}
	
	public void doInpaint(ImageElement element,int radius,List<MaskData> maskDatas,InpaintListener listener){
				checkArgument(element.getWidth()!=0 && element.getHeight()==0,"doInpaint:0 image element maybe need load");
		
				Benchmark.start("total");
				
				//resultPanel.add(new Image(element.getSrc()));
				//use edge case
				int margin=2;
				
				Benchmark.start("expand");
				
				
				//canvas and sharedCanvas is same
				ImageElementUtils.copytoCanvasWithMargin(element, sharedCanvas,true,margin,true);
				ImageData expandedImageData=CanvasUtils.getImageData(sharedCanvas,true);
				
				
				Benchmark.endAndLog("expand");
				Uint8Array array=null;
				
				//Uint8Array grayByte=null;
				
				
				//created by maskData
				Uint8Array merged=createMaskData(sharedCanvas,maskDatas);
				
				/*
				Uint8Array expanded=InPaint.expandMaskByte(merged,  imageData.getWidth(),data.getExpand());
				createAndInsertImage(expanded,resultPanel);
				
				grayByte=InPaint.expandMaskByteAsGray(expanded,  imageData.getWidth(),data.getFade());
				*/
				
				ImageData maskData=CanvasUtils.getImageData(sharedCanvas,false);
				InPaint.createImageDataFromMaskAsGray(maskData,merged);
				
				
				CanvasUtils.copyTo(maskData,sharedCanvas);
				String dataUrl=sharedCanvas.toDataUrl();
				listener.createGreyScaleMaks(dataUrl);
				
				
				
				
				
				
				Uint8Array newByte=InPaint.createMaskByColor(maskData, 0,0,0,false);//not 0 is mask
				
				//return 0 or 1
				
				array=newByte;
				
				
				
				
				Benchmark.start("inpaint");
				InPaint.inpaint(expandedImageData, array, radius);
				
				//somehow(maybe transparent problem) expanded to result should keep same size
				
				
				sharedCanvas.setCoordinateSpaceWidth(expandedImageData.getWidth()-4);
				sharedCanvas.setCoordinateSpaceHeight(expandedImageData.getHeight()-4);
				sharedCanvas.getContext2d().putImageData(expandedImageData,-2,-2);
				
				
				//CanvasUtils.copyTo(imageData,sharedCanvas);
				//CanvasUtils.copyTo(imageData,sharedCanvas);
				
				String inpaintDataUrl=sharedCanvas.toDataUrl();
				listener.createInpaintImage(inpaintDataUrl);
				
				
				
				//createAndInsertImage use sizes
				sharedCanvas.setCoordinateSpaceWidth(expandedImageData.getWidth());
				sharedCanvas.setCoordinateSpaceHeight(expandedImageData.getHeight());
				
				//for support mergin
				
				
				
				//create grayscale later
				
				Uint8Array drawByte=Uint8Array.createUint8(newByte.length());
				//better to do last ?
				for(int i=0;i<newByte.length();i++){
					drawByte.set(i, newByte.get(i)*255);
				}
				
				ImageData maskData2=CanvasUtils.getImageData(sharedCanvas,false);
				//LogUtils.log(maskByte.length()+","+maskData.getWidth()+"x"+maskData.getHeight());
				//InPaint.createImageDataFromMask(maskData,maskByte,0,0,0,255,true);//for black & white
				InPaint.createImageDataFromMaskAsGray(maskData2,drawByte);
				
				CanvasUtils.copyTo(maskData,sharedCanvas);
				listener.createInpainteMaks(sharedCanvas.toDataUrl());
				
				//createAndInsertImage(drawByte,inpaintMaskPanel);
					
				
				
				Benchmark.endAndLog("inpaint");
				
				Benchmark.start("mix");
				CanvasUtils.copyTo(expandedImageData,sharedCanvas);//sharedcanvas broken by last create-image
				ImageData paintedData=CanvasUtils.getImageData(sharedCanvas, true);
				CanvasUtils.drawImage(sharedCanvas,element,margin,margin);
				
				if(merged!=null){
				CanvasUtils.copyAlpha(paintedData,merged);
				Canvas paintedCanvas=CanvasUtils.createCanvas(null, paintedData);
				CanvasUtils.drawImage(sharedCanvas,paintedCanvas);
				}
				
				
				//cut off margin
				String lastImage=CanvasUtils.toDataUrl(sharedCanvas, sharedCanvas, margin, margin, sharedCanvas.getCoordinateSpaceWidth()-margin*2, sharedCanvas.getCoordinateSpaceHeight()-margin*2);
				
				listener.createMixedImage(lastImage);
				
			}
		


private Uint8Array createMaskData(final Canvas canvas,List<MaskData> maskDatas){
	//LogUtils.log(maskData.toString());
	
	//ImageElementUtils.copytoCanvas(element, sharedCanvas);
	
	ImageData imageData=CanvasUtils.getImageData(canvas,true);
	
	Uint8Array merged=null;
	
	for(MaskData maskData:maskDatas){
		Uint8Array bt;
		Uint8Array grayByte;
	if(maskData.isTransparent()){
		bt=InPaint.createMaskByAlpha(imageData);
	}else{
		int rgb[]=ColorUtils.toRGB(maskData.getColor());
		
		
		if(maskData.isSimilarColor()){
			bt=InPaint.createMaskBySimilarColor(imageData, rgb[0], rgb[1], rgb[2],maskData.getMaxLength());
		}else{
			bt=InPaint.createMaskByColor(imageData, rgb[0], rgb[1], rgb[2]);
		}
	}
	//LogUtils.log("bt:"+bt.length());
	Uint8Array expanded=InPaint.expandMaskByte(bt,  imageData.getWidth(),maskData.getExpand());
	
	//LogUtils.log("expanded:"+expanded.length());
	grayByte=InPaint.expandMaskByteAsGray(expanded,  imageData.getWidth(),maskData.getFade());
	//LogUtils.log("grayByte:"+grayByte.length());
	if(merged==null){
		merged=grayByte;
	}else{
		merge(merged,grayByte);
	}
	
	
	}
	//LogUtils.log("merged:"+merged.length());
	//createAndInsertImage(merged,resultPanel);
	return merged;
}

public void merge(Uint8Array array1,Uint8Array array2){
	if(array1.length()!=array2.length()){
		throw new IllegalArgumentException("not same length");
	}
	
	for(int i=0;i<array1.length();i++){
		int v1=array1.get(i);
		int v2=array2.get(i);
		if(v2>v1){
			array1.set(i, v2);//over write
		}
	}
	
}
}
