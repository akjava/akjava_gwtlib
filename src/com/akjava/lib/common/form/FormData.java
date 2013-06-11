package com.akjava.lib.common.form;

import java.util.List;

import com.akjava.lib.common.form.FormFieldDataDto.OptionLabelToValueFunction;
import com.google.common.collect.Lists;

public class FormData {
private String name;
private String className;
private String description;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getClassName() {
	return className;
}
public void setClassName(String className) {
	this.className = className;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}

private List<FormFieldData> formFieldDatas;
public List<FormFieldData> getFormFieldDatas() {
	return formFieldDatas;
}
public void setFormFieldDatas(List<FormFieldData> formFieldDatas) {
	this.formFieldDatas = formFieldDatas;
}

public boolean isChecked(String key,String value){
	//
	return false;
}

//for select & checkbox but it is too complex
public String getLabelText(String key,String value){

	for(FormFieldData fdata:getFormFieldDatas()){
		if(fdata.getKey().equals(key)){
			if(fdata.getType()== FormFieldData.TYPE_SELECT_SINGLE ||fdata.getType()== FormFieldData.TYPE_SELECT_MULTI ){
				OptionLabelToValueFunction function=new OptionLabelToValueFunction(fdata.getOptionValues());
				
				List<String> values=Lists.newArrayList(value.split(","));
				return FormDataDto.commaJoiner.join(Lists.transform(values, function));
				
			}else if(fdata.getType()==FormFieldData.TYPE_CHECK){
				if(fdata.getOptionValues()==null){
					return value;//value must be checked
				}else{
					String checkedPrintValue=null;
					String uncheckedPrintValue=null;
					if(fdata.getOptionValues().size()>0){
						checkedPrintValue=fdata.getOptionValues().get(0).getPrintValue();
					}
					if(fdata.getOptionValues().size()>1){
						uncheckedPrintValue=fdata.getOptionValues().get(1).getPrintValue();
					}
					
					String lower=value.toLowerCase();
					if(value!=null && "on".equals(lower)){//on is defalt check value
						if(checkedPrintValue!=null){
							return checkedPrintValue;
						}else{
							return value;
						}
						
					}else{
						if(fdata.getOptionValues().size()>0){
							if(fdata.getOptionValues().get(0).getValue().equals(value)){
								return checkedPrintValue;
							}else{
								if(fdata.getOptionValues().size()>1){
									return uncheckedPrintValue;
								}
							}
							
							
							
							
						}
						
					}
					return value;//maybe some mistake
				}
				//
			}else{
				//simply return value another case
				return value;
			}
		}
	}	
	return null;
}
//TODO
/*//to store value
public String getValueByLabel(String key,String label){
	//for multiple values
}
*/
}
