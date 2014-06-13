package com.akjava.gwt.lib.client;

import com.google.common.base.Ascii;
import com.google.common.base.Optional;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.TextArea;

public class TextSelection {

	TextArea targetTextArea;
	public TextArea getTargetTextArea() {
		return targetTextArea;
	}
	public TextSelection(int start,int end,TextArea text){
		this.start=start;
		this.end=end;
		this.targetTextArea=text;
	}
	public void replace(String replace) {
		setText(getSelectionBefore()+replace+getSelectionAfter());
	}
	
	public void replaceInLine(String replace) {
		
		if(replace.endsWith("\n")){
			//replace text should not contain line-end
			replace=replace.substring(0,replace.length()-1);
		}
		
		
		String selection=getSelection();
		String append="";
		if(selection.endsWith("\n")){//not last line
			append="\n";
		}
		setText(getSelectionBefore()+replace+append+getSelectionAfter());
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
	public boolean containLineBreak(){
		return getSelection().indexOf("\n")!=-1;
	}
	public String getSelection() {
		return targetTextArea.getText().substring(start,end);
	}
	public String getLineEndRemovedSelection() {
		String selection=getSelection();
		if(selection.endsWith("\n")){
			return selection.substring(0,selection.length()-1);
		}else{
			return selection;
		}
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
	
	public void select(){
		targetTextArea.setSelectionRange(start, end-start);
	}
	
	public void setFocus(){
		targetTextArea.setFocus(true);
	}
	public void setCursorPos(int pos){
		targetTextArea.setCursorPos(pos);
	}
	
	public String getTextUntilPrevLineBreak(){
		String targetText=targetTextArea.getText();
		String text="";
		for(int i=start-1;i>=0;i--){
			char ch=targetText.charAt(i);
			if(ch==Ascii.LF){
				break;
			}else{
				text=ch+text;
			}
		}
		return text;
	}
	/**
	 * not contain prev-line line-break
	 * but contain end line-break if exits
	 * @return
	 */
	public Optional<TextSelection> getNextLine(){
		int firstLineBreak=-1;
		String targetText=targetTextArea.getText();
		if(end>=targetText.length()){
			return Optional.absent();
		}
		if(targetText.charAt(end-1)==Ascii.LF){
			firstLineBreak=end;
			LogUtils.log("end with line-break");
		}else{
			for(int i=end+1;i<targetText.length();i++){
			char ch=targetText.charAt(i);
			if(ch==Ascii.LF){
				firstLineBreak=i;
				break;
			}
			}
		}
		if(firstLineBreak==-1){
			return Optional.absent();
		}
		int start=firstLineBreak+1;
		if(start>=targetText.length()){
			return Optional.absent();
		}
		int end=start;
		//String text="";
		for(int i=firstLineBreak+1;i<targetText.length();i++){
			char ch=targetText.charAt(i);
			end=i;
			if(ch==Ascii.LF){
				break;
			}
		}
		LogUtils.log("tmp-next:"+start+","+Math.min(end+1,targetText.length()));
		return Optional.of(new TextSelection(start,Math.min(end+1,targetText.length()),targetTextArea));
	}
	
	/**
	 * not contain prev-line line-break
	 * but contain end line-break if exits
	 * @return
	 */
	public TextSelection getCurrentLine(){
		
		
		
		int firstLineBreak=0;
		String targetText=targetTextArea.getText();
		
		if(targetText.length()>start && targetText.charAt(start)==Ascii.LF){
			if(start>0 && targetText.charAt(start-1)==Ascii.LF || start==0){
			//first line is line-separator
			return new TextSelection(start,start,targetTextArea);//this empty selection
			}
		}
		
		for(int i=start-1;i>=0;i--){
			char ch=targetText.charAt(i);
			if(ch==Ascii.LF){
				firstLineBreak=i+1;
				break;
			}
			}
		
		int start=firstLineBreak;
		int end=start;
		//String text="";
		for(int i=firstLineBreak+1;i<targetText.length();i++){
			char ch=targetText.charAt(i);
			end=i;
			if(ch==Ascii.LF){
				break;
			}
		}
		return new TextSelection(start,Math.min(end+1,targetText.length()),targetTextArea);
	}
	
	 public static Optional<TextSelection> createTextSelection(TextArea textArea){
	    	try{
		  		if(textArea.getSelectedText()==null ){
		  			return Optional.absent();
		  		}}catch(Exception e){
		  			return Optional.absent();
		  		}
		  		int pos=textArea.getCursorPos();
		  		if(pos==textArea.getText().length()){
		  			return Optional.of(new TextSelection(textArea.getText().length(),textArea.getText().length(),textArea));
		  		}
		  		
		  		int ch=textArea.getText().charAt(pos);
		  		int len=textArea.getSelectionLength();
		  		
		  		if(ch==Ascii.CR){
		  			pos+=2;
		  		}
		  		
		  		
		  		
		  		//String realSelect=textArea.getText().substring(pos,pos+len);
		  		return Optional.of(new TextSelection(pos,pos+len,textArea));
	    }
}
