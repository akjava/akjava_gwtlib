package com.akjava.gwt.lib.client.experimental;

import com.google.common.base.Converter;

/*
 * min -99 max 90
 */
public class ImageScaleRangeConverter extends Converter<Integer,Double>{

	@Override
	protected Double doForward(Integer rangeValue) {
		if(rangeValue==0){
			return 1.0;
		}
		if(rangeValue>0){
			return 1.0+rangeValue*0.1;
		}else if(rangeValue<0){
			
			return 1.0+rangeValue*0.01;
		}
		return 1.0;
	}

	@Override
	protected Integer doBackward(Double scale) {
		if(scale==1){
			return 0;
		}else if(scale>1){
			return (int)(scale*10)-10;
		}else{//scale<1
			int r=100-(int)(scale*100);
			return -r;
		}
	}
	
	private static Converter<Integer,Double> converter;
	public static Converter<Integer,Double> getRangeToScale(){
		Converter<Integer,Double> result=converter;
		return (result == null) ? converter = new ImageScaleRangeConverter() : result;
	}
	
	private static Converter<Double,Integer> reverse;
	public static Converter<Double,Integer> getScaleToRange(){
		Converter<Double,Integer> result=reverse;
		return (result == null) ? reverse = new ImageScaleRangeConverter().reverse() : result;
	}

}
