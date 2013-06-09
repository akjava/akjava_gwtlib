package com.akjava.lib.common.form;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.akjava.lib.common.functions.LabelAndValueDto;
import com.akjava.lib.common.functions.SplitLineFunction;
import com.google.common.base.Function;
import com.google.common.base.Objects;


public class FormFieldDataDto {
	
	//TODO convert list
	
	public static FormFieldData csvToFormData(String singleLine){
		return new CsvToFormDataFunction().apply(singleLine);
	}
	public static Map<String,String> formDataToMap(FormFieldData data){
		Map<String,String> hashMap=new LinkedHashMap<String, String>();
		hashMap.put("name", data.getName());
		hashMap.put("key", data.getKey());
		hashMap.put("type", FormFieldData.getTypeLabel(data.getType()));
		hashMap.put("optionValues", LabelAndValueDto.labelAndValueToString(data.getOptionValues()));
		hashMap.put("defaultValue", data.getDefaultValue());
		hashMap.put("createAuto", ""+data.isCreateAuto());
		//TODO add validators
		hashMap.put("placeHolder", data.getPlaceHolder());
		hashMap.put("comment", data.getComment());
		/**
		 * 	return Objects.toStringHelper(this).add("name", name).add("key",key).add("type", getTypeLabel(type)+"["+type+"]")
			.add("optionValues",LabelAndValueDto.labelAndValueToString(optionValues))
			.add("defaultValue", defaultValue).add("createAuto",""+createAuto)
			//TODO add validators
			.add("placeHolder", placeHolder).add("comment", comment)
			.toString();
		 */
		return hashMap;
	}
	
	public static class CsvToFormDataFunction implements Function<String, FormFieldData>{
		private boolean optionWithNumber=true;
		public boolean isOptionWithNumber() {
			return optionWithNumber;
		}
		public void setOptionWithNumber(boolean optionWithNumber) {
			this.optionWithNumber = optionWithNumber;
		}
		@Override
		public FormFieldData apply(String value) {
			FormFieldData data=new FormFieldData();
			List<String> csvs=new SplitLineFunction(true, true).apply(value);
			
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
				if(optionWithNumber){
					data.setOptionValues(LabelAndValueDto.lineToLabelAndValuesWithNumber(optionText));
				}else{
					data.setOptionValues(LabelAndValueDto.lineToLabelAndValues(optionText));
				}
				
			}
			
			if(csvs.size()>4){
				data.setDefaultValue(csvs.get(4));
			}
			
			if(csvs.size()>5){
				String isAuto=csvs.get(5);
				if(isAuto.equals("true")){
					data.setCreateAuto(true);
				}
			}
			
			//TODO parse validator
			
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
}
