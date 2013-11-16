package test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import junit.framework.TestCase;

import com.akjava.lib.common.form.FormData;
import com.akjava.lib.common.form.FormDataDto;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class FormFieldDtoRelationTest extends TestCase{

	public void test1(){
		URL url = Resources.getResource("test/resources/relation.txt");
		try {
			String collect="Relation(data),Data(text)";
			String text = Resources.toString(url, Charsets.UTF_8);
			List<FormData> datas=FormDataDto.linesToFormData(text);
			String result="";
			if(datas.size()!=3){
				fail("data size is not 3");
			}
			if(datas.get(0).getChildrens()==null || datas.get(0).getChildrens().size()!=1){
				fail("invalid children size:"+datas.get(0).getClassName());
			}
			if(datas.get(2).getParents()==null || datas.get(2).getParents().size()!=1){
				fail("invalid parent size:"+datas.get(2).getClassName()+":"+datas.get(2).getParents().size());
			}
			result+=datas.get(0).getChildrens().get(0).toString();
			result+=","+datas.get(2).getParents().get(0).toString();
			assertEquals(collect,result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
