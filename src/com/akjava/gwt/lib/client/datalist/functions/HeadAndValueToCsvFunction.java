package com.akjava.gwt.lib.client.datalist.functions;

import com.akjava.gwt.lib.client.datalist.SimpleTextData;
import com.akjava.lib.common.utils.CSVUtils;
import com.google.common.base.Function;

/**
 * not support cdate yet;
 * @author aki
 *
 */
public class HeadAndValueToCsvFunction implements Function<SimpleTextData,String> {
	@Override
	public String apply(SimpleTextData input) {
		return CSVUtils.toSimpleQuoteString(""+input.getId())+"\t"+
				 CSVUtils.toSimpleQuoteString(input.getName()!=null?input.getName():"")+"\t"+
				 CSVUtils.toSimpleQuoteString(input.getData()!=null?input.getData():"");
	}

}
