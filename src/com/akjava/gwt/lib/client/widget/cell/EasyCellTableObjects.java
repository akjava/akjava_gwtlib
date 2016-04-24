package com.akjava.gwt.lib.client.widget.cell;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.akjava.gwt.lib.client.LogUtils;
import com.akjava.lib.common.utils.ListUtils;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

/**
 * single selection
 * @author aki
 *
 * @param <T>
 */
public abstract class EasyCellTableObjects<T> {
	protected List<T> datas=new ArrayList<T>();
	protected SimpleCellTable<T> simpleCellTable;
	
	protected SingleSelectionModel<T> selectionModel;
	
	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public SimpleCellTable<T> getSimpleCellTable() {
		return simpleCellTable;
	}

	public void setSimpleCellTable(SimpleCellTable<T> simpleCellTable,boolean showPager) {
		this.simpleCellTable = simpleCellTable;
		
		
	}
	public T getSelection(){
		return selectionModel.getSelectedObject();
	}
	
	public boolean isSelected(){
		return getSelection()!=null;
	}
	
	public Optional<Integer> getSelectedIndex(@Nullable T data){
		if(data==null){
			return Optional.absent();
		}
		int value=datas.indexOf(data);
		if(value==-1){
			return Optional.absent();
		}
		return Optional.of(value);
	}
	public Optional<Integer> getSelectedIndex(){
		T data=getSelection();
		if(data==null){
			return Optional.absent();
		}
		int value=datas.indexOf(data);
		if(value==-1){
			return Optional.absent();
		}
		return Optional.of(value);
	}
	
	public abstract void onSelect(@Nullable T selection);

	public void setSelected(T item,boolean selected){
		selectionModel.setSelected(item, selected);
	}
	public SingleSelectionModel<T> getSelectionModel() {
		return selectionModel;
	}

	public void setSelectionModel(SingleSelectionModel<T> selectionModel) {
		this.selectionModel = selectionModel;
	}

	public EasyCellTableObjects(SimpleCellTable<T> simpleCellTable){
		this(simpleCellTable,true);
	}
	
	public EasyCellTableObjects(SimpleCellTable<T> simpleCellTable, boolean showPager) {
		this.simpleCellTable=simpleCellTable;
		selectionModel=new SingleSelectionModel<T>();
		selectionModel.addSelectionChangeHandler(new Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				onSelect(selectionModel.getSelectedObject());
			}
		});
		
		simpleCellTable.setSelectionModel(selectionModel);
		simpleCellTable.getPager().setVisible(showPager);
	}

	public void addItem(T data){
		datas.add(data);
		update();
	}
	
	public void insertItem(int index,T data){
		datas.add(index,data);
		update();
	}
	public void clearAllItems(){
		unselect();
		datas.clear();
		update();
	}
	
	public void removeItem(T data){
		selectionModel.setSelected(data, false);
		datas.remove(data);
		
		update();
	}
	public Optional<T> getItemAt(int index){
		if(index>=0 && index<datas.size()){
			return Optional.of(datas.get(index));
		}
		return Optional.absent();
	}
	public T removeItem(int index){
		for(T data:getItemAt(index).asSet()){
			selectionModel.setSelected(data, false);
			datas.remove(data);
			update();
			return data;
		}
		return null;
	}
	/**
	 * @deprecated
	 * @param flush //no more flush support.
	 */
	public void update(boolean flush){
		simpleCellTable.setData(datas,flush);
	}
	
	public void update(){
		simpleCellTable.setData(datas);//no more flush support?
	}

	public void downItem(T object) {
		if(datas.size()>1){
		int index=datas.indexOf(object);
		datas.remove(object);
		datas.add(Math.min(index+1, datas.size()),object);
		update(true);
		}
		
	}
	
	public void upItem(T object) {
		if(datas.size()>1){
		int index=datas.indexOf(object);
		datas.remove(object);
		datas.add(Math.max(0, index-1),object);
		update(true);
		}
	}
	public void topItem(T object){
		
		if(datas.size()<=1){//no need
			return;
		}
		
		ListUtils.top(datas, object);
		
		update();
		
	}
	public void bottomItem(T object){
		if(datas.size()<=1){//no need
			return;
		}
		ListUtils.bottom(datas, object);
		update();
	}
	
	
	
	
	
	public Optional<T> getNext(T data) {
		if(data==null){
			return Optional.absent();
		}
		int index=datas.indexOf(data);
		if(index==-1){
			return Optional.absent();
		}
		
		if(index<datas.size()-1){
			return Optional.of(datas.get(index+1));
		}else{
			return Optional.absent();
		}
	}

	
	public Optional<T> getPrev(T data) {
		if(data==null){
			return Optional.absent();
		}
		int index=datas.indexOf(data);
		if(index==-1){
			return Optional.absent();
		}
		if(index>0){
			return Optional.of(datas.get(index-1));
		}else{
			return Optional.absent();
		}
	}

	
	public Optional<T> getFirst() {
		if(datas.size()>0){
			return Optional.of(datas.get(0));
		}else{
			return Optional.absent();
		}
	}

	
	public Optional<T> getLast() {
		if(datas.size()>0){
			return Optional.of(datas.get(datas.size()-1));
		}else{
			return Optional.absent();
		}
	}
	
	
	public <E> List<E> convertToList(Function<T,E> function){
		return FluentIterable.from(datas).transform(function).toList();
	}
	

	public void unselect() {
		T selection=getSelection();
		if(selection!=null){
			selectionModel.setSelected(selection, false);
		}
	}
	public Column<T,T> generateUpDownActionColumn(String upLabel,String downLabel){
		return new ActionCellGenerator<T>(){

			@Override
			public void executeAt(int index, T object) {
				if(index==0){
					EasyCellTableObjects.this.upItem(object);
				}else if(index==1){
					EasyCellTableObjects.this.downItem(object);
				}
			}}.generateColumn(upLabel, downLabel);
	}
	}
	
