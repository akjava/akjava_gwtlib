package com.akjava.gwt.lib.client.datalist;

import com.akjava.gwt.lib.client.HeaderAndValue;
import com.google.common.base.Function;

public class SimpleTextDataFunctions {

	public enum NameOnlyFunction implements Function<SimpleTextData,String>{
		INSTANCE;
		@Override
		public String apply(SimpleTextData input) {
			return input.getName();
		}
	}
	public enum DataOnlyFunction implements Function<SimpleTextData,String>{
		INSTANCE;
		@Override
		public String apply(SimpleTextData input) {
			return input.getData();
		}
	}
	public enum IDOnlyFunction implements Function<HeaderAndValue,Integer>{
		INSTANCE;
		@Override
		public Integer apply(HeaderAndValue input) {
			return input.getId();
		}
	}
	public enum CDateOnlyFunction implements Function<SimpleTextData,Long>{
		INSTANCE;
		@Override
		public Long apply(SimpleTextData input) {
			return input.getCdate();
		}
	}
	
	



}
