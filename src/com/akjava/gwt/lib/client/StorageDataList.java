package com.akjava.gwt.lib.client;

import java.util.ArrayList;
import java.util.List;

/**
 * try to use Storage data as simple list datas
 * @author aki
 *
 */
public class StorageDataList {
private String key;
public static final String KEY_INDEX="_IND_";
public static final String KEY_DATA="_DAT_";
public static final String KEY_HEADER="_HED_";
private IStorageControler controler;
	public StorageDataList(IStorageControler controler,String key){
		this.controler=controler;
		this.key=key;
	}
	
	public int incrementId(){
		try{
		int index=controler.getValue(key+KEY_INDEX, 0);
		int ret=index;
		index++;
		controler.setValue(key+KEY_INDEX, index);
		return ret;
		}catch(StorageException e){
			onError(e);
		}
		return -1;
	}

	private void onError(StorageException e){
		if(exceptionListener!=null){
			exceptionListener.onError(e);
		}else{
			LogUtils.log("Storage-Error:"+e.getMessage());
		}
	}
	
	public int getCurrentId(){
		try{
			return controler.getValue(key+KEY_INDEX, 0);
		}catch(StorageException e){
			onError(e);
		}
		return -1;
	}
	//becareful
	public void setCurrentId(int index){
try{
	controler.setValue(key+KEY_INDEX, index);
		}catch(StorageException e){
			onError(e);
		}
		
	}
	
	public List<HeaderAndValue> getDataList(){
try{
	List<HeaderAndValue> values=new ArrayList<HeaderAndValue>();
	int id=getCurrentId();
	for(int i=0;i<id;i++){
		String header=controler.getValue(key+KEY_HEADER+i, null);
		if(header!=null){
			String data=controler.getValue(key+KEY_DATA+i, null);
			values.add(new HeaderAndValue(i, header, data));
		}
	}
	return values;
		}catch(StorageException e){
			onError(e);
		}
		return null;
	}
	
	public void clearData(int id){
try{
	controler.removeValue(key+KEY_DATA+id);
	controler.removeValue(key+KEY_HEADER+id);
		}catch(StorageException e){
			onError(e);
		}
		
	}
	/**
	 * @deprecated
	 * @param header
	 * @param value
	 */
	public void setDataValue(String header,String value){
try{
	int id=getCurrentId();
	controler.setValue(key+KEY_DATA+id, value);
	controler.setValue(key+KEY_HEADER+id, header);
	
		}catch(StorageException e){
			onError(e);
		}
		
		
	}
	
	public int addData(String header,String value){
	int id=-1;
	try{
		id=getCurrentId();
		controler.setValue(key+KEY_DATA+id, value);
		controler.setValue(key+KEY_HEADER+id, header);
		incrementId();
		return id;
	}catch(StorageException e){
		onError(e);
	}
	return -1;
	}
	
	
	public void updateDataHeader(int id,String header){
try{
	controler.setValue(key+KEY_HEADER+id, header);
		}catch(StorageException e){
			onError(e);
		}
		
	}
	
	public void updateDataValue(int id,String value){
try{
	controler.setValue(key+KEY_DATA+id, value);
		}catch(StorageException e){
			onError(e);
		}
		
	}
	public void updateData(int id,String header,String value){
try{
	controler.setValue(key+KEY_HEADER+id, header);
	controler.setValue(key+KEY_DATA+id, value);
		}catch(StorageException e){
			onError(e);
		}
		
	}
	
	public HeaderAndValue getDataValue(int id){
try{
	String header=controler.getValue(key+KEY_HEADER+id, null);
	if(header!=null){
		String data=controler.getValue(key+KEY_DATA+id, null);
		return new HeaderAndValue(id, header, data);
	}
	return null;
		}catch(StorageException e){
			onError(e);
		}
	return null;
	}
	
	/**
	 * @deprecated
	 * @author aki
	 *
	 */
	public static class QuotaExceededError extends Error{
		private String message;
		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public QuotaExceededError(String message) {
			this.message=message;
		}

		private static final long serialVersionUID = 1L;

		
	}
	private StorageExceptionListener exceptionListener;
	public StorageExceptionListener getExceptionListener() {
		return exceptionListener;
	}

	public void setExceptionListener(StorageExceptionListener exceptionListener) {
		this.exceptionListener = exceptionListener;
	}

	public interface StorageExceptionListener{
		public void onError(StorageException exception);
	}
	

	
}
