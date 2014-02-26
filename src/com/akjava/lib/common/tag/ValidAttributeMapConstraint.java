package com.akjava.lib.common.tag;

import com.google.common.base.CharMatcher;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.MapConstraint;

public class ValidAttributeMapConstraint implements MapConstraint<String,String>{
	private ValidAttributeMapConstraint(){}
	private static ValidAttributeMapConstraint validAttributeMapConstraint;
	public static ValidAttributeMapConstraint getValidAttributeMapConstraint(){
		if(validAttributeMapConstraint==null){
			validAttributeMapConstraint=new ValidAttributeMapConstraint();
		}
		return validAttributeMapConstraint;
	}
	
	public final static CharMatcher AZ=CharMatcher.inRange('A', 'Z').or(CharMatcher.inRange('a', 'z'));
	
	public final static CharMatcher IDENTIFIER_CHAR = CharMatcher.is('_')
		    .or(AZ)
		    .or(CharMatcher.inRange('0', '9'))
		    .precomputed();
	
	
	@Override
	public void checkKeyValue(String key, String value) {
		if(!getValidAttributePredicate().apply(key)){
			throw new IllegalArgumentException("invalid key:"+key);
		}
	}

	public ValidAttributePredicate getValidAttributePredicate(){
		return ValidAttributePredicate.INSTANCE;
	}
	public enum ValidAttributePredicate implements Predicate<String>{
		INSTANCE;
		@Override
		public boolean apply(String input) {
			if(Strings.isNullOrEmpty(input)){
				return false;
			}
			
			if(!AZ.matches(input.charAt(0))){
				return false;
			}
			
			return IDENTIFIER_CHAR.matchesAllOf(input);
		}
		
	}
	
}
