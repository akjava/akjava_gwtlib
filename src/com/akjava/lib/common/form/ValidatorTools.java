package com.akjava.lib.common.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.akjava.lib.common.param.Parameter;
import com.akjava.lib.common.param.ParameterUtils;
import com.akjava.lib.common.utils.ValuesUtils;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class ValidatorTools {

	private ValidatorTools(){}
//private  static BiMap<String,Validator> bimap=null;
	/*
public static BiMap<String,Validator> getValidatorMap(){
	if(bimap==null){
		init();
	}
	return bimap;
}
*/


public static Map<String,Validator> validatorMap=new HashMap<String, Validator>();
public static Validator getValidator(String line) throws ValidatorNotFoundException{
	Validator validator=validatorMap.get(line);
	if(validator!=null){
		return validator;
	}
	Parameter param=ParameterUtils.parse(line);
	String name=param.getName().toLowerCase();
	if(name.equals(StaticValidators.VALIDATOR_NOT_EMPTY.toLowerCase())){
		return StaticValidators.notEmptyValidator();
	}
	else if(name.equals(StaticValidators.VALIDATOR_ASCII_NUMBER.toLowerCase())){
		return StaticValidators.asciiNumberOnly();
	}
	else if(name.equals(StaticValidators.VALIDATOR_ASCII_NUMBER_AND_CHAR.toLowerCase())){
		return StaticValidators.asciiNumberAndCharOnly();
	}
	else if(name.equals(StaticValidators.VALIDATOR_ASCII_NUMBER_AND_CHAR_AND_UNDERBAR.toLowerCase())){
		return StaticValidators.asciiNumberAndCharAndUnderbarOnly();
	}
	else if(name.equals(StaticValidators.VALIDATOR_HANKAKU_KANA.toLowerCase())){
		return StaticValidators.hankakuKana();
	}
	else if(name.equals(StaticValidators.VALIDATOR_HIRAGANA.toLowerCase())){
		return StaticValidators.hiragana();
	}
	else if(name.equals(StaticValidators.VALIDATOR_ASCII_CHAR.toLowerCase())){
		return StaticValidators.asciiCharOnly();
	}
	else if(name.equals(StaticValidators.VALIDATOR_START_ASCII_CHAR.toLowerCase())){
		return StaticValidators.startAsciiChar();
	}else if(name.equals(StaticValidators.VALIDATOR_DECIMAL_NUMBER.toLowerCase())){
		return StaticValidators.decimalNumber();
	}else if(name.equals(StaticValidators.VALIDATOR_INTEGER_NUMBER.toLowerCase())){
		return StaticValidators.integerNumber();
	}else if(name.equals(Validators.VALIDATOR_RANGE_NUMBER.toLowerCase())){
		
		Double min=null;
		Double max=null;
		if(param.size()>0){
		try{
		min=Double.parseDouble(param.get(0));
		}catch (Exception e) {
			//use null;
		}
		if(param.size()>1){
		try{
			min=Double.parseDouble(param.get(0));
			}catch (Exception e) {
				//use null;
			}
		}
		}
		return Validators.rangedNumber(min, max);
	}
	else if(name.equals(Validators.VALIDATOR_MAX_STRING_SIZE)){
		if(param.size()!=1){
			throw new ValidatorNotFoundException(name+" need size attribute:"+line);
		}else{
			int size;
			try{
				size=Integer.parseInt(param.get(0));
			}catch (Exception e) {
				throw new ValidatorNotFoundException(name+" invalid size attribute:"+line);
			}
			validator=Validators.maxStringSize(size);
		}
	}
	else if(name.equals(Validators.VALIDATOR_MAX_STRING_BYTE_SIZE)){
		if(param.size()!=1){
			throw new ValidatorNotFoundException(name+" need size attribute:"+line);
		}else{
			int size;
			try{
				size=ValuesUtils.parseByte(param.get(0));
			}catch (Exception e) {
				throw new ValidatorNotFoundException(name+" invalid size attribute:"+line);
			}
			validator=Validators.maxStringByteSize(size);
		}
	}
	else if(name.equals(Validators.VALIDATOR_BETWEEN_STRING_SIZE)){
		if(param.size()!=2){
			throw new ValidatorNotFoundException(name+" need min and max attribute:"+line);
		}else{
			int min;
			int max;
			try{
				min=Integer.parseInt(param.get(0));
				max=Integer.parseInt(param.get(1));
			}catch (Exception e) {
				throw new ValidatorNotFoundException(name+" invalid size attribute:"+line);
			}
			validator=Validators.betweenStringSize(min,max);
		}
	}else if(name.equals(Validators.VALIDATOR_AVAIABLE_VALUE_ONLY)){
		if(param.size()<2){
			throw new ValidatorNotFoundException(name+" need at leaset 2 attributes:"+line);
		}else{
			boolean bool;
			try{
				String v=param.get(0).toLowerCase();
				if(v.equals("true")){
					bool=true;
				}else if(v.equals("false")){
					bool=false;
				}else{
					throw new Exception();
				}
			}catch (Exception e) {
				throw new ValidatorNotFoundException(name+" invalid boolean attribute:"+line);
			}
			List<String> values=new ArrayList<String>();
			for(int i=1;i<param.size();i++){
				values.add(param.get(i));
			}
			validator=Validators.avaiableValueOnly(values, bool);
		}
	}
	
	
	if(validator!=null){
	validatorMap.put(line, validator);
	return validator;
	}
	
	throw new ValidatorNotFoundException("not found:"+line);
}

public static boolean contain(List<Validator> validators,String checkName){
	if(checkName==null || checkName.isEmpty()){
		return false;
	}
	checkName=checkName.toLowerCase();
	for(Validator validator:validators){
		String name=validator.getName().toLowerCase();
		if(name.equals(checkName)){
			return true;
		}
	}
	
	return false;
}

public static boolean hasLimitValidator(List<Validator> validators){
	
	for(Validator validator:validators){
		String name=validator.getName().toLowerCase();
		if(name.equals("max")||name.equals("maxb")|| name.equals("between")){
			return true;
		}
	}
	
	return false;
}
public static boolean hasNumberValidator(List<Validator> validators){
	
	for(Validator validator:validators){
		String name=validator.getName().toLowerCase();
		if(name.equals("asciiNumber")||name.equals("integerNumber")|| name.equals("range")||name.equals("asciiNumber")){
			return true;
		}
	}
	
	return false;
}
/*
	private static void init(){
		bimap=HashBiMap.create();
		bimap.put(StaticValidators.VALIDATOR_NOT_EMPTY.toLowerCase(), StaticValidators.notEmptyValidator());
		bimap.put(StaticValidators.VALIDATOR_ASCII_NUMBER.toLowerCase(), StaticValidators.asciiNumberOnly());
		bimap.put(StaticValidators.VALIDATOR_ASCII_NUMBER_AND_CHAR.toLowerCase(), StaticValidators.asciiNumberAndCharOnly());
		bimap.put(StaticValidators.VALIDATOR_ASCII_NUMBER_AND_CHAR_AND_UNDERBAR.toLowerCase(), StaticValidators.asciiNumberAndCharAndUnderbarOnly());
		
		bimap.put(StaticValidators.VALIDATOR_HANKAKU_KANA.toLowerCase(), StaticValidators.hankakuKana());
		bimap.put(StaticValidators.VALIDATOR_HIRAGANA.toLowerCase(), StaticValidators.hiragana());
		
		bimap.put(StaticValidators.VALIDATOR_ASCII_CHAR.toLowerCase(), StaticValidators.asciiCharOnly());
		bimap.put(StaticValidators.VALIDATOR_START_ASCII_CHAR.toLowerCase(), StaticValidators.startAsciiChar());
		
		
		//TODO should support name contain args like msize(12),bet(6:12)
		bimap.put("between", Validators.betweenStringSize(6, 12));
		
		bimap.put("less8", Validators.maxStringSize(8));
		bimap.put("less8b", Validators.maxStringByteSize(8));
		
		bimap.put("less64", Validators.maxStringSize(64));
		bimap.put("less128", Validators.maxStringSize(128));
		bimap.put("less256", Validators.maxStringSize(256));
		
		bimap.put("less500", Validators.maxStringSize(500));
		bimap.put("less1m", Validators.maxStringByteSize(1024*1024));
	}*/

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
		return validator.toString();
		/*
		String label= bimap.inverse().get(validator);
		if(label==null){
			return validator.getName();
		}else{
			return label;
		}
		*/
	}
	/**
	 * use lower case inside
	 * @param key
	 * @return
	 */
	/*
	public static Validator getValidator(String key)throws ValidatorNotFoundException {
		if(bimap==null){
			init();
		}
		if(key==null){
			throw new ValidatorNotFoundException("null validator key");
		}
		Validator v= bimap.get(key.toLowerCase());
		if(v==null){
			throw new ValidatorNotFoundException("null validator for "+key);
		}
		return v;
	}*/
}
