package com.akjava.lib.common.functions;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Joiner;

public class StringFunctions {
	/**
	 * 
	 * 
	 * for example
	 * RowListStringJoinFunction(":")
	 * [a1,b1] and [a2,b2]  [a1:a2,b1:b2]
	 * 
	 * @author aki
	 *
	 */
	public static class  RowListStringJoinFunction implements Function<List<List<String>>,List<String>>{
		private String joint;
		public RowListStringJoinFunction(String joint){
			this.joint=joint;
		}
		@Override
		public List<String> apply(List<List<String>> value) {
			
			List<String> result=new ArrayList<String>();
			
			if(value.size()==0){
				return result;
			}
			List<String> first=value.get(0);
			int rowCount=first.size();
			if(rowCount==0){
				return result;
			}
			Joiner joiner=Joiner.on(joint);
			for(int i=0;i<rowCount;i++){
				List<String> vs=new ArrayList<String>();
				for(int j=0;j<value.size();j++){
					vs.add(value.get(j).get(i));
				}
				result.add(joiner.join(vs));
			}
			
			
			return result;
		}
	}
}
