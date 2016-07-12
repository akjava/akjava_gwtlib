package com.akjava.gwt.lib.client;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Window;


public class LogUtils {
	public static final native void log(JavaScriptObject object)/*-{
if(typeof console === "undefined"){
    console = { log: function() { },error:function(){},warn:function(){} };
  }
	if(console){
		console.log(object);
	}
	}-*/;
	public static final  void log(Object value){
		if(value!=null){
		if(value instanceof JavaScriptObject){
			log((JavaScriptObject)value);
		}else{
		log(value.toString());
		}
		}else{
			log("null");
		}
	}
	public static final  void log(Number value){
		log(""+value);
	}
	public static final  void logImageDataUrl(String dataUrl){
		Window.open(dataUrl, ""+System.currentTimeMillis(), null);
	}
	
	public static final  Stopwatch stopwatch(){
		return Stopwatch.createStarted();
	}
	
	public static final  void millisecond(String head,Stopwatch watch){
		log(head+" "+watch.elapsed(TimeUnit.MILLISECONDS)+" ms");
	}
	public static final  void microsecond(String head,Stopwatch watch){
		log(head+" "+watch.elapsed(TimeUnit.MICROSECONDS)+" micro second");
	}
	
	public static final  void logAndAlert(String log){
		logAndAlert(log, log);
	}
	public static final  void logAndAlert(String log,String alert){
		log(log);
		Window.alert(alert);
	}
	public static final native void log(String object)/*-{
if(typeof console === "undefined"){
    console = { log: function() { },error:function(){},warn:function(){} };
  }
	if(console){
		console.log(object);
	}
	}-*/;
	
	public static final native void error(JavaScriptObject object)/*-{
if(typeof console === "undefined"){
    console = { log: function() { },error:function(){},warn:function(){} };
  }
if(console){
	console.error(object);
}
}-*/;
public static final  void error(Object value){
	if(value!=null){
	if(value instanceof JavaScriptObject){
		error((JavaScriptObject)value);
	}else{
	error(value.toString());
	}
	}else{
		error("null");
	}
}
public static final  void error(Number value){
	error(""+value);
}

public static final native void error(String object)/*-{
if(typeof console === "undefined"){
    console = { log: function() { },error:function(){},warn:function(){} };
  }
if(console){
	console.error(object);
}
}-*/;

public static final native void warn(JavaScriptObject object)/*-{
if(typeof console === "undefined"){
    console = { log: function() { },error:function(){},warn:function(){} };
  }
if(console){
console.warn(object);
}
}-*/;
public static final  void warn(Object value){
if(value!=null){
if(value instanceof JavaScriptObject){
	warn((JavaScriptObject)value);
}else{
warn(value.toString());
}
}else{
	warn("null");
}
}
public static final  void warn(Number value){
warn(""+value);
}

public static final native void warn(String object)/*-{
if(typeof console === "undefined"){
    console = { log: function() { },error:function(){},warn:function(){} };
  }
if(console){
console.warn(object);
}
}-*/;

}
