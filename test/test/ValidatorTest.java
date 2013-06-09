package test;

import junit.framework.TestCase;

import com.akjava.lib.common.form.Validator;
import com.akjava.lib.common.form.ValidatorTools;

public class ValidatorTest extends TestCase{

	public void testNotEmpty(){
		String value=null;
		Validator validator=ValidatorTools.getValidator("notEmpty");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testNotEmpty2(){
		String value="";
		Validator validator=ValidatorTools.getValidator("notEmpty");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testNotEmpty3(){
		String value="value";
		Validator validator=ValidatorTools.getValidator("notEmpty");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testAsciiNumber1(){
		String value="0";
		Validator validator=ValidatorTools.getValidator("asciiNumber");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testAsciiNumber2(){
		String value="99";
		Validator validator=ValidatorTools.getValidator("asciiNumber");
		
		assertEquals(true,validator.validate(value));
	}
	//minus not support yet
	public void testAsciiNumber3(){
		String value="-100";
		Validator validator=ValidatorTools.getValidator("asciiNumber");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testAsciiNumber4(){
		String value="a";
		Validator validator=ValidatorTools.getValidator("asciiNumber");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testAsciiNumber5(){
		String value="１０";
		Validator validator=ValidatorTools.getValidator("asciiNumber");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testAsciiNumberAndChar1(){
		String value="a";
		Validator validator=ValidatorTools.getValidator("AsciiNumberAndChar");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testAsciiNumberAndChar2(){
		String value="0";
		Validator validator=ValidatorTools.getValidator("AsciiNumberAndChar");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testAsciiNumberAndChar3(){
		String value="000aaa";
		Validator validator=ValidatorTools.getValidator("AsciiNumberAndChar");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testAsciiNumberAndChar4(){
		String value="-000aaa";
		Validator validator=ValidatorTools.getValidator("AsciiNumberAndChar");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testAsciiNumberAndChar5(){
		String value="１０";
		Validator validator=ValidatorTools.getValidator("AsciiNumberAndChar");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testAsciiNumberAndChar6(){
		String value="_";
		Validator validator=ValidatorTools.getValidator("AsciiNumberAndChar");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testHankakuKana1(){
		String value="abc";
		Validator validator=ValidatorTools.getValidator("HankakuKana");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testHankakuKana2(){
		String value="カナ";
		Validator validator=ValidatorTools.getValidator("HankakuKana");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testHankakuKana3(){
		String value="ｶﾅ";
		Validator validator=ValidatorTools.getValidator("HankakuKana");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testHiragana1(){
		String value="abc";
		Validator validator=ValidatorTools.getValidator("Hiragana");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testHiragana2(){
		String value="アイウエオ";
		Validator validator=ValidatorTools.getValidator("Hiragana");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testHiragana3(){
		String value="あいうえお";
		Validator validator=ValidatorTools.getValidator("Hiragana");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testBetween1(){
		String value="12345";
		Validator validator=ValidatorTools.getValidator("between");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testBetween4(){
		String value="";
		Validator validator=ValidatorTools.getValidator("between");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testBetween2(){
		String value="12345678";
		Validator validator=ValidatorTools.getValidator("between");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testBetween3(){
		String value="1234567890123";
		Validator validator=ValidatorTools.getValidator("between");
		
		assertEquals(false,validator.validate(value));
	}
	
	
	public void testMaxStringSize1(){
		String value="12345";
		Validator validator=ValidatorTools.getValidator("less8");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testMaxStringSize2(){
		String value="";
		Validator validator=ValidatorTools.getValidator("less8");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testMaxStringSize3(){
		String value=null;
		Validator validator=ValidatorTools.getValidator("less8");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testMaxStringSize4(){
		String value="12345678";
		Validator validator=ValidatorTools.getValidator("less8");
		
		assertEquals(true,validator.validate(value));
	}
	
	
	public void testMaxStringSize5(){
		String value="123456789";
		Validator validator=ValidatorTools.getValidator("less8");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testMaxStringSize6(){
		String value="１２３４５６７８";
		Validator validator=ValidatorTools.getValidator("less8");
		
		assertEquals(true,validator.validate(value));
	}
	
	
	public void testMaxStringSize7(){
		String value="１２";
		Validator validator=ValidatorTools.getValidator("less8");
		
		assertEquals(true,validator.validate(value));
	}
	
	
	public void testMaxStringByte1(){
		String value="12345";
		Validator validator=ValidatorTools.getValidator("less8b");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testMaxStringByte2(){
		String value="";
		Validator validator=ValidatorTools.getValidator("less8b");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testMaxStringByte3(){
		String value=null;
		Validator validator=ValidatorTools.getValidator("less8b");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testMaxStringByte4(){
		String value="12345678";
		Validator validator=ValidatorTools.getValidator("less8b");
		
		assertEquals(true,validator.validate(value));
	}
	
	
	public void testMaxStringByte5(){
		String value="123456789";
		Validator validator=ValidatorTools.getValidator("less8b");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testMaxStringByte6(){
		String value="１２３４５６７８";
		Validator validator=ValidatorTools.getValidator("less8b");
		
		assertEquals(false,validator.validate(value));
	}
	
	
	public void testMaxStringByte7(){
		String value="１２";
		Validator validator=ValidatorTools.getValidator("less8b");
		
		assertEquals(true,validator.validate(value));
	}
	
}
