package com.akjava.gwt.lib.client.experimental.opencv;

import java.util.List;

import com.akjava.lib.common.graphics.Rect;
import com.google.common.collect.Lists;

public  class PositiveData{
	private String fileName;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public List<Rect> getRects() {
		return rects;
	}
	public void setRects(List<Rect> rects) {
		this.rects = rects;
	}
	private List<Rect> rects=Lists.newArrayList();
}

