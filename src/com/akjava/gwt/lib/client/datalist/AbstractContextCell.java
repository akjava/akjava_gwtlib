package com.akjava.gwt.lib.client.datalist;

import java.util.Set;

import com.google.common.collect.Sets;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;


public abstract class AbstractContextCell<T> extends AbstractCell<T>{
	private CellContextMenu cellContextMenu;

	public CellContextMenu getCellContextMenu() {
		return cellContextMenu;
	}

	public void setCellContextMenu(CellContextMenu cellContextMenu) {
		this.cellContextMenu = cellContextMenu;
	}


	@Override
     public Set<String> getConsumedEvents()
     {
           return consumeEvents;
     }
	private final static Set<String> consumeEvents=Sets.newHashSet("contextmenu","dblclick");

	@Override
	public void onBrowserEvent(com.google.gwt.cell.client.Cell.Context context,
			Element parent, T value, NativeEvent event,
			ValueUpdater<T> valueUpdater) {
		super.onBrowserEvent(context, parent, value, event, valueUpdater);
		event.preventDefault();
        event.stopPropagation();
        
        if(event.getType().equals("dblclick")){
        	onDoubleClick(event.getClientX(), event.getClientY());
        }else{
        	  if(cellContextMenu!=null){
              	cellContextMenu.onContextMenu(event.getClientX(), event.getClientY());
              }
        }
        
      
	}
	
	public abstract void onDoubleClick(int clientX,int clientY);
	
}
