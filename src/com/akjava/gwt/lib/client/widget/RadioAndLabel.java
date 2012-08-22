package com.akjava.gwt.lib.client.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;


public class RadioAndLabel extends HorizontalPanel{
public RadioAndLabel(String name,String labelText,boolean checked){
	 radio=new RadioButton(name);
	 add(radio);
	 if(checked){
		 radio.setValue(checked);
	 }
	 label=new Label(labelText);
	 label.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			//
		}
	});
	 add(label);
}
private RadioButton radio;
public RadioButton getRadio() {
	return radio;
}
private Label label;
public Label getLabel() {
	return label;
}
public boolean getValue() {
	return radio.getValue();
}
public void setValue(boolean b) {
	radio.setValue(b);
}
public String getLabelText(){
	return label.getText();
}
}
