package com.akjava.gwt.lib.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.TextArea;

public class TextSelection {

	TextArea targetTextArea;
	public TextSelection(int start,int end,TextArea text){
		this.start=start;
		this.end=end;
		this.targetTextArea=text;
	}
	public void replace(String replace) {
		setText(getSelectionBefore()+replace+getSelectionAfter());
	}
	int start;
	int end;

	public boolean isSelected(){
		return end>start;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public String getSelection() {
		return targetTextArea.getText().substring(start,end);
	}
	public String getSelectionBefore(){
		return targetTextArea.getText().substring(0,start);
	}
	public String getSelectionAfter(){
		return targetTextArea.getText().substring(end);
	}
	public void setText(String text){
		targetTextArea.setText(text);
		
	}
	public void setFocus(){
		targetTextArea.setFocus(true);
	}
	public void setCursorPos(int pos){
		targetTextArea.setCursorPos(pos);
	}
	
	
	 public static TextSelection createTextSelection(TextArea textArea){
	    	try{
		  		if(textArea.getSelectedText()==null ){
		  			return null;
		  		}}catch(Exception e){
		  			return null;
		  		}
		  		int pos=textArea.getCursorPos();
		  		if(pos==textArea.getText().length()){
		  			return new TextSelection(textArea.getText().length(),textArea.getText().length(),textArea);
		  		}
		  		GWT.log("pos:"+pos);
		  		int ch=textArea.getText().charAt(pos);
		  		int len=textArea.getSelectionLength();
		  		GWT.log("ch:"+ch+","+len);
		  		if(ch==13){
		  			pos+=2;
		  		}
		  		GWT.log("pos:"+pos);
		  		
		  		
		  		//String realSelect=textArea.getText().substring(pos,pos+len);
		  		return new TextSelection(pos,pos+len,textArea);
	    }
}
