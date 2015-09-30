package com.akjava.gwt.lib.client.experimental;

import javax.annotation.Nullable;

import com.akjava.gwt.lib.client.CanvasUtils;
import com.akjava.gwt.lib.client.LogUtils;
import com.akjava.gwt.lib.client.experimental.CanvasDragMoveControler;
import com.akjava.gwt.lib.client.experimental.CanvasMoveListener;
import com.akjava.gwt.lib.client.experimental.CursorUtils;
import com.akjava.lib.common.graphics.IntRect;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;

/**
 * TODO become editor
 * @author aki
 *
 */
public class AreaSelectionControler {
	private CanvasDragMoveControler canvasControler;
	private IntRect selectionRect=new IntRect();
	public IntRect getSelectionRect() {
		return selectionRect;
	}
	


	private Canvas canvas;
	
	public Canvas getCanvas() {
		return canvas;
	}

	private int boxSize=20;
	
	public AreaSelectionControler(){
		this(null);
	}
	
	public boolean canContinueStart(int x,int y){
		return true;
	}
	public AreaSelectionControler(@Nullable Canvas canvas){
		if(canvas==null){
			canvas=Canvas.createIfSupported();
		}
		this.canvas=canvas;
		CanvasUtils.disableSelection(this.canvas);
		
		
canvasControler = new CanvasDragMoveControler(canvas,new CanvasMoveListener() {
			
			public static final int MOVE=0;
			public static final int REIZE_RIGHT_BOTTOM=1;
			public static final int REIZE_LEFT_BOTTOM=2;
			public static final int REIZE_RIGHT_TOP=3;
			public static final int REIZE_LEFT_TOP=4;
			public static final int RESIZE_TOP=5;
			public static final int RESIZE_BOTTOM=6;
			public static final int RESIZE_RIGHT=7;
			public static final int RESIZE_LEFT=8;
			
		
			
			int initialStartX;
			int initialStartY;
			int mode;
			IntRect lastRect;
			
		
			/**
			 * for overwrite action
			 * @param x
			 * @param y
			 * @return
			 */

			boolean ignoredOnStart;
			
			@Override
			public void start(int sx, int sy) {
				
				if(!canContinueStart(sx, sy)){
					ignoredOnStart=true;
					return;
				}else{
					ignoredOnStart=false;
				}
				
				
				lastRect=selectionRect.copy();
				
				if(selectionRect.hasWidthAndHeight() && selectionRect.contains(sx, sy)){
					if(selectionRect.expand(-boxSize*2, -boxSize*2).contains(sx, sy)){
						mode=MOVE;
						
						
					
					}else{
						int x=sx;
						int y=sy;
						if(selectionRect.rightTop(boxSize).contains(x, y)){
							mode=REIZE_RIGHT_TOP;	
							initialStartX=sx-selectionRect.getWidth();
							initialStartY=selectionRect.getBottomPos();
						}else if(selectionRect.rightBottom(boxSize).contains(x, y)){
							mode=REIZE_RIGHT_BOTTOM;
							initialStartX=sx-selectionRect.getWidth();
							initialStartY=selectionRect.getY();
						}else if(selectionRect.leftTop(boxSize).contains(x, y)){
							mode=REIZE_LEFT_TOP;
							
							initialStartX=sx+selectionRect.getWidth();
							initialStartY=sy+selectionRect.getHeight();
						}else if(selectionRect.leftBottom(boxSize).contains(x, y)){
							mode=REIZE_LEFT_BOTTOM;
							
							initialStartX=sx+selectionRect.getWidth();
							initialStartY=sy-selectionRect.getHeight();
						}else if(selectionRect.right(boxSize).contains(x, y)){
							mode=RESIZE_RIGHT;
							initialStartX=sx-selectionRect.getWidth();
						}else if(selectionRect.left(boxSize).contains(x, y)){
							mode=RESIZE_LEFT;
							initialStartX=sx+selectionRect.getWidth();
						}else if(selectionRect.top(boxSize).contains(x, y)){
							mode=RESIZE_TOP;
							initialStartY=sy+selectionRect.getHeight();
						}else if(selectionRect.bottom(boxSize).contains(x, y)){
							mode=RESIZE_BOTTOM;
							initialStartY=sy-selectionRect.getHeight();
						}else{
							setCursor("pointer");
						}
						
						
						
					
					}
					
					
				}else{
					mode=REIZE_RIGHT_BOTTOM;
					selectionRect.setX(sx);
					selectionRect.setY(sy);
					selectionRect.setWidth(0);
					selectionRect.setHeight(0);
					
					initialStartX=sx;
					initialStartY=sy;
				}
				//i have no idea why none need?
				//CursorUtils.setCursor(canvas, CursorUtils.NONE);
			}
			
			@Override
			public void end(int ex, int ey) {
				if(ignoredOnStart){
					return;
				}
				
				if(canvasControler.getClickStartX()==ex && canvasControler.getClickStartY()==ey){//no move restore
					LogUtils.log("no count");
					selectionRect=lastRect;
					updateRect();
				}
			}
			
			@Override
			public void dragged(int startX, int startY, int endX, int endY, int vectorX, int vectorY) {
				if(ignoredOnStart){
					return;
				}
				
				if(mode==MOVE){
					selectionRect.setX(selectionRect.getX()+vectorX);
					selectionRect.setY(selectionRect.getY()+vectorY);
					
					//only move limit
					if(selectionRect.getRightPos()>spaceWidth){
						selectionRect.setX(spaceWidth-selectionRect.getWidth());
					}
					
					if(selectionRect.getBottomPos()>spaceHeight){
						selectionRect.setY(spaceHeight-selectionRect.getHeight());
					}
				}else if(mode==REIZE_RIGHT_BOTTOM){
					//REIZE_RIGHT_BOTTOM
					
					
					if(endX>initialStartX){
						int w=selectionRect.getWidth()+vectorX;
						selectionRect.setWidth(w);
					}else{
						selectionRect.setX(endX);
						selectionRect.setWidth(initialStartX-endX);
					}
					
					if(endY>initialStartY){
						int h=selectionRect.getHeight()+vectorY;
						selectionRect.setHeight(h);
					}else{
						selectionRect.setY(endY);
						selectionRect.setHeight(initialStartY-endY);
					}
					
					
					
					
				
				
					
					
					
				}else if(mode==REIZE_RIGHT_TOP){
					//horizontal
					if(endX>initialStartX){
						int w=selectionRect.getWidth()+vectorX;
						selectionRect.setWidth(w);
					}else{
						selectionRect.setX(endX);
						selectionRect.setWidth(initialStartX-endX);
					}
					
					
					
					
					//vertical
					if(endY>initialStartY){
						selectionRect.setY(lastRect.getBottomPos());
						selectionRect.setHeight(endY-selectionRect.getY());
					}else{
						selectionRect.setY(selectionRect.getY()+vectorY);
						selectionRect.setHeight(lastRect.getBottomPos()-selectionRect.getY());
						
						if(selectionRect.getY()>lastRect.getBottomPos()){
							int y=selectionRect.getY();
							selectionRect.setY(lastRect.getBottomPos());
							selectionRect.setHeight(y-selectionRect.getY());
						}
					}
					
				}else if(mode==REIZE_LEFT_TOP){
					
					//horizontal
					if(endX>initialStartX){//flipped case
						selectionRect.setX(lastRect.getRightPos());
						selectionRect.setWidth(endX-initialStartX);
					}else{
						selectionRect.expandLeft(-vectorX);
					}
					
					if(endY>initialStartY){//flipped case
						selectionRect.setY(lastRect.getBottomPos());
						selectionRect.setHeight(endY-initialStartY);
					}else{
						selectionRect.expandTop(-vectorY);
					}
				}else if(mode==REIZE_LEFT_BOTTOM){
					
					//horizontal
					if(endX>initialStartX){//flipped case
						selectionRect.setX(lastRect.getRightPos());
						selectionRect.setWidth(endX-initialStartX);
					}else{
						selectionRect.expandLeft(-vectorX);
					}
					
					//vertical
					if(endY<initialStartY){//flipped case
						selectionRect.setHeight(initialStartY-endY);
						selectionRect.setY(lastRect.getY()-selectionRect.getHeight());
						
					}else{
						selectionRect.expandBottom(vectorY);
					}
				}else if(mode==RESIZE_RIGHT){
					//horizontal
					if(endX<initialStartX){
						selectionRect.setX(endX);
						selectionRect.setWidth(initialStartX-endX);
					}else{
						selectionRect.expandRight(vectorX);
					}
				}else if(mode==RESIZE_LEFT){
					if(endX>initialStartX){//flipped case
						selectionRect.setX(lastRect.getRightPos());
						selectionRect.setWidth(endX-initialStartX);
					}else{
						selectionRect.expandLeft(-vectorX);
					}
				}else if(mode==RESIZE_TOP){
					if(endY>initialStartY){//flipped case
						selectionRect.setY(lastRect.getBottomPos());
						selectionRect.setHeight(endY-initialStartY);
					}else{
						selectionRect.expandTop(-vectorY);
					}
				}else if(mode==RESIZE_BOTTOM){
					if(endY<initialStartY){//flipped case
						selectionRect.setHeight(initialStartY-endY);
						selectionRect.setY(lastRect.getY()-selectionRect.getHeight());
						
					}else{
						selectionRect.expandBottom(vectorY);
					}
				}
				
				//limit-rect
				if(selectionRect.getX()<0){
					selectionRect.setX(0);
				}
				if(selectionRect.getY()<0){
					selectionRect.setY(0);
				}
				
				if(selectionRect.getRightPos()>spaceWidth){
					selectionRect.setWidth(spaceWidth-selectionRect.getX());
				}
				
				if(selectionRect.getBottomPos()>spaceHeight){
					selectionRect.setHeight(spaceHeight-selectionRect.getY());
				}
				
				if(selectionRect.hasWidthAndHeight()){//for stop flicking
				updateRect();
				}
			}
		});
		

	canvas.addMouseMoveHandler(new MouseMoveHandler() {
	
	@Override
	public void onMouseMove(MouseMoveEvent event) {
		int x=event.getX();
		int y=event.getY();
		
		if(canvasControler.isStarted()){
			//LogUtils.log("dragged");
			return;
		}
		
		if(selectionRect.contains(x, y)){
			if(selectionRect.rightTop(boxSize).contains(x, y)){
				setCursor("ne-resize");
				//LogUtils.log("ne-resize");
			}else if(selectionRect.rightBottom(boxSize).contains(x, y)){
				setCursor("se-resize");
			}else if(selectionRect.leftTop(boxSize).contains(x, y)){
				setCursor("nw-resize");
			}else if(selectionRect.leftBottom(boxSize).contains(x, y)){
				setCursor("sw-resize");
			}else if(selectionRect.right(boxSize).contains(x, y)){
				setCursor("e-resize");
			}else if(selectionRect.left(boxSize).contains(x, y)){
				setCursor("w-resize");
			}else if(selectionRect.top(boxSize).contains(x, y)){
				setCursor("n-resize");
			}else if(selectionRect.bottom(boxSize).contains(x, y)){
				setCursor("s-resize");
			}else{
				setCursor("pointer");
			}
		}else{
			setCursor("default");	
		}
		
	}
});
	}
	
