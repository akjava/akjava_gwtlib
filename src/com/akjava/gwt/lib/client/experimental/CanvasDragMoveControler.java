package com.akjava.gwt.lib.client.experimental;

import com.akjava.gwt.lib.client.LogUtils;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;

public  class CanvasDragMoveControler{
	
	
	public CanvasDragMoveControler(CanvasMoveListener moveListener) {
		super();
		this.moveListener = moveListener;
	}
	
	private boolean isShiftKeyDown;
	
	public boolean isShiftKeyDown() {
		return isShiftKeyDown;
	}

	public CanvasDragMoveControler(Canvas canvas,CanvasMoveListener moveListener) {
		this(moveListener);
		
		canvas.addMouseMoveHandler(new MouseMoveHandler() {
			@Override
			public void onMouseMove(MouseMoveEvent event) {
				if(isStarted()){
					isShiftKeyDown=event.isShiftKeyDown();
					move(event.getX(), event.getY());
				}
			}
		});
		
		canvas.addMouseUpHandler(new MouseUpHandler() {

			@Override
			public void onMouseUp(MouseUpEvent event) {
				isShiftKeyDown=event.isShiftKeyDown();
				end(event.getX(), event.getY());
			}
		});
		
		canvas.addMouseDownHandler(new MouseDownHandler() {
			
			@Override
			public void onMouseDown(MouseDownEvent event) {
				isShiftKeyDown=event.isShiftKeyDown();
				start(event.getX(), event.getY());
			}
		});
		
		canvas.addMouseOutHandler(new MouseOutHandler() {
			
			@Override
			public void onMouseOut(MouseOutEvent event) {
				isShiftKeyDown=event.isShiftKeyDown();
				end(event.getX(), event.getY());
			}
		});
	}
	
	private int startX;
	private boolean started;
	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public void start(int x,int y){
		startX=x;
		startY=y;
		started=true;
		if(moveListener!=null){
			moveListener.start(startX, startY);
		}
	}
	
	public void move(int x,int y){
		if(!started ){
			return;
		}
		int dx=x-startX;
		int dy=y-startY;
		
		
		
		
		if(moveListener!=null && (dx!=0 || dy!=0)){
			moveListener.dragged(startX, startY,x,y, dx, dy);
		}
		
		
		
		startX=x;
		startY=y;
	}
	public void end(int x,int y){
		move(x,y);
		started=false;
		moveListener.end(x,y);
	}
	public int getStartX() {
		return startX;
	}
	public void setStartX(int startX) {
		this.startX = startX;
	}
	public int getStartY() {
		return startY;
	}
	public void setStartY(int startY) {
		this.startY = startY;
	}
	public int getMovedX() {
		return movedX;
	}
	public void setMovedX(int movedX) {
		this.movedX = movedX;
	}
	public int getMovedY() {
		return movedY;
	}
	public void setMovedY(int movedY) {
		this.movedY = movedY;
	}
	private int startY;
	
	
	
	private int movedX;
	private int movedY;
	
	private CanvasMoveListener moveListener;
	
}