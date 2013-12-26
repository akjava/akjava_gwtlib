package com.akjava.lib.common.form;





public class StaticValidators {
public static final String VALIDATOR_NOT_EMPTY="NotEmpty";
public static final String VALIDATOR_ASCII_NUMBER="AsciiNumber";
public static final String VALIDATOR_ASCII_NUMBER_AND_CHAR="AsciiNumberAndChar";
public static final String VALIDATOR_ASCII_NUMBER_AND_CHAR_AND_UNDERBAR="AsciiNumberAndCharAndUnderBar";

public static final String VALIDATOR_DECIMAL_NUMBER="DecimalNumber";
public static final String VALIDATOR_INTEGER_NUMBER="IntegerNumber";

public static final String VALIDATOR_ASCII_CHAR="AsciiChar";
public static final String VALIDATOR_START_ASCII_CHAR="StartAsciiChar";

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
			
			@Override
			public String toString() {
				return getName();
			}
		  }
	  
	  //validate if start minus
	  public static IntegerNumber integerNumber(){
		  return IntegerNumber.INSTANCE;
	  }
	  
	  public enum IntegerNumber implements Validator {
		    INSTANCE;
			@Override
			public String getName() {
				return VALIDATOR_INTEGER_NUMBER;
			}
			@Override
			public boolean validate(String value) {
				if(value.startsWith("-")){
					return asciiNumberOnly().validate(value.substring(1));
				}else{
					return asciiNumberOnly().validate(value);
				}
			}
			@Override
			public String toString() {
				return getName();
			}
		  }
	  
	  public static DecimalNumber decimalNumber(){
		  return DecimalNumber.INSTANCE;
	  }
	  
	  public enum DecimalNumber implements Validator {
		    INSTANCE;
			@Override
			public String getName() {
				return VALIDATOR_DECIMAL_NUMBER;
			}
			@Override
			public boolean validate(String value) {
				if(value.startsWith("-")){
					value=value.substring(1);//don't care minus
				}
				
				int pointIndex=value.indexOf(".");
				if(pointIndex==-1){
					return asciiNumberOnly().validate(value);
				}else{
					String first=value.substring(0,pointIndex);
					String last=value.substring(pointIndex+1);
					
					
					//point only is invalid.ascii validator can't catch empty
					if(first.isEmpty()&&last.isEmpty()){
						return false;
					}
					return asciiNumberOnly().validate(first) && asciiNumberOnly().validate(last);
				}
				
			}
			@Override
			public String toString() {
				return getName();
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
			@Override
			public String toString() {
				return getName();
			}
		  }
	  
	  
	  public static StartAsciiChar startAsciiChar(){
		  return StartAsciiChar.INSTANCE;
	  }
	  
	  public enum StartAsciiChar implements Validator {
		    INSTANCE;
			@Override
			public String getName() {
				return VALIDATOR_START_ASCII_CHAR;
			}
			@Override
			public boolean validate(String value) {
				if(!Character.isLetter(value.charAt(0)) || value.charAt(0)>128){
					return false;
				}
				return true;
			}
			@Override
			public String toString() {
				return getName();
			}
		  }
	  
	  
	  
	  public static AsciiCharOnly asciiCharOnly(){
		  return AsciiCharOnly.INSTANCE;
	  }
	  
	  public enum AsciiCharOnly implements Validator {
		    INSTANCE;
			@Override
			public String getName() {
				return VALIDATOR_ASCII_CHAR;
			}
			@Override
			public boolean validate(String value) {
				for(int i=0;i<value.length();i++){
					if(!Character.isLetter(value.charAt(i)) || value.charAt(i)>128){
						return false;
					}
				}
				return true;
			}
			@Override
			public String toString() {
				return getName();
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
			@Override
			public String toString() {
				return getName();
			}
		  }
	  
	  public static AsciiNumberAndCharAndUnderbarOnly asciiNumberAndCharAndUnderbarOnly(){
		  return AsciiNumberAndCharAndUnderbarOnly.INSTANCE;
	  }
	  public enum AsciiNumberAndCharAndUnderbarOnly implements Validator {
		    INSTANCE;
			@Override
			public String getName() {
				return VALIDATOR_ASCII_NUMBER_AND_CHAR_AND_UNDERBAR;
			}
			@Override
			public boolean validate(String value) {
				for(int i=0;i<value.length();i++){
					char ch=value.charAt(i);
					if(!Character.isLetterOrDigit(ch) || ch>128){
						if(ch!='_'){
						return false;
						}
					}
				}
				return true;
			}
			@Override
			public String toString() {
				return getName();
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
				@Override
				public String toString() {
					return getName();
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
				@Override
				public String toString() {
					return getName();
				}
			  }
		  
		  public static Hiragana hiragana(){
			  return Hiragana.INSTANCE;
		  } 
		  
		  public enum Hiragana implements Validator {
			    INSTANCE;
				@Override
				public String getName() {
					return VALIDATOR_HIRAGANA;
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
				@Override
				public String toString() {
					return getName();
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
