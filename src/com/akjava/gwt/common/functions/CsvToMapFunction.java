package com.akjava.gwt.common.functions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Function;

public class CsvToMapFunction implements Function<Iterable<String>, Map<String,String>>{
private List<String> keys;
public CsvToMapFunction(List<String> keys){
	this.keys=keys;
}
	@Override
	public Map<String, String> apply(Iterable<String> values) {
		Map<String,String> ret=new HashMap<String, String>();
		int index=0;
		for(String value:values){
			if(index<keys.size()){
			ret.put(keys.get(index), value);
			index++;
			}
		}
		for(int i=index;i<keys.size();i++){
			ret.put(keys.get(i), "");
		}
		return ret;
	}

}
