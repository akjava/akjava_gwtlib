package test;

import java.util.List;

import junit.framework.TestCase;

import com.akjava.lib.common.utils.CSVUtils;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class LineSplitTest extends TestCase{

	/**
	 * text split ignore last lines
	 */
	public void test1(){
		String text="hello\n\n";
		String[] lines=CSVUtils.splitLines(text);
		assertEquals(1, lines.length);
	}
	
	//split all lines
	public void test2(){
		
		String text="hello\n\n";
		Iterable<String> sp=Splitter.on('\n').split(text);
		List<String> vs=Lists.newArrayList(sp);

		assertEquals(3, vs.size());
	}
}
