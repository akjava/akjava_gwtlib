package test.common.form.validator;

import java.util.List;

import junit.framework.TestCase;

import com.akjava.lib.common.form.Validator;
import com.akjava.lib.common.form.ValidatorDto;
import com.akjava.lib.common.form.ValidatorTools;
import com.akjava.lib.common.form.ValidatorTools.ValidatorNotFoundException;
import com.akjava.lib.common.form.Validators;
import com.google.common.collect.Lists;

public class ValidatorDtoTest extends TestCase{

	public void testValidatorToName1(){
		String collect="notempty";//convert lowercase
		try{
		List<Validator> validators=Lists.newArrayList(ValidatorTools.getValidator("notempty"));
		String result=ValidatorDto.validatorListToNamesLine(validators);
		
		assertEquals(collect, result);
		}catch(Exception e){
			fail();
		}
	}
	
	//catch null
	public void testValidatorToName2(){
		String collect="notempty";//convert lowercase
		try{
			List<Validator> validators=Lists.newArrayList(ValidatorTools.getValidator("xxx"));
			//System.out.println(validators);
			String result=ValidatorDto.validatorListToNamesLine(validators);
			
			assertEquals(collect, result);
			}catch(Exception e){
				assertEquals(e.getClass(),new ValidatorNotFoundException("").getClass());
			}
	}
	//multiple
	public void testValidatorToName3(){
		String collect="notempty,asciinumber";
		try{
		List<Validator> validators=Lists.newArrayList(ValidatorTools.getValidator("notempty"),ValidatorTools.getValidator("asciinumber"));
		String result=ValidatorDto.validatorListToNamesLine(validators);
		
		assertEquals(collect, result);
		}catch(Exception e){
			fail();
		}
	}
	//not regist 
	public void testValidatorToName4(){
		String collect="notempty,max(10)";
		try{
		List<Validator> validators=Lists.newArrayList(ValidatorTools.getValidator("notempty"),Validators.maxStringSize(10));
		String result=ValidatorDto.validatorListToNamesLine(validators);
		
		assertEquals(collect, result);
		}catch(Exception e){
			fail();
		}
	}
	
	//multiple
	public void testNameToValidator1(){
		try{
			List<Validator> collect=Lists.newArrayList(ValidatorTools.getValidator("notempty"),ValidatorTools.getValidator("asciinumber"));
			
			String names="notempty,asciinumber";
			
			List<Validator> validators=ValidatorDto.namesLineToValidatorList(names);
		
		assertEquals(collect, validators);
		}catch(Exception e){
			fail();
		}
	}
	
}
