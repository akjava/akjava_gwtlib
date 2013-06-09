package com.akjava.lib.common.tag;

public class LabelAndValue {
public LabelAndValue(String value){
	this(null,value,false);
}
public LabelAndValue(String label,String value){
	this(label,value,false);
}
public LabelAndValue(String label, String value,boolean selected) {
	this.label=label;
	this.value=value;
	this.selected=selected;
}
private boolean selected;
public boolean isSelected() {
	return selected;
}
public void setSelected(boolean selected) {
	this.selected = selected;
}
private String label;
private String value;
public String getLabel() {
	return label;
}
public void setLabel(String label) {
	this.label = label;
}
public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}

public String toOption(){
	String option="<option ";
	String outValue=value;
	String selectValue="";
	if(selected){
		selectValue=" selected";
	}
	if(outValue.indexOf('"')!=-1){
		outValue=outValue.replace("\"", "&quot;");
	}
	if(label!=null){
		option+="\""+outValue+"\"";
		option+=selectValue+">";
		option+=label;
	}else{
		option+=selectValue+">"+value;
	}
	return option;
}

}
