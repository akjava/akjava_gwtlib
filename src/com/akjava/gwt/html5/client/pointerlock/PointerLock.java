package com.akjava.gwt.html5.client.pointerlock;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.RootPanel;

public class PointerLock {

	public final static  native boolean havePointerLock()/*-{
	return 'pointerLockElement' in $wnd.document || 'mozPointerLockElement' in $wnd.document || 'webkitPointerLockElement' in $wnd.document;
	}-*/;
	
	public final static  native boolean isPointerLockElement(Element element)/*-{
	//	console.log($wnd.document.pointerLockElement);
	return $wnd.document.pointerLockElement === element || $wnd.document.mozPointerLockElement === element || $wnd.document.webkitPointerLockElement === element;
	}-*/;
	
	public final static  native boolean isPointerLock()/*-{
	//	console.log($wnd.document.pointerLockElement);
	return $wnd.document.pointerLockElement  || $wnd.document.mozPointerLockElement || $wnd.document.webkitPointerLockElement ;
	}-*/;
	
	public final static  native boolean requestPointerLock(Element element)/*-{
	element.requestPointerLock();
	}-*/;
	
	public final static void addPointerLockChange(PointerLockListener listener){
		_addPointerLockChange(wrapPointerLockListener(listener));
	}
	
	private final static  native void _addPointerLockChange(JavaScriptObject jsfunction)/*-{
				$wnd.document.addEventListener( 'pointerlockchange', jsfunction, false );
				$wnd.document.addEventListener( 'mozpointerlockchange', jsfunction, false );
				$wnd.document.addEventListener( 'webkitpointerlockchange', jsfunction, false );
	}-*/;
	
	public static interface PointerLockListener {
	    void pointerLockChanged(NativeEvent event);
	}
	
	private native static JavaScriptObject wrapPointerLockListener(PointerLockListener callback) /*-{
    return function(event)
    {
        callback.@com.akjava.gwt.html5.client.pointerlock.PointerLock.PointerLockListener::pointerLockChanged(Lcom/google/gwt/dom/client/NativeEvent;)(event);
    };
}-*/;
	
	//TODO error
}
	