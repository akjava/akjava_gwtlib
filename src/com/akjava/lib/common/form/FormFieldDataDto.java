package com.akjava.lib.common.form;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.akjava.lib.common.form.FormDataDto.FormDataToCsvFunction;
import com.akjava.lib.common.functions.LabelAndValueDto;
import com.akjava.lib.common.functions.SplitLineFunction;
import com.google.common.base.Function;
import com.google.common.base.Joiner;


public class FormFieldDataDto {
	private static final CsvToFormFieldFunction csvToFormFieldFunctionOptionWithNumber=new CsvToFormFieldFunction(true);
	private static final CsvToFormFieldFunction csvToFormFieldFunction=new CsvToFormFieldFunction(false);
	//TODO convert list
	
	public  static CsvToFormFieldFunction getCsvToFormFieldFunction(boolean withNumber){
		if(withNumber){
			return csvToFormFieldFunctionOptionWithNumber;
		}else{
			return csvToFormFieldFunction;
		}
	}

	
	public static Map<String,String> formDataToMap(FormFieldData data){
		Map<String,String> hashMap=new LinkedHashMap<String, String>();
		hashMap.put("name", data.getName());
		hashMap.put("key", data.getKey());
		hashMap.put("type", FormFieldData.getTypeLabel(data.getType()));
		hashMap.put("optionValues", LabelAndValueDto.labelAndValueToString(data.getOptionValues()));
		hashMap.put("defaultValue", data.getDefaultValue());
		hashMap.put("createAuto", data.isCreateAuto()?"yes":"");
		hashMap.put("validators", ValidatorDto.validatorListToNamesLine(data.getValidators()));
		hashMap.put("placeHolder", data.getPlaceHolder());
		hashMap.put("comment", data.getComment());
		return hashMap;
	}
	
	public static FormFieldToCsvFunction getFormFieldToCsvFunction(){
		return FormFieldToCsvFunction.INSTANCE;
	}
	
	public enum  FormFieldToCsvFunction implements Function<FormFieldData,String >{
		INSTANCE;
		@Override
		public String apply(FormFieldData data) {
			
			Map<String,String> map=formDataToMap(data);
			return Joiner.on("\t").useForNull("").join(map.values());
		}
		
	}
	 static class CsvToFormFieldFunction implements Function<String, FormFieldData>{
		private boolean optionWithNumber=true;
		public CsvToFormFieldFunction(boolean optionWithNumber){
			this.optionWithNumber=optionWithNumber;
		}
		
		/*
		public void setOptionWithNumber(boolean optionWithNumber) {
			this.optionWithNumber = optionWithNumber;
		}*/
		@Override
		public FormFieldData apply(String value) {
			FormFieldData data=new FormFieldData();
			List<String> csvs=new SplitLineFunction(true, false).apply(value);
			
			if(csvs.size()>0){
				data.setName(csvs.get(0));//String
			}
			if(csvs.size()>1){
				data.setKey(csvs.get(1));//String
			}
			
			if(csvs.size()>2){
				int type=0;
				String v=csvs.get(2).toLowerCase();
				if(v.equals("text_short")){
					type=0;
				}else if(v.equals("text_long")){
					type=1;
				}else if(v.equals("id")){
					type=2;
				}else if(v.equals("check")){
					type=3;
				}else if(v.equals("select_single")){
					type=4;
				}else if(v.equals("select_multi")){
					type=5;
				}else if(v.equals("create_date")){
					type=6;
				}else if(v.equals("create_user")){
					type=7;
				}
				data.setType(type);
			}
			
			if(csvs.size()>3){
				String optionText=csvs.get(3);
				if(!optionText.isEmpty()){
					if(optionWithNumber){
						data.setOptionValues(LabelAndValueDto.lineToLabelAndValuesWithNumber(optionText));
					}else{
						data.setOptionValues(LabelAndValueDto.lineToLabelAndValues(optionText));
					}
				}
				
				
			}
			
			if(csvs.size()>4){
				data.setDefaultValue(csvs.get(4));
			}
			
			if(csvs.size()>5){
				String isAuto=csvs.get(5).toLowerCase();
				if(isAuto.equals("true") || isAuto.equals("yes")){
					data.setCreateAuto(true);
				}
			}
			
			//TODO parse validator
			if(csvs.size()>6){
				String validatorText=csvs.get(6);
				if(!validatorText.isEmpty()){
					data.setValidators(ValidatorDto.namesLineToValidatorList(validatorText));
				}
				
			}
			//
			
			if(csvs.size()>7){
				data.setPlaceHolder(csvs.get(7));
			}
			
			if(csvs.size()>8){
				data.setComment(csvs.get(8));
			}
			
			return data;
		}
		
	}
	public static String formFieldToCsv(FormFieldData field) {
		// TODO Auto-generated method stub
		return getFormFieldToCsvFunction().apply(field);
	}


	public static FormFieldData csvToFormField(String collect) {
		// TODO Auto-generated method stub
		return getCsvToFormFieldFunction(true).apply(collect);
	}
}
