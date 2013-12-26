package test.common.form.validator;

import junit.framework.TestCase;

import com.akjava.lib.common.form.StaticValidators;
import com.akjava.lib.common.form.Validator;
import com.akjava.lib.common.form.ValidatorTools;
import com.akjava.lib.common.form.ValidatorTools.ValidatorNotFoundException;

public class ValidatorConvertTest extends TestCase{

	public void testToValidator1(){
		String text=StaticValidators.VALIDATOR_NOT_EMPTY;
		Validator validator;
		try {
			validator = ValidatorTools.getValidator(text);
			assertEquals(text, validator.toString());
		} catch (ValidatorNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}	
	}
	public void testToValidator2(){
		String text=StaticValidators.VALIDATOR_ASCII_NUMBER;
		Validator validator;
		try {
			validator = ValidatorTools.getValidator(text);
			assertEquals(text, validator.toString());
		} catch (ValidatorNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}	
	}
	public void testToValidator3(){
		String text=StaticValidators.VALIDATOR_ASCII_NUMBER_AND_CHAR;
		Validator validator;
		try {
			validator = ValidatorTools.getValidator(text);
			assertEquals(text, validator.toString());
		} catch (ValidatorNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}	
	}
	public void testToValidator4(){
		String text=StaticValidators.VALIDATOR_ASCII_NUMBER_AND_CHAR_AND_UNDERBAR;
		Validator validator;
		try {
			validator = ValidatorTools.getValidator(text);
			assertEquals(text, validator.toString());
		} catch (ValidatorNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}	
	}
	public void testToValidator5(){
		String text=StaticValidators.VALIDATOR_HANKAKU_KANA;
		Validator validator;
		try {
			validator = ValidatorTools.getValidator(text);
			assertEquals(text, validator.toString());
		} catch (ValidatorNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}	
	}
	public void testToValidator6(){
		String text=StaticValidators.VALIDATOR_HIRAGANA;
		Validator validator;
		try {
			validator = ValidatorTools.getValidator(text);
			assertEquals(text, validator.toString());
		} catch (ValidatorNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}	
	}
	public void testToValidator7(){
		String text=StaticValidators.VALIDATOR_HIRAGANA;
		Validator validator;
		try {
			validator = ValidatorTools.getValidator(text);
			assertEquals(text, validator.toString());
		} catch (ValidatorNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}	
	}
	public void testToValidator8(){
		String text=StaticValidators.VALIDATOR_ASCII_CHAR;
		Validator validator;
		try {
			validator = ValidatorTools.getValidator(text);
			assertEquals(text, validator.toString());
		} catch (ValidatorNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}	
	}
	
	public void testToValidator9(){
		String text="max(5)";
		Validator validator;
		try {
			validator = ValidatorTools.getValidator(text);
			assertEquals(text, validator.toString());
		} catch (ValidatorNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}	
	}
	
	public void testToValidator10(){
		String text="maxb(5)";
		Validator validator;
		try {
			validator = ValidatorTools.getValidator(text);
			assertEquals(text, validator.toString());
		} catch (ValidatorNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}	
	}
	public void testToValidator11(){
		String text="between(0:5)";
		Validator validator;
		try {
			validator = ValidatorTools.getValidator(text);
			assertEquals(text, validator.toString());
		} catch (ValidatorNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}	
	}
	public void testToValidator12(){
		String text="avaiable(true:0:1:2)";
		Validator validator;
		try {
			validator = ValidatorTools.getValidator(text);
			assertEquals(text, validator.toString());
		} catch (ValidatorNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}	
	}
	
	/**
	 * number ranged support double so return double text
	 */
	public void testToValidator13(){
		String text="range(0.0:3.0)";
		Validator validator;
		try {
			validator = ValidatorTools.getValidator(text);
			assertEquals(text, validator.toString());
		} catch (ValidatorNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}	
	}
	
	public void testToValidator14(){
		String text="DecimalNumber";
		Validator validator;
		try {
			validator = ValidatorTools.getValidator(text);
			assertEquals(text, validator.toString());
		} catch (ValidatorNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}	
	}
	
	public void testToValidator15(){
		String text="IntegerNumber";
		Validator validator;
		try {
			validator = ValidatorTools.getValidator(text);
			assertEquals(text, validator.toString());
		} catch (ValidatorNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}	
	}
	
	public void testFail1(){
		String text="max";
		Validator validator;
		try {
			validator = ValidatorTools.getValidator(text);
			fail(validator.toString());
		} catch (ValidatorNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}	
	} 
	
	public void testFail2(){
		String text="max(1:2)";
		Validator validator;
		try {
			validator = ValidatorTools.getValidator(text);
			fail(validator.toString());
		} catch (ValidatorNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}	
	} 
	
	public void testFail3(){
		String text="maxb";
		Validator validator;
		try {
			validator = ValidatorTools.getValidator(text);
			fail(validator.toString());
		} catch (ValidatorNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}	
	} 
	public void testFail4(){
		String text="maxb(2:3)";
		Validator validator;
		try {
			validator = ValidatorTools.getValidator(text);
			fail(validator.toString());
		} catch (ValidatorNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}	
	} 
	public void testFail5(){
		String text="between";
		Validator validator;
		try {
			validator = ValidatorTools.getValidator(text);
			fail(validator.toString());
		} catch (ValidatorNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}	
	} 
	public void testFail6(){
		String text="between(1)";
		Validator validator;
		try {
			validator = ValidatorTools.getValidator(text);
			fail(validator.toString());
		} catch (ValidatorNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}	
	} 
	public void testFail7(){
		String text="between(1:2:3)";
		Validator validator;
		try {
			validator = ValidatorTools.getValidator(text);
			fail(validator.toString());
		} catch (ValidatorNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}	
	} 
	public void testFail8(){
		String text="avaiable(1:2:3)";
		Validator validator;
		try {
			validator = ValidatorTools.getValidator(text);
			fail(validator.toString());
		} catch (ValidatorNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}	
	} 
	public void testFail9(){
		String text="avaiable(true)";
		Validator validator;
		try {
			validator = ValidatorTools.getValidator(text);
			fail(validator.toString());
		} catch (ValidatorNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}	
	} 
	public void testFail10(){
		String text="avaiable";
		Validator validator;
		try {
			validator = ValidatorTools.getValidator(text);
			fail(validator.toString());
		} catch (ValidatorNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}	
	}
	

	
	//need min or max value
	public void testFail11(){
		String text="range";
		Validator validator;
		try {
			validator = ValidatorTools.getValidator(text);
			fail(validator.toString());
		} catch (ValidatorNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}	
	} 
	
	
}
