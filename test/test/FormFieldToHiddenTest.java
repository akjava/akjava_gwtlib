package test;

import junit.framework.TestCase;

import com.akjava.lib.common.form.FormFieldData;
import com.akjava.lib.common.form.FormFieldDataDto;
import com.akjava.lib.common.tag.LabelAndValue;
import com.akjava.lib.common.tag.Tag;

public class FormFieldToHiddenTest extends TestCase{

	public void testText1(){
		FormFieldData data=new FormFieldData();
		data.setType(FormFieldData.TYPE_TEXT_SHORT);
		data.setName("Text");
		data.setKey("text");
		
		String value="test1";
		Tag tag=FormFieldDataDto.getFormFieldToHiddenTagFunction().apply(data);
		tag.setAttribute("value", value);
		
		assertEquals("<input type=\"hidden\" name=\"text\" value=\"test1\"/>", tag.toString());
	}
	
	public void testText2(){
		FormFieldData data=new FormFieldData();
		data.setType(FormFieldData.TYPE_TEXT_SHORT);
		data.setName("Text");
		data.setKey("text");
		
		String value="\"test1\"";
		Tag tag=FormFieldDataDto.getFormFieldToHiddenTagFunction().apply(data);
		tag.setAttribute("value", value);
		
		assertEquals("<input type=\"hidden\" name=\"text\" value=\"&quot;test1&quot;\"/>", tag.toString());
	}
	
	public void testId1(){
		FormFieldData data=new FormFieldData();
		data.setType(FormFieldData.TYPE_ID);
		data.setName("Id");
		data.setKey("id");
		
		Tag tag=FormFieldDataDto.getFormFieldToHiddenTagFunction().apply(data);
		String value="test1";
		tag.setAttribute("value", value);
		assertEquals("<input type=\"hidden\" name=\"id\" value=\"test1\"/>", tag.toString());
	}
	
	public void testTextLong1(){
		FormFieldData data=new FormFieldData();
		data.setType(FormFieldData.TYPE_TEXT_LONG);
		data.setName("TextLong");
		data.setKey("text_long");
		
		Tag tag=FormFieldDataDto.getFormFieldToHiddenTagFunction().apply(data);
		String value="test1";
		tag.setAttribute("value", value);
		assertEquals("<input type=\"hidden\" name=\"text_long\" value=\"test1\"/>", tag.toString());
	}
	
	public void testCheck1(){
		FormFieldData data=new FormFieldData();
		data.setType(FormFieldData.TYPE_CHECK);
		data.setName("Check");
		data.setKey("check");
		
		Tag tag=FormFieldDataDto.getFormFieldToHiddenTagFunction().apply(data);
		String value="test1";
		tag.setAttribute("value", value);
		assertEquals("<input type=\"hidden\" name=\"check\" value=\"test1\"/>", tag.toString());
	}
	
	public void testSelect1(){
		FormFieldData data=new FormFieldData();
		data.setType(FormFieldData.TYPE_SELECT_SINGLE);
		data.setName("Single");
		data.setKey("single");
		
		data.getOptionValues().add(new LabelAndValue("test1"));
		data.getOptionValues().add(new LabelAndValue("test2"));
		
		Tag tag=FormFieldDataDto.getFormFieldToHiddenTagFunction().apply(data);
		String value="test1";
		tag.setAttribute("value", value);
		assertEquals("<input type=\"hidden\" name=\"single\" value=\"test1\"/>", tag.toString());
	}
	
	public void testSelect2(){
		FormFieldData data=new FormFieldData();
		data.setType(FormFieldData.TYPE_SELECT_SINGLE);
		data.setName("Single");
		data.setKey("single");
		
		data.getOptionValues().add(new LabelAndValue("test1","0"));
		data.getOptionValues().add(new LabelAndValue("test2","1"));
		
		Tag tag=FormFieldDataDto.getFormFieldToHiddenTagFunction().apply(data);
		String value="test1";
		tag.setAttribute("value", value);
		assertEquals("<input type=\"hidden\" name=\"single\" value=\"test1\"/>", tag.toString());
	}
	public void testSelectMulti1(){
		FormFieldData data=new FormFieldData();
		data.setType(FormFieldData.TYPE_SELECT_MULTI);
		data.setName("Multi");
		data.setKey("multi");
		
		data.getOptionValues().add(new LabelAndValue("test1"));
		data.getOptionValues().add(new LabelAndValue("test2"));
		
		Tag tag=FormFieldDataDto.getFormFieldToHiddenTagFunction().apply(data);
		String value="test1";
		tag.setAttribute("value", value);
		assertEquals("<input type=\"hidden\" name=\"multi\" value=\"test1\"/>", tag.toString());
	}
}
