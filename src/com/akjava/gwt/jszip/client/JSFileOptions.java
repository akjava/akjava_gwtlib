package com.akjava.gwt.jszip.client;

import com.google.gwt.core.client.JavaScriptObject;

public class JSFileOptions extends JavaScriptObject{
	protected JSFileOptions(){}
	
	public final static native JSFileOptions newJSFileOptions()/*-{
	return {};
	}-*/;

	public final native JSFileOptions base64(boolean base64)/*-{
	this.base64=base64;
	return this;
	}-*/;
	
	public final native JSFileOptions binary(boolean binary)/*-{
	this.binary=binary;
	return this;
	}-*/;
	

	public final native JSFileOptions date(JavaScriptObject date)/*-{
	this.date=date;
	return this;
	}-*/;
	

	
	public final native JSFileOptions compression(String compression)/*-{
	return this.compression=compression;
	}-*/;
	
	public final native JSFileOptions optimizedBinaryString(String optimizedBinaryString)/*-{
	this.optimizedBinaryString=optimizedBinaryString;
	return this;
	}-*/;
	
	/**
	 * @deprecated on jszip2.4
	 */ 
	public final native boolean getBase64()/*-{
	return this.base64;
	}-*/;
	/**
	 * @deprecated on jszip2.4
	 */
	public final native boolean getBinary()/*-{
	return this.binary;
	}-*/;
	
	/**
	 * @deprecated on jszip2.4
	 */
	
	public final native boolean getDir()/*-{
	return this.dir;
	}-*/;
	/**
	 * @deprecated on jszip2.4
	 */
	public final native JavaScriptObject getDate()/*-{
	return this.date;
	}-*/;
	public final native String getCompression()/*-{
	return this.compression;
	}-*/;
	

}
