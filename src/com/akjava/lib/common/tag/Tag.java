package com.akjava.lib.common.tag;

import java.util.LinkedHashMap;
import java.util.Map;

public class Tag {
private String name;
private boolean singleTag;
public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public boolean isSingleTag() {
	return singleTag;
}

public void setSingleTag(boolean singleTag) {
	this.singleTag = singleTag;
}

public String getText() {
	return text;
}

public void setText(String text) {
	this.text = text;
}

public Map<String, String> getAttbibutes() {
	return attbibutes;
}

public void setAttbibutes(Map<String, String> attbibutes) {
	this.attbibutes = attbibutes;
}

private String text;
private Map<String,String> attbibutes=new LinkedHashMap<String, String>();

public Tag(String name){
	this.name=name;
}

public void setAttribute(String name){
	setAttribute(name,name);
}
public void setAttribute(String name,String value){
	attbibutes.put(name, value);
}

public void setId(String id){
	setAttribute("id", id);
}

public void setClass(String clasz){
	setAttribute("class", clasz);
}

public String toString(){
	StringBuffer buffer=new StringBuffer();
	buffer.append("<"+name);
	
	for(String attr:attbibutes.keySet()){
		String value=attbibutes.get(attr);
		if(value.indexOf('"')!=-1){
			 value=value.replace("\"", "&quot;");
		}
		 buffer.append(" "+attr+"=\""+value+"\"");
	}
	
	if(singleTag){
		buffer.append("/>");
	}else{
		buffer.append(">");
		if(text!=null){
			buffer.append(text);
		}
		buffer.append("</"+name+">");
	}
	
	return buffer.toString();
}


}