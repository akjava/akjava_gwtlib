package com.akjava.gwt.lib.client.widget.cell;

import java.util.List;

import com.akjava.gwt.lib.client.LogUtils;
import com.google.common.collect.ForwardingList;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

/**
 * 
 * @author aki
 *
 * @param <T>
 */
public abstract class CellListControlList<T> extends ForwardingList<T>{
private List<T> rawList;
private CellList<T> cellList;
protected SingleSelectionModel<T> selectionModel;

	public CellListControlList(List<T> rawList,CellList<T> cellList){
		this.rawList=rawList;
		this.cellList=cellList;
		
		selectionModel=new SingleSelectionModel<T>();
		selectionModel.addSelectionChangeHandler(new Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				onSelect(selectionModel.getSelectedObject());
			}
		});
		
		cellList.setSelectionModel(selectionModel);
	}
	
	@Override
	protected List<T> delegate() {
		return rawList;
	}

	
	public T getSelection(){
		return selectionModel.getSelectedObject();
	}
	
	public abstract void onSelect(T selection);

	public void setSelected(T item,boolean selected){
		selectionModel.setSelected(item, selected);
	}
	public SingleSelectionModel<T> getSelectionModel() {
		return selectionModel;
	}

	public void setSelectionModel(SingleSelectionModel<T> selectionModel) {
		this.selectionModel = selectionModel;
	}
	
	@Override
	public void add(int index, T  newAddIndexElement) {
		
		delegate().add(index, newAddIndexElement);
		
		updateCellList();
	}
	@Override
	public void clear() {
		super.clear();
		updateCellList();
	}
	@Override
	public boolean add(T element) {
		boolean b= super.add(element);
		
		updateCellList();
		return b;
	}
	@Override
	public boolean remove(Object object) {
		boolean b= super.remove(object);
		updateCellList();
		return b;
	}
	//TODO support more
	
	
	public void unselect() {
		T selection=getSelection();
		if(selection!=null){
			selectionModel.setSelected(selection, false);
		}
	}
	
	public void updateCellList(){
		cellList.setRowData(this);
	}
	
}
