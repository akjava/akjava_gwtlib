package com.akjava.gwt.lib.client;

import com.google.gwt.typedarrays.shared.ArrayBuffer;
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
	
	public interface LoadBinaryListener{
		public void onLoadBinaryFile(ArrayBuffer buffer);
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
}
