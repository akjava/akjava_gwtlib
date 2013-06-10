package com.akjava.lib.common.form;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class Validators {
	public static final String VALIDATOR_MAX_STRING_SIZE="MaxStringSize";
	public static final String VALIDATOR_BETWEEN_STRING_SIZE="BetweenStringSize";
	public static final String VALIDATOR_MAX_STRING_BYTE_SIZE="MaxStringByteSize";
	
	
	public static MaxStringSize maxStringSize(int max){
		  return new MaxStringSize(max);
	  }
	
	public static MaxStringByteSize maxStringByteSize(int max){
		  return new MaxStringByteSize(max);
	  }
	
	public static BetweenStringSize betweenStringSize(int min,int max){
		  return new BetweenStringSize(min,max);
	  }
	 
	 private static class MaxStringByteSize implements Validator{
			private int max;
			private String encode="UTF-8";
			private MaxStringByteSize(int max){
				this.max=max;
			}
			@Override
			public boolean validate(String value) {
				if(value==null || value.isEmpty()){//need value
					return true;
				}
				
				try {
					byte[] bts=value.getBytes(encode);
					return bts.length<=max;
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			}

			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return VALIDATOR_MAX_STRING_SIZE;
			}
			
		 }
	 
	 private static class BetweenStringSize implements Validator{
		private int max;
		private int min;
		private BetweenStringSize(int min,int max){
			this.min=min;
			this.max=max;
			
		}
		@Override
		public boolean validate(String value) {
			if(value==null || value.isEmpty()){
				return false;
			}
			
				return value.length()>=min && value.length()<=max;
			
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return VALIDATOR_BETWEEN_STRING_SIZE;
		}
		
	 }
	 
	 private static class MaxStringSize implements Validator{
		private int max;
		private MaxStringSize(int max){
			this.max=max;
		}
		@Override
		public boolean validate(String value) {
			if(value==null || value.isEmpty()){
				return true;
			}
			
				return value.length()<=max;
			
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return VALIDATOR_MAX_STRING_SIZE;
		}
		
	 }
	 
		public static AvaiableValueOnly avaiableValueOnly(List<String> values,boolean caseSensitive){
			  return new AvaiableValueOnly(values,caseSensitive);
		  }
		
		
	 private static class AvaiableValueOnly implements Validator{
		private List<String> values;
		private List<String> lowercases;
		private boolean caseSensitive;
		private AvaiableValueOnly(List<String> values,boolean caseSensitive){
			this.values=values;
			this.caseSensitive=caseSensitive;
			if(!caseSensitive){
				lowercases=new ArrayList<String>();
				for(String v:values){
					if(v!=null){
						lowercases.add(v.toLowerCase());
					}else{
						lowercases.add(null);
					}
				}
			}
		}
		@Override
		public boolean validate(String value) {
			if(value==null){
				return values.contains(null);
			}else{
				if(caseSensitive){
					return values.contains(value);
				}else{
					return lowercases.contains(value.toLowerCase());
				}
			}
			
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return VALIDATOR_MAX_STRING_SIZE;
		}
		
	 } 
}
