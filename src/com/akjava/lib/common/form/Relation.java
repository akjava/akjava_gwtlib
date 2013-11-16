package com.akjava.lib.common.form;

public class Relation {
private String key;
public String getKey() {
	return key;
}
public void setKey(String key) {
	this.key = key;
}
public FormData getData() {
	return data;
}
public void setData(FormData data) {
	this.data = data;
}
private FormData data;
public Relation(FormData data,String key){
	this.data=data;
	this.key=key;
}
public String toString(){
	return data.getClassName()+"("+key+")";
}
}
