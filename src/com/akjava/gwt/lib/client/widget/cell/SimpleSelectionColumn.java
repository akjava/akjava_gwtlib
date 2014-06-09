package com.akjava.gwt.lib.client.widget.cell;

import java.util.List;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.user.cellview.client.Column;

public abstract class SimpleSelectionColumn<T> extends Column<T,String> implements FieldUpdater<T, String>{

	public SimpleSelectionColumn(List<String> values) {
		super(new SelectionCell(values));
		setFieldUpdater(this);
	}

	

}
