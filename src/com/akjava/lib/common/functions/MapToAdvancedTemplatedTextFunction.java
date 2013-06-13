package com.akjava.lib.common.functions;

import java.util.Map;

import com.akjava.lib.common.utils.TemplateUtils;
import com.google.common.base.Function;

public class MapToAdvancedTemplatedTextFunction implements Function<Map<String,String>,String>{
private String template;
public MapToAdvancedTemplatedTextFunction(String template){
	this.template=template;
}
	@Override
	public String apply(Map<String, String> map) {
		return TemplateUtils.createAdvancedText(template, map);
	}

}
