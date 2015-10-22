package com.akjava.gwt.lib.client.experimental;

import com.akjava.gwt.lib.client.ImageElementListener;
import com.akjava.gwt.lib.client.ImageElementLoader;
import com.akjava.gwt.lib.client.LogUtils;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.ErrorEvent;

public abstract class LoggingImageElementLoader {
	public abstract void onLoad(final ImageElement imageElement);
	
	public void load(final Canvas canvas){
		load(canvas.toDataUrl());
	}
	public void load(final String url){
		new ImageElementLoader().load(url, new ImageElementListener() {
			
			@Override
			public void onLoad(ImageElement element) {
				LoggingImageElementLoader.this.onLoad(element);
			}
			
			@Override
			public void onError(String url, ErrorEvent event) {
				LogUtils.log("LoggingImageElementLoader:"+url);
				LogUtils.log(event.getNativeEvent());
			}
		});
	}
}
