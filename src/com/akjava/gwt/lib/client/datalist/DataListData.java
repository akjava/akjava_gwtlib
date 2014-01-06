package com.akjava.gwt.lib.client.datalist;

public class DataListData<T>{
	public DataListData(T data){
		this.data=data;
	}
	private T data;
	private boolean modified;
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public boolean isModified() {
		return modified;
	}
	public void setModified(boolean modified) {
		this.modified = modified;
	}	
}