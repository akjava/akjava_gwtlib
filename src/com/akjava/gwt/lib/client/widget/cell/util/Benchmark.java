package com.akjava.gwt.lib.client.widget.cell.util;

import java.util.HashMap;
import java.util.Map;

public class Benchmark {
	private static Map<String,Long> keyMap=new HashMap<String,Long>();
	public static void start(String key){
		keyMap.put(key, System.currentTimeMillis());
	}
	public static long end(String key){
		Long t=keyMap.get(key);
		if(t==null){
			return -1;
		}
		return System.currentTimeMillis()-t.longValue();
	}
}
