package test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.akjava.lib.common.tag.LabelAndValue;
import com.akjava.lib.common.tag.Tag;
import com.akjava.lib.common.tag.TagBuilder;

public class TagTest extends TestCase{
	public void doTest(String collect,Tag tag){
		assertEquals(collect, tag.toString());
	}
	
	//text
	
	//empty
	public void testInputText1(){
		Tag tag=TagBuilder.createText("name", "");
		doTest("<input type=\"text\" name=\"name\" value=\"\"/>",tag);
	}
	
	//null
	public void testInputText2(){
		Tag tag=TagBuilder.createText("name", null);
		doTest("<input type=\"text\" name=\"name\"/>",tag);
	}
	
	//multiline
	public void testInputText3(){
		Tag tag=TagBuilder.createText("name", "hello world\nline2");
		doTest("<input type=\"text\" name=\"name\" value=\"hello world\nline2\"/>",tag);
	}
	
	public void testInputQuot(){
		Tag tag=TagBuilder.createText("name", "\"");
		doTest("<input type=\"text\" name=\"name\" value=\"&quot;\"/>",tag);
	}
	
	//hidden
	
	//empty
	public void testHidden1(){
		Tag tag=TagBuilder.createHidden("name", "");
		doTest("<input type=\"hidden\" name=\"name\" value=\"\"/>",tag);
	}
	
	//null
	public void testHidden2(){
		Tag tag=TagBuilder.createHidden("name", null);
		doTest("<input type=\"hidden\" name=\"name\"/>",tag);
	}
	
	//empty not allowed
	public void testAttribute1(){
		Tag tag=TagBuilder.createHidden("name", null);
		try{
		tag.setAttribute("");
		}catch(IllegalArgumentException e){
			assertTrue(true);
			return;
		}
		fail();
		
		
	}
	//not start it
	public void testAttribute2(){
		Tag tag=TagBuilder.createHidden("name", null);
		try{
		tag.setAttribute("_");
		}catch(IllegalArgumentException e){
			assertTrue(true);
			return;
		}
		fail();
	}
	
	//this ok
	public void testAttribute3(){
		Tag tag=TagBuilder.createHidden("name", null);
		try{
		tag.setAttribute("a_9");
		}catch(IllegalArgumentException e){
			fail();
			return;
		}
		assertTrue(true);
	}

	
	//checkbox
	
	//simple
	public void testCheckbox1(){
		Tag tag=TagBuilder.createCheckbox("name", "v",false);
		doTest("<input type=\"checkbox\" name=\"name\" value=\"v\"/>",tag);
	}
	
	//empty
	public void testCheckbox2(){
		Tag tag=TagBuilder.createCheckbox("name", "",false);
		doTest("<input type=\"checkbox\" name=\"name\" value=\"\"/>",tag);
	}
	
	//null
	public void testCheckbox3(){
		Tag tag=TagBuilder.createCheckbox("name", null,false);
		doTest("<input type=\"checkbox\" name=\"name\"/>",tag);
	}
	
	//checkd
	public void testCheckbox4(){
		Tag tag=TagBuilder.createCheckbox("name", "v",true);
		doTest("<input type=\"checkbox\" name=\"name\" value=\"v\" checked=\"checked\"/>",tag);
	}
	//radio
	
	//single
	public void testRadio(){
		LabelAndValue lvalue=new LabelAndValue("test", "TEST");
		Tag tag=TagBuilder.createRadio("name", lvalue);
		doTest("<input type=\"radio\" name=\"name\" value=\"TEST\"/>",tag);
	}
	public void testRadioText(){
		LabelAndValue lvalue=new LabelAndValue("test", "TEST");
		String tagText=TagBuilder.createRadioText("name", lvalue);
		assertEquals("<input type=\"radio\" name=\"name\" value=\"TEST\"/>test",tagText);
	}
	//double
	public void testRadio2Text(){
		List<LabelAndValue> lvalues=new ArrayList<LabelAndValue>();
		lvalues.add(new LabelAndValue("test1", "TEST1"));
		lvalues.add(new LabelAndValue("test2", "TEST2"));
		String tagText=TagBuilder.createRadioText("name", lvalues);
		assertEquals("<input type=\"radio\" name=\"name\" value=\"TEST1\"/>test1\n<input type=\"radio\" name=\"name\" value=\"TEST2\"/>test2",tagText);
	}
	//one selected
	public void testRadio3Text(){
		List<LabelAndValue> lvalues=new ArrayList<LabelAndValue>();
		lvalues.add(new LabelAndValue("test1", "TEST1"));
		lvalues.add(new LabelAndValue("test2", "TEST2",true));
		String tagText=TagBuilder.createRadioText("name", lvalues);
		assertEquals("<input type=\"radio\" name=\"name\" value=\"TEST1\"/>test1\n<input type=\"radio\" name=\"name\" value=\"TEST2\" checked=\"checked\"/>test2",tagText);
	}
	
	
	//textarea
	//empty
		public void testTextArea1(){
			Tag tag=TagBuilder.createTextArea("name", "hello");
			doTest("<textarea name=\"name\">hello</textarea>",tag);
		}
		
		//null
		public void testTextArea2(){
			Tag tag=TagBuilder.createTextArea("name", null);
			doTest("<textarea name=\"name\"></textarea>",tag);
		}
		
		//multiline
		public void testTextArea3(){
			Tag tag=TagBuilder.createTextArea("name", "hello world\nline2");
			doTest("<textarea name=\"name\">hello world\nline2</textarea>",tag);
		}
	
	
	//select
	
	//single
		public void testSelect1(){
			List<LabelAndValue> lvalues=new ArrayList<LabelAndValue>();
			lvalues.add(new LabelAndValue("test1", "TEST1"));
			Tag tag=TagBuilder.createSelect("name",lvalues,false);
			
			doTest("<select name=\"name\"><option value=\"TEST1\">test1</select>",tag);
		}
	//double
		public void testSelect2(){
			List<LabelAndValue> lvalues=new ArrayList<LabelAndValue>();
			lvalues.add(new LabelAndValue("test1", "TEST1"));
			lvalues.add(new LabelAndValue("test2", "TEST2"));
			Tag tag=TagBuilder.createSelect("name",lvalues,false);
			
			doTest("<select name=\"name\"><option value=\"TEST1\">test1\n<option value=\"TEST2\">test2</select>",tag);
		}
	//selected
		public void testSelect3(){
			List<LabelAndValue> lvalues=new ArrayList<LabelAndValue>();
			lvalues.add(new LabelAndValue("test1", "TEST1"));
			lvalues.add(new LabelAndValue("test2", "TEST2",true));
			Tag tag=TagBuilder.createSelect("name",lvalues,false);
			
			doTest("<select name=\"name\"><option value=\"TEST1\">test1\n<option value=\"TEST2\" selected>test2</select>",tag);
		}
	//multiple
		public void testSelect4(){
			List<LabelAndValue> lvalues=new ArrayList<LabelAndValue>();
			lvalues.add(new LabelAndValue("test1", "TEST1",true));
			lvalues.add(new LabelAndValue("test2", "TEST2",true));
			Tag tag=TagBuilder.createSelect("name",lvalues,true);
			
			doTest("<select name=\"name\" multiple=\"multiple\"><option value=\"TEST1\" selected>test1\n<option value=\"TEST2\" selected>test2</select>",tag);
		}
	//TODO
	//submit
	//reset
	//form
	
}
