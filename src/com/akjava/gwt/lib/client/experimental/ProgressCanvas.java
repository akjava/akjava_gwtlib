package com.akjava.gwt.lib.client.experimental;

import com.akjava.gwt.lib.client.CanvasUtils;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public  class ProgressCanvas{
		private PopupPanel popup;
		private int currentStep;
		private int maxStep;
		private String bgColor="#fff";
		private String barColor="#f00";
		private static Canvas canvas=Canvas.createIfSupported();//share
		private int canvasWidth=200;
		private int canvasHeight=16;
		public ProgressCanvas(String title,int maxStep){
			this.maxStep=maxStep;
			popup = new PopupPanel(false,true);
			popup.setGlassEnabled(true);//need call here,TODO support as option
			VerticalPanel mainPanel = new VerticalPanel();//need on initial
			Label titleLabel=new Label(title);
			mainPanel.add(titleLabel);
			popup.add(mainPanel);
			
			canvas = CanvasUtils.createCanvas(canvas,canvasWidth, canvasHeight);
			mainPanel.add(canvas);
		}
		
		public void updateCanvas(){
			CanvasUtils.fillRect(canvas, bgColor);
			if(currentStep>=maxStep){
				CanvasUtils.fillRect(canvas, barColor);
			}else{
				canvas.getContext2d().setFillStyle(barColor);
				int dw=(int)((double)canvasWidth/maxStep*currentStep);
				
				canvas.getContext2d().fillRect(0, 0, dw, canvasHeight);
			}
		}
		
		public void show(){
			popup.center();
			popup.show();
			
			
		}
		public void hide(){
			popup.hide();
		}
		public void progress(int step){
			currentStep+=step;
			updateCanvas();
		}
		
	}