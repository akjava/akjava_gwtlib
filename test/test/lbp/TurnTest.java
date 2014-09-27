package test.lbp;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.akjava.gwt.lib.client.LogUtils;
import com.akjava.gwt.lib.client.experimental.lbp.BinaryPattern;
import com.akjava.gwt.lib.client.experimental.lbp.SimpleLBP;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

public class TurnTest extends TestCase {
	
	public void testFlipHorizontal(){
		String correct=Joiner.on("\n").join(ImmutableList.of(
				"1 block:0x0 NE=1",
				"2 block:1x0 N=1",
				"3 block:0x1 ",
				"4 block:1x1 "));
				
		int[][] ints=new int[4][4];
		ints[0][1]=128;
		
		
		SimpleLBP lbp=new SimpleLBP(true,1);
		int[] binaryPattern=lbp.dataToBinaryPattern(ints, 2, 2);
				
		int[] flipped=SimpleLBP.flipHorizontal(binaryPattern,2,2);
		
		String result=SimpleLBP.toBinaryPatternToDebug(flipped,2,2);
		
		assertEquals(correct, result);
		
		//logFlipHorizontal();
		
	}
	
	public void test3x3Turn1(){
		int[][] ints=new int[3][3];
		ints[0][1]=128;
		
		
		SimpleLBP lbp=new SimpleLBP(true,1);
		int[] binaryPattern=lbp.dataToBinaryPattern(ints,3,3, 0, 0);
		
		int[] result=SimpleLBP.turn3x3(binaryPattern, 0);
		
		assertTrue(Iterables.elementsEqual(Ints.asList(binaryPattern), Ints.asList(result)));
	}
	
	/*
	public void test3x3Turn2(){
		int[][] ints=new int[4][4];
		ints[0][1]=128;
		
		
		SimpleLBP lbp=new SimpleLBP(true,1);
		int[] binaryPattern=lbp.dataToBinaryPattern(ints,3,3,0,0);
		
		int[] result=SimpleLBP.turn3x3(binaryPattern, 360);
		
		assertTrue(Iterables.elementsEqual(Ints.asList(binaryPattern), Ints.asList(result)));
	}*/
	
	public void test3x3Random1(){
		int[][] ints=new int[28][28];
		for(int i=0;i<28;i++){
			for(int j=0;j<28;j++){
				int v=(int) (Math.random()*256);
				ints[i][j]=v;
			}
		}
		
		SimpleLBP lbp=new SimpleLBP(true,2);
		int[] binaryPattern=lbp.dataToBinaryPattern(ints,3,3,4,4);
		
		int[] first=SimpleLBP.turn3x3(binaryPattern, 45);
		int[] second=SimpleLBP.turn3x3(first, 315);
		
		assertTrue(Iterables.elementsEqual(Ints.asList(binaryPattern),Ints.asList( second)));
		
	}
	
	public void test3x3Random2(){
		int[][] ints=new int[28][28];
		for(int i=0;i<28;i++){
			for(int j=0;j<28;j++){
				int v=(int) (Math.random()*256);
				ints[i][j]=v;
			}
		}
		
		SimpleLBP lbp=new SimpleLBP(true,2);
		int[] binaryPattern=lbp.dataToBinaryPattern(ints,3,3,4,4);
		
		int[] first=SimpleLBP.turn3x3(binaryPattern, 90);
		int[] second=SimpleLBP.turn3x3(first, 270);
		
		assertTrue(Iterables.elementsEqual(Ints.asList(binaryPattern),Ints.asList( second)));
		
	}
	
	
	public void test3x3Turndegree(){
		int[][] ints=new int[3][3];
		ints[0][1]=128;
		
		
		SimpleLBP lbp=new SimpleLBP(true,1);
		int[] binaryPattern=lbp.dataToBinaryPattern(ints,3,3,0,0);
		String result=(SimpleLBP.toBinaryPatternToDebug(binaryPattern,3,3));
		
		String correct=Joiner.on("\n").join(ImmutableList.of(
				"1 block:0x0 E=1",
				"2 block:1x0 ",
				"3 block:2x0 W=1",
				"4 block:0x1 NE=1",
				"5 block:1x1 N=1",
				"6 block:2x1 NW=1",
				"7 block:0x2 ",
				"8 block:1x2 ",
				"9 block:2x2 "));
				
		assertEquals(correct,result);
	}
	public void test3x3Turn45degree(){
		int[][] ints=new int[3][3];
		ints[0][1]=128;
		
		
		SimpleLBP lbp=new SimpleLBP(true,1);
		int[] binaryPattern=lbp.dataToBinaryPattern(ints,3,3,0,0);
		
		int[] result=SimpleLBP.turn3x3(binaryPattern, 45);
		
		System.out.println("binary:"+Joiner.on(",").join(Ints.asList(binaryPattern)));
		System.out.println("result:"+Joiner.on(",").join(Ints.asList(result)));
		
		
		
		
		System.out.println("before");
		System.out.println(SimpleLBP.toBinaryPatternToDebug(binaryPattern,3,3));
		
		System.out.println("turn 45");
		System.out.println(SimpleLBP.toBinaryPatternToDebug(result,3,3));
		
		
		String correct=Joiner.on("\n").join(ImmutableList.of(
				"1 block:0x0 E=1",
				"2 block:1x0 SE=1",
				"3 block:2x0 ",
				"4 block:0x1 ",
				"5 block:1x1 NE=1",
				"6 block:2x1 NW=1",
				"7 block:0x2 ",
				"8 block:1x2 ",
				"9 block:2x2 N=1"));
		assertEquals(correct,SimpleLBP.toBinaryPatternToDebug(result,3,3));
		
		
		//assertTrue(Iterables.elementsEqual(Ints.asList(binaryPattern), Ints.asList(result)));
	}
	
