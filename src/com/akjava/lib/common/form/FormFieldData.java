package com.akjava.lib.common.form;

import java.util.ArrayList;
import java.util.List;

import com.akjava.lib.common.param.Parameter;
import com.akjava.lib.common.tag.LabelAndValue;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class FormFieldData {
private String name;
private String key;
private int type;
private FormData parent;
public FormData getParent() {
	return parent;
}
public void setParent(FormData parent) {
	this.parent = parent;
}
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
private List<Modifier> modifiers=new ArrayList<Modifier>();
public List<Modifier> getModifiers() {
	return modifiers;
}
public void setModifiers(List<Modifier> modifiers) {
	this.modifiers = modifiers;
}
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

private String optionText;

public String getOptionText() {
	return optionText;
}
public void setOptionText(String optionText) {
	this.optionText = optionText;
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

public static final int TYPE_MODIFIED_DATE=8;
public static final int TYPE_MODIFIED_USER=9;

public static final int TYPE_NUMBER=10;//long
//TODO future support
public static final int TYPE_INTEGER=11;
public static final int TYPE_DECIMAL=12;

public static String getTypeByNumber(int value){
	switch(value){	
	case TYPE_TEXT_LONG:
	return "text_long";

	case TYPE_ID:
	return "id";
	case TYPE_CHECK:
	return "check";
	
	case TYPE_SELECT_SINGLE:
	return "select";

	case TYPE_SELECT_MULTI:
	return "select_multi";

	case TYPE_CREATE_DATE:
	return "create_date";

	case TYPE_CREATE_USER:
	return "create_user";
	
	case TYPE_MODIFIED_DATE:
		return "modified_date";
		
	case TYPE_MODIFIED_USER:
		return "modified_user";
	case TYPE_NUMBER:
		return "number";
	default:
		return "text";
	}
}
public static final List<String> TYPES=Lists.newArrayList("text","text_short","text_long","id","check","select","select_single","select_multi","create_date","create_user","modified_date","modified_user","number");
public static int getTypeByLabel(String v){
	int type=0;
	if(v.equals("text")||v.equals("text_short")){
		type=TYPE_TEXT_SHORT;
	}else if(v.equals("text_long")){
		type=TYPE_TEXT_LONG;
	}else if(v.equals("id")){
		type=TYPE_ID;
	}else if(v.equals("check")){
		type=TYPE_CHECK;
	}else if(v.equals("select")||v.equals("select_single")){
		type=TYPE_SELECT_SINGLE;
	}else if(v.equals("select_multi")){
		type=TYPE_SELECT_MULTI;
	}else if(v.equals("create_date")){
		type=TYPE_CREATE_DATE;
	}else if(v.equals("create_user")){
		type=TYPE_CREATE_USER;
	}else if(v.equals("modified_date")){
		type=TYPE_CREATE_DATE;
	}else if(v.equals("modified_user")){
		type=TYPE_MODIFIED_USER;
	}else if(v.equals("number")){
		type=TYPE_NUMBER;
	}
	return type;
}
public static boolean isSelectionType(int type){
	return type==TYPE_SELECT_MULTI||type==TYPE_SELECT_SINGLE;
}
public boolean isRelativeField(){
	Parameter parameter=FormDataDto.parseParameter(getOptionText());
	return parameter!=null;
}

public String toString(){
	return Joiner.on(";").withKeyValueSeparator("=").useForNull("null").join(FormFieldDataDto.formDataToMap(this));
}
}
