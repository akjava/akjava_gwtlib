package com.akjava.gwt.common.functions;

import java.util.Map;

import com.akjava.lib.common.utils.TemplateUtils;
import com.google.common.base.Function;

public class MapToTemplatedTextFunction implements Function<Map<String,String>,String>{
private String template;
public MapToTemplatedTextFunction(String template){
	this.template=template;
}
	@Override
	public String apply(Map<String, String> map) {
		return TemplateUtils.createText(template, map);
	}

}
