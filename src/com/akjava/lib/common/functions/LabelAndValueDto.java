package com.akjava.lib.common.functions;

import java.util.ArrayList;
import java.util.List;

import com.akjava.lib.common.tag.LabelAndValue;
import com.akjava.lib.common.utils.ValuesUtils;
import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class LabelAndValueDto {
private LabelAndValueDto(){}
private static final Joiner COMMA_JOINER=Joiner.on(",");
	public static List<LabelAndValue> lineToLabelAndValues(String line){
		String[] csv=line.split(",");
		List<String> values=Lists.newArrayList(csv);
		return Lists.newArrayList(Lists.transform(values, new TextToLabelAndValueFunction()));
	}
	
	public static List<LabelAndValue> lineToLabelAndValuesWithNumber(String line){
		String[] csv=line.split(",");
		List<String> values=Lists.newArrayList(csv);
		int index=0;
		for(int i=0;i<values.size();i++){
			String value=values.get(i);
			if(value.indexOf(":")==-1){//auto set number
				values.set(i, value+":"+index);
				index++;
			}
		}
		
		return Lists.newArrayList(Lists.transform(values, new TextToLabelAndValueFunction()));
	}
	
	public static String labelAndValueToString(List<LabelAndValue> values){
		List<String> texts=Lists.transform(values,Functions.toStringFunction());
		return COMMA_JOINER.join(texts);
	}
	
	public static String[] separateLabelValueAndSelection(String line){
		String[] csv=line.split(",");
		List<String> values=Lists.newArrayList(csv);
		List<LabelAndValue> lvalues=Lists.transform(values, new TextToLabelAndValueFunction());
		List<String> selections=new ArrayList<String>();
		for(LabelAndValue lv:lvalues){
			if(lv.isSelected()){
				selections.add(lv.getPrintValue());
				lv.setSelected(false);
			}
		}
		
		String[] result=new String[2];
		result[0]=labelAndValueToString(lvalues);
		result[1]=COMMA_JOINER.join(selections);
		
		return result;
	}
	
	public static class TextToLabelAndValueFunction implements Function<String,LabelAndValue>{

		@Override
		public LabelAndValue apply(String text) {
			LabelAndValue lv=null;
			String[] vs=text.split(":");
			if(vs.length>1){
				if(vs.length>2){
					lv=new LabelAndValue(vs[0], vs[1],ValuesUtils.toBoolean(vs[2], false));
				}else{
					lv=new LabelAndValue(vs[0], vs[1]);
				}
			}else{
			lv=new LabelAndValue(text);
			}
			
			return lv;
		}

		
		
	}
}
