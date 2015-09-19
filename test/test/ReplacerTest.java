package test;

import java.util.List;

import junit.framework.TestCase;

import com.akjava.gwt.lib.client.experimental.ReplaceEachOther;

public class ReplacerTest extends TestCase{

	public void test1(){
		String text="right-arm";
		ReplaceEachOther replace1=new ReplaceEachOther("left", "right");
		
		String expected="left-arm";
		assertEquals(expected, replace1.replace(text));
	}
	public void test1b(){
		String text="left-arm";
		ReplaceEachOther replace1=new ReplaceEachOther("left", "right");
		
		String expected="right-arm";
		assertEquals(expected, replace1.replace(text));
	}
	public void test1c(){
		String text="left-right-arm";
		ReplaceEachOther replace1=new ReplaceEachOther("left", "right");
		
		String expected="left-right-arm";
		assertEquals(expected, replace1.replace(text));
	}
	
	public void test2(){
		String text="right-arm";
		ReplaceEachOther replace1=new ReplaceEachOther("left", "right");
		
		List<ReplaceEachOther> commons=replace1.createCommonCase();
		
		
		
		String expected="left-arm";
		assertEquals(expected, ReplaceEachOther.replaceAll(commons,text));
	}
	public void test2b(){
		String text="Right-arm";
		ReplaceEachOther replace1=new ReplaceEachOther("left", "right");
		
		List<ReplaceEachOther> commons=replace1.createCommonCase();
		
		
		
		String expected="Left-arm";
		assertEquals(expected, ReplaceEachOther.replaceAll(commons,text));
	}
	public void test2c(){
		String text="RIGHT-arm";
		ReplaceEachOther replace1=new ReplaceEachOther("left", "right");
		
		List<ReplaceEachOther> commons=replace1.createCommonCase();
		
		
		
		String expected="LEFT-arm";
		assertEquals(expected, ReplaceEachOther.replaceAll(commons,text));
	}
	public void test2d(){
		String text="right-Right-RIGHT-arm";
		ReplaceEachOther replace1=new ReplaceEachOther("left", "right");
		
		List<ReplaceEachOther> commons=replace1.createCommonCase();
		
		
		
		String expected="left-Left-LEFT-arm";
		assertEquals(expected, ReplaceEachOther.replaceAll(commons,text));
	}
	
	public void test2e(){
		String text="right-Left-RIGHT-arm";
		ReplaceEachOther replace1=new ReplaceEachOther("left", "right");
		
		List<ReplaceEachOther> commons=replace1.createCommonCase();
		
		
		
		String expected="left-Right-LEFT-arm";
		assertEquals(expected, ReplaceEachOther.replaceAll(commons,text));
	}
}
