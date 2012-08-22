package com.akjava.gwt.lib.client;

public class FrameUtils {

public static final native int getLastFrameIndex()/*-{
	return $wnd.frames.length-1;
}-*/;

public static final native void writeToFrame(int index,String text)/*-{
var doc = $wnd.frames[index].document;
console.log(doc);
doc.open();
doc.write(text);
doc.close();
}-*/;


}
