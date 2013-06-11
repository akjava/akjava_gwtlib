package com.akjava.lib.common.form;

import java.util.List;

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

}
