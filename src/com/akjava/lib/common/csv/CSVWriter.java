package com.akjava.lib.common.csv;

import java.util.List;

import com.akjava.lib.common.csv.CSVUtils.CSVJoiner;
import com.google.common.base.Joiner;
import com.google.common.collect.FluentIterable;

/**
 * @deprecated use Converter
 * @author aki
 *
 */
public class CSVWriter {
	private CSVJoiner joiner;
	private char separator;
	public CSVWriter(char separator){
		this.separator=separator;
		joiner=new CSVJoiner(separator,'"');
	}
	public String listToCsvLine(List<String> csv){
		return joiner.apply(csv);
	}
	
	public String toCsv(List<List<String>> csvs,String lineSeparator){
		return Joiner.on(lineSeparator).join(FluentIterable.from(csvs).transform(joiner));
	}
	


	
	
}
