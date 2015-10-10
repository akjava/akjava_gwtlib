package test;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import com.akjava.lib.common.form.FormFieldData;
import com.akjava.lib.common.form.FormFieldDataDto.FormFieldToInputEditTagFunction;
import com.akjava.lib.common.tag.LabelAndValue;
import com.akjava.lib.common.tag.Tag;

public class FormFieldToInputEditTest extends TestCase{

	public void testText1(){
		FormFieldData data=new FormFieldData();
		data.setType(FormFieldData.TYPE_TEXT_SHORT);
		data.setName("Text");
		data.setKey("text");
		
		Map<String,String> valueMap=new HashMap<String, String>();
		valueMap.put("text", "test1");
		
		Tag tag=new FormFieldToInputEditTagFunction(valueMap).apply(data);
		
		assertEquals("<input type=\"text\" name=\"text\" value=\"test1\"/>", tag.toString());
	}
	
	public void testId1(){
		FormFieldData data=new FormFieldData();
		data.setType(FormFieldData.TYPE_ID);
		data.setName("Id");
		data.setKey("id");
		
		Map<String,String> valueMap=new HashMap<String, String>();
		valueMap.put("id", "test1");
		
		Tag tag=new FormFieldToInputEditTagFunction(valueMap).apply(data);
		assertEquals("<input type=\"text\" name=\"id\" value=\"test1\"/>", tag.toString());
	}
	
	public void testTextLong1(){
		FormFieldData data=new FormFieldData();
		data.setType(FormFieldData.TYPE_TEXT_LONG);
		data.setName("TextLong");
		data.setKey("text_long");
		
		Map<String,String> valueMap=new HashMap<String, String>();
		valueMap.put("text_long", "test1");
		
		Tag tag=new FormFieldToInputEditTagFunction(valueMap).apply(data);
		assertEquals("<textarea name=\"text_long\">test1</textarea>", tag.toString());
	}
	
	public void testCheck1(){
		FormFieldData data=new FormFieldData();
		data.setType(FormFieldData.TYPE_CHECK);
		data.setName("Check");
		data.setKey("check");
		
		Map<String,String> valueMap=new HashMap<String, String>();
		valueMap.put("check", "on");
		Tag tag=new FormFieldToInputEditTagFunction(valueMap).apply(data);
		
		assertEquals("<input type=\"checkbox\" name=\"check\" checked=\"checked\"/>", tag.toString());
	}
	
	public void testSelect1(){
		FormFieldData data=new FormFieldData();
		data.setType(FormFieldData.TYPE_SELECT_SINGLE);
		data.setName("Single");
		data.setKey("single");
		
		data.getOptionValues().add(new LabelAndValue("test1"));
		data.getOptionValues().add(new LabelAndValue("test2"));
		
		Map<String,String> valueMap=new HashMap<String, String>();
		valueMap.put("single", "test2");
		Tag tag=new FormFieldToInputEditTagFunction(valueMap).apply(data);
		
		assertEquals("<select name=\"single\"><option>test1\n<option selected>test2</select>", tag.toString());
	}
	
	public void testSelect2(){
		FormFieldData data=new FormFieldData();
		data.setType(FormFieldData.TYPE_SELECT_SINGLE);
		data.setName("Single");
		data.setKey("single");
		
		data.getOptionValues().add(new LabelAndValue("test1","0"));
		data.getOptionValues().add(new LabelAndValue("test2","1"));
		
		Map<String,String> valueMap=new HashMap<String, String>();
		valueMap.put("single", "1");
		Tag tag=new FormFieldToInputEditTagFunction(valueMap).apply(data);
		
		assertEquals("<select name=\"single\"><option value=\"0\">test1\n<option value=\"1\" selected>test2</select>", tag.toString());
	}
	public void testSelectMulti1(){
		FormFieldData data=new FormFieldData();
		data.setType(FormFieldData.TYPE_SELECT_MULTI);
		data.setName("Multi");
		data.setKey("multi");
		
		data.getOptionValues().add(new LabelAndValue("test1"));
		data.getOptionValues().add(new LabelAndValue("test2"));
		
		Map<String,String> valueMap=new HashMap<String, String>();
		valueMap.put("multi", "test2");
		Tag tag=new FormFieldToInputEditTagFunction(valueMap).apply(data);
		
		assertEquals("<select name=\"multi\" multiple=\"multiple\"><option>test1\n<option selected>test2</select>", tag.toString());
	}
	
	public void testSelectMulti2(){
		FormFieldData data=new FormFieldData();
		data.setType(FormFieldData.TYPE_SELECT_MULTI);
		data.setName("Multi");
		data.setKey("multi");
		
		data.getOptionValues().add(new LabelAndValue("test1"));
		data.getOptionValues().add(new LabelAndValue("test2"));
		
		Map<String,String> valueMap=new HashMap<String, String>();
		valueMap.put("multi", "test2:test1");
		Tag tag=new FormFieldToInputEditTagFunction(valueMap).apply(data);
		
		assertEquals("<select name=\"multi\" multiple=\"multiple\"><option selected>test1\n<option selected>test2</select>", tag.toString());
	}
}
