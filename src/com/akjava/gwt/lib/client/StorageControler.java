package com.akjava.gwt.lib.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.storage.client.Storage;

public class StorageControler implements IStorageControler {
	private Storage storage;
	
	
	public StorageControler(){
		storage = Storage.getLocalStorageIfSupported();
		
	}
	
	public StorageControler(boolean isLocalStorage){
		if(isLocalStorage){
		storage = Storage.getLocalStorageIfSupported();
		}else{
		storage=Storage.getSessionStorageIfSupported();
		}
		
	}
	/* (non-Javadoc)
	 * @see com.akjava.gwt.lib.client.IStorageControler#isAvailable()
	 */
	@Override
	public boolean isAvailable(){
		return storage!=null;
	}
	
	/* 
	 * Quota error usually happen
	 * (non-Javadoc)
	 * @see com.akjava.gwt.lib.client.IStorageControler#setValue(java.lang.String, java.lang.String)
	 */
	@Override
	public void  setValue(String key,String value) throws StorageException{
		if(storage==null){
			throw new StorageException("Storage not found");
		}else{
			try{
			if(value==null){//for null set
			storage.removeItem(key);	
			}else{
			storage.setItem(key, value);
			}
			}catch (Exception e) {
				throw new StorageException(e.getMessage());
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.akjava.gwt.lib.client.IStorageControler#setValue(java.lang.String, int)
	 */
	@Override
	public void  setValue(String key,int value) throws StorageException{
		if(storage==null){
			throw new StorageException("Storage not found");
		}else{
			try{
			storage.setItem(key, ""+value);
			}catch(Exception e){
				throw new StorageException(e.getMessage());
			}
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
	
	//TODO add IStorageControler and test all
	public boolean getValue(String key,boolean defaultValue){
		if(storage==null){
			return defaultValue;
		}else{
			String v=storage.getItem(key);
			if(v==null){
				return defaultValue;
			}else{
				try{
					return Boolean.parseBoolean(v);
					}catch(Exception e){}
				return defaultValue;
			}
		}
	}
	
	//TODO add IStorageControler and test all
	public void  setValue(String key,boolean value) throws StorageException{
		if(storage==null){
			throw new StorageException("Storage not found");
		}else{
			try{
			storage.setItem(key, ""+value);
			}catch(Exception e){
				throw new StorageException(e.getMessage());
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
