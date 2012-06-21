package com.akjava.gwt.lib.client.widget;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;

public class WordFilterBox extends HorizontalPanel{
private TextBox textBox;
private HorizontalPanel middlePanel;
public TextBox getTextBox() {
	return textBox;
}

public void setTextBox(TextBox textBox) {
	this.textBox = textBox;
}

public HorizontalPanel getMiddlePanel() {
	return middlePanel;
}

public void setMiddlePanel(HorizontalPanel middlePanel) {
	this.middlePanel = middlePanel;
}

private RadioButton startWith;
private RadioButton contain;
private RadioButton endWith;
private RadioButton exactly;

public WordFilterBox(TextBox textbox){
	if(textbox==null){
		textbox=new TextBox();
		add(textbox);
	}
	this.textBox = textbox;
	
	
	
	middlePanel = new HorizontalPanel();
	add(middlePanel);
	HorizontalPanel radioPanel=new HorizontalPanel();
	add(radioPanel);
	startWith = new RadioButton("mode", "Start With");
	radioPanel.add(startWith);
	startWith.setValue(true);
	contain = new RadioButton("mode", "Contain");
	radioPanel.add(contain);
	endWith = new RadioButton("mode", "End With");
	radioPanel.add(endWith);
	exactly = new RadioButton("mode", "exactly");
	radioPanel.add(exactly);
	
	endWith.setValue(true);
}

public WordFilterBox(){
	this(null);
}
public boolean isEndsWith(){
	return endWith.getValue();
}
public boolean iStartWith(){
	return startWith.getValue();
}
public static final int START_MODE=0;
public static final int CONTAIN_MODE=1;
public static final int END_MODE=2;
public static final int EXACTLY_MODE=3;
public int getMode(){
	if(startWith.getValue()){
		return START_MODE;
	}else if(contain.getValue()){
		return CONTAIN_MODE;
	}else if(exactly.getValue()){
		return EXACTLY_MODE;
	}else{
		return END_MODE;
	}
}

public boolean isMutchText(String word){
	String text=textBox.getText();
	if(text.isEmpty()){
		return true;
	}
	text=text.toLowerCase();
	word=word.toLowerCase();
	switch(getMode()){
	case START_MODE:
		if(word.startsWith(text)){
			return true;
		}
		break;
	case END_MODE:
		if(word.endsWith(text)){
			return true;
		}
		break;
	case CONTAIN_MODE:
		if(word.indexOf(text)!=-1){
			return true;
		}
		break;
	case EXACTLY_MODE:
		if(word.equals(text)){
			return true;
		}
		break;
	}
	
	return false;
}

/**
 * @deprecated
 * @return
 */
public String getFilterText(){
	if(textBox.getText().isEmpty()){
		return "";
	}
	
	//to keep textbox clean,chomp space or some punctuation.
	textBox.setText(chompWord(textBox.getText()));
	
	/*
	if(startWith.getValue()){
		return textBox.getText()+"%";
	}else if(contain.getValue()){
		return "%"+textBox.getText()+"%";
	}else if(exactly.getValue()){
		return textBox.getText();
	}else{
		return "%"+textBox.getText();
	}*/
	return getFilterText(textBox.getText(), getMode());
}

public static String getFilterText(String text,int mode){
	if(mode==START_MODE){
		return text+"%";
	}else if(mode==CONTAIN_MODE){
		return "%"+text+"%";
	}else if(mode==EXACTLY_MODE){
		return text;
	}else{
		return "%"+text;
	}
}

public void setText(String text){
	textBox.setText(text);
}


public static final String[] chomps={",",".",":","!","?",";"};
public static String chompWord(String text){
	text=text.trim();
	boolean dochomp=false;
	for(int i=0;i<chomps.length;i++){
		if(text.endsWith(chomps[i])){
			dochomp=true;
		}
	}
	if(dochomp){
		return text.substring(0,text.length()-1);
	}else{
		return text;
	}
}

public void setMode(int mode) {
	if(mode==START_MODE){
		startWith.setValue(true);
	}else if(mode==CONTAIN_MODE){
		contain.setValue(true);
	}else if(mode==EXACTLY_MODE){
		exactly.setValue(true);
	}else{
		endWith.setValue(true);
	}
}


}
