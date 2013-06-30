package com.akjava.lib.common.form;

import java.util.List;

import com.akjava.lib.common.form.ModifierTools.ModifierNotFoundException;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class ModifierDto {
private static NameToModifierFunction nameToModifierFunction=new NameToModifierFunction();
private static ModifierToNameFunction modifierToNameFunction=new ModifierToNameFunction();


	public static List<String> modifierListToNameList(List<Modifier> validators){
		return Lists.transform(validators, modifierToNameFunction);
	}

	public static String modifierListToNamesLine(List<Modifier> validators){
		return Joiner.on(',').join(Lists.transform(validators, modifierToNameFunction));
	}
	

	public static List<Modifier> namesLineToModifierList(String names){
	return nameListToModifierList(Lists.newArrayList(names.split(",")));
	}

	public static List<Modifier> nameListToModifierList(List<String> names){
		return Lists.transform(names, nameToModifierFunction);
	}
	
	public static class NameToModifierFunction implements Function<String,Modifier>{
		@Override
		public Modifier apply(String value) {
			try {
				return ModifierTools.getModifier(value);
			} catch (ModifierNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		
	}
	
	public static class ModifierToNameFunction implements Function<Modifier,String>{
		@Override
		public String apply(Modifier value) {
			
			String name=ModifierTools.getModifierMap().inverse().get(value);
			if(name==null){
				//not registed
				name=value.getName();
			}
			return name;
		}
		
	}
}
