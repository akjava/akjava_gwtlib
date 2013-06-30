package com.akjava.lib.common.form;

import com.akjava.lib.common.utils.HTMLUtils;

public class Modifiers {
	public static final String MODIFIER_SANITIZE="sanitize";
	public static final String MODIFIER_TABTOSPACE="tabtospace";
	public static SanitizeModifier getSanitizeModier(){
		  return SanitizeModifier.INSTANCE;
	  }
	  
	  public enum SanitizeModifier implements Modifier {
		    INSTANCE;

			@Override
			public String apply(String value) {
				return HTMLUtils.sanitize(value);
			}

			@Override
			public String getName() {
				return MODIFIER_SANITIZE;
			}
			
		  }
	  
	  
		public static TabToSpaceModifier getTabToSpaceModier(){
			  return TabToSpaceModifier.INSTANCE;
		  }
		  
		  public enum TabToSpaceModifier implements Modifier {
			    INSTANCE;

				@Override
				public String apply(String value) {
					if(value==null){
						return null;
					}
					return value.replace("\t", "    ");
				}

				@Override
				public String getName() {
					return MODIFIER_TABTOSPACE;
				}
				
			  }
}
