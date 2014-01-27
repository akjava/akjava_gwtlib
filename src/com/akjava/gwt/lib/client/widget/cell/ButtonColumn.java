package com.akjava.gwt.lib.client.widget.cell;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.user.cellview.client.Column;

public abstract class ButtonColumn<T> extends Column<T,String> implements FieldUpdater<T, String>{

	public ButtonColumn() {
		super(new ButtonCell());
		setFieldUpdater(this);
	}

	

}
