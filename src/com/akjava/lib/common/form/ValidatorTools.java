package com.akjava.lib.common.form;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class ValidatorTools {

	private ValidatorTools(){}
private  static BiMap<String,Validator> validatorMap=null;
public static BiMap<String,Validator> getValidatorMap(){
	if(validatorMap==null){
		init();
	}
	return validatorMap;
}
	private static void init(){
		validatorMap=HashBiMap.create();
		validatorMap.put(StaticValidators.VALIDATOR_NOT_EMPTY.toLowerCase(), StaticValidators.notEmptyValidator());
		validatorMap.put(StaticValidators.VALIDATOR_ASCII_NUMBER.toLowerCase(), StaticValidators.asciiNumberOnly());
		validatorMap.put(StaticValidators.VALIDATOR_ASCII_NUMBER_AND_CHAR.toLowerCase(), StaticValidators.asciiNumberAndCharOnly());
		validatorMap.put(StaticValidators.VALIDATOR_ASCII_NUMBER_AND_CHAR_AND_UNDERBAR.toLowerCase(), StaticValidators.asciiNumberAndCharAndUnderbarOnly());
		
		validatorMap.put(StaticValidators.VALIDATOR_HANKAKU_KANA.toLowerCase(), StaticValidators.hankakuKana());
		validatorMap.put(StaticValidators.VALIDATOR_HIRAGANA.toLowerCase(), StaticValidators.hiragana());
		
		validatorMap.put(StaticValidators.VALIDATOR_ASCII_CHAR.toLowerCase(), StaticValidators.asciiCharOnly());
		validatorMap.put(StaticValidators.VALIDATOR_START_ASCII_CHAR.toLowerCase(), StaticValidators.startAsciiChar());
		
		
		//TODO should support name contain args like msize(12),bet(6:12)
		validatorMap.put("between", Validators.betweenStringSize(6, 12));
		
		validatorMap.put("less8", Validators.maxStringSize(8));
		validatorMap.put("less8b", Validators.maxStringByteSize(8));
		
		validatorMap.put("less64", Validators.maxStringSize(64));
		validatorMap.put("less128", Validators.maxStringSize(128));
		validatorMap.put("less256", Validators.maxStringSize(256));
		
		validatorMap.put("less500", Validators.maxStringSize(500));
		validatorMap.put("less1m", Validators.maxStringByteSize(1024*1024));
		

	}

	public static class ValidatorNotFoundException extends Exception{

		public ValidatorNotFoundException(String string) {
			super(string);
		}

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	
	}
	
	public static String getValidatorLabel(Validator validator) {
		String label= validatorMap.inverse().get(validator);
		if(label==null){
			return validator.getName();
		}else{
			return label;
		}
	}
	/**
	 * use lower case inside
	 * @param key
	 * @return
	 */
	public static Validator getValidator(String key)throws ValidatorNotFoundException {
		if(validatorMap==null){
			init();
		}
		if(key==null){
			throw new ValidatorNotFoundException("null validator key");
		}
		Validator v= validatorMap.get(key.toLowerCase());
		if(v==null){
			throw new ValidatorNotFoundException("null validator for "+key);
		}
		return v;
	}
}
