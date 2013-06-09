package test;

import java.util.List;

import junit.framework.TestCase;

import com.akjava.lib.common.functions.LabelAndValueDto;
import com.akjava.lib.common.tag.LabelAndValue;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class LabelAndValueTest extends TestCase{
	public void doTest(String collect,List<LabelAndValue> values){
		assertEquals(collect, Joiner.on(",").join(values));
	}
	
	public void testSimple(){
		LabelAndValue lv=new LabelAndValue("hello");
		doTest("hello",Lists.newArrayList(lv));
	}
	
	public void testSimple2(){
		LabelAndValue lv=new LabelAndValue("hello","world");
		doTest("hello:world",Lists.newArrayList(lv));
	}
	
	public void testSimple3(){
		LabelAndValue lv=new LabelAndValue("hello","world",true);
		doTest("hello:world:true",Lists.newArrayList(lv));
	}
	
	public void testParse(){
		String value="hello";
		List<LabelAndValue> lvs=LabelAndValueDto.lineToLabelAndValues(value);
		doTest("hello",lvs);
	}
	
	public void testParse2(){
		String value="hello,hello2";
		List<LabelAndValue> lvs=LabelAndValueDto.lineToLabelAndValues(value);
		doTest("hello,hello2",lvs);
	}
	
	public void testParse3(){
		String value="hello:world,hello2,hello3:world:true";
		List<LabelAndValue> lvs=LabelAndValueDto.lineToLabelAndValues(value);
		doTest("hello:world,hello2,hello3:world:true",lvs);
	}
	
	public void testParse4(){
		String value="hello";
		List<LabelAndValue> lvs=LabelAndValueDto.lineToLabelAndValuesWithNumber(value);
		doTest("hello:0",lvs);
	}
	
	public void testParse5(){
		String value="hello,world";
		List<LabelAndValue> lvs=LabelAndValueDto.lineToLabelAndValuesWithNumber(value);
		doTest("hello:0,world:1",lvs);
	}
	
	public void testParse6(){
		String value="hello:world,hello2,hello3:world:true";
		String[] vs=LabelAndValueDto.separateLabelValueAndSelection(value);
		assertEquals("hello3",vs[1]);
	}
	
	
}
