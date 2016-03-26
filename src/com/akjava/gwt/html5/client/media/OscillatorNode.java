package com.akjava.gwt.html5.client.media;

import com.google.gwt.core.client.JavaScriptObject;

public class OscillatorNode extends JavaScriptObject{
protected OscillatorNode(){}

public final  native String getType()/*-{
return this.type;
}-*/;

public final  native void setType(String  param)/*-{
this.type=param;
}-*/;
public final  native Frequency getFrequency()/*-{
return this.frequency;
}-*/;

public final  native void start(double start)/*-{
this.start(start);
}-*/;

public final  native void stop()/*-{
this.stop();
}-*/;



}