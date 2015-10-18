package com.akjava.gwt.lib.client.experimental;

import static com.google.common.base.Preconditions.checkNotNull;

import com.akjava.gwt.lib.client.CanvasUtils;
import com.akjava.gwt.lib.client.LogUtils;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.dom.client.NativeEvent;
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
	
	//private boolean isShiftKeyDown;
	private KeyDownState keyDownState=new KeyDownState();
	public KeyDownState getKeyDownState() {
		return keyDownState;
	}

	public void setKeyDownState(KeyDownState keyDownState) {
		this.keyDownState = keyDownState;
	}

	private boolean rightMouse;
	
	public static class KeyDownState{
		private boolean shiftKeyDown;
		private boolean  altKeyDown;
		private boolean ctrlKeyDown;
		public boolean isShiftKeyDown() {
			return shiftKeyDown;
		}
		public void setShiftKeyDown(boolean shiftKeyDown) {
			this.shiftKeyDown = shiftKeyDown;
		}
		public boolean isAltKeyDown() {
			return altKeyDown;
		}
		public void setAltKeyDown(boolean altKeyDown) {
			this.altKeyDown = altKeyDown;
		}
		public boolean isControlKeyDown() {
			return ctrlKeyDown;
		}
		public void setControlKeyDown(boolean ctrlKeyDown) {
			this.ctrlKeyDown = ctrlKeyDown;
		}
		}
	
	public boolean isRightMouse() {
		return rightMouse;
	}

	public void setRightMouse(boolean rightMouse) {
		this.rightMouse = rightMouse;
	}

	/**
	 * @deprecated use getKeyDownState
	 * @return
	 */
	public boolean isShiftKeyDown() {
		return keyDownState.isShiftKeyDown();
	}

	private Canvas canvas;
	public CanvasDragMoveControler(Canvas canvas,CanvasMoveListener moveListener) {
		this(moveListener);
		this.canvas=checkNotNull(canvas,"CanvasDragMoveControler:need canvas");
		
		canvas.addMouseMoveHandler(new MouseMoveHandler() {
			@Override
			public void onMouseMove(MouseMoveEvent event) {
				if(isTouchDowning()){
					keyDownState.setAltKeyDown(event.isAltKeyDown());
					keyDownState.setShiftKeyDown(event.isShiftKeyDown());
					keyDownState.setControlKeyDown(event.isControlKeyDown());
					
					if(scaleX==1 && scaleY==1){
						move(event.getX(), event.getY());
					}else{
						int x=mouseToCanvasX(event.getX());
						int y=mouseToCanvasY(event.getY());
						move(x,y);
					}
				}
			}
		});
		
		canvas.addMouseUpHandler(new MouseUpHandler() {

			@Override
			public void onMouseUp(MouseUpEvent event) {
				if(event.getNativeButton()==NativeEvent.BUTTON_RIGHT){
					rightMouse=true;
				}else{
					rightMouse=false;
				}
				keyDownState.setAltKeyDown(event.isAltKeyDown());
				keyDownState.setShiftKeyDown(event.isShiftKeyDown());
				keyDownState.setControlKeyDown(event.isControlKeyDown());
				
				
				if(scaleX==1 && scaleY==1){
					end(event.getX(), event.getY());
				}else{
					int x=mouseToCanvasX(event.getX());
					int y=mouseToCanvasY(event.getY());
					end(x,y);
				}
				
			}
		});
		
		canvas.addMouseDownHandler(new MouseDownHandler() {
			
			@Override
			public void onMouseDown(MouseDownEvent event) {
				if(event.getNativeButton()==NativeEvent.BUTTON_RIGHT){
					rightMouse=true;
				}else{
					rightMouse=false;
				}
				keyDownState.setAltKeyDown(event.isAltKeyDown());
				keyDownState.setShiftKeyDown(event.isShiftKeyDown());
				keyDownState.setControlKeyDown(event.isControlKeyDown());
				
				if(scaleX==1 && scaleY==1){
					start(event.getX(), event.getY());
				}else{
					int x=mouseToCanvasX(event.getX());
					int y=mouseToCanvasY(event.getY());
					start(x,y);
				}
			}
		});
		
		canvas.addMouseOutHandler(new MouseOutHandler() {
			
			@Override
			public void onMouseOut(MouseOutEvent event) {
				keyDownState.setAltKeyDown(event.isAltKeyDown());
				keyDownState.setShiftKeyDown(event.isShiftKeyDown());
				keyDownState.setControlKeyDown(event.isControlKeyDown());
				if(event.getNativeButton()==NativeEvent.BUTTON_RIGHT){
					rightMouse=true;
				}else{
					rightMouse=false;
				}
				if(scaleX==1 && scaleY==1){
					end(event.getX(), event.getY());
				}else{
					int x=mouseToCanvasX(event.getX());
					int y=mouseToCanvasY(event.getY());
					end(x,y);
				}
			}
		});
	}
	
	//FUTURE
	private int mouseToCanvasX(int mouseX){
		return (int)((mouseX-scrollX)/scaleX);
	}
	private int mouseToCanvasY(int mouseY){
		return (int)((mouseY-scrollY)/scaleY);
	}
	
	private int scrollX;
	public int getScrollX() {
		return scrollX;
	}

	public void setScrollX(int scrollX) {
		this.scrollX = scrollX;
		LogUtils.log("setScrollX:"+scrollX+"x"+scrollY);
		
	}

	private int scrollY;//TODO
	
	private double scaleX=1;
	public double getScaleX() {
		return scaleX;
	}

	public double getScaleY() {
		return scaleY;
	}

	private double scaleY=1;
	
	
	public void setScale(double scale){
		scaleX=scale;
		scaleY=scale;
		CanvasUtils.scaleViewerSize(canvas, scale);
	}
	
	private int startX;
	private boolean started;
	public boolean isTouchDowning() {
		return started;
	}

	
	public void start(int x,int y){
		startX=x;
		startY=y;
		clickStartX=x;
		clickStartY=y;
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
		
		
		movedX=x;
		movedY=y;
		
		if(moveListener!=null && (dx!=0 || dy!=0)){
			moveListener.dragged(startX, startY,x,y, dx, dy);
		}
		
		
		
		
		startX=x;
		startY=y;
	}
	
	//mouse down click start.never change until next click
	private int clickStartX;
	
	public int getClickStartX() {
		return clickStartX;
	}

	public int getClickStartY() {
		return clickStartY;
	}

	private int clickStartY;
	
	public void end(int x,int y){
		move(x,y);
		started=false;
		moveListener.end(x,y);
	}
	
	
	//drag start,replace moved.
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
	//drag end,every drag event
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