package com.akjava.gwt.jszip.client;

import com.akjava.gwt.html5.client.file.Uint8Array;
import com.google.gwt.core.client.JavaScriptObject;

public class JSFile extends JavaScriptObject{
	protected JSFile(){}
	
	public final native boolean isDir()/*-{
	return this.dir;
	}-*/;
	
	public final native String getName()/*-{
	return this.name;
	}-*/;
	
	/**
	 * after 2.5 date,dir,base64,binary is deprecated.date & dir access directly
	 * @return
	 */
	public final native JSFileOptions getOptions()/*-{
	return this.options;
	}-*/;
	
	public final native String asText()/*-{
	return this.asText();
	}-*/;
	
	public final native String asBinary()/*-{
	return this.asBinary();
	}-*/;
	
	public final native JavaScriptObject asArrayBuffer()/*-{
	return this.asArrayBuffer();
	}-*/;
	public final native Uint8Array asUint8Array()/*-{
	return this.asUint8Array();
	}-*/;
	public final native JavaScriptObject asNodeBuffer()/*-{
	return this.asNodeBuffer();
	}-*/;
	


	public final native JavaScriptObject getDate()/*-{
	return this.date;
	}-*/;
}
