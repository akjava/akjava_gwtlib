package com.akjava.lib.common.form;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;

public class FormFieldValidate {

	private HashMultimap<String,Validator> errorValidators=HashMultimap.create();
	
	public FormFieldValidate(){
		errorValidators.put("name", StaticValidators.notEmptyValidator());
		errorValidators.put("key", StaticValidators.notEmptyValidator());
		errorValidators.put("type", StaticValidators.notEmptyValidator());
		
		
		errorValidators.put("key",StaticValidators.startAsciiChar());
		errorValidators.put("key",StaticValidators.asciiNumberAndCharAndUnderbarOnly());
		
		errorValidators.put("type", Validators.avaiableValueOnly(FormFieldData.TYPES, false));
		
		errorValidators.put("createAuto", Validators.avaiableValueOnly(Lists.newArrayList("yes","","no"), false));
		
		//errorValidators.put("validators", Validators.avaiableValueOnly(ValidatorTools.getValidatorMap().keySet(), false));
	}
	
	public HashMultimap<String,String> doCheckError(FormFieldData data){
		HashMultimap<String,String> errors=HashMultimap.create();
		Map<String,String> map=FormFieldDataDto.formDataToMap(data);
		for(String key:map.keySet()){
			String value=map.get(key);
			
			Set<Validator> validators=errorValidators.get(key);
			for(Validator validator:validators){
				if(!validator.validate(value)){
					errors.put(key,ValidatorTools.getValidatorLabel(validator));
				}
			}
		}
		return errors;
	}
	
	//FUTURE
	public void doCheckWarning(FormFieldData data){

	}
}
