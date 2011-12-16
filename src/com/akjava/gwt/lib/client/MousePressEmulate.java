package com.akjava.gwt.lib.client;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.Timer;

/**
 * add handlers
 * @author aki
 *
 */
public class MousePressEmulate implements MouseDownHandler,MouseUpHandler,MouseOutHandler{
Timer timer;
private int repeatTime;
private long start;
private MousePressingListener pressingListener;
private boolean sync=true;
private boolean doing;
	@Override
	public void onMouseUp(MouseUpEvent event) {
		timer.cancel();
	}

	@Override
	public void onMouseDown(MouseDownEvent event) {
		pressingListener.onMouseDown(event);
		start=System.currentTimeMillis();
		timer=new Timer(){

			@Override
			public void run() {
				if(System.currentTimeMillis()<start+repeatTime){
					return;
				}
				if(sync&&doing){
					return;
				}
				doing=true;
				pressingListener.onMousePressing();
				doing=false;
			}};
		timer.scheduleRepeating(repeatTime);
	}

	@Override
	public void onMouseOut(MouseOutEvent event) {
		timer.cancel();
	}
	
	public static interface MousePressingListener {
		public void onMouseDown(MouseDownEvent event);
		public void onMousePressing();
	}

	
}
