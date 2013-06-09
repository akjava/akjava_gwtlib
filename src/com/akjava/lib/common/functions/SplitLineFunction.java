package com.akjava.lib.common.functions;

import java.util.List;

import com.google.common.base.CharMatcher;
import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class SplitLineFunction implements Function<String, List<String>> {
private Splitter splitter;

public SplitLineFunction(boolean splitTab,boolean splitComma){
	String v="";
	if(splitTab){
		v+="\t";
	}
	if(splitComma){
		v+=",";
	}
	if(v.isEmpty()){
		throw new  RuntimeException("need tab or comma support");
	}
	setSplitChars(v);
}
public void setSplitChars(String text){
	CharMatcher matcher=CharMatcher.anyOf(text);
	splitter=Splitter.on(matcher);
}

	public SplitLineFunction(String splitters){
		setSplitChars(splitters);
	}
	
	@Override
	public List<String> apply(String line) {
		return Lists.newArrayList(splitter.split(line));
	}

}
