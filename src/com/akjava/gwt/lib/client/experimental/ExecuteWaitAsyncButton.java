package com.akjava.gwt.lib.client.experimental;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;

public abstract class ExecuteWaitAsyncButton extends Button{
	public ExecuteWaitAsyncButton(String label){
		this(label,true);
	}
	private int schedule=10;
	private boolean readyExecute;
	public int getSchedule() {
		return schedule;
	}
	public void setSchedule(int schedule) {
		this.schedule = schedule;
	}
	public boolean isReadyExecute() {
		return readyExecute;
	}
	public void setReadyExecute(boolean readyExecute) {
		this.readyExecute = readyExecute;
	}
	public ExecuteWaitAsyncButton(String label,final boolean autoEnableButton){
		super(label);
		this.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				readyExecute=false;
				startExecute(autoEnableButton);
			}
		});
	}
	public void startExecute(final boolean autoEnableButton){
		if(!isEnabled()){
			return;
		}
		setEnabled(false);
		beforeExecute();
		Timer timer=new Timer(){
			@Override
			public void run() {
				if(readyExecute){
					cancel();
				try{
					executeOnClick();
				}catch(Exception e){
					new RuntimeException(e);//i dont care
				}finally{
					if(autoEnableButton){
					setEnabled(true);
					}
				}
				}
			}
			
		};
		timer.scheduleRepeating(schedule);
	}
	public void beforeExecute(){}
	public abstract void executeOnClick();
}