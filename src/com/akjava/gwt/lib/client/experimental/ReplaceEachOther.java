package com.akjava.gwt.lib.client.experimental;

import java.util.List;

import com.akjava.lib.common.utils.ValuesUtils;
import com.google.common.collect.Lists;

public class ReplaceEachOther {
private String replaceA;
public String getReplaceA() {
	return replaceA;
}

public void setReplaceA(String replaceA) {
	this.replaceA = replaceA;
}

public String getReplaceB() {
	return replaceB;
}

public void setReplaceB(String replaceB) {
	this.replaceB = replaceB;
}

private String replaceB;

public ReplaceEachOther(String replaceA, String replaceB) {
	super();
	this.replaceA = replaceA;
	this.replaceB = replaceB;
}

/**
 * 
 *  convert left-arm > right-arm
 *  not convert if contain both,left-right-foot > left-right-foot 
 * @param text
 * @return
 */
public String replace(String text){
	if(text.contains(replaceA) && !text.contains(replaceB)){
		return text.replace(replaceA, replaceB);
	}else if(text.contains(replaceB) && !text.contains(replaceA)){
		return text.replace(replaceB, replaceA);
	}else{
		return text;
	}
}
//do replace with ,lower Camel UPPER case
public String replaceEachCommonCase(String text){
	List<ReplaceEachOther> commons=createCommonCase();
	return replaceAll(commons, text);
}

/*
 * create lower ,Camel,UPPER
 */
public List<ReplaceEachOther>  createCommonCase(){
	List<ReplaceEachOther> replacers=Lists.newArrayList();
	
	String textA=this.replaceA;
	String textB=this.replaceB;
	
	textA=textA.toLowerCase();
	textB=textB.toLowerCase();
	replacers.add(new ReplaceEachOther(textA, textB));
	
	textA=ValuesUtils.toUpperCamel(textA);
	textB=ValuesUtils.toUpperCamel(textB);
	replacers.add(new ReplaceEachOther(textA, textB));
	
	textA=textA.toUpperCase();
	textB=textB.toUpperCase();
	replacers.add(new ReplaceEachOther(textA, textB));
	
	
	return replacers;
}

public static String  replaceAll(List<ReplaceEachOther> replacer,String text){
	for(ReplaceEachOther eachOther:replacer){
		text=eachOther.replace(text);
	}
	return text;
}
}
