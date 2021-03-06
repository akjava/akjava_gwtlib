package test.lbp;

import java.util.List;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import com.akjava.gwt.lib.client.experimental.lbp.BinaryPattern;
import com.akjava.gwt.lib.client.experimental.lbp.SimpleLBP;
import com.google.common.base.Joiner;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

public class LBPTest extends TestCase {

	public void testSimple(){
		int[][] ints=new int[4][4];
		ints[1][1]=128;
		ints[2][2]=128;
		
		SimpleLBP lbp=new SimpleLBP(true,1);
		int[][] converted=lbp.convert(ints);
		
		System.out.println("input");
		for(int i=0;i<ints.length;i++){
			String result=Joiner.on(",").join(Ints.asList(ints[i]));
			System.out.println(result);
		}
		System.out.println("converted");
		for(int i=0;i<converted.length;i++){
			String result=Joiner.on(",").join(Ints.asList(converted[i]));
			System.out.println(result);
		}
		
		int[] retInt=BinaryPattern.dataToBinaryPattern(converted,2,2,2);
		List<Integer> lists=Ints.asList(retInt);
		List<List<Integer>> p=Lists.partition(lists, 8);
		
		System.out.println("binary");
		for(List<Integer> ints2:p){
			String result=Joiner.on(",").join(ints2);
			System.out.println(result);
		}
		
		
		
	}
	
	/*
	 * test step by step and direct same
	 */
	public void testSame1(){
		int[][] ints=new int[4][4];
		ints[1][1]=128;
		ints[2][2]=128;
		
		SimpleLBP lbp=new SimpleLBP(true,1);
		int[][] converted=lbp.convert(ints);
		
		int[] retInt=BinaryPattern.dataToBinaryPattern(converted,2,2,2);
		List<Integer> lists=Ints.asList(retInt);
		
		String lbpPlutBinaryPattern=Joiner.on(",").join(lists);
		String direct=Joiner.on(",").join(Ints.asList(lbp.dataToBinaryPattern(ints, 2, 2)));
		
		assertEquals(lbpPlutBinaryPattern, direct);
			
	}
	
	public void testBench(){
		Stopwatch watch1=Stopwatch.createUnstarted();
		Stopwatch watch2=Stopwatch.createUnstarted();
		
		for(int k=0;k<100;k++){
		int[][] ints=new int[36][36];
		
		for(int i=0;i<36;i++){
			for(int j=0;j<36;j++){
				int v=(int) (256*Math.random());
				ints[i][j]=v;
			}
		}
		
		SimpleLBP lbp=new SimpleLBP(true,2);
		watch1.start();
		int[][] converted=lbp.convert(ints);
		
		int[] retInt=BinaryPattern.dataToBinaryPattern(converted,2,4,4);
		List<Integer> lists=Ints.asList(retInt);
		
		String lbpPlutBinaryPattern=Joiner.on(",").join(lists);
		watch1.stop();
		watch2.start();
		String direct=Joiner.on(",").join(Ints.asList(lbp.dataToBinaryPattern(ints, 4, 4)));
		watch2.stop();
		
		if(!lbpPlutBinaryPattern.equals(direct)){
			fail();
		}
		
		}
		
		System.out.println("convine:"+watch1.elapsed(TimeUnit.MILLISECONDS)+",direct:"+watch2.elapsed(TimeUnit.MILLISECONDS));
	}
	
	
	
	public void testSame2(){
		int[][] ints=new int[36][36];
		
		for(int i=0;i<36;i++){
			for(int j=0;j<36;j++){
				int v=(int) (256*Math.random());
				ints[i][j]=v;
			}
		}
		
		SimpleLBP lbp=new SimpleLBP(true,2);
		int[][] converted=lbp.convert(ints);
		
		int[] retInt=BinaryPattern.dataToBinaryPattern(converted,2,4,4);
		List<Integer> lists=Ints.asList(retInt);
		
		String lbpPlutBinaryPattern=Joiner.on(",").join(lists);
		String direct=Joiner.on(",").join(Ints.asList(lbp.dataToBinaryPattern(ints, 4, 4)));
		
		assertEquals(lbpPlutBinaryPattern, direct);
		
		
	}
	
	public void testBinary3x3(){
		int[][] ints=new int[3][3];
		ints[1][1]=128;
		SimpleLBP lbp=new SimpleLBP(true,1);
		int[][] converted=lbp.convert(ints);
		int[] retInt=BinaryPattern.dataToBinaryPattern(converted,3,0,0);
		
		assertEquals(9*8, retInt.length);
		
		String result=SimpleLBP.toBinaryPatternToDebug(retInt,3,3);
		System.out.println(result);
		
		List<Integer> lists=Ints.asList(retInt);
	}
	
	/*
	 * put center not changed
	 */
	public void testBinary3x3Horizontal(){
		int[][] ints=new int[3][3];
		ints[0][1]=128;
		SimpleLBP lbp=new SimpleLBP(true,1);
		int[][] converted=lbp.convert(ints);
		int[] retInt=BinaryPattern.dataToBinaryPattern(converted,3,0,0);
		
		//System.out.println("#before");
		//System.out.println(SimpleLBP.toBinaryPatternToDebug(retInt,3,3));
		
		
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
		
		int[] flipped=SimpleLBP.flipHorizontal(retInt, 3, 3);
		
		String result=SimpleLBP.toBinaryPatternToDebug(flipped,3,3);
		
		assertEquals(correct, result);
		
		
	}
	
	/*
	 * put center not changed
	 */
	public void testBinary3x3Horizontal2(){
		int[][] ints=new int[3][3];
		ints[0][0]=128;
		SimpleLBP lbp=new SimpleLBP(true,1);
		int[][] converted=lbp.convert(ints);
		int[] retInt=BinaryPattern.dataToBinaryPattern(converted,3,0,0);
		
		System.out.println("#before");
		//System.out.println(SimpleLBP.toBinaryPatternToDebug(retInt,3,3));
		/*
 	1 block:0x0 
	2 block:1x0 W=1
	3 block:2x0 
	4 block:0x1 N=1
	5 block:1x1 NW=1
	6 block:2x1 
	7 block:0x2 
	8 block:1x2 
	9 block:2x2 
		 */
		
		String correct=Joiner.on("\n").join(ImmutableList.of(
				"1 block:0x0 ",
				"2 block:1x0 E=1",
				"3 block:2x0 ",
				"4 block:0x1 ",
				"5 block:1x1 NE=1",
				"6 block:2x1 N=1",
				"7 block:0x2 ",
				"8 block:1x2 ",
				"9 block:2x2 "));
		
		int[] flipped=SimpleLBP.flipHorizontal(retInt, 3, 3);
		
		String result=SimpleLBP.toBinaryPatternToDebug(flipped,3,3);
		
		assertEquals(correct, result);
		
		
	}
}
