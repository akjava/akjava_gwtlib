package com.akjava.lib.common.form;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class ModifierTools {

	private ModifierTools(){}
private  static BiMap<String,Modifier> modifierMap=null;
public static BiMap<String,Modifier> getModifierMap(){
	if(modifierMap==null){
		init();
	}
	return modifierMap;
}
	private static void init(){
		modifierMap=HashBiMap.create();
		modifierMap.put(Modifiers.MODIFIER_SANITIZE.toLowerCase(), Modifiers.getSanitizeModier());
		modifierMap.put(Modifiers.MODIFIER_TABTOSPACE.toLowerCase(), Modifiers.getTabToSpaceModier());
		modifierMap.put(Modifiers.MODIFIER_LINETOBR.toLowerCase(), Modifiers.getLineToBreModifier());
		

	}

	public static class ModifierNotFoundException extends Exception{

		public ModifierNotFoundException(String string) {
			super(string);
		}

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	
	}
	
	public static String getModifierLabel(Modifier modifier) {
		String label= modifierMap.inverse().get(modifier);
		if(label==null){
			return modifier.getName();
		}else{
			return label;
		}
	}
	/**
	 * use lower case inside
	 * @param key
	 * @return
	 */
	public static Modifier getModifier(String key)throws ModifierNotFoundException {
		if(modifierMap==null){
			init();
		}
		if(key==null){
			throw new ModifierNotFoundException("null validator key");
		}
		Modifier v= modifierMap.get(key.toLowerCase());
		if(v==null){
			throw new ModifierNotFoundException("null validator for "+key);
		}
		return v;
	}
}
