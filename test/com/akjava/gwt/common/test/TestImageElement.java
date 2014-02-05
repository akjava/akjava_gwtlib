package com.akjava.gwt.common.test;

import com.akjava.gwt.lib.client.CanvasUtils;
import com.akjava.gwt.lib.client.ImageElementUtils;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.ImageData;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.ui.RootPanel;

public class TestImageElement extends GWTTestCase{

	private Canvas canvas;
	@Override
	public String getModuleName() {

		return "com.akjava.gwt.common.Common";
	}
	@Override
	public void gwtSetUp(){
		canvas=CanvasUtils.createCanvas(6, 6);
	}
	public void testImage(){
		
		RootPanel.get().add(canvas);
		
		canvas.getContext2d().setFillStyle("#fff");
		canvas.getContext2d().fillRect(2, 2, 2, 2);
		
		//ImageElementUtils.inPaintMargin(canvas,2);
		
		String correct="";
		for(int y=0;y<6;y++){
			for(int x=0;x<6;x++){
				correct+=toLabel(x,y,255,255,255,255)+"\n";
			}
		}
		
		ImageData data=canvas.getContext2d().getImageData(0, 0, 6, 6);
		String result="";
		for(int y=0;y<6;y++){
			for(int x=0;x<6;x++){
				correct+=toLabel(data,x,y)+"\n";
			}
		}
		
		assertEquals(correct, result);
	}
	
	public String toLabel(ImageData data,int x,int y){
		int r=data.getRedAt(x, y);
		int g=data.getGreenAt(x, y);
		int b=data.getBlueAt(x, y);
		int a=data.getAlphaAt(x, y);
		return toLabel(x,y,r,g,b,a);
	}
	
	public String toLabel(int x,int y,int r,int g,int b,int a){
		return x+"x"+y+"="+r+","+g+","+b+","+a;
	}

}
