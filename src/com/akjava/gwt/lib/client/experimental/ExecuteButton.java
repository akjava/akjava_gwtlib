package com.akjava.gwt.lib.client.experimental;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;

public abstract class ExecuteButton extends Button{
	public ExecuteButton(String label){
		this(label,true);
	}
	
	
	
	public ExecuteButton(String label,final boolean autoEnableButton){
		super(label);
		this.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
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
			
		};
		timer.schedule(10);
	}
	public void beforeExecute(){}
	public abstract void executeOnClick();
}