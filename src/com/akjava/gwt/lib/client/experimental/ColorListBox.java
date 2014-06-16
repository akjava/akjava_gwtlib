package com.akjava.gwt.lib.client.experimental;

import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.OptionElement;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.ListBox;

public class ColorListBox extends ListBox{

	public ColorListBox(){
		super();
		this.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				if(getSelectedIndex()==-1){
					return;
				}
				updateBackgroundColor();
			}
		});
	}
	
	public void updateBackgroundColor(){
		if(getSelectedIndex()==-1){
			return;
		}
		String value=getValue(getSelectedIndex());
		getElement().getStyle().setBackgroundColor(value);
	}
	
	
	public String getSelectionColor(){
		if(getSelectedIndex()==-1){
			return null;
		}
		return getValue(getSelectedIndex());
	}
	
	public void setSelectedColor(String color){
		for(int i=0;i<this.getItemCount();i++){
			if(this.getValue(i).equals(color)){
				this.setSelectedIndex(i);
				break;
			}
		}
	}
	
	@Override
	public void setSelectedIndex(int index) {
		super.setSelectedIndex(index);
		updateBackgroundColor();
	}

	@Override
	public void addItem(String item, String value) {
		super.addItem(item, value);
		updateColors();
	}

	/**
	 * call this when you add item
	 */
	public void updateColors(){
		//Add item to listBox.
		SelectElement selectElement = SelectElement.as(this.getElement());
		NodeList<OptionElement> options = selectElement.getOptions();

		for (int i = 0; i < options.getLength(); i++) {
			//LogUtils.log("setbackground:"+options.getItem(i).getValue());
		     options.getItem(i).getStyle().setBackgroundColor(options.getItem(i).getValue());
		}
		
		updateBackgroundColor();
	}
}
