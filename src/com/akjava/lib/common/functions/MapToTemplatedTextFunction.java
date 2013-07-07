package com.akjava.lib.common.functions;

import com.akjava.lib.common.utils.TemplateUtils;
import com.google.common.base.Function;

public class MapToTemplatedTextFunction implements Function<String,String>{
private String template;
public MapToTemplatedTextFunction(String template){
	this.template=template;
}
	@Override
	public String apply(String value) {
		return TemplateUtils.createText(template, value);
	}

}
