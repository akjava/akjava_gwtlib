package test.datalist;

import junit.framework.TestCase;

import com.akjava.gwt.lib.client.datalist.SimpleTextData;
import com.akjava.gwt.lib.client.datalist.SimpleTextDataDataConverter;

public class SimpleTextDataTest extends TestCase{

	public void test_string1(){
		String text="000";
		SimpleTextData correct=new SimpleTextData(-1, null, "","000");

		doTestAll(correct,text);
	}

	public void test_string2(){
		String text="";
		SimpleTextData correct=new SimpleTextData(-1, null, "","");

		//empty date set current time
		doTestData(correct,text);
	}
	
	public void test_string3(){
		String text="text";
		SimpleTextData correct=new SimpleTextData(-1, null, text,"");

		//empty date set current time
		doTestData(correct,text);
	}
	
	public void test_string4(){
		String text="001,text";
		SimpleTextData correct=new SimpleTextData(-1, null, "text",001);

		//empty date set current time
		doTestData(correct,text);
	}
	
	public void test_string5(){
		String text="001,text,2221";
		SimpleTextData correct=new SimpleTextData(-1, null, "text,2221",001);

		//empty date set current time
		doTestData(correct,text);
	}
	public void test_string6(){
		String text="00,1,text,2221";
		SimpleTextData correct=new SimpleTextData(-1, null, "1,text,2221",00);

		//empty date set current time
		doTestData(correct,text);
	}
	
	private void doTestData(SimpleTextData correct, String text) {
		SimpleTextDataDataConverter converter=new SimpleTextDataDataConverter();
		SimpleTextData converted=converter.reverse().convert(text);
		assertEquals(correct.getData(), converted.getData());
	}
	
	private void doTestAll(SimpleTextData correct, String text) {
		SimpleTextDataDataConverter converter=new SimpleTextDataDataConverter();
		SimpleTextData converted=converter.reverse().convert(text);
		assertEquals(correct.toString(), converted.toString());
	}
}
