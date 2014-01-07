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

public static final int TYPE_INTEGER=11;//int
public static final int TYPE_POINT=12;//a decimal point
public static final int TYPE_HIDDEN=13;


public static final String VALUE_TYPE_TEXT_SHORT="text";

public static final String VALUE_TYPE_TEXT_LONG="text_long";
public static final String VALUE_TYPE_ID="id";
public static final String VALUE_TYPE_CHECK="check";
public static final String VALUE_TYPE_SELECT_SINGLE="select";
public static final String VALUE_TYPE_SELECT_MULTI="select_multi";
public static final String VALUE_TYPE_CREATE_DATE="create_date";
public static final String VALUE_TYPE_CREATE_USER="create_user";

public static final String VALUE_TYPE_MODIFIED_DATE="modified_date";
public static final String VALUE_TYPE_MODIFIED_USER="modified_user";

public static final String VALUE_TYPE_NUMBER="number";//long

public static final String VALUE_TYPE_INTEGER="int";
public static final String VALUE_TYPE_POINT="point";
public static final String VALUE_TYPE_HIDDEN="hidden";

public static String getTypeByNumber(int value){
	switch(value){	
	case TYPE_TEXT_LONG:
	return VALUE_TYPE_TEXT_LONG;

	case TYPE_ID:
	return VALUE_TYPE_ID;
	case TYPE_CHECK:
	return VALUE_TYPE_CHECK;
	
	case TYPE_SELECT_SINGLE:
	return VALUE_TYPE_SELECT_SINGLE;

	case TYPE_SELECT_MULTI:
	return VALUE_TYPE_SELECT_MULTI;

	case TYPE_CREATE_DATE:
	return VALUE_TYPE_CREATE_DATE;

	case TYPE_CREATE_USER:
	return VALUE_TYPE_CREATE_USER;
	
	case TYPE_MODIFIED_DATE:
		return VALUE_TYPE_MODIFIED_DATE;
		
	case TYPE_MODIFIED_USER:
		return VALUE_TYPE_MODIFIED_USER;
	case TYPE_NUMBER:
		return VALUE_TYPE_NUMBER;
	case TYPE_INTEGER:
		return VALUE_TYPE_INTEGER;
	case TYPE_POINT:
		return VALUE_TYPE_POINT;
	case TYPE_HIDDEN:
		return VALUE_TYPE_HIDDEN;	
	default:
		return VALUE_TYPE_TEXT_SHORT;
	}
}
public static final List<String> TYPES=Lists.newArrayList(VALUE_TYPE_TEXT_SHORT,"text_short",
		VALUE_TYPE_TEXT_LONG,VALUE_TYPE_ID,VALUE_TYPE_CHECK,
		VALUE_TYPE_SELECT_SINGLE,"select_single",
		VALUE_TYPE_SELECT_MULTI,VALUE_TYPE_CREATE_DATE,VALUE_TYPE_CREATE_USER,VALUE_TYPE_MODIFIED_DATE,VALUE_TYPE_MODIFIED_USER,VALUE_TYPE_NUMBER,"long",VALUE_TYPE_INTEGER,VALUE_TYPE_POINT,VALUE_TYPE_HIDDEN);
public static int getTypeByLabel(String v){
	int type=0;
	if(v.equals(VALUE_TYPE_TEXT_SHORT)||v.equals("text_short")){
		type=TYPE_TEXT_SHORT;
	}else if(v.equals(VALUE_TYPE_TEXT_LONG)){
		type=TYPE_TEXT_LONG;
	}else if(v.equals(VALUE_TYPE_ID)){
		type=TYPE_ID;
	}else if(v.equals(VALUE_TYPE_CHECK)){
		type=TYPE_CHECK;
	}else if(v.equals(VALUE_TYPE_SELECT_SINGLE)||v.equals("select_single")){
		type=TYPE_SELECT_SINGLE;
	}else if(v.equals(VALUE_TYPE_SELECT_MULTI)){
		type=TYPE_SELECT_MULTI;
	}else if(v.equals(VALUE_TYPE_CREATE_DATE)){
		type=TYPE_CREATE_DATE;
	}else if(v.equals(VALUE_TYPE_CREATE_USER)){
		type=TYPE_CREATE_USER;
	}else if(v.equals(VALUE_TYPE_MODIFIED_DATE)){
		type=TYPE_CREATE_DATE;
	}else if(v.equals(VALUE_TYPE_MODIFIED_USER)){
		type=TYPE_MODIFIED_USER;
	}else if(v.equals(VALUE_TYPE_NUMBER)||v.equals("long")){
		type=TYPE_NUMBER;
	}else if(v.equals(VALUE_TYPE_INTEGER)){
		type=TYPE_INTEGER;
	}else if(v.equals(VALUE_TYPE_POINT)||v.equals("double")){
		type=TYPE_POINT;
	}else if(v.equals(VALUE_TYPE_HIDDEN)){
		type=TYPE_HIDDEN;
	}
	return type;
}
/**
 * text_short not tested so much
 * @param type
 * @return
 */
public static boolean isSupportRelativeOptionType(int type){
	return type==TYPE_SELECT_MULTI||type==TYPE_SELECT_SINGLE||type==TYPE_NUMBER||type==TYPE_TEXT_SHORT;
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
