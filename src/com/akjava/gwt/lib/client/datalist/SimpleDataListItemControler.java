package com.akjava.gwt.lib.client.datalist;

import com.akjava.gwt.lib.client.LogUtils;
import com.akjava.gwt.lib.client.StorageDataList;

public abstract class SimpleDataListItemControler extends ItemIOControler{

	protected SimpleDataList simpleDataList;
	
	public SimpleDataList getSimpleDataListWidget() {
		return simpleDataList;
	}

	public SimpleDataListItemControler(StorageDataList dataList){
		super();
		setDataList(dataList);
		//TODO load first?
		simpleDataList=new SimpleDataList(this, -1);	
	}
	
	@Override
	public  void updateList(){
		simpleDataList.update();
		if(selectedId!=-1){
		selectById(selectedId);
			simpleDataList.setSelectionStatus(true);
		}else{
			simpleDataList.setSelectionStatus(false);	
		}
	}
	
	public  void setCurrentName(String name){
		if(simpleDataList.getSelection()!=null){
		 simpleDataList.getSelection().getData().setHeader(name);
		}
	}
	public  String getCurrentName(){
		if(simpleDataList.getSelection()!=null){
		return simpleDataList.getSelection().getData().getHeader();
		}else{
		return "undefined";
		}
	}
	public void prev(){
		simpleDataList.prev();
	}
	public void next(){
		simpleDataList.next();
	}
	
	public void select(int index){
		simpleDataList.select(index);
	}
	public void selectById(int id){
		//selectedId=id;
		//updateList();
		//LogUtils.log("select by id:"+getCurrentName()+","+id);
		simpleDataList.selectById(id);
	}
	
	
}
