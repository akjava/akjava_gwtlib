package com.akjava.gwt.lib.client;

import com.google.gwt.storage.client.Storage;

public class StorageControler {
	private Storage storage;
	public StorageControler(){
		storage = Storage.getLocalStorageIfSupported();
		
	}
	public boolean isAvailable(){
		return storage!=null;
	}
	
	public void  setValue(String key,String value){
		if(storage==null){
			return;
		}else{
			if(value==null){
			storage.removeItem(key);	
			}else{
			storage.setItem(key, value);
			}
		}
	}
	
	public void  setValue(String key,int value){
		if(storage==null){
			return;
		}else{
			storage.setItem(key, ""+value);
		}
	}
	public void removeValue(String key){
		if(storage==null){
			return;
		}else{
			storage.removeItem(key);
		}
	}
	
	public int getValue(String key,int defaultValue){
		if(storage==null){
			return defaultValue;
		}else{
			String v=storage.getItem(key);
			if(v==null){
				return defaultValue;
			}else{
				try{
					return Integer.parseInt(v);
					}catch(Exception e){}
				return defaultValue;
			}
		}
		
		
	}
	
	public String getValue(String key,String defaultValue){
		if(storage==null){
			return defaultValue;
		}else{
			String v=storage.getItem(key);
			if(v==null){
				return defaultValue;
			}else{
				return v;
			}
		}
	}
}
