package com.akjava.gwt.lib.client.datalist;

import java.util.List;
import java.util.Stack;

import com.akjava.gwt.lib.client.HeaderAndValue;
import com.akjava.gwt.lib.client.StorageDataList;
import com.google.common.base.Optional;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;

public abstract class ItemIOControler implements HasValueChangeHandlers<HeaderAndValue>{
private StorageDataList dataList;	


public void setDataList(StorageDataList dataList) {
	this.dataList = dataList;
}

public StorageDataList getDataList() {
	return dataList;
}
private boolean modified;
protected int selectedId=-1;

private HeaderAndValue lastSaved;
public HeaderAndValue getLastSaved() {
	return lastSaved;
}

public boolean save(){
	if(selectedId==-1){
		return saveAs();
	}else{
		HeaderAndValue hv=createSaveData(getCurrentName());
		
		lastSaved=dataList.getDataValue(selectedId);//for backup
		
		dataList.updateData(selectedId, hv.getHeader(), hv.getData());
		
		//validate
		HeaderAndValue storedHV=dataList.getDataValue(selectedId);
		if(!storedHV.getData().equals(hv.getData())){
			Window.alert("faild to save");
			throw new RuntimeException("faild to save");
		}
		
		ValueChangeEvent.fire(this, hv);
		return true;
	}
}

public boolean saveAs(){
	String saveName=Window.prompt("name", getCurrentName());
	if(saveName==null){
		return false;
	}
	
	HeaderAndValue hv=createSaveData(saveName);
	GWT.log(dataList+","+hv);
	int selection=dataList.addData(hv.getHeader(), hv.getData());
	selectedId=selection;
	
	//validate
	HeaderAndValue storedHV=dataList.getDataValue(selectedId);
	if(!storedHV.getData().equals(hv.getData())){
		Window.alert("faild to save");
		throw new RuntimeException("faild to save");
	}
	
	
	updateList();
	ValueChangeEvent.fire(this, hv);
	
	return true;
}


public String rename(String newName){
	
	
	HeaderAndValue hv=createSaveData(newName);
	dataList.updateData(selectedId, hv.getHeader(), hv.getData());
	setCurrentName(newName);//doit for avoid reload
	ValueChangeEvent.fire(this, hv);
	return newName;
}
	
public String rename(){
	String saveName=Window.prompt("name", getCurrentName());
	if(saveName==null){
		return null;
	}
	
	HeaderAndValue hv=createSaveData(saveName);
	dataList.updateData(selectedId, hv.getHeader(), hv.getData());
	setCurrentName(saveName);//doit for avoid reload
	ValueChangeEvent.fire(this, hv);
	return saveName;
}

public boolean delete(){
	boolean confirm=Window.confirm("Delete "+getCurrentName()+"?");
	if(!confirm){
		return false;
	}
	
	lastSaved=dataList.getDataValue(selectedId);//for backup
	
	dataList.clearData(selectedId);
	selectedId=-1;
	unselect();
	ValueChangeEvent.fire(this, null);
	return true;
}


private Stack<Integer> backs=new  Stack<Integer>();
private Stack<Integer> fowards=new  Stack<Integer>();

/*
 * be careful this selection don't change cell selection.
 */
public void load(int id){
	selectedId=id;
	//TODO confirm
	if(id!=-1){
	HeaderAndValue hv=dataList.getDataValue(id);
	if(hv==null){
		loadData(Optional.<HeaderAndValue>absent());
	}else{
		loadData(Optional.of(hv));
	}
	
	}
	
	if(id!=-1 && (backs.size()==0 || backs.peek()!=id)){
		backs.push(id);
	}
	
}



public abstract void setCurrentName(String name);
public abstract String getCurrentName();
public abstract HeaderAndValue createSaveData(String fileName);
public abstract HeaderAndValue createNewData(String fileName);

/**
 * Optional.absense means notselected;clear selection
 * @param hv
 */
public abstract void loadData(Optional<HeaderAndValue> hv);

public abstract void exportDatas(List<HeaderAndValue> list);

public abstract void updateList();

public abstract void importData();
public abstract void clearAll();

public abstract void copy(Object object);

public abstract void paste();

public abstract void recoverLastSaved(HeaderAndValue hv);

private String controlerName;
public String getControlerName() {
	return controlerName;
}

public void setControlerName(String controlerName) {
	this.controlerName = controlerName;
}

public void exportAll() {
	List<HeaderAndValue> list=dataList.getDataList();
	exportDatas(list);
}



public abstract void restore();

public void unselect() {
	selectedId=-1;
	updateList();
	loadData(Optional.<HeaderAndValue>absent());
}

public HeaderAndValue doNew(){
	return doNew("Undefined");
}
public HeaderAndValue doNew(String defaultName){
	String saveName=Window.prompt("name", defaultName);
	if(saveName==null){
		return null;
	}
	
	HeaderAndValue hv=createNewData(saveName);
	
	int selection=dataList.addData(hv.getHeader(), hv.getData());
	selectedId=selection;
	updateList();
	ValueChangeEvent.fire(this, hv);
	return hv;
}

public void back() {
	if(backs.size()>1){
		backs.pop();//remove current
	}
	final int id=backs.peek();
	
	selectedId=id;//change select
	updateList();
	/*
	Scheduler.get().scheduleDeferred(new ScheduledCommand() {
		
		@Override
		public void execute() {
			HeaderAndValue hv=dataList.getDataValue(id);
			
			load(id);
		}
	});
	*/
	
}

EventBus bus = new SimpleEventBus();

@Override
public void fireEvent(GwtEvent<?> event) {
    bus.fireEvent(event);
}

@Override
public HandlerRegistration addValueChangeHandler(
		ValueChangeHandler<HeaderAndValue> handler) {
	return bus.addHandler(ValueChangeEvent.getType(), handler);
}

public abstract void doDoubleClick(int clientX, int clientY) ;

}
