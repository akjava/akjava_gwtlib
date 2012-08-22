package com.akjava.gwt.lib.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class ImageElementLoader{
	public void load(String url,final ImageElementListener listener){
		
		final Image img=new Image(url);
		img.setVisible(true);
		img.addLoadHandler(new LoadHandler() {
			
			@Override
			public void onLoad(LoadEvent event) {
				RootPanel.get().remove(img);
				ImageElement element=ImageElement.as(img.getElement());
				listener.onLoad(element);
			}
		});
		RootPanel.get().add(img);		
	}
}