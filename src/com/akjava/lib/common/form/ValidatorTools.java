package com.akjava.lib.common.form;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ValidatorTools {
	private ValidatorTools(){}
public  static LinkedHashMap<String,Validator> validatorMap=null;

	private static void init(){
		validatorMap=new LinkedHashMap<String, Validator>();
		validatorMap.put(StaticValidators.VALIDATOR_NOT_EMPTY.toLowerCase(), StaticValidators.notEmptyValidator());
		validatorMap.put(StaticValidators.VALIDATOR_ASCII_NUMBER.toLowerCase(), StaticValidators.asciiNumberOnly());
		validatorMap.put(StaticValidators.VALIDATOR_ASCII_NUMBER_AND_CHAR.toLowerCase(), StaticValidators.asciiNumberAndCharOnly());
		validatorMap.put(StaticValidators.VALIDATOR_HANKAKU_KANA.toLowerCase(), StaticValidators.hankakuKana());
		validatorMap.put(StaticValidators.VALIDATOR_HIRAGANA.toLowerCase(), StaticValidators.hiragana());
		
		//TODO better name
		validatorMap.put("between", Validators.betweenStringSize(6, 12));
		
		validatorMap.put("less8", Validators.maxStringSize(8));
		validatorMap.put("less8b", Validators.maxStringByteSize(8));
		
		validatorMap.put("less64", Validators.maxStringSize(64));
		validatorMap.put("less128", Validators.maxStringSize(128));
		validatorMap.put("less256", Validators.maxStringSize(256));
		
		validatorMap.put("less500", Validators.maxStringSize(500));
		validatorMap.put("less1m", Validators.maxStringByteSize(1024*1024));
	}

	/**
	 * use lower case inside
	 * @param key
	 * @return
	 */
	public static Validator getValidator(String key){
		if(validatorMap==null){
			init();
		}
		if(key==null){
			return null;
		}
		return validatorMap.get(key.toLowerCase());
	}
}
