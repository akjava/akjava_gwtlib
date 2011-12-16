package com.akjava.gwt.lib.client.widget.cell;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;

public class ColumnBuilder<C,T> {

	CellTable<T> table;
	public ColumnBuilder(CellTable<T> table){
		this.table=table;
	}
	
	@SuppressWarnings("hiding")
	public <C> void addColumn(Cell<C> cell, String headerText,
		      final GetValue<C,T> getter, FieldUpdater<T, C> fieldUpdater) {
		    Column<T, C> column = new Column<T, C>(cell) {
		      @Override
		      public C getValue(T object) {
		        return getter.getValue(object);
		      }
		    };
		    column.setFieldUpdater(fieldUpdater);
		    
		    table.addColumn(column, headerText);
		  }
	 
	 public static interface GetValue<C,T> {
		  C getValue(T contact);
		}
}
