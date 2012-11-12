package com.akjava.gwt.lib.client.storage;

import java.util.ArrayList;
import java.util.List;

import com.akjava.gwt.lib.client.IStorageControler;
import com.akjava.gwt.lib.client.StorageException;
import com.akjava.lib.common.utils.ValuesUtils;



public class MultiStorageControler implements IStorageControler{
public List<IStorageControler> controlers=new ArrayList<IStorageControler>();


public List<IStorageControler> getControlers() {
	return controlers;
}

public void setControlers(List<IStorageControler> controlers) {
	this.controlers = controlers;
}

@Override
public boolean isAvailable() {
if(controlers.size()==0){
	return false;
}
for(IStorageControler controler:controlers){
	if(!controler.isAvailable()){
		return false;
	}
}
	return true;
}

@Override
public void setValue(String key, String value) throws StorageException {
	if(controlers.size()==0){
		throw new StorageException("empty controlers");
	}
	for(IStorageControler controler:controlers){
		controler.setValue(key, value);
	}
}

@Override
public void setValue(String key, int value) throws StorageException {
	if(controlers.size()==0){
		throw new StorageException("empty controlers");
	}
for(IStorageControler controler:controlers){
	controler.setValue(key, value);
	}
}

@Override
public void removeValue(String key) throws StorageException {
	if(controlers.size()==0){
		throw new StorageException("empty controlers");
	}
for(IStorageControler controler:controlers){
	controler.removeValue(key);
	}
}

@Override
public int getValue(String key, int defaultValue) throws StorageException {
String value=getValue(key,""+defaultValue);
return ValuesUtils.toInt(value, defaultValue);
}

@Override
public String getValue(String key, String defaultValue) throws StorageException {
if(controlers.size()==0){
	throw new StorageException("empty controlers");
}
String first=controlers.get(0).getValue(key,defaultValue);

/*
for(int i=1;i<controlers.size();i++){
	String value=controlers.get(i).getValue(key, defaultValue);
	if(!Objects.equal(first, value)){
		//can't error stop anything
		//LogUtils.log("difference value:key="+key+",firstValue="+first+",invalid="+value);
	}
}
*/

return first;
}

}
