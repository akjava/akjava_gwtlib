package com.akjava.gwt.lib.client.experimental.cifar10;

import com.google.gwt.canvas.dom.client.ImageData;

public  class Cifar10Data{
	private int classNumber;
	public int getClassNumber() {
		return classNumber;
	}
	public void setClassNumber(int classNumber) {
		this.classNumber = classNumber;
	}
	public ImageData getImageData() {
		return imageData;
	}
	public void setImageData(ImageData imageData) {
		this.imageData = imageData;
	}
	private ImageData imageData;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}