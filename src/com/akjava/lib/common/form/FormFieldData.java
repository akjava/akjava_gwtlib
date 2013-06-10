package com.akjava.lib.common.form;

import java.util.ArrayList;
import java.util.List;

import com.akjava.lib.common.tag.LabelAndValue;
import com.google.common.base.Joiner;

public class FormFieldData {
private String name;
private String key;
private int type;
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
private String defaultValue;

public String getDefaultValue() {
	return defaultValue;
}
public void setDefaultValue(String defaultValue) {
	this.defaultValue = defaultValue;
}
private boolean createAuto;
private List<LabelAndValue> optionValues=new ArrayList<LabelAndValue>();

private List<Validator> validators=new ArrayList<Validator>();
private String placeHolder;
private String comment;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getKey() {
	return key;
}
public void setKey(String key) {
	this.key = key;
}


public boolean isCreateAuto() {
	return createAuto;
}
public void setCreateAuto(boolean createAuto) {
	this.createAuto = createAuto;
}
public List<LabelAndValue> getOptionValues() {
	return optionValues;
}
public void setOptionValues(List<LabelAndValue> optionValues) {
	this.optionValues = optionValues;
}
public List<Validator> getValidators() {
	return validators;
}
public void setValidators(List<Validator> validators) {
	this.validators = validators;
}
public String getPlaceHolder() {
	return placeHolder;
}
public void setPlaceHolder(String placeHolder) {
	this.placeHolder = placeHolder;
}
public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}
public static final int TYPE_TEXT_SHORT=0;
public static final int TYPE_TEXT_LONG=1;
public static final int TYPE_ID=2;
public static final int TYPE_CHECK=3;
public static final int TYPE_SELECT_SINGLE=4;
public static final int TYPE_SELECT_MULTI=5;
public static final int TYPE_CREATE_DATE=6;
public static final int TYPE_CREATE_USER=7;

public static String getTypeLabel(int value){
	switch(value){	
	case 1:
	return "text_long";

	case 2:
	return "id";
	case 3:
	return "check";
	
	case 4:
	return "select_single";

	case 5:
	return "select_multi";

	case 6:
	return "create_date";

	case 7:
	return "create_user";

	default:
		return "text_short";
	}
}
public static int getTypeByLabel(String v){
	int type=0;
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
	return type;
}

public String toString(){
	return Joiner.on(";").withKeyValueSeparator("=").useForNull("null").join(FormFieldDataDto.formDataToMap(this));
}
}
