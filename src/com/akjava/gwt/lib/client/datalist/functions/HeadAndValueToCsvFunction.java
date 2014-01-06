package com.akjava.gwt.lib.client.datalist.functions;

import com.akjava.gwt.lib.client.HeaderAndValue;
import com.akjava.lib.common.utils.CSVUtils;
import com.google.common.base.Function;

public class HeadAndValueToCsvFunction implements Function<HeaderAndValue,String> {
	@Override
	public String apply(HeaderAndValue input) {
		return CSVUtils.toSimpleQuoteString(""+input.getId())+"\t"+
				 CSVUtils.toSimpleQuoteString(input.getHeader()!=null?input.getHeader():"")+"\t"+
				 CSVUtils.toSimpleQuoteString(input.getData()!=null?input.getData():"");
	}

}
