package com.akjava.gwt.lib.client.experimental;

import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.UIObject;

public class CursorUtils {
public static final String NONE="none";//somehow not exist on Cursor;
	public static void setRootCursor(Cursor cursor){
		 DOM.setStyleAttribute(RootPanel.getBodyElement(), "cursor", cursor.getCssName());
	}
	
	/**
	 * not work? or style problem
	 * @param object
	 * @param cursor
	 */
	public static void setCursor(UIObject object,Cursor cursor){
		object.getElement().getStyle().setCursor(cursor);
	}
	
	public static void setCursor(UIObject object,String cursor){
		object.getElement().getStyle().setProperty("cursor",cursor);
	}
	
	public static void clearCursor(UIObject object){
		object.getElement().getStyle().clearCursor();
	}
}
