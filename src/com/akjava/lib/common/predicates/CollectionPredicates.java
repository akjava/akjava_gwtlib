package com.akjava.lib.common.predicates;

import java.util.Collection;

import com.google.common.base.Predicate;

public class CollectionPredicates {

	public static class RangedSize implements Predicate<Collection>{
		Integer low;
		Integer high;
		public static final Integer UNLIMITED=null;
		public RangedSize(Integer low,Integer high){
			this.low=low;
			this.high=high;
		}
		@Override
		public boolean apply(Collection input) {
			// TODO Auto-generated method stub
			if(low!=null){
				if(input.size()<low){
					return false;
				}
			}
			
			if(high!=null){
				if(input.size()>high){
					return false;
				}
			}
			return true;
		}
		
	}
}
