package com.akjava.gwt.lib.client.widget;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.TextBox;

public abstract class EnterKeySupportTextBox extends TextBox{

	public EnterKeySupportTextBox() {
		super();
		this.addKeyDownHandler(new KeyDownHandler() {

		    @Override
		    public void onKeyDown(KeyDownEvent event) {
		     if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
		               onEnterKeyDown();
		           }
		    }
		});
	}
	public abstract void onEnterKeyDown();

}
