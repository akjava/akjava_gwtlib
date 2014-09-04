package com.akjava.gwt.lib.client.experimental.opencv;

import java.util.List;

import com.akjava.lib.common.graphics.Rect;
import com.akjava.lib.common.utils.ValuesUtils;
import com.google.common.base.CharMatcher;
import com.google.common.base.Converter;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public  class CVImageDataConverter extends Converter<String, CVImageeData>{
		private static Splitter splitter=Splitter.on(CharMatcher.WHITESPACE);
		private static Joiner joiner=Joiner.on(" ");
		@Override
		protected CVImageeData doForward(String a) {
			if(a.isEmpty()){
				CVImageeData data=new CVImageeData();
				data.setFileName("");
				return data;
			}
			List<String> values=splitter.omitEmptyStrings().splitToList(a);
			
			CVImageeData data=new CVImageeData();
			data.setFileName(values.get(0));
			if(values.size()>1){
			//int rectNumber=Objects.firstNonNull(Ints.tryParse(values.get(1)), 0);
			int rectNumber=ValuesUtils.toInt(values.get(1), 0);
			//set number on final
				for(int i=0;i<rectNumber;i++){
					int findex=2+i*4;
					if(values.size()>1+(i+1)*4){
						
						int x=ValuesUtils.toInt(values.get(findex), 0);
						int y=ValuesUtils.toInt(values.get(findex+1), 0);
						int w=ValuesUtils.toInt(values.get(findex+2), 0);
						int h=ValuesUtils.toInt(values.get(findex+3), 0);
						if(x>=0 && y>=0 && w>0 && h>0){
							data.getRects().add(new Rect(x,y,w,h));
						}
					}
				}
				
				//contain invalid rect,clear all
				if(rectNumber!=data.getRects().size()){
					data.getRects().clear();
				}
				
			}
			return data;
		}

		@Override
		protected String doBackward(CVImageeData data) {
			List<String> vs=Lists.newArrayList();
			vs.add(data.getFileName());
			
			if(data.getRects().size()>0){
			vs.add(String.valueOf(data.getRects().size()));
			}
			for(Rect r:data.getRects()){
				//right now no check invalid rect.
				vs.add(String.valueOf(r.getX()));
				vs.add(String.valueOf(r.getY()));
				vs.add(String.valueOf(r.getWidth()));
				vs.add(String.valueOf(r.getHeight()));
			}
			return joiner.join(vs);
		}
		
	}