package test;

import junit.framework.TestCase;

import com.akjava.lib.common.form.Validator;
import com.akjava.lib.common.form.ValidatorTools;
import com.akjava.lib.common.form.ValidatorTools.ValidatorNotFoundException;

public class ValidatorTest extends TestCase{

	public void testNotEmpty() throws ValidatorNotFoundException{
		String value=null;
		Validator validator=ValidatorTools.getValidator("notEmpty");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testNotEmpty2() throws ValidatorNotFoundException{
		String value="";
		Validator validator=ValidatorTools.getValidator("notEmpty");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testNotEmpty3() throws ValidatorNotFoundException{
		String value="value";
		Validator validator=ValidatorTools.getValidator("notEmpty");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testAsciiNumber1() throws ValidatorNotFoundException{
		String value="0";
		Validator validator=ValidatorTools.getValidator("asciiNumber");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testAsciiNumber2() throws ValidatorNotFoundException{
		String value="99";
		Validator validator=ValidatorTools.getValidator("asciiNumber");
		
		assertEquals(true,validator.validate(value));
	}
	//minus not support yet
	public void testAsciiNumber3() throws ValidatorNotFoundException{
		String value="-100";
		Validator validator=ValidatorTools.getValidator("asciiNumber");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testAsciiNumber4() throws ValidatorNotFoundException{
		String value="a";
		Validator validator=ValidatorTools.getValidator("asciiNumber");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testAsciiNumber5() throws ValidatorNotFoundException{
		String value="１０";
		Validator validator=ValidatorTools.getValidator("asciiNumber");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testAsciiNumberAndChar1() throws ValidatorNotFoundException{
		String value="a";
		Validator validator=ValidatorTools.getValidator("AsciiNumberAndChar");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testAsciiNumberAndChar2() throws ValidatorNotFoundException{
		String value="0";
		Validator validator=ValidatorTools.getValidator("AsciiNumberAndChar");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testAsciiNumberAndChar3() throws ValidatorNotFoundException{
		String value="000aaa";
		Validator validator=ValidatorTools.getValidator("AsciiNumberAndChar");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testAsciiNumberAndChar4() throws ValidatorNotFoundException{
		String value="-000aaa";
		Validator validator=ValidatorTools.getValidator("AsciiNumberAndChar");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testAsciiNumberAndChar5() throws ValidatorNotFoundException{
		String value="１０";
		Validator validator=ValidatorTools.getValidator("AsciiNumberAndChar");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testAsciiNumberAndChar6() throws ValidatorNotFoundException{
		String value="_";
		Validator validator=ValidatorTools.getValidator("AsciiNumberAndChar");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testHankakuKana1() throws ValidatorNotFoundException{
		String value="abc";
		Validator validator=ValidatorTools.getValidator("HankakuKana");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testHankakuKana2() throws ValidatorNotFoundException{
		String value="カナ";
		Validator validator=ValidatorTools.getValidator("HankakuKana");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testHankakuKana3() throws ValidatorNotFoundException{
		String value="ｶﾅ";
		Validator validator=ValidatorTools.getValidator("HankakuKana");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testHiragana1() throws ValidatorNotFoundException{
		String value="abc";
		Validator validator=ValidatorTools.getValidator("Hiragana");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testHiragana2() throws ValidatorNotFoundException{
		String value="アイウエオ";
		Validator validator=ValidatorTools.getValidator("Hiragana");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testHiragana3() throws ValidatorNotFoundException{
		String value="あいうえお";
		Validator validator=ValidatorTools.getValidator("Hiragana");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testBetween1() throws ValidatorNotFoundException{
		String value="12345";
		Validator validator=ValidatorTools.getValidator("between");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testBetween4() throws ValidatorNotFoundException{
		String value="";
		Validator validator=ValidatorTools.getValidator("between");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testBetween2() throws ValidatorNotFoundException{
		String value="12345678";
		Validator validator=ValidatorTools.getValidator("between");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testBetween3() throws ValidatorNotFoundException{
		String value="1234567890123";
		Validator validator=ValidatorTools.getValidator("between");
		
		assertEquals(false,validator.validate(value));
	}
	
	
	public void testMaxStringSize1() throws ValidatorNotFoundException{
		String value="12345";
		Validator validator=ValidatorTools.getValidator("less8");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testMaxStringSize2() throws ValidatorNotFoundException{
		String value="";
		Validator validator=ValidatorTools.getValidator("less8");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testMaxStringSize3() throws ValidatorNotFoundException{
		String value=null;
		Validator validator=ValidatorTools.getValidator("less8");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testMaxStringSize4() throws ValidatorNotFoundException{
		String value="12345678";
		Validator validator=ValidatorTools.getValidator("less8");
		
		assertEquals(true,validator.validate(value));
	}
	
	
	public void testMaxStringSize5() throws ValidatorNotFoundException{
		String value="123456789";
		Validator validator=ValidatorTools.getValidator("less8");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testMaxStringSize6() throws ValidatorNotFoundException{
		String value="１２３４５６７８";
		Validator validator=ValidatorTools.getValidator("less8");
		
		assertEquals(true,validator.validate(value));
	}
	
	
	public void testMaxStringSize7() throws ValidatorNotFoundException{
		String value="１２";
		Validator validator=ValidatorTools.getValidator("less8");
		
		assertEquals(true,validator.validate(value));
	}
	
	
	public void testMaxStringByte1() throws ValidatorNotFoundException{
		String value="12345";
		Validator validator=ValidatorTools.getValidator("less8b");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testMaxStringByte2() throws ValidatorNotFoundException{
		String value="";
		Validator validator=ValidatorTools.getValidator("less8b");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testMaxStringByte3() throws ValidatorNotFoundException{
		String value=null;
		Validator validator=ValidatorTools.getValidator("less8b");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testMaxStringByte4() throws ValidatorNotFoundException{
		String value="12345678";
		Validator validator=ValidatorTools.getValidator("less8b");
		
		assertEquals(true,validator.validate(value));
	}
	
	
	public void testMaxStringByte5() throws ValidatorNotFoundException{
		String value="123456789";
		Validator validator=ValidatorTools.getValidator("less8b");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testMaxStringByte6() throws ValidatorNotFoundException{
		String value="１２３４５６７８";
		Validator validator=ValidatorTools.getValidator("less8b");
		
		assertEquals(false,validator.validate(value));
	}
	
	
	public void testMaxStringByte7() throws ValidatorNotFoundException{
		String value="１２";
		Validator validator=ValidatorTools.getValidator("less8b");
		
		assertEquals(true,validator.validate(value));
	}
	
	
	public void testAsciiChar1() throws ValidatorNotFoundException{
		String value="abc";
		Validator validator=ValidatorTools.getValidator("asciiChar");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testAsciiChar2() throws ValidatorNotFoundException{
		String value="12a";
		Validator validator=ValidatorTools.getValidator("asciiChar");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testStartAsciiChar1() throws ValidatorNotFoundException{
		String value="abc12";
		Validator validator=ValidatorTools.getValidator("startasciiChar");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testStartAsciiChar2() throws ValidatorNotFoundException{
		String value="12a";
		Validator validator=ValidatorTools.getValidator("startasciiChar");
		
		assertEquals(false,validator.validate(value));
	}
	
	public void testAsciiUnderbar1() throws ValidatorNotFoundException{
		String value="12a";
		Validator validator=ValidatorTools.getValidator("AsciiNumberAndCharAndUnderBar");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testAsciiUnderbar2() throws ValidatorNotFoundException{
		String value="_12";
		Validator validator=ValidatorTools.getValidator("AsciiNumberAndCharAndUnderBar");
		
		assertEquals(true,validator.validate(value));
	}
	
	public void testAsciiUnderbar3() throws ValidatorNotFoundException{
		String value="a-b";
		Validator validator=ValidatorTools.getValidator("AsciiNumberAndCharAndUnderBar");
		
		assertEquals(false,validator.validate(value));
	}
}
