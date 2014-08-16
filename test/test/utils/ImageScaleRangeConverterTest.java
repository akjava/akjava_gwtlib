package test.utils;

import junit.framework.TestCase;

import com.akjava.gwt.lib.client.experimental.ImageScaleRangeConverter;

public class ImageScaleRangeConverterTest extends TestCase{

	public void testZero(){
		int v=0;
		double dv=getScale(v);
		assertEquals(v, scaleToRangeValue(dv));
	}
	
	
	
	public void testMinus(){
		int v=-99;
		double dv=getScale(v);
		assertEquals(v, scaleToRangeValue(dv));
	}
	
	public void testPlus(){
		int v=90;
		double dv=getScale(v);
		assertEquals(v, scaleToRangeValue(dv));
	}
	
	public void testMinus2(){
		int v=-10;
		double dv=getScale(v);
		assertEquals(v, scaleToRangeValue(dv));
	}
	
	public void testPlus2(){
		int v=10;
		double dv=getScale(v);
		assertEquals(v, scaleToRangeValue(dv));
	}
	
	public void testMinus3(){
		int v=-1;
		double dv=getScale(v);
		assertEquals(v, scaleToRangeValue(dv));
	}
	
	public void testPlus3(){
		int v=1;
		double dv=getScale(v);
		assertEquals(v, scaleToRangeValue(dv));
	}
	
	
	
	private double getScale(int v){
return new ImageScaleRangeConverter().convert(v);
	}
	
	private int scaleToRangeValue(double scale){
		return new ImageScaleRangeConverter().reverse().convert(scale);
	}
}
