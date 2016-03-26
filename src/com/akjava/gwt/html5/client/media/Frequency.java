package com.akjava.gwt.html5.client.media;

import com.google.gwt.core.client.JavaScriptObject;

public class Frequency extends JavaScriptObject{
protected Frequency(){}

public final  native int getValue()/*-{
return this.value;
}-*/;

public final  native void setValue(int  param)/*-{
this.value=param;
}-*/;
}