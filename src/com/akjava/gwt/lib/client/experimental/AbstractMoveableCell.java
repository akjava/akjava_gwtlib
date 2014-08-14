package com.akjava.gwt.lib.client.experimental;

import java.util.List;

import com.akjava.gwt.lib.client.datalist.AbstractContextCell;
import com.akjava.gwt.lib.client.widget.cell.SimpleContextMenu;
import com.akjava.lib.common.utils.ListUtils;
import com.google.gwt.user.client.Command;

public abstract class AbstractMoveableCell<T> extends AbstractContextCell<T> {

	public AbstractMoveableCell(){

		final SimpleContextMenu simpleMenu=new SimpleContextMenu(){
			public boolean isAvaiableContextMenu(){
				return getSelectedObject()!=null;
			}
		};
		
		
		simpleMenu.addCommand("Move Selection to Top", new Command(){
			@Override
			public void execute() {
				if(getSelectedObject()!=null){
					ListUtils.top(getList(),getSelectedObject());
					updateList();
					simpleMenu.hide();
				}
				
			}

			});
		
		simpleMenu.addCommand("Move Selection to Up", new Command(){
			@Override
			public void execute() {
				if(getSelectedObject()!=null){
					ListUtils.up(getList(),getSelectedObject());
					updateList();
					simpleMenu.hide();
				}
				
			}

			});
		
		simpleMenu.addCommand("Move Selection to Down", new Command(){
			@Override
			public void execute() {
				if(getSelectedObject()!=null){
					ListUtils.down(getList(),getSelectedObject());
					updateList();
					simpleMenu.hide();
				}
				
			}

			});
		
		simpleMenu.addCommand("Move Selection to Bottom", new Command(){
			@Override
			public void execute() {
				if(getSelectedObject()!=null){
					ListUtils.bottom(getList(),getSelectedObject());
					updateList();
					simpleMenu.hide();
				}
				
			}

			});
		
		this.setCellContextMenu(simpleMenu);
	}
	
	public abstract T getSelectedObject();
	public abstract List<T> getList();
	public abstract void updateList();
	public abstract void onSetCellContextMenu(SimpleContextMenu contextMenu);

}
