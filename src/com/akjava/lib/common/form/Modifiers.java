package com.akjava.lib.common.form;

import com.akjava.lib.common.utils.CSVUtils;
import com.akjava.lib.common.utils.HTMLUtils;

public class Modifiers {
	public static final String MODIFIER_SANITIZE="Sanitize";
	public static final String MODIFIER_TABTOSPACE="TabToSpace";
	public static final String MODIFIER_LINETOBR="LineToBr";
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
		  
			public static LineToBreModifier getLineToBreModifier(){
				  return LineToBreModifier.INSTANCE;
			  }
			  
			  public enum LineToBreModifier implements Modifier {
				    INSTANCE;

					@Override
					public String apply(String value) {
						if(value==null){
							return null;
						}
						return CSVUtils.toNLineSeparator(value).replace("\n", "<br/>");
					}

					@Override
					public String getName() {
						return MODIFIER_LINETOBR;
					}
					
				  }
}
