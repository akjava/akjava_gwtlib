package test.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import com.akjava.lib.common.utils.CSVUtils;
import com.google.common.collect.Lists;

public class CSVUtilsTest extends TestCase{

	
	public void testToCsvString0(){
		String test="\"\"";
		assertEquals("\"\"", CSVUtils.toCsvString(test, "\t", ""));
	}
	
	public void testToCsvString1(){
		String test="hello";
		assertEquals("hello", CSVUtils.toCsvString(test, "\t", ""));
	}
	
	public void testToCsvString2(){
		String test="hello\t";
		assertEquals("hello", CSVUtils.toCsvString(test, "\t", ""));
	}
	
	public void testToCsvString3(){
		String test="hello\"";
		assertEquals("hello\"", CSVUtils.toCsvString(test, "\t", ""));
	}
	
	public void testToCsvString4(){
		String test="hello\nworld";
		assertEquals("\"hello\nworld\"", CSVUtils.toCsvString(test, "\t", ""));
	}
	public void testToCsvString5(){
		String test="hello\"\nworld";
		assertEquals("\"hello\"\"\nworld\"", CSVUtils.toCsvString(test, "\t", ""));
	}
	
	
	

	
	public void testMapToCsv1(){
		Map<String,String> map=new HashMap<String, String>();
		map.put("key1", "hello");
		List<String> keys=Lists.newArrayList("key1");
		
		assertEquals("hello", CSVUtils.mapToCsv(map, keys, "\t"));
	}
	
	public void testMapToCsv2(){
		Map<String,String> map=new HashMap<String, String>();
		map.put("key1", "hello");
		map.put("key2", "world");
		List<String> keys=Lists.newArrayList("key1","key2");
		
		assertEquals("hello\tworld", CSVUtils.mapToCsv(map, keys, "\t"));
	}
	/*
	 * no effect just contain quote
	 */
	public void testMapToCsv3(){
		Map<String,String> map=new HashMap<String, String>();
		map.put("key1", "hell\"o");
		map.put("key2", "world");
		List<String> keys=Lists.newArrayList("key1","key2");
		
		assertEquals("hell\"o\tworld", CSVUtils.mapToCsv(map, keys, "\t"));
	}
	
	/*
	 * quote escaped when data contain line-separator and quoted;
	 */
	public void testMapToCsv4(){
		Map<String,String> map=new HashMap<String, String>();
		map.put("key1", "hell\"o\nhello");
		map.put("key2", "world");
		List<String> keys=Lists.newArrayList("key1","key2");
		
		assertEquals("\"hell\"\"o\nhello\"\tworld", CSVUtils.mapToCsv(map, keys, "\t"));
	}
	/*
	 * linseseparator text will gone 
	 */
	public void testMapToCsv5(){
		Map<String,String> map=new HashMap<String, String>();
		map.put("key1", "hello\thello");
		map.put("key2", "world");
		List<String> keys=Lists.newArrayList("key1","key2");
		
		assertEquals("hellohello\tworld", CSVUtils.mapToCsv(map, keys, "\t"));
	}

}
