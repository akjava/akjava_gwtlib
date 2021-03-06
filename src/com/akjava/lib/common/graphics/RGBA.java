package com.akjava.lib.common.graphics;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkPositionIndex;

public class RGBA {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(alpha);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + blue;
		result = prime * result + green;
		result = prime * result + red;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RGBA other = (RGBA) obj;
		if (Double.doubleToLongBits(alpha) != Double.doubleToLongBits(other.alpha))
			return false;
		if (blue != other.blue)
			return false;
		if (green != other.green)
			return false;
		if (red != other.red)
			return false;
		return true;
	}
	public RGBA(int red, int green, int blue){
		this(red,green,blue,1);
	}
public RGBA(int red, int green, int blue, double alpha) {
		checkArgument(alpha>=0 && alpha<=1.0);
		this.red = checkPositionIndex(red, 255);
		this.green = checkPositionIndex(green, 255);
		this.blue = checkPositionIndex(blue, 255);
		this.alpha = alpha;
	}
private int red;
public int getRed() {
	return red;
}
public void setRed(int red) {
	this.red = checkPositionIndex(red, 255);
}
public int getGreen() {
	return green;
}
public void setGreen(int green) {
	this.green = checkPositionIndex(green, 255);
}
public int getBlue() {
	return blue;
}
public void setBlue(int blue) {
	this.blue = checkPositionIndex(blue, 255);
}
public double getAlpha() {
	return alpha;
}
public void setAlpha(double alpha) {
	this.alpha = alpha;
}

public String toString(){
	return "rgba("+red+","+green+","+blue+","+alpha+")";
}

private int green;
private int blue;
private double alpha;
}
