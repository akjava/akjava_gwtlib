package com.akjava.gwt.lib.client.widget.cell;

import java.util.ArrayList;
import java.util.List;

import com.akjava.gwt.lib.client.LogUtils;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionModel;


public abstract class SimpleCellTable<T> extends VerticalPanel{
	NaturalPager pager;
	public NaturalPager getPager() {
		return pager;
	}

	private CellTable<T> table;

	public static DateTimeFormat DATE_FORMAT_DATE_ONLY=DateTimeFormat.getFormat("yy/MM/dd");
	public static DateTimeFormat DATE_FORMAT_DATE_TIME=DateTimeFormat.getFormat("yy/MM/dd hh:mm:ss");



	public CellTable<T> getCellTable() {
		return table;
	}

	ListDataProvider<T> dataProvider = new ListDataProvider<T>();
	private HorizontalPanel controlPanel;
	
	/**
	 * this is pager contain panel
	 * @return
	 */
	public HorizontalPanel getControlPanel() {
		return controlPanel;
	}
	public SimpleCellTable(int pageSize){
		table=new CellTable<T>();
		table.setRowCount(0);
		dataProvider.addDataDisplay(table);
		
		//resultTable.setPageSize(pageSize);
		addColumns(table);
		pager = new NaturalPager();
		controlPanel = new HorizontalPanel();
		controlPanel.setVerticalAlignment(HorizontalPanel.ALIGN_MIDDLE);
		this.add(controlPanel);
		
		controlPanel.add(pager);
		pager.setDisplay(table);
		pager.setPageSize(pageSize);
		//pager.setRangeLimited(false);
		 this.add(table);
	}
	
	@Override
	public void setWidth(String width){
		super.setWidth(width);
		table.setWidth(width);
	}
	
	public abstract void addColumns(CellTable<T> table);
	
	public void setData(List<T> datas){
		setData(datas,false);
	}
	
	public void setData(List<T> datas,boolean flush){
		if(datas==null){
			datas=new ArrayList<T>();
		}
		
		table.setRowCount(datas.size());
		
		pager.setPage(0);
		
		dataProvider.setList(datas);
		if(flush){//draw immediately
			table.flush();
		}
	}
	
	/**
	 * call this,if dont render on event,however try Scheduler first
	 * 
	 * Scheduler.get().scheduleDeferred(new ScheduledCommand() {
					@Override
					public void execute() {
						//
					}
				});
	 */
	public void flush(){
		table.flush();
	}
public void setData(List<T> datas,int page){
		table.setRowCount(datas.size());
		pager.setPage(page);
	
		dataProvider.setList(datas);
		//resultTable.setRowCount(datas.size());
		
		//resultTable.setRowData(datas);
	}
	public void setPage(int page){
		pager.setPage(page);
		
	}
	public int getPage(){
		return pager.getPage();
	}
	public void setSelectionModel(
			SelectionModel<T> selectionModel) {
		getCellTable().setSelectionModel(selectionModel);
	}
}
