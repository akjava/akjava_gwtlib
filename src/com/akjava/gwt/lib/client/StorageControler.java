package com.akjava.gwt.lib.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.storage.client.Storage;

public class StorageControler implements IStorageControler {
	private Storage storage;
	
	
	public StorageControler(){
		storage = Storage.getLocalStorageIfSupported();
		
	}
	/* (non-Javadoc)
	 * @see com.akjava.gwt.lib.client.IStorageControler#isAvailable()
	 */
	@Override
	public boolean isAvailable(){
		return storage!=null;
	}
	
	/* (non-Javadoc)
	 * @see com.akjava.gwt.lib.client.IStorageControler#setValue(java.lang.String, java.lang.String)
	 */
	@Override
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
	
	/* (non-Javadoc)
	 * @see com.akjava.gwt.lib.client.IStorageControler#setValue(java.lang.String, int)
	 */
	@Override
	public void  setValue(String key,int value){
		if(storage==null){
			return;
		}else{
			storage.setItem(key, ""+value);
		}
	}
	/* (non-Javadoc)
	 * @see com.akjava.gwt.lib.client.IStorageControler#removeValue(java.lang.String)
	 */
	@Override
	public void removeValue(String key){
		if(storage==null){
			return;
		}else{
			storage.removeItem(key);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.akjava.gwt.lib.client.IStorageControler#getValue(java.lang.String, int)
	 */
	@Override
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
	
	/* (non-Javadoc)
	 * @see com.akjava.gwt.lib.client.IStorageControler#getValue(java.lang.String, java.lang.String)
	 */
	@Override
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
	
	public int getLength(){
		return storage.getLength();
	}
	public String key(int index){
		return storage.key(index);
	}
	public Map<String,String> toHashMap(){
		Map<String,String> hashMap=new HashMap<String, String>();
		for(int i=0;i<getLength();i++){
			String key=key(i);
			String value=getValue(key, "");
			hashMap.put(key, value);
		}
		return hashMap;
	}
}
