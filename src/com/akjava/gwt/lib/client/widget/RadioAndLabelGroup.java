package com.akjava.gwt.lib.client.widget;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class RadioAndLabelGroup extends HorizontalPanel{
List<RadioAndLabel> radios=new ArrayList<RadioAndLabel>();
//TODO future
public RadioAndLabelGroup(String key,Set<String> values,String selection){
	for(String label:values){
		boolean b=false;
		if(label.equals(selection)){
			b=true;
		}
		RadioAndLabel rl=new RadioAndLabel(key, label, b);
		add(rl);
		radios.add(rl);
	}
	if(selection==null){
		radios.get(0).setValue(true);
	}
}

public void addClickHandler(ClickHandler handler){
	for(RadioAndLabel rl:radios){
		rl.getRadio().addClickHandler(handler);
		rl.getLabel().addClickHandler(handler);
	}
}

public int getSelectedIndex(){
	int index=-1;
	for(int i=0;i<radios.size();i++){
		RadioAndLabel rl=radios.get(i);
		if(rl.getValue()){
			return index;
		}
	}
	
	return index;
}

public String getSelectedLabel(){
	String ret=null;
	int index=getSelectedIndex();
	if(index!=-1){
		return radios.get(index).getLabelText();
	}
	return ret;
}

}
