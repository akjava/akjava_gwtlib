package test.datalist;

import com.akjava.gwt.lib.client.datalist.RichTitle;

import junit.framework.TestCase;

public class RichTitleTest extends TestCase{

	public void testFromLine(){
		String line="hello";
		RichTitle collect=new RichTitle(line);
		assertEquals(line, collect.toString());
	}
	
	public void testFromLine2(){
		String line="hello(sub)";
		RichTitle collect=new RichTitle(line);
		assertEquals(line, collect.toString());
	}
	
	public void testFromLine3(){
		String line="(subonly)";
		RichTitle collect=new RichTitle(line);
		assertEquals(line, collect.toString());
	}
	
	public void testFromLine4(){
		String line="hello/hello2";
		RichTitle collect=new RichTitle(line);
		assertEquals(line, collect.toString());
	}
	
	public void testFromLine5(){
		String line="hello/hello2(sub1)";
		RichTitle collect=new RichTitle(line);
		assertEquals(line, collect.toString());
	}
	
	public void testFromLine6(){
		String line="hello/hello2(sub1/sub2)";
		RichTitle collect=new RichTitle(line);
		assertEquals(line, collect.toString());
	}
	public void testFromLine7(){
		String line="";
		RichTitle collect=new RichTitle(line);
		assertEquals(line, collect.toString());
	}
}
