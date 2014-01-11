package com.akjava.gwt.lib.client.datalist;

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
		 //String title_meta[]=splitHeader(simpleDataList.getSelection().getData().getHeader());
		 //simpleDataList.getSelection().getData().setHeader(name+","+title_meta[1]);
		 simpleDataList.getSelection().getData().setName(name);	
		}
	}
	
	/*
	protected String[] splitHeader(String header){
		String result[]=new String[2];
		int last=header.lastIndexOf(",");
		if(last==-1){
			result[0]=header;
			result[1]="";
		}else{
			result[0]=header.substring(0,last);
			result[1]=header.substring(last+1);
		}
		return result;
	}*/
	
	public  String getCurrentName(){
		if(simpleDataList.getSelection()!=null){
		return simpleDataList.getSelection().getData().getName();
		//String title_meta[]=splitHeader(simpleDataList.getSelection().getData().getHeader());	
		//return title_meta[0];
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
