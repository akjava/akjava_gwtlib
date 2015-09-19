package com.akjava.gwt.lib.client;

import java.util.List;

import com.akjava.gwt.lib.client.experimental.AsyncMultiCaller;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.ErrorEvent;

/*
 * not test yet
 */
public class MultiImageElementLoader {

	public static interface MultiImageElementListener{
		public void onLoad(List<String> successPaths,List<ImageElement> imageElements);
		public void onError(List<String> errorPaths);
	}
	public void loadImages(final MultiImageElementListener listener,String... nameValues){
		final List<String> names=Lists.newArrayList();
		for(String name:nameValues){
			names.add(name);
		}
		loadImages(names,listener);
	}
	public void loadImages(List<String> fileNames,final MultiImageElementListener listener){
		Preconditions.checkNotNull(listener);
		
		
		final List<ImageElement> loaded=Lists.newArrayList();
		final List<String> loadedPaths=Lists.newArrayList();
		final List<String> faildPaths=Lists.newArrayList();
		
		if(fileNames==null || fileNames.isEmpty()){
			listener.onLoad(loadedPaths,loaded);
			return;
		}
		
		AsyncMultiCaller<String> srcCaller=new AsyncMultiCaller<String>(fileNames) {
			
			@Override
			public void execAsync(final String data) {
				new ImageElementLoader().load(data, new ImageElementListener() {
					
					@Override
					public void onLoad(ImageElement element) {
						loaded.add(element);
						loadedPaths.add(data);
						done(data,true);
					}
					
					@Override
					public void onError(String url, ErrorEvent event) {
						faildPaths.add(data);
						done(data,true);//not stop on faild
					}
				});
				
			}
			
			@Override
			public void doFinally(boolean cancelled) {
				if(!faildPaths.isEmpty()){
					listener.onError(faildPaths);
				}
				
				listener.onLoad(loadedPaths,loaded);
			}
		};
		
		srcCaller.startCall(10);
	}
}
