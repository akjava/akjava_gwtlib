package com.akjava.gwt.lib.client;

import com.google.gwt.core.client.JsArray;

public class JavaScriptUtils {
	//much compatible
	public static native final JsArray createJSArray()/*-{
	return $wnd.eval("new Array()");
	}-*/;
}
