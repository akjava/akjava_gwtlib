package test;

import junit.framework.TestCase;

import com.akjava.lib.common.form.FormFieldData;
import com.akjava.lib.common.form.FormFieldDataDto;
import com.akjava.lib.common.tag.LabelAndValue;
import com.akjava.lib.common.tag.Tag;

public class FormFieldToInputTest extends TestCase{

	public void testText1(){
		FormFieldData data=new FormFieldData();
		data.setType(FormFieldData.TYPE_TEXT_SHORT);
		data.setName("Text");
		data.setKey("text");
		
		Tag tag=FormFieldDataDto.getFormFieldToInputTagFunction().apply(data);
		
		assertEquals("<input type=\"text\" name=\"text\"/>", tag.toString());
	}
	
	public void testId1(){
		FormFieldData data=new FormFieldData();
		data.setType(FormFieldData.TYPE_ID);
		data.setName("Id");
		data.setKey("id");
		
		Tag tag=FormFieldDataDto.getFormFieldToInputTagFunction().apply(data);
		
		assertEquals("<input type=\"text\" name=\"id\"/>", tag.toString());
	}
	
	public void testTextLong1(){
		FormFieldData data=new FormFieldData();
		data.setType(FormFieldData.TYPE_TEXT_LONG);
		data.setName("TextLong");
		data.setKey("text_long");
		
		Tag tag=FormFieldDataDto.getFormFieldToInputTagFunction().apply(data);
		
		assertEquals("<textarea name=\"text_long\"></textarea>", tag.toString());
	}
	
	public void testCheck1(){
		FormFieldData data=new FormFieldData();
		data.setType(FormFieldData.TYPE_CHECK);
		data.setName("Check");
		data.setKey("check");
		
		Tag tag=FormFieldDataDto.getFormFieldToInputTagFunction().apply(data);
		
		assertEquals("<input type=\"checkbox\" name=\"check\"/>", tag.toString());
	}
	
	public void testSelect1(){
		FormFieldData data=new FormFieldData();
		data.setType(FormFieldData.TYPE_SELECT_SINGLE);
		data.setName("Single");
		data.setKey("single");
		
		data.getOptionValues().add(new LabelAndValue("test1"));
		data.getOptionValues().add(new LabelAndValue("test2"));
		
		Tag tag=FormFieldDataDto.getFormFieldToInputTagFunction().apply(data);
		
		assertEquals("<select name=\"single\"><option>test1\n<option>test2</select>", tag.toString());
	}
	
	public void testSelect2(){
		FormFieldData data=new FormFieldData();
		data.setType(FormFieldData.TYPE_SELECT_SINGLE);
		data.setName("Single");
		data.setKey("single");
		
		data.getOptionValues().add(new LabelAndValue("test1","0"));
		data.getOptionValues().add(new LabelAndValue("test2","1"));
		
		Tag tag=FormFieldDataDto.getFormFieldToInputTagFunction().apply(data);
		
		assertEquals("<select name=\"single\"><option value=\"0\">test1\n<option value=\"1\">test2</select>", tag.toString());
	}
	public void testSelectMulti1(){
		FormFieldData data=new FormFieldData();
		data.setType(FormFieldData.TYPE_SELECT_MULTI);
		data.setName("Multi");
		data.setKey("multi");
		
		data.getOptionValues().add(new LabelAndValue("test1"));
		data.getOptionValues().add(new LabelAndValue("test2"));
		
		Tag tag=FormFieldDataDto.getFormFieldToInputTagFunction().apply(data);
		
		assertEquals("<select name=\"multi\" multiple=\"multiple\"><option>test1\n<option>test2</select>", tag.toString());
	}
}
