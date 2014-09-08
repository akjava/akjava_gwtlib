package com.akjava.gwt.lib.client.experimental;

import java.io.IOException;
import java.util.List;

import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.client.ui.ValueListBox;

public class ToStringValueListBox<T> extends ValueListBox<T>{
	public ToStringValueListBox(List<T> values) {
		this();
		setValue(values.get(0));
		setAcceptableValues(values);
	}
	public ToStringValueListBox() {
		super(new Renderer<T>(){

			@Override
			public String render(T object) {
				if(object==null){
					return null;
				}
				return object.toString();
			}

			@Override
			public void render(T object, Appendable appendable) throws IOException {
				
			}
			
		});
		
	}
	
}