	public void test3x3Turn90degree(){
		int[][] ints=new int[4][4];
		ints[0][1]=128;
		
		
		SimpleLBP lbp=new SimpleLBP(true,1);
		int[] binaryPattern=lbp.dataToBinaryPattern(ints,3,3,0,0);
		
		int[] result=SimpleLBP.turn3x3(binaryPattern, 90);
		
		System.out.println("binary:"+Joiner.on(",").join(Ints.asList(binaryPattern)));
		System.out.println("result:"+Joiner.on(",").join(Ints.asList(result)));
		
		
		
		
		System.out.println("before");
		System.out.println(SimpleLBP.toBinaryPatternToDebug(binaryPattern,3,3));
		
		System.out.println("turn 90");
		System.out.println(SimpleLBP.toBinaryPatternToDebug(result,3,3));
		
		
		String correct=Joiner.on("\n").join(ImmutableList.of(
				"1 block:0x0 ",
				"2 block:1x0 SE=1",
				"3 block:2x0 S=1",
				"4 block:0x1 ",
				"5 block:1x1 E=1",
				"6 block:2x1 ",
				"7 block:0x2 ",
				"8 block:1x2 NE=1",
				"9 block:2x2 N=1"));
		assertEquals(correct,SimpleLBP.toBinaryPatternToDebug(result,3,3));
		
		
		//assertTrue(Iterables.elementsEqual(Ints.asList(binaryPattern), Ints.asList(result)));
	}

	
	
	private List<String> intsToStringList(int[] ints){
		List<String> values=Lists.newArrayList();
		for(int v:ints){
			values.add(Strings.padStart(""+v, 3, ' '));
		}
		return values;
	}
	/** log
	1 block:0x0 N=1
	2 block:1x0 NW=1
	3 block:0x1 
	4 block:1x1 
	 */
	public void logFlipHorizontal(){
		int[][] ints=new int[4][4];
		ints[0][1]=128;
		
		
		SimpleLBP lbp=new SimpleLBP(true,1);
		int[][] converted=lbp.convert(ints);
		
		System.out.println("input");
		for(int i=0;i<ints.length;i++){
			String result=Joiner.on(",").join(intsToStringList(ints[i]));
			System.out.println("y="+i+":"+result);
		}
		System.out.println("average");
		int[][] test=lbp.convertAverageValueForImprovedLBPTest(ints);
		for(int i=0;i<test.length;i++){
			String result=Joiner.on(",").join(intsToStringList(test[i]));
			System.out.println("y="+i+":"+result);
		}
		
		System.out.println("converted");
		for(int i=0;i<converted.length;i++){
			String result=Joiner.on(",").join(intsToStringList(converted[i]));
			System.out.println(result);
		}
		
		System.out.println("binary");
		for(int i=0;i<converted.length;i++){
			List<String> labels=new ArrayList<String>();
			for(int j=0;j<converted[i].length;j++){
				String label=SimpleLBP.toBinaryForDebug(converted[i][j]);
				labels.add(label);
				
			}
			String result=Joiner.on(",").join(labels);
			System.out.println(result);
		}
		
		System.out.println("direction");
		for(int i=0;i<converted.length;i++){
			List<String> labels=new ArrayList<String>();
			for(int j=0;j<converted[i].length;j++){
				String label=SimpleLBP.toDirectionLabelForDebug(converted[i][j]);
				labels.add(label);
				
			}
			String result=Joiner.on(",").join(labels);
			System.out.println("Y="+i+" "+result);
		}
		
		
		int[] retInt=BinaryPattern.dataToBinaryPattern(converted,2,2,2);
		List<Integer> lists=Ints.asList(retInt);
		List<List<Integer>> p=Lists.partition(lists, 8);
		
		System.out.println("binary X x Y");
		
		for(int i=0;i<p.size();i++){
			List<Integer> ints2=p.get(i);
			int x=i%2;
			int y=i/2;
			String result=Joiner.on(",").join(ints2);
			System.out.println((i+1)+" block:"+x+"x"+y+"="+result);
		}	
		
		System.out.println("binary-label");
		System.out.println(SimpleLBP.toBinaryPatternToDebug(retInt,2,2));
	}
}
