package com.akjava.lib.common.param;

import java.util.LinkedList;
import java.util.List;

public class Parameter {
public Parameter(String name){
	this.name=name;
}
private String name;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
private String remain;

public String getRemain() {
	return remain;
}
public void setRemain(String remain) {
	this.remain = remain;
}
public List<String> getAttributes() {
	return attributes;
}
public void setAttributes(List<String> attributes) {
	this.attributes = attributes;
}
public void add(String attribute){
	attributes.add(attribute);
}
public int size(){
	return attributes.size();
}
public String get(int index){
	if(index<0||index>=size()){
		throw new RuntimeException("on Parameter.get:invalid array index ="+index);
	}
	return attributes.get(index);
}
private List<String> attributes=new LinkedList<String>();
@Override
public String toString() {
	String result=name;
	if(hasAttribute()){
		result+="(";
		for(int i=0;i<attributes.size();i++){
			result+=attributes.get(i);
			if(i!=attributes.size()-1){
				result+=":";
			}
		}
		result+=")";
	}
	return result;
}
public boolean hasAttribute(){
	return attributes!=null && attributes.size()>0;
}


}
