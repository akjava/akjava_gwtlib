package com.akjava.gwt.lib.client;

import com.akjava.gwt.lib.client.MultiImageElementLoader.MultiImageElementListener;
import com.google.common.base.Ascii;
import com.google.common.collect.Lists;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.typedarrays.shared.ArrayBuffer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Navigator;
import com.google.gwt.xhr.client.ReadyStateChangeHandler;
import com.google.gwt.xhr.client.XMLHttpRequest;
import com.google.gwt.xhr.client.XMLHttpRequest.ResponseType;

public class BrowserUtils {
	public static native String getUserAgent() /*-{
	return navigator.userAgent.toLowerCase();
	}-*/;
	
	public static boolean isChrome(){
		return getUserAgent().indexOf("chrome")!=-1;
	}
	public static boolean isFirefox(){
		return getUserAgent().indexOf("firefox")!=-1;
	}
	public final native static boolean isIOS()/*-{
	return navigator.userAgent.match(/(iPad|iPhone|iPod)/g) ? true : false ;
	}-*/;

	public static boolean isIE(){
		String agent=Window.Navigator.getUserAgent().toLowerCase();
		return agent.indexOf("msie")!=-1 || agent.indexOf("trident")!=-1;
	}

	public static boolean isIAndroid(){
		String agent=Navigator.getUserAgent();
		if(Ascii.toLowerCase(agent).indexOf("android")!=-1){
			return true;
		}else{
			return false;
		}
	}
	
	public interface LoadBinaryListener{
		public void onLoadBinaryFile(ArrayBuffer buffer);
		public void onFaild(int states,String statesText);
	}
	public interface LoadTextListener{
		public void onLoadTextFile(String text);
		public void onFaild(int states,String statesText);
	}
	
	public static void loadBinaryFile(String url,final LoadBinaryListener listener) {
		
		XMLHttpRequest request=XMLHttpRequest.create();
		request.setResponseType(ResponseType.ArrayBuffer);
		request.setOnReadyStateChange(new ReadyStateChangeHandler() {
			@Override
			public void onReadyStateChange(XMLHttpRequest xhr) {
				if(xhr.getResponseArrayBuffer()==null){//pre loading
					return;
				}
				if(xhr.getStatus()==200){
				ArrayBuffer arrayBufer=xhr.getResponseArrayBuffer();
				
				listener.onLoadBinaryFile(arrayBufer);
				}else{
					listener.onFaild(xhr.getStatus(),xhr.getStatusText());
				}
				
			}
		});
		request.open("GET",url);
		request.send();
	}
	
	public static void loadImageFile(String url,final ImageElementListener listener){
		new ImageElementLoader().load(url, listener);
	}
	
	public static void loadImageFiles(Iterable<String> urls,final MultiImageElementListener listener){
		new MultiImageElementLoader().loadImages(Lists.newArrayList(urls), listener);
	}
	
	public static void loadTextFile(String url,final LoadTextListener listener){
		   try {
				new RequestBuilder(RequestBuilder.GET, url).sendRequest(null, new RequestCallback() {
					
					@Override
					public void onResponseReceived(Request request, Response response) {
						listener.onLoadTextFile(response.getText());
					}
					
					@Override
					public void onError(Request request, Throwable exception) {
						listener.onFaild(0,exception.getMessage());
					}
				});
			} catch (RequestException e) {
				listener.onFaild(0, e.getMessage());
			}
	}
	
	
}
