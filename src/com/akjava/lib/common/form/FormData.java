package com.akjava.lib.common.form;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.akjava.lib.common.form.FormFieldDataDto.OptionLabelToValueFunction;
import com.akjava.lib.common.form.FormFieldDataDto.OptionValueToLabelFunction;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

public class FormData {
private List<Relation> childrens=new ArrayList<Relation>();
public List<Relation> getChildrens() {
	return childrens;
}
public List<Relation> getParents() {
	return parents;
}
public synchronized void addChildren(Relation relation){
	childrens.add(relation);
}
public synchronized void addParent(Relation parent){
	parents.add(parent);
}

private List<Relation> parents=new ArrayList<Relation>();
private String name;
private String className;
private String description;
private boolean adminOnly;

private int pageSize=10;
private int adminPageSize=10;
private int subPageSize=5;

private String subPageOrder;

public int getSubPageSize() {
	return subPageSize;
}
public void setSubPageSize(int subPageSize) {
	this.subPageSize = subPageSize;
}
public String getSubPageOrder() {
	return subPageOrder;
}
public void setSubPageOrder(String subPageOrder) {
	this.subPageOrder = subPageOrder;
}

private String adminPageOrder;//if null used cdate if exists,otherwise use first one;usually it is id
public int getPageSize() {
	return pageSize;
}
public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
}
public int getAdminPageSize() {
	return adminPageSize;
}
public void setAdminPageSize(int adminPageSize) {
	this.adminPageSize = adminPageSize;
}
public String getAdminPageOrder() {
	return adminPageOrder;
}
public void setAdminPageOrder(String adminPageOrder) {
	this.adminPageOrder = adminPageOrder;
}
public String getPageOrder() {
	return pageOrder;
}
public void setPageOrder(String pageOrder) {
	this.pageOrder = pageOrder;
}

private String pageOrder;//if null used cdate if exists,otherwise use first one;usually it is id

public boolean isAdminOnly() {
	return adminOnly;
}
public void setAdminOnly(boolean adminOnly) {
	this.adminOnly = adminOnly;
}
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

public void addFormFieldData(FormFieldData fdata){
	if(formFieldDatas==null){
		formFieldDatas=new ArrayList<FormFieldData>();			
	}
	formFieldDatas.add(fdata);
	fdata.setParent(this);
}


/*
public void setFormFieldDatas(List<FormFieldData> formFieldDatas) {
	this.formFieldDatas = formFieldDatas;
}
*/

public boolean isChecked(String key,String value){
	//
	return false;
}

public FormFieldData getFieldData(String key){
	for(FormFieldData fdata:formFieldDatas){
		if(fdata.getKey().equals(key)){
			return fdata;
		}
	}
	return null;
}

public FormFieldData getIdFieldData(){
	for(FormFieldData fdata:getFormFieldDatas()){
		if(fdata.getType()==FormFieldData.TYPE_ID){
			return fdata;
		}
		//TODO support String ID
	}
	return null;
}

@Override
public String toString() {
	
	String fields="";
	if(getFormFieldDatas()!=null){
		for(FormFieldData fdata:getFormFieldDatas()){
			fields+="<"+fdata.toString()+">\n";
		}
	}
	
	String out=Objects.toStringHelper(this).add("name", name).add("className", className).add("description", description).add("adminOnly", ""+isAdminOnly())
			.add("fields", "\n"+fields+"\n").toString();
	return out;
}
//for select & checkbox but it is too complex
public String getLabelText(String key,String value){

	for(FormFieldData fdata:getFormFieldDatas()){
		if(fdata.getKey().equals(key)){
			if(fdata.getType()== FormFieldData.TYPE_SELECT_SINGLE ||fdata.getType()== FormFieldData.TYPE_SELECT_MULTI ){
				OptionValueToLabelFunction function=new OptionValueToLabelFunction(fdata.getOptionValues());
				
				List<String> values=Lists.newArrayList(value.split(","));
				
				return FormDataDto.commaJoiner.join(Lists.transform(values, function));
				
			}else if(fdata.getType()==FormFieldData.TYPE_CHECK){
				if(fdata.getOptionValues()==null){
					return value;//value must be "on"
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


//only for select or select_multi (check has no label)

public String getValueByLabel(String key,String label){
	for(FormFieldData fdata:getFormFieldDatas()){
		if(fdata.getKey().equals(key)){
			if(fdata.getType()== FormFieldData.TYPE_SELECT_SINGLE ||fdata.getType()== FormFieldData.TYPE_SELECT_MULTI ){
				OptionLabelToValueFunction function=new OptionLabelToValueFunction(fdata.getOptionValues());
				List<String> values=Lists.newArrayList(label.split(","));
				
				return FormDataDto.commaJoiner.join(Lists.transform(values, function));
			}
		}
	}
	return label;
}

}
