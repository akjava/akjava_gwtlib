package com.akjava.gwt.lib.client;

import com.google.gwt.storage.client.Storage;

public class StorageControler implements IStorageControler {
	private Storage storage;
	private StorageControler(){
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
}
