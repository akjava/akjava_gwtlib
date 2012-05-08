package com.akjava.gwt.lib.client;

import java.util.ArrayList;
import java.util.List;

public class StorageDataList {
private String key;
public static final String KEY_INDEX="_IND_";
public static final String KEY_DATA="_DAT_";
public static final String KEY_HEADER="_HED_";
private StorageControler controler;
	public StorageDataList(StorageControler controler,String key){
		this.controler=controler;
		this.key=key;
	}
	
	public int incrementId(){
		int index=controler.getValue(key+KEY_INDEX, 0);
		int ret=index;
		index++;
		controler.setValue(key+KEY_INDEX, index);
		return ret;
	}
	
	public int getCurrentId(){
		return controler.getValue(key+KEY_INDEX, 0);
	}
	//becareful
	public void setCurrentId(int index){
		controler.setValue(key+KEY_INDEX, index);
	}
	
	public List<HeaderAndValue> getDataList(){
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
	}
	
	public void clearData(int id){
		controler.removeValue(key+KEY_DATA+id);
		controler.removeValue(key+KEY_HEADER+id);
	}
	
	public void setDataValue(String header,String value){
		int id=getCurrentId();
		controler.setValue(key+KEY_DATA+id, value);
		controler.setValue(key+KEY_HEADER+id, header);
	}
	
	
	public void updateDataHeader(int id,String header){
		controler.setValue(key+KEY_HEADER+id, header);
	}
	
	public void updateDataValue(int id,String value){
		controler.setValue(key+KEY_DATA+id, value);
	}
	
	public HeaderAndValue getDataValue(int id){
		String header=controler.getValue(key+KEY_HEADER+id, null);
		if(header!=null){
			String data=controler.getValue(key+KEY_DATA+id, null);
			return new HeaderAndValue(id, header, data);
		}
		return null;
	}
	
	public  static class HeaderAndValue{
		private int id;
		private String header;
		private String data;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getHeader() {
			return header;
		}
		public void setHeader(String header) {
			this.header = header;
		}
		public String getData() {
			return data;
		}
		public void setData(String data) {
			this.data = data;
		}
		public HeaderAndValue(int id,String header,String data){
			this.id=id;
			this.header=header;
			this.data=data;
		}
	}
	
}