	private boolean noNeedChangeCursor;
	public boolean isNoNeedChangeCursor() {
		return noNeedChangeCursor;
	}

	public void setNoNeedChangeCursor(boolean noNeedChangeCursor) {
		this.noNeedChangeCursor = noNeedChangeCursor;
	}

	private void setCursor(String cursor){
		if(noNeedChangeCursor){
			return;
		}
		CursorUtils.setCursor(AreaSelectionControler.this.canvas, cursor);	
	}
	
	public void updateRect() {
		
		CanvasUtils.clear(canvas);
		if(selectionRect.hasWidthAndHeight()){
			
			CanvasUtils.fillRect(canvas, "rgba(0,0,0,0.5)");
			canvas.getContext2d().clearRect(selectionRect.getX(), selectionRect.getY(), selectionRect.getWidth(), selectionRect.getHeight());
			//canvas.getContext2d().fillRect(selectionRect.getX(), selectionRect.getY(), selectionRect.getWidth(), selectionRect.getHeight());
		}
		drawExtra(canvas);
	}
	
	/** for extended*/
	public void drawExtra(Canvas canvas){
		
	}
	
	public void setSpace(int w,int h){
		this.spaceWidth=w;
		this.spaceHeight=h;
	}
	
	private int spaceWidth;
	public int getSpaceWidth() {
		return spaceWidth;
	}

	public void setSpaceWidth(int spaceWidth) {
		this.spaceWidth = spaceWidth;
	}

	public int getSpaceHeight() {
		return spaceHeight;
	}

	public void setSpaceHeight(int spaceHeight) {
		this.spaceHeight = spaceHeight;
	}

	private int spaceHeight;
}
