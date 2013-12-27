package test.utils;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import com.akjava.lib.common.utils.TemplateUtils;

public class TemplateUtilsTest extends TestCase {

	/**
	 * not contain
	 */
	public void testSimple1(){
		String v="hello";
		String correct="hello";
		assertEquals(correct, TemplateUtils.createText(v, "world"));
	}
	
	/**
	 * replace only 'value'
	 */
	public void testSimple2(){
		String v="hello ${value}";
		String correct="hello world";
		assertEquals(correct, TemplateUtils.createText(v, "world"));
	}
	/**
	 * not replace if templava-v is not value
	 */
	public void testSimple3(){
		String v="hello ${other}";
		String correct="hello ${other}";
		assertEquals(correct, TemplateUtils.createText(v, "world"));
	}
	
	/**
	 * not contain
	 */
	public void testMap1(){
		String v="hello";
		String correct="hello";
		Map<String,String> map=new HashMap<String, String>();
		map.put("word", "world");
		assertEquals(correct, TemplateUtils.createText(v,map));
	}
	
	/**
	 * replace only key you put in
	 */
	public void testMap2(){
		String v="hello ${word}";
		String correct="hello world";
		Map<String,String> map=new HashMap<String, String>();
		map.put("word", "world");
		assertEquals(correct, TemplateUtils.createText(v,map));
	}
	
	/**
	 * not replace
	 */
	public void testMap3(){
		String v="hello ${other}";
		String correct="hello ${other}";
		Map<String,String> map=new HashMap<String, String>();
		map.put("word", "world");
		assertEquals(correct, TemplateUtils.createText(v,map));
	}
	
	/**
	 * null key  skipped
	 */
	public void testMap4(){
		String v="hello ${word}";
		String correct="hello ${word}";
		Map<String,String> map=new HashMap<String, String>();
		map.put(null, "a");
		assertEquals(correct, TemplateUtils.createText(v,map));
	}
	
	/**
	 * null value  skipped with log
	 */
	public void testMap5(){
		String v="hello ${word}";
		String correct="hello ${word}";
		Map<String,String> map=new HashMap<String, String>();
		map.put("word", null);
		assertEquals(correct, TemplateUtils.createText(v,map));
	}
	
	
	/**
	 * not contain
	 */
	public void testAdvancedMap1(){
		String v="hello";
		String correct="hello";
		Map<String,String> map=new HashMap<String, String>();
		map.put("word", "world");
		assertEquals(correct, TemplateUtils.createAdvancedText(v,map));
	}
	
	/**
	 * replace only key you put in
	 */
	public void testAdvancedMap2(){
		String v="hello ${word}";
		String correct="hello world";
		Map<String,String> map=new HashMap<String, String>();
		map.put("word", "world");
		assertEquals(correct, TemplateUtils.createAdvancedText(v,map));
	}
	
	/**
	 * not replace
	 */
	public void testAdvancedMap3(){
		String v="hello ${other}";
		String correct="hello ${other}";
		Map<String,String> map=new HashMap<String, String>();
		map.put("word", "world");
		assertEquals(correct, TemplateUtils.createAdvancedText(v,map));
	}
	
	/**
	 * null key  skipped
	 */
	public void testAdvancedMap4(){
		String v="hello ${word}";
		String correct="hello ${word}";
		Map<String,String> map=new HashMap<String, String>();
		map.put(null, "a");
		assertEquals(correct, TemplateUtils.createAdvancedText(v,map));
	}
	
	/**
	 * null value  skipped with log
	 */
	public void testAdvancedMap5(){
		String v="hello ${word}";
		String correct="hello ${word}";
		Map<String,String> map=new HashMap<String, String>();
		map.put("word", null);
		assertEquals(correct, TemplateUtils.createAdvancedText(v,map));
	}
	
	/**
	 * upper-camel case
	 */
	public void testAdvanced1(){
		String v="hello ${u+word}";
		String correct="hello World";
		Map<String,String> map=new HashMap<String, String>();
		map.put("word", "world");
		assertEquals(correct, TemplateUtils.createAdvancedText(v,map));
	}
	
	/**
	 * lower-camel case
	 */
	public void testAdvanced2(){
		String v="hello ${l+word}";
		String correct="hello world";
		Map<String,String> map=new HashMap<String, String>();
		map.put("word", "World");
		assertEquals(correct, TemplateUtils.createAdvancedText(v,map));
	}
	
	/**
	 * upper-camel case
	 */
	public void testAdvanced3(){
		String v="hello ${U+word}";
		String correct="hello WORLD";
		Map<String,String> map=new HashMap<String, String>();
		map.put("word", "world");
		assertEquals(correct, TemplateUtils.createAdvancedText(v,map));
	}
	
	/**
	 * lower-camel case
	 */
	public void testAdvanced4(){
		String v="hello ${L+word}";
		String correct="hello world";
		Map<String,String> map=new HashMap<String, String>();
		map.put("word", "WORLD");
		assertEquals(correct, TemplateUtils.createAdvancedText(v,map));
	}
	
	/**
	 * file-name
	 */
	public void testAdvanced5(){
		String v="hello ${name+word}";
		String correct="hello file";
		Map<String,String> map=new HashMap<String, String>();
		map.put("word", "file.txt");
		assertEquals(correct, TemplateUtils.createAdvancedText(v,map));
	}
	/**
	 * file-extension
	 */
	public void testAdvanced6(){
		String v="hello ${ext+word}";
		String correct="hello txt";
		Map<String,String> map=new HashMap<String, String>();
		map.put("word", "file.txt");
		assertEquals(correct, TemplateUtils.createAdvancedText(v,map));
	}
	
	/**
	 * file-name,but not have extension
	 */
	public void testAdvanced7(){
		String v="hello ${name+word}";
		String correct="hello file";
		Map<String,String> map=new HashMap<String, String>();
		map.put("word", "file");
		assertEquals(correct, TemplateUtils.createAdvancedText(v,map));
	}
	/**
	 * file-extension,but not have extension
	 */
	public void testAdvanced8(){
		String v="hello ${ext+word}";
		String correct="hello ";
		Map<String,String> map=new HashMap<String, String>();
		map.put("word", "file");
		assertEquals(correct, TemplateUtils.createAdvancedText(v,map));
	}
}
