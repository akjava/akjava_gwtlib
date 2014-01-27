package com.akjava.gwt.lib.client;

import com.google.gwt.canvas.client.Canvas;
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

}
