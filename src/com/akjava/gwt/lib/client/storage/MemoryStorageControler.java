package com.akjava.gwt.lib.client.storage;

import java.util.HashMap;
import java.util.Map;

import com.akjava.gwt.lib.client.IStorageControler;
import com.akjava.lib.common.utils.ValuesUtils;


public class MemoryStorageControler implements IStorageControler{
private StorageValueChangeListener listener;
public StorageValueChangeListener getListener() {
	return listener;
}

public void setListener(StorageValueChangeListener listener) {
	this.listener = listener;
}


private Map<String,String> map=new HashMap<String, String>();
	
	public void setMap(Map<String, String> map) {
	this.map = map;
}

	public Map<String, String> getMap() {
	return map;
}

	@Override
	public boolean isAvailable() {
		return true;
	}

	@Override
	public void setValue(String key, String value) {
		map.put(key, value);
		if(listener!=null){
			listener.onValueChanged(key,value);
		}
	}

	@Override
	public void setValue(String key, int value) {
		map.put(key, ""+value);
		if(listener!=null){
			listener.onValueChanged(key, ""+value);
		}
	}

	@Override
	public void removeValue(String key) {
		map.remove(key);
		if(listener!=null){
			listener.onValueChanged(key, null);
		}
	}

	@Override
	public int getValue(String key, int defaultValue) {
		// TODO Auto-generated method stub
		return ValuesUtils.toInt(map.get(key), defaultValue);
	}

	@Override
	public String getValue(String key, String defaultValue) {
		String v=map.get(key);
		if(v==null){
			v=defaultValue;
		}
		return v;
	}
	

	public interface StorageValueChangeListener{
		public void onValueChanged(String key,String value);
	}
	

}
