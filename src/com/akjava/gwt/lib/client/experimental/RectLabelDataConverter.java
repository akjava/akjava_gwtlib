package com.akjava.gwt.lib.client.experimental;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.akjava.gwt.lib.client.LogUtils;
import com.akjava.lib.common.utils.CSVUtils;
import com.google.common.base.Converter;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class RectLabelDataConverter extends Converter<String, Map<String, Map<String,List<String>>>> {

	@Override
	protected Map<String, Map<String, List<String>>> doForward(String text) {
		Map<String, Map<String, List<String>>> result=new LinkedHashMap<String, Map<String,List<String>>>();
		List<String> lines=CSVUtils.splitLinesWithGuava(text);
		for(String line:lines){
			if(line.isEmpty()){
				continue;
			}
			String[] csv=line.split("\t");
			String name=null;
			String rect=null;
			if(csv.length>1){
				name=csv[0];
				rect=csv[1];
				String[] rectValue=rect.split(",");
				if(rectValue.length!=4){
					LogUtils.log("invalid rect:"+rect);
					continue;
				}
			}else{//at least need name and rect
				continue;
			}
			
			List<String> labelList;
			if(csv.length>2){//some case no label
				String[] labels=csv[1].split(",");
				labelList=Lists.newArrayList(labels);
			}else{
				labelList=Lists.newArrayList();
			}
			
			Map<String, List<String>> map=result.get(name);
			if(map==null){
				map=new LinkedHashMap<String, List<String>>();
				result.put(name, map);
			}
			
			map.put(rect, labelList);
			
		}
		
		return result;
	}

	@Override
	protected String doBackward(Map<String, Map<String, List<String>>> data) {
		List<String> lines=Lists.newArrayList();
		Joiner kanmaJoiner=Joiner.on(",");
		Joiner tabJoiner=Joiner.on("\t");
		for(String key:data.keySet()){
			Map<String, List<String>> rects=data.get(key);
			for(String rect:rects.keySet()){
				List<String> labels=rects.get(rect);
				lines.add(tabJoiner.join(ImmutableList.of(key,rect,kanmaJoiner.join(labels))));
			}
		}
		return Joiner.on("\r\n").join(lines);
	}

}
