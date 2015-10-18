package com.akjava.gwt.inpaint.client;

import com.akjava.lib.common.graphics.Rect;

/**
 * copy from html5app
 * @author aki
 *
 */
public class MaskData {
private boolean transparent=true;//most use
private String color="#ffffff";
private int expand=2;
private int fade=5;
private boolean similarColor;
private int maxLength=16;
private Rect clipRect=new Rect(0,0,0,0);
private boolean useClip;

public MaskData fade(int fade){
	this.fade=fade;
	return this;
}

public MaskData expand(int expand){
	this.expand=expand;
	return this;
}

public Rect getClipRect() {
	return clipRect;
}
public void setClipRect(Rect clipRect) {
	this.clipRect = clipRect;
}
public boolean isUseClip() {
	return useClip;
}
public void setUseClip(boolean useClip) {
	this.useClip = useClip;
}
private boolean invert;
public boolean isTransparent() {
	return transparent;
}
public void setTransparent(boolean transparent) {
	this.transparent = transparent;
}
public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}
public int getExpand() {
	return expand;
}
public void setExpand(int expand) {
	this.expand = expand;
}
public int getFade() {
	return fade;
}
public void setFade(int fade) {
	this.fade = fade;
}
public boolean isSimilarColor() {
	return similarColor;
}
public void setSimilarColor(boolean similarColor) {
	this.similarColor = similarColor;
}
public int getMaxLength() {
	return maxLength;
}
public void setMaxLength(int maxLength) {
	this.maxLength = maxLength;
}

public boolean isInvert() {
	return invert;
}
public void setInvert(boolean invert) {
	this.invert = invert;
}
@Override
public String toString() {
	return "MaskData [transparent=" + transparent + ", color=" + color + ", expand=" + expand + ", fade=" + fade + ", similarColor=" + similarColor
			+ ", maxLength=" + maxLength + ", clipRect=" + clipRect + ", useClip=" + useClip + ", invert=" + invert + "]";
}
}
