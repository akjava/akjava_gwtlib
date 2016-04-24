package com.akjava.gwt.lib.client.datalist;

import java.util.List;

import com.akjava.lib.common.utils.CSVUtils;
import com.akjava.lib.common.utils.ValuesUtils;
import com.google.common.base.Converter;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class SimpleTextDataCsvLineConverter extends Converter<SimpleTextData,String[]>{

	public String splitter="\t";
	public String getSplitter() {
		return splitter;
	}

	public void setSplitter(String splitter) {
		this.splitter = splitter;
	}

	public String getSplitterReplaced() {
		return splitterReplaced;
	}

	public void setSplitterReplaced(String splitterReplaced) {
		this.splitterReplaced = splitterReplaced;
	}

	public String splitterReplaced="    ";
	//public String lineSeparator="\r\n";
	@Override
	protected String[] doForward(SimpleTextData data) {
		List<String> tmp=Lists.newArrayList();
		
		tmp.add(convertCsvValue(String.valueOf(data.getId())));
		
		//null value converted to space
		tmp.add(convertCsvValue(data.getName()!=null?data.getName():""));
		tmp.add(convertCsvValue(data.getData()!=null?data.getData():""));
		tmp.add(convertCsvValue(String.valueOf(data.getCdate())));
		
		return tmp.toArray(new String[0]);
		//return Joiner.on(splitter).join(tmp);
	}

	@Override
	protected SimpleTextData doBackward(String[] input) {
		int id=-1;
		String header="";
		String data="";
		long cdate=0;
		
		if(input.length>0){
			id=ValuesUtils.toInt(input[0], -1);
		}
		if(input.length>1){
			header=input[1].replace(splitterReplaced, splitter);
		}
		if(input.length>2){
			data=input[2].replace(splitterReplaced, splitter);
		}
		
		if(input.length>2){
			cdate=ValuesUtils.toLong(input[3],0);
		}
		
		SimpleTextData hv=new SimpleTextData(id,header,data,cdate);
		return hv;
	}
	
	protected String convertCsvValue(String value){
		return CSVUtils.toSimpleQuoteString(value.replace(splitter, splitterReplaced));
	}

}
