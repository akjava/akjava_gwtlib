package com.akjava.lib.common.form;

import java.util.List;

import com.akjava.lib.common.form.ValidatorTools.ValidatorNotFoundException;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class ValidatorDto {
private static NameToValidatorFunction nameToValidatorFunction=new NameToValidatorFunction();
private static ValidatorToNameFunction validatorToNameFunction=new ValidatorToNameFunction();


	public static List<String> validatorListToNameList(List<Validator> validators){
		return Lists.transform(validators, validatorToNameFunction);
	}

	public static String validatorListToNamesLine(List<Validator> validators){
		return Joiner.on(',').join(Lists.transform(validators, validatorToNameFunction));
	}
	

	public static List<Validator> namesLineToValidatorList(String names){
	return nameListToValidatorList(Lists.newArrayList(names.split(",")));
	}

	public static List<Validator> nameListToValidatorList(List<String> names){
		return Lists.transform(names, nameToValidatorFunction);
	}
	
	public static class NameToValidatorFunction implements Function<String,Validator>{
		@Override
		public Validator apply(String value) {
			try {
				return ValidatorTools.getValidator(value);
			} catch (ValidatorNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		
	}
	
	public static class ValidatorToNameFunction implements Function<Validator,String>{
		@Override
		public String apply(Validator value) {
			String name=ValidatorTools.validatorMap.inverse().get(value);
			if(name==null){
				//not registed
				name=value.getName();
			}
			return name;
		}
		
	}
}
