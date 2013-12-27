package test.common.form.validator;

import java.util.List;

import com.akjava.lib.common.form.FormData;
import com.akjava.lib.common.form.FormDataDto;
import com.akjava.lib.common.form.FormFieldData;
import com.akjava.lib.common.tag.TagBuilder;
import com.akjava.lib.common.utils.ValuesUtils;

import junit.framework.TestCase;

public class CheckTest extends TestCase{

	public void  testTotalCheck1(){
		String form="name\t" +
				"ClassName\t" +
				"description";
		
		String line="Test	night	check	夜";
		List<FormData> datas=FormDataDto.linesToFormData(ValuesUtils.toListLines(form+"\n"+line));
		FormData data=datas.get(0);
		
		FormFieldData fdata=data.getFormFieldDatas().get(0);
		
		
		assertEquals("collect", fdata.toString());
	}
}
