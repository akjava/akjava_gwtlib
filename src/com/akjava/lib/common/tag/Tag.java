package com.akjava.lib.common.tag;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Tag {
private String name;
private boolean singleTag;
private String specialEnd=null;//for selected
private List<Tag> childrens=new ArrayList<Tag>();
private Tag parent;
public Tag getParent() {
	return parent;
}

public void setParent(Tag parent) {
	this.parent = parent;
}

public List<Tag> getChildrens() {
	return childrens;
}

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

public Map<String, String> getAttributes() {
	return attributes;
}
public String getAttribute(String key){
	return attributes.get(key);
}

public void setAttributes(Map<String, String> attributes) {
	this.attributes = attributes;
}

private String text;
private Map<String,String> attributes=new LinkedHashMap<String, String>();

public Tag(String name){
	this.name=name;
}

public void addChild(Tag tag){
	childrens.add(tag);
	tag.setParent(this);
}

public Tag attr(String name,int value){
	setAttribute(name,""+ value);
	return this;
}

public Tag attr(String name,String value){
	setAttribute(name, value);
	return this;
}
public Tag single(){
	setSingleTag(true);
	return this;
}

public Tag text(String text){
	setText(text);
	return this;
}

public void setAttribute(String name){
	setAttribute(name,name);
}
public void setAttribute(String name,String value){
	attributes.put(name, value);
}

public void setId(String id){
	setAttribute("id", id);
}

public void setClass(String clasz){
	setAttribute("class", clasz);
}

public String getStartTagText(){
	StringBuffer buffer=new StringBuffer();
	buffer.append("<"+name);
	
	for(String attr:attributes.keySet()){
		String value=attributes.get(attr);
		if(value.indexOf('"')!=-1){
			 value=value.replace("\"", "&quot;");
		}
		 buffer.append(" "+attr+"=\""+value+"\"");
	}
	
	if(specialEnd!=null){
		buffer.append(" "+specialEnd);
	}
	
	if(singleTag){
		buffer.append("/>");
	}else{
		buffer.append(">");
	}
	return buffer.toString();
}

public String getEndTagText(){
	if(isSingleTag()){
		return "";
	}
	return "</"+name+">";
}

public String toString(){
	
	if(singleTag){
		return getStartTagText();
	}else{
		StringBuffer buffer=new StringBuffer();
		buffer.append(getStartTagText());
		if(text!=null){
			buffer.append(text);
		}
		
		for(Tag tag:childrens){
			buffer.append("\n"+tag.toString());
		}
		
		
		buffer.append(getEndTagText());
		return buffer.toString();
	}
	
	
}

public String getSpecialEnd() {
	return specialEnd;
}

public void setSpecialEnd(String specialEnd) {
	this.specialEnd = specialEnd;
}


}
