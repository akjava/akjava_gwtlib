package test.graphics;

import junit.framework.TestCase;

import com.akjava.lib.common.graphics.BilinearCalculator;

public class BilinearTest extends TestCase{

	public void test1(){
		BilinearCalculator calculator=new BilinearCalculator(64,64);
		
		double correct=1;
		double value=calculator.calculateValue(0, 0, 1, 0, 0, 0);
		
		assertEquals(correct, value);
	}
	
	public void test2(){
		BilinearCalculator calculator=new BilinearCalculator(64,64);
		
		double correct=0;
		double value=calculator.calculateValue(0, 0, 0, 0, 0, 0);
		
		assertEquals(correct, value);
	}
	
	public void test3(){
		BilinearCalculator calculator=new BilinearCalculator(64,64);
		
		double correct=0;
		double value=calculator.calculateValue(0.5, 0.5, 0, 0, 0, 0);
		
		assertEquals(correct, value);
	}
	
	public void test4(){
		BilinearCalculator calculator=new BilinearCalculator(64,64);
		
		double correct=1;
		double value=calculator.calculateValue(0.5, 0.5, 1, 1, 1, 1);
		
		assertEquals(correct, value);
	}
	
	public void test5(){
		BilinearCalculator calculator=new BilinearCalculator(64,64);
		
		double correct=1.5;
		double value=calculator.calculateValue(0.5, 0.5, 4, 0, 1, 1);
		
		assertEquals(correct, value);
	}
	
	public void test6(){
		BilinearCalculator calculator=new BilinearCalculator(64,64);
		
		double correct=1;
		double value=calculator.calculateValue(0.9, 1, 10, 0, 0, 0);
		
		assertEquals(correct, value);
	}
	
	public void test7(){
		BilinearCalculator calculator=new BilinearCalculator(64,64);
		
		double correct=9;
		double value=calculator.calculateValue(0.1, 1, 10, 0, 0, 0);
		
		assertEquals(correct, value);
	}
	public void test8(){
		BilinearCalculator calculator=new BilinearCalculator(64,64);
		
		double correct=10;
		double value=calculator.calculateValue(64.5, 64.5, 10, 0, 0, 0);
		
		assertEquals(correct, value);
	}
}
