package com.akjava.lib.common.graphics;

public class BilinearCalculator {
private int width;
private int height;

public BilinearCalculator(int width, int height) {
	super();
	this.width = width;
	this.height = height;
}

/**
 * 
 * @param x 
 * @param y
 * @param value
 * @param rightValue x+1
 * @param bottomValue y+1
 * @param rightBottomValue x+1,y+1
 * @return
 */
public double calculateValue(double x,double y,double value,double rightValue,double bottomValue,double rightBottomValue){
	
	if(x>width){
		x=width;
	}
	if(y>height){
		y=height;
	}
	
    double rightRatio=x%1;
    double valueRatioX=1.0-rightRatio;
	
    double bottomRatio=y%1;
    double valueRatioY=1.0-rightRatio;
    
    double upSide=valueRatioX*value+rightRatio*rightValue;
    double downSide=valueRatioX*bottomValue+rightRatio*rightBottomValue;
    
	return valueRatioY*upSide+downSide*bottomRatio;
}

public double calculateValue(double x,double y,BilinearValueGetter valueGetter){
	int intX=(int)x;
	int intY=(int)y;
	return calculateValue(x, y, valueGetter.getValue(intX,intY),valueGetter.getRightValue(intX,intY),valueGetter.getBottomValue(intX,intY),valueGetter.getRightBottomValue(intX,intY));
}

public static interface BilinearValueGetter{
	public double getValue(int x,int y);
	public double getRightValue(int x,int y);
	public double getBottomValue(int x,int y);
	public double getRightBottomValue(int x,int y);
}

public static  class SizeLimitBilinearValueGetter implements BilinearValueGetter{
	private int width;
	private int height;
	public SizeLimitBilinearValueGetter(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	public double getValueAt(int x,int y){
		return 0;
	}
	
	@Override
	public double getValue(int x, int y) {
		return getValueAt(x,y);
	}

	@Override
	public double getRightValue(int x, int y) {
		if(x+1>width){
			return getValueAt(x,y);
		}
		return getValueAt(x+1,y);
	}

	@Override
	public double getBottomValue(int x, int y) {
		if(y+1>height){
			return getValueAt(x,y);
		}
		return getValueAt(x,y+1);
	}

	@Override
	public double getRightBottomValue(int x, int y) {
		if(x+1>width|| y+1>height){
			return getValueAt(x,y);
		}
		return getValueAt(x+1,y+1);
	}
	
}


}
