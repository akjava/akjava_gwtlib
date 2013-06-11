package test;

import java.util.List;

import junit.framework.TestCase;

import com.akjava.lib.common.form.FormData;
import com.akjava.lib.common.form.FormDataDto;
import com.akjava.lib.common.utils.ValuesUtils;
import com.google.common.base.Joiner;

public class FormDataDtoTest extends TestCase{

	public void testFromCsvLine1(){
		String csv="name\t" +
				"ClassName\t" +
				"description";
		FormData data=FormDataDto.getCsvLineToFormDataFunction().apply(csv);
		String converted=FormDataDto.getFormDataWithoutOptionsToCsvFunction().apply(data);
		
		assertEquals(csv, converted);
	}
	
	public void testFromCsv2(){
		String csv="name\t" +
				"ClassName\t" +
				"description";
		String option="hello\tworld\ttext\t\t\t\t\t\t";
		csv+="\n"+option;
		
		List<FormData> datas=FormDataDto.linesToFormData(ValuesUtils.toListLines(csv));
		FormData data=datas.get(0);
		
		
		Joiner lineJoiner=Joiner.on("\n");
		String converted=lineJoiner.join(FormDataDto.getFormDataToCsvFunction().apply(data));
		
		assertEquals(csv, converted);
	}

	
	
	public void testCheckLabelText1(){
		String form="name\t" +
				"ClassName\t" +
				"description";
		
		String field1="name\t" +
				"key\t" +
				"check\t" +
				"YES,NO\t" +
				"default\t" +
				"yes\t" +
				"notempty,asciichar\t"+
				"placeholder\t" +
				"comment";
		
		List<FormData> datas=FormDataDto.linesToFormData(ValuesUtils.toListLines(form+"\n"+field1));
		FormData data=datas.get(0);
		
		assertEquals(data.getLabelText("key", "YES"), "YES");
	}
	public void testCheckLabelText2(){
		String form="name\t" +
				"ClassName\t" +
				"description";
		
		String field1="name\t" +
				"key\t" +
				"check\t" +
				"YES,NO\t" +
				"default\t" +
				"yes\t" +
				"notempty,asciichar\t"+
				"placeholder\t" +
				"comment";
		
		List<FormData> datas=FormDataDto.linesToFormData(ValuesUtils.toListLines(form+"\n"+field1));
		FormData data=datas.get(0);
		
		assertEquals(data.getLabelText("key", "on"), "YES");
	}
	public void testCheckLabelText3(){
		String form="name\t" +
				"ClassName\t" +
				"description";
		
		String field1="name\t" +
				"key\t" +
				"check\t" +
				"YES,NO\t" +
				"default\t" +
				"yes\t" +
				"notempty,asciichar\t"+
				"placeholder\t" +
				"comment";
		
		List<FormData> datas=FormDataDto.linesToFormData(ValuesUtils.toListLines(form+"\n"+field1));
		FormData data=datas.get(0);
		
		assertEquals(data.getLabelText("key", ""), "NO");
	}
	
	public void testCheckLabelText4(){
		String form="name\t" +
				"ClassName\t" +
				"description";
		
		String field1="name\t" +
				"key\t" +
				"check\t" +
				"YES\t" +
				"default\t" +
				"yes\t" +
				"notempty,asciichar\t"+
				"placeholder\t" +
				"comment";
		
		List<FormData> datas=FormDataDto.linesToFormData(ValuesUtils.toListLines(form+"\n"+field1));
		FormData data=datas.get(0);
		
		assertEquals(data.getLabelText("key", ""), "");
	}
	public void testCheckLabelText5(){
		String form="name\t" +
				"ClassName\t" +
				"description";
		
		String field1="name\t" +
				"key\t" +
				"check\t" +
				"\t" +
				"default\t" +
				"yes\t" +
				"notempty,asciichar\t"+
				"placeholder\t" +
				"comment";
		
		List<FormData> datas=FormDataDto.linesToFormData(ValuesUtils.toListLines(form+"\n"+field1));
		FormData data=datas.get(0);
		
		assertEquals(data.getLabelText("key", ""), "");
	}
}
