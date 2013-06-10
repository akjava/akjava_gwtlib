package com.akjava.lib.common.form;

import com.google.common.collect.HashMultimap;

public class FormFieldValidate {

	private HashMultimap<String,Validator> errorValidators=HashMultimap.create();
	
	public FormFieldValidate(){
		errorValidators.put("name", StaticValidators.notEmptyValidator());
		errorValidators.put("id", StaticValidators.notEmptyValidator());
		errorValidators.put("type", StaticValidators.notEmptyValidator());
		
		
		errorValidators.put("id",StaticValidators.startAsciiChar());
	}
	
	public void doCheckError(FormFieldData data){

	}
	
	public void doCheckWarning(FormFieldData data){

	}
}
