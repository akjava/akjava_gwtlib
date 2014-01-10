package com.akjava.gwt.lib.client;

import com.google.common.base.Function;

public class HeaderAndValueFunctions {

	public enum HeaderOnlyFunction implements Function<HeaderAndValue,String>{
		INSTANCE;
		@Override
		public String apply(HeaderAndValue input) {
			return input.getHeader();
		}
	}
	public enum ValueOnlyFunction implements Function<HeaderAndValue,String>{
		INSTANCE;
		@Override
		public String apply(HeaderAndValue input) {
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
}
