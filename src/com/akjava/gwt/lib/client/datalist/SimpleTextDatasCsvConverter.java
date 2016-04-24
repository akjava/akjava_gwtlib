package com.akjava.gwt.lib.client.datalist;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.util.List;

import com.akjava.gwt.lib.client.LogUtils;
import com.akjava.lib.common.csv.CSVReader;
import com.google.common.base.Converter;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class SimpleTextDatasCsvConverter extends Converter<List<SimpleTextData>,String>{

	public String lineSeparator="\r\n";
	
	public String getLineSeparator() {
		return lineSeparator;
	}

	public void setLineSeparator(String lineSeparator) {
		this.lineSeparator = lineSeparator;
	}

	@Override
	protected String doForward(List<SimpleTextData> datas) {
		checkNotNull(datas,"SimpleTextDatasCsvConverter:not expected null");
		SimpleTextDataCsvLineConverter converter=new SimpleTextDataCsvLineConverter();
		List<String> lines=Lists.newArrayList();
		Joiner joiner=Joiner.on("\t");
		for(String[] csv:converter.convertAll(datas)){
			lines.add(joiner.join(csv));
		}
		return Joiner.on(lineSeparator).join(lines);
	}

	/**
	 * return Immutable list;
	 */
	@Override
	protected List<SimpleTextData> doBackward(String csv) {
		checkNotNull(csv,"SimpleTextDatasCsvConverter:not expected null");
		//TODO support modify quote
		CSVReader reader=new CSVReader(csv,'\t','"',true);
		try {
			List<String[]> csvs=reader.readAll();
			return ImmutableList.copyOf(new SimpleTextDataCsvLineConverter().reverse().convertAll(csvs));
		} catch (IOException e) {
			LogUtils.log(e.getMessage());
		}
		return null;
	}

}
