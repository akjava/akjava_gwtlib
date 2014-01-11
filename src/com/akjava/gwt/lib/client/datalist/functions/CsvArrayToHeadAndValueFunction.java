package com.akjava.gwt.lib.client.datalist.functions;

import com.akjava.gwt.lib.client.datalist.SimpleTextData;
import com.akjava.lib.common.utils.ValuesUtils;
import com.google.common.base.Function;

/**
 * not support cdate yet;
 * @author aki
 *
 */
/*
 * the case read from CSV Reader
 */
public class CsvArrayToHeadAndValueFunction implements Function<String[],SimpleTextData> {
	@Override
	public SimpleTextData apply(String[] input) {
		int id=-1;
		String header="";
		String data="";
		
		if(input.length>0){
			id=ValuesUtils.toInt(input[0], -1);
		}
		if(input.length>1){
			header=input[1];
		}
		if(input.length>2){
			data=input[2];
		}
		SimpleTextData hv=new SimpleTextData(id,header,data);
		return hv;
	}

}
