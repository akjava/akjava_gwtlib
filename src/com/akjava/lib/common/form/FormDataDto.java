package com.akjava.lib.common.form;

import java.util.List;

import com.akjava.lib.common.functions.LabelAndValueDto;
import com.akjava.lib.common.functions.SplitLineFunction;
import com.google.common.base.Function;


public class FormDataDto {
	
	//TODO convert list
	
	public static FormData csvToFormData(String singleLine){
		return new CsvToFormDataFunction().apply(singleLine);
	}
	
	public static class CsvToFormDataFunction implements Function<String, FormData>{
		private boolean optionWithNumber=true;
		public boolean isOptionWithNumber() {
			return optionWithNumber;
		}
		public void setOptionWithNumber(boolean optionWithNumber) {
			this.optionWithNumber = optionWithNumber;
		}
		@Override
		public FormData apply(String value) {
			FormData data=new FormData();
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
