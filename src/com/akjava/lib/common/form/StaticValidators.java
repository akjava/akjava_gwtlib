package com.akjava.lib.common.form;





public class StaticValidators {
public static final String VALIDATOR_NOT_EMPTY="NotEmpty";
public static final String VALIDATOR_ASCII_NUMBER="AsciiNumber";
public static final String VALIDATOR_ASCII_NUMBER_AND_CHAR="AsciiNumberAndChar";

public static final String VALIDATOR_DATE_TYPE="DateType";

public static final String VALIDATOR_HANKAKU_KANA="HankakuKana";
public static final String VALIDATOR_HIRAGANA="Hiragana";
//public static final String VALIDATOR_ASCII_NUMBER_AND_CHAR_HANKAKU_KANA="AsciiNumberAndCharAndHankakuKana";

public static NotEmptyValidator notEmptyValidator(){
		  return NotEmptyValidator.INSTANCE;
	  }
	  
	  public enum NotEmptyValidator implements Validator {
		    INSTANCE;
			@Override
			public String getName() {
				return VALIDATOR_NOT_EMPTY;
			}
			@Override
			public boolean validate(String value) {
				return value!=null && !value.isEmpty();
			}
		  }
	  
	  
	  public static AsciiNumberOnly asciiNumberOnly(){
		  return AsciiNumberOnly.INSTANCE;
	  }
	  
	  public enum AsciiNumberOnly implements Validator {
		    INSTANCE;
			@Override
			public String getName() {
				return VALIDATOR_ASCII_NUMBER;
			}
			@Override
			public boolean validate(String value) {
				for(int i=0;i<value.length();i++){
					if(!Character.isDigit(value.charAt(i)) || value.charAt(i)>128){
						return false;
					}
				}
				return true;
			}
		  }
	  public static AsciiNumberAndCharOnly asciiNumberAndCharOnly(){
		  return AsciiNumberAndCharOnly.INSTANCE;
	  }
	  public enum AsciiNumberAndCharOnly implements Validator {
		    INSTANCE;
			@Override
			public String getName() {
				return VALIDATOR_ASCII_NUMBER_AND_CHAR;
			}
			@Override
			public boolean validate(String value) {
				for(int i=0;i<value.length();i++){
					
					if(!Character.isLetterOrDigit(value.charAt(i)) || value.charAt(i)>128){
						return false;
					}
				}
				return true;
			}
		  }
	  
		
		  public static DateTypeChecker dateTypeChecker(){
			  return DateTypeChecker.INSTANCE;
		  }
		  
		  public enum DateTypeChecker implements Validator {
			    INSTANCE;
				@Override
				public String getName() {
					return VALIDATOR_DATE_TYPE;
				}
				@Override
				public boolean validate(String value) {
					String vs[] = value.split("/");
					if (vs.length != 3) {
						return false;
					} else {
						 for (int j = 0; j < vs.length; j++) {
							if (vs[j].isEmpty()) {
								return false;
								
							}
						}
					}
					return true;
				}
			  }
		  
		  public static HankakuKana hankakuKana(){
			  return HankakuKana.INSTANCE;
		  } 
		  
		  public  enum HankakuKana implements Validator {
			    INSTANCE;
				@Override
				public String getName() {
					return VALIDATOR_HANKAKU_KANA;
				}
				@Override
				public boolean validate(String value) {
					for(int i=0;i<value.length();i++){
						if( value.charAt( i ) < 0xFF61 || value.charAt( i ) > 0xFF9F ){
							return false;
							} 
					}
					return true;
				}
			  }
		  
		  public static Hiragana hiragana(){
			  return Hiragana.INSTANCE;
		  } 
		  
		  public enum Hiragana implements Validator {
			    INSTANCE;
				@Override
				public String getName() {
					return VALIDATOR_HANKAKU_KANA;
				}
				@Override
				public boolean validate(String value) {
					for(int i=0;i<value.length();i++){
						if( value.charAt( i ) < 0x3040  || value.charAt( i ) > 0x309F ){
							return false;
							} 
					}
					return true;
				}
			  }
		  
		  
		  /*
		   * no need
		  public static AsciiOnlyKanaHanKanaOnly asciiKanaHanKanaOnly(){
			  return AsciiOnlyKanaHanKanaOnly.INSTANCE;
		  }
		  
		 
		  enum AsciiOnlyKanaHanKanaOnly implements Validator {
			    INSTANCE;
				@Override
				public String getName() {
					return VALIDATOR_ASCII_NUMBER_AND_CHAR_HANKAKU_KANA;
				}
				@Override
				public boolean validate(String value) {
					for(int i=0;i<value.length();i++){
						
						if( value.charAt( i ) >= 0xFF61 && value.charAt( i ) <= 0xFF9F ){
							continue;
							}
						
						if(Character.isDigit(value.charAt(i))){
							continue;
						}
					}
					return true;
				}
			  }
			  */
		  
		  
	
}
