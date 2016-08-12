package com.akjava.lib.common.graphics;

public class Lerps {

	private Lerps(){}
	public static double lerp(double v1,double v2,double alpha){
		return v1+(v2-v1)*alpha;
	}
}
