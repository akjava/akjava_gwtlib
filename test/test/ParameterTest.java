package test;

import junit.framework.TestCase;

import com.akjava.lib.common.param.Parameter;
import com.akjava.lib.common.param.ParameterUtils;

public class ParameterTest extends TestCase{

	public void testSimple1(){
		String line="param";
		Parameter p=ParameterUtils.parse(line);
		
		assertEquals("param", p.getName());
	}
	
	public void testSimple2(){
		String line="param()";
		Parameter p=ParameterUtils.parse(line);
		
		assertEquals("param", p.getName());
	}
	
	public void testSimple3(){
		String line="param(1)";
		Parameter p=ParameterUtils.parse(line);
		
		assertEquals("param", p.getName());
	}
	
	public void testSimple4(){
		String line="param(1:2)";
		Parameter p=ParameterUtils.parse(line);
		
		assertEquals("param", p.getName());
	}
	
	public void testAttribute1(){
		String line="param";
		Parameter p=ParameterUtils.parse(line);
		int s=p.getAttributes().size();
		assertEquals(0, s);
	}
	
	public void testAttribute2(){
		String line="param()";
		Parameter p=ParameterUtils.parse(line);
		int s=p.getAttributes().size();
		assertEquals(0, s);
	}
	
	public void testAttribute3(){
		String line="param(x)";
		Parameter p=ParameterUtils.parse(line);
		int s=p.getAttributes().size();
		assertEquals(1, s);
	}
	public void testAttribute4(){
		String line="param(2:2)";
		Parameter p=ParameterUtils.parse(line);
		int s=p.getAttributes().size();
		assertEquals(2, s);
	}
	
	public void testAttribute5(){
		String line="param(2:2";
		Parameter p=ParameterUtils.parse(line);
		int s=p.getAttributes().size();
		assertEquals(2, s);
	}
	
	public void testAttributeValue1(){
		String line="param(1)";
		Parameter p=ParameterUtils.parse(line);
		String v=p.get(0);
		assertEquals("1", v);
	}
	public void testAttributeValue2(){
		String line="param(1:2)";
		Parameter p=ParameterUtils.parse(line);
		String v=p.get(1);
		assertEquals("2", v);
	}
	public void testAttributeValue3(){
		String line="param(1,2)";
		Parameter p=ParameterUtils.parse(line);
		String v=p.get(0);
		assertEquals("1,2", v);
	}
	
	public void testRemain(){
		String line="param(1,2)hello world";
		Parameter p=ParameterUtils.parse(line);
		String v=p.getRemain();
		assertEquals("hello world", v);
	}
	
	public void testRemain2(){
		String line="param(1,2)";
		Parameter p=ParameterUtils.parse(line);
		String v=p.getRemain();
		assertTrue(v==null);
	}
	
	public void testToString1(){
		String line="param";
		Parameter p=ParameterUtils.parse(line);

		assertEquals(line, p.toString());
	}
	
	public void testToString2(){
		String line="param()";
		Parameter p=ParameterUtils.parse(line);

		assertEquals("param", p.toString());
	}
	
	public void testToString3(){
		String line="param(x)";
		Parameter p=ParameterUtils.parse(line);

		assertEquals(line, p.toString());
	}
	public void testToString4(){
		String line="param(2:2)";
		Parameter p=ParameterUtils.parse(line);

		assertEquals(line, p.toString());
	}
}
