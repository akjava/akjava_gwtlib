package com.akjava.gwt.lib.client.entries;

import com.akjava.gwt.lib.client.StorageControler;
import com.akjava.gwt.lib.client.StorageException;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * for something storage reset.
 * @author aki
 *
 */
public class ResetStorageAndRedirect implements EntryPoint {

	public static final String PROPERTY_REDIRECT_URL_ID="ResetStorageAndRedirect_url";
	public static final String PROPERTY_REDIRECT_KEY_ID="ResetStorageAndRedirect_key";
	public static final String PROPERTY_REDIRECT_VALUE_ID="ResetStorageAndRedirect_value";
	@Override
	public void onModuleLoad() {
		String key=null;
		RootPanel keyTarget=RootPanel.get(PROPERTY_REDIRECT_KEY_ID);
		if(keyTarget==null){//maybe don't use ignore it
			return;
		}
		key=keyTarget.getElement().getAttribute("value");
		if(key==null){
			return;
		}
		
		
		String value=null;
		RootPanel valueTarget=RootPanel.get(PROPERTY_REDIRECT_VALUE_ID);
		if(valueTarget==null){
			value="";
		}else{
			value=valueTarget.getElement().getAttribute("value");
		}
		if(value==null){
			value="";
		}
		
		try {
			new StorageControler().setValue(key, value);
		} catch (StorageException e) {
			e.printStackTrace();
		}
		
		
		//for redirect
		String url=null;
		RootPanel urlTarget=RootPanel.get(PROPERTY_REDIRECT_URL_ID);
		if(urlTarget==null){
			url="";
		}else{
			url=urlTarget.getElement().getAttribute("value");
		}
		if(url==null){
			url="";
		}
		
		if(!url.isEmpty()){
			Location.replace(url);
		}
	}

}
