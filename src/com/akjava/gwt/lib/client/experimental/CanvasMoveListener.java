package com.akjava.gwt.lib.client.experimental;
public  interface CanvasMoveListener{
	public void dragged(int startX,int startY,int endX,int endY,int vectorX,int vectorY);
	public void start(int sx,int sy);
	public void end(int sx,int sy);
}