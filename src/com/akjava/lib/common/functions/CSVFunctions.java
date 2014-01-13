package com.akjava.lib.common.functions;

import java.util.HashMap;
import java.util.Map;

import com.akjava.lib.common.utils.CSVUtils;
import com.google.common.base.Function;

public class CSVFunctions {
	
	
	private static PairCsvFunction tabPairCsvFunction=new PairCsvFunction("\t");
	public static PairCsvFunction getTabPairCsvFunction() {
		return tabPairCsvFunction;
	}

	private static PairCsvFunction commaPairCsvFunction=new PairCsvFunction(",");


	public static PairCsvFunction getCommaPairCsvFunction() {
		return commaPairCsvFunction;
	}


	public static class PairCsvFunction implements Function<String,Map<String,String>>{
		private String separator;

		public PairCsvFunction(String separator) {
			super();
			this.separator = separator;
		}
		@Override
		public Map<String,String> apply(String input){
			String[] lines=CSVUtils.splitLines(input);
			Map<String,String> map=new HashMap<String, String>();
			for(String line:lines){
				if(line.isEmpty()){
					continue;
				}
				String[] csv=CSVUtils.splitAtFirst(line, separator);
				if(csv.length==2){
					map.put(csv[0], csv[1]);
				}else{
					map.put(csv[0], "");
				}
			}
			return map;
		}
	}
	

}
