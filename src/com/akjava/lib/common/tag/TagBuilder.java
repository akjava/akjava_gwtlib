package com.akjava.lib.common.tag;

import java.util.ArrayList;
import java.util.List;

import com.akjava.lib.common.utils.ValuesUtils;

/*
 * as possible as can simple create html for form
 */
public class TagBuilder {
private TagBuilder(){}

	public static Tag createHidden(String name,String value){
		Tag tag=new Tag("input");
		tag.setSingleTag(true);
		tag.setAttribute("type", "hidden");
		tag.setAttribute("name", name);
		if(value!=null){
		tag.setAttribute("value", value);
		}
		return tag;
	}
	
	public static Tag createText(String name,String value){
		Tag tag=new Tag("input");
		tag.setSingleTag(true);
		tag.setAttribute("type", "text");
		tag.setAttribute("name", name);
		if(value!=null){
		tag.setAttribute("value", value);
		}
		return tag;
	}
	
	public static Tag createTextArea(String name,String value){
		Tag tag=new Tag("textarea");
		
		
		tag.setAttribute("name", name);
		if(value!=null){
		tag.setText(value);
		}
		return tag;
	}
	
	public static Tag createCheckbox(String name,String value,boolean checkd){
		Tag tag=new Tag("input");
		tag.setSingleTag(true);
		tag.setAttribute("type", "checkbox");
		tag.setAttribute("name", name);
		if(value!=null){
		tag.setAttribute("value", value);
		}
		if(checkd){
			tag.setAttribute("checked");
		}
		return tag;
	}
	
	
	//really need?
	public static String createRadioTextByString(String name,List<String> values){
		List<LabelAndValue> lvalues=new ArrayList<LabelAndValue>();
		for(String v:values){
			lvalues.add(new LabelAndValue(v));
		}
		return createRadioText(name,lvalues,null);
	}
	
	public static String createRadioText(String name,List<LabelAndValue> lvalues){
		return createRadioText(name,lvalues,null);
	}
	public static String createRadioText(String name,List<LabelAndValue> lvalues,String joinText){
		if(joinText==null){
			joinText="\n";
		}
		String tagText="";
		for(int i=0;i<lvalues.size();i++){
			LabelAndValue lvalue=lvalues.get(i);
			String tmp=createRadio(name, lvalue).toString();
			tmp+=lvalue.getLabel()!=null?lvalue.getLabel():lvalue.getValue();
			tagText+=tmp;
			if(i!=lvalues.size()-1){
				tagText+=joinText;
			}
		}
		
		return tagText;
	}
	
	//need for multiple radios
	public static String createRadioText(String name,LabelAndValue lvalue){
		String tagText=createRadio(name, lvalue).toString();
		tagText+=lvalue.getLabel()!=null?lvalue.getLabel():lvalue.getValue();
		return tagText;
	}
	
	public static Tag createRadio(String name,LabelAndValue lvalue){
		Tag tag=new Tag("input");
		tag.setSingleTag(true);
		tag.setAttribute("type", "radio");
		tag.setAttribute("name", name);
		
		tag.setAttribute("value", lvalue.getValue());
		
		if(lvalue.isSelected()){
			tag.setAttribute("checked");
		}
		return tag;
	}
	public static Tag createSelect(String name,List<LabelAndValue> lvalues,boolean multiple){
		Tag tag=new Tag("select");
		tag.setAttribute("name", name);
		if(multiple){
			tag.setAttribute("multiple");
		}
		
		String options="";
		if(lvalues!=null){
		for(LabelAndValue lvalue:lvalues){
			options+=lvalue.toOption()+"\n";
		}
		options=ValuesUtils.chomp(options);
		tag.setText(options);
		}
		
		return tag;
	}
}
