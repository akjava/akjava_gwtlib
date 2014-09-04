package com.akjava.gwt.lib.client.experimental;

import static com.google.common.base.Preconditions.checkNotNull;

import com.akjava.gwt.html5.client.file.Blob;
import com.akjava.gwt.lib.client.CanvasUtils;
import com.akjava.gwt.lib.client.ImageElementUtils;
import com.akjava.lib.common.io.FileType;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.ImageData;
import com.google.gwt.dom.client.ImageElement;

public class ImageBuilder {
public static Canvas sharedCanvas;
	public static Canvas getSharedCanvas() {
	if(sharedCanvas==null){
		sharedCanvas=Canvas.createIfSupported();
	}
	return sharedCanvas;
}
	
	
public static class WebPBuilder extends ImageBuilder{
	public static WebPBuilder from(String dataUrl){
		checkNotNull(dataUrl);
		return new WebPBuilder(dataUrl);
	}
	
	public static WebPBuilder from(Canvas canvas){
		checkNotNull(canvas);
		return new WebPBuilder(canvas);
	}
	
	/* need wait to load use ImageElement
	public static WebPBuilder from(Blob blob){
		checkNotNull(blob);
		return new WebPBuilder(blob);
	}
	*/
	
	public static WebPBuilder from(ImageElement imageElement){
		checkNotNull(imageElement);
		return new WebPBuilder(imageElement);
	}
	
	private WebPBuilder(String dataUrl){
		super(dataUrl);
		super.onWeebp();
	}
	
	private WebPBuilder(Canvas canvas){
		super(canvas);
		super.onWeebp();
	}
	/*
	private WebPBuilder(Blob blob){
		super(blob);
		super.onWeebp();
	}
	*/
	
	private WebPBuilder(ImageElement imageElement){
		super(imageElement);
		super.onWeebp();
	}
	
	
	@Override
	public ImageBuilder onWeebp(){
		throw new UnsupportedOperationException();
	}
	
	@Override
	public ImageBuilder onPng(){
		throw new UnsupportedOperationException();
	}
	@Override
	public ImageBuilder onJpeg(){
		throw new UnsupportedOperationException();
	}
	
}
	
public static void setSharedCanvas(Canvas sharedCanvas) {
	ImageBuilder.sharedCanvas = sharedCanvas;
}

private Canvas canvas;
private String dataUrl;
//private Blob blob;
private ImageElement imageElement;
protected FileType fileType=FileType.PNG;
	public static ImageBuilder from(String dataUrl){
		checkNotNull(dataUrl);
		return new ImageBuilder(dataUrl);
	}
	

	/*
	public static ImageBuilder from(Blob blob){
		checkNotNull(blob);
		return new ImageBuilder(blob);
	}
	*/
	
	public static ImageBuilder from(Canvas canvas){
		checkNotNull(canvas);
		return new ImageBuilder(canvas);
	}
	
	/*
	public static ImageBuilder from(ImageData imageData){
		checkNotNull(imageData);
		//need own canvas
		return new ImageBuilder(CanvasUtils.createCanvas(getSharedCanvas(), imageData));
	}
	*/
	
	public static ImageBuilder from(ImageElement imageElement){
		checkNotNull(imageElement);
		return new ImageBuilder(imageElement);
	}
	
	private ImageBuilder(String dataUrl){
		this.dataUrl=dataUrl;
	}
	
	private ImageBuilder(Canvas canvas){
		this.canvas=canvas;
	}
	/*
	private ImageBuilder(Blob blob){
		this.blob=blob;
	}
	*/
	
	private ImageBuilder(ImageElement imageElement){
		this.imageElement=imageElement;
	}
	

	
	public ImageBuilder onWeebp(){
		this.fileType=FileType.WEBP;
		return this;
	}
	public ImageBuilder onPng(){
		this.fileType=FileType.PNG;
		return this;
	}
	public ImageBuilder onJpeg(){
		this.fileType=FileType.JPEG;
		return this;
	}
	public ImageBuilder on(FileType fileType){
		this.fileType=fileType;
		return this;
	}
	
	//for write FileSystem API
	public Blob toBlob(){
		String dataUrl=toDataUrl();
		return Blob.createBase64Blob(dataUrl, fileType.getMimeType());
	}
	
	public String toDataUrl(){
		//need resize?
		Canvas shareCanvas=getSharedCanvas();
		if(shareCanvas==null){
			shareCanvas=Canvas.createIfSupported();
			setSharedCanvas(shareCanvas);
		}
		
		if(dataUrl!=null){
			ImageElementUtils.copytoCanvas(dataUrl, shareCanvas);
			return shareCanvas.toDataUrl(fileType.getMimeType());
		}
		
		/*
		if(blob!=null){
			ImageElementUtils.copytoCanvas(blob, shareCanvas);
			return shareCanvas.toDataUrl(fileType.getMimeType());
		}
		*/
		
		if(imageElement!=null){
			ImageElementUtils.copytoCanvas(imageElement, shareCanvas);
			return shareCanvas.toDataUrl(fileType.getMimeType());
		}
		
		if(canvas!=null){
			CanvasUtils.copyTo(canvas, shareCanvas,true);
			return shareCanvas.toDataUrl(fileType.getMimeType());
		}
		
		return null;//other type
	}
}
