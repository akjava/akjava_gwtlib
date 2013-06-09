package com.akjava.lib.common.form;

import java.io.UnsupportedEncodingException;


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
}
