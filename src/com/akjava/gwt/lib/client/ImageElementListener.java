package com.akjava.gwt.lib.client;

import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.ErrorEvent;

public interface ImageElementListener {

	public void onLoad(ImageElement element);
	public void onError(String url,ErrorEvent event);

}
