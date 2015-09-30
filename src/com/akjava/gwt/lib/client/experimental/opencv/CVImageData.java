package com.akjava.gwt.lib.client.experimental.opencv;

import java.util.List;

import com.akjava.lib.common.graphics.IntRect;
import com.google.common.collect.Lists;

/*
 * for contain OpenCV Positive data,however if rects is empty ,should detect rect is same as image-size
 */
public  class CVImageData implements HasRects{
	private String fileName;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public List<IntRect> getRects() {
		return rects;
	}
	public void setRects(List<IntRect> rects) {
		this.rects = rects;
	}
	private List<IntRect> rects=Lists.newArrayList();
}

