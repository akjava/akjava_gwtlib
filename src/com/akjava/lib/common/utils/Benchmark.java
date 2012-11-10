package com.akjava.lib.common.utils;

import java.util.HashMap;
import java.util.Map;

import com.akjava.gwt.lib.client.LogUtils;

/*
 * i have no idea why benchmark is here?
 */
public class Benchmark {
	private static Map<String,Long> keyMap=new HashMap<String,Long>();
	public static String last;
	public static void start(String key){
		keyMap.put(key, System.currentTimeMillis());
		last=key;
	}
	public static long end(String key){
		Long t=keyMap.get(key);
		if(t==null){
			return -1;
		}
		return System.currentTimeMillis()-t.longValue();
	}
	public static long endAndLog(String key){
		long t=end(last);
		LogUtils.log("Benchmark "+last+":"+t+"ms");
		return t;
	}
	
	public static void logLast(){
		if(last==null){
			return;
		}
		endAndLog(last);
		
	}
	
}
