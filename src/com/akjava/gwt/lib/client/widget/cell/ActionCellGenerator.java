package com.akjava.gwt.lib.client.widget.cell;

import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.CompositeCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.HasCell;
import com.google.gwt.user.cellview.client.Column;

public abstract class ActionCellGenerator<T> {

	
	public Column<T,T> generateColumn(List<String> collection){
		List<HasCell<T, ?>> cells = new LinkedList<HasCell<T, ?>>();
		for(int i=0;i<collection.size();i++){
			final int index=i;
			 cells.add(new ActionHasCell(collection.get(i), new Delegate<T>() {
			        @Override
			        public void execute(T object) {
			        	 executeAt(index,object);
			        }
			    }));
		}
	    CompositeCell<T> cell = new CompositeCell<T>(cells);
	    return new Column<T, T>(cell) {
			@Override
			public T getValue(T object) {
				return object;
			}
	    	
		};
	}
	public Column<T,T> generateColumn(final String firstLabel,final String secondLabel){
		return generateColumn(Lists.newArrayList(firstLabel,secondLabel));
	}

	public abstract void executeAt(int index,T object);
	
	
	private class ActionHasCell implements HasCell<T, T> {
	    private ActionCell<T> cell;

	    public ActionHasCell(String text, Delegate<T> delegate) {
	        cell = new ActionCell<T>(text, delegate);
	    }

	    @Override
	    public Cell<T> getCell() {
	        return cell;
	    }

	    @Override
	    public FieldUpdater<T, T> getFieldUpdater() {
	        return null;
	    }

	    @Override
	    public T getValue(T object) {
	        return object;
	    }
	}
	
}
