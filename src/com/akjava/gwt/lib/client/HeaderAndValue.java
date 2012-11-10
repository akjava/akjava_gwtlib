package com.akjava.gwt.lib.client;
/*
 * simple data
 */
public class HeaderAndValue{
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