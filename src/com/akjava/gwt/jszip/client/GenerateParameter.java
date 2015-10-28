package com.akjava.gwt.jszip.client;

import com.google.gwt.core.client.JavaScriptObject;

public class GenerateParameter extends JavaScriptObject{
	public static final String COMPRESSION_STORE="STORE";
	public static final String COMPRESSION_DEFLATE="DEFLATE";
	public static final String TYPE_BASE64="base64";
	public static final String TYPE_STRING="string";
	public static final String TYPE_UNIT8ARRAY="uint8array";
	public static final String TYPE_ARRAYBUFFER ="blob";
	public static final String TYPE_BLOB ="arraybuffer";
	public static final String TYPE_NODEBUFFER ="nodebuffer";
	protected GenerateParameter(){}
	
	public final static native GenerateParameter newGenerateParameter()/*-{
	return {};
	}-*/;
	public final  native GenerateParameter type(String type)/*-{
	this.type=type;
	return this;
	}-*/;
	public final  native GenerateParameter compression(String compression)/*-{
	this.compression=compression;
	return this;
	}-*/;
}
