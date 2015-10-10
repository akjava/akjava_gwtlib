package com.akjava.gwt.lib.client.datalist;

import java.util.List;
import java.util.Stack;

import javax.annotation.Nullable;

import com.akjava.gwt.lib.client.LogUtils;
import com.akjava.gwt.lib.client.StorageDataList;
import com.akjava.gwt.lib.client.datalist.command.AddCommand;
import com.akjava.gwt.lib.client.datalist.command.RemoveCommand;
import com.akjava.gwt.lib.client.datalist.command.RenameCommand;
import com.akjava.gwt.lib.client.experimental.undo.SimpleUndoControler;
import com.google.common.base.Optional;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;

public abstract class ItemIOControler implements HasValueChangeHandlers<SimpleTextData>{
private StorageDataList dataList;	


protected SimpleUndoControler undoControler;
public SimpleUndoControler getUndoControler() {
	return undoControler;
}

public void setUndoControler(@Nullable SimpleUndoControler undoControler) {
	this.undoControler = undoControler;
}

public void setDataList(StorageDataList dataList) {
	this.dataList = dataList;
}

public StorageDataList getDataList() {
	return dataList;
}
private boolean modified;
protected int selectedId=-1;

private SimpleTextData lastSaved;
public SimpleTextData getLastSaved() {
	return lastSaved;
}

public boolean save(){
	if(selectedId==-1){
		return saveAs();
	}else{
		SimpleTextData hv=createSaveData(getCurrentName());
		
		lastSaved=dataList.getDataValue(selectedId);//for backup
		//store cdate+data
		 String storedData=hv.getCdate()+","+hv.getData();
		dataList.updateData(selectedId, hv.getName(), storedData);
		
		//validate
		SimpleTextData storedHV=dataList.getDataValue(selectedId);
		if(!(storedHV.getCdate()+","+storedHV.getData()).equals(storedData)){
			Window.alert("faild to save:expected ="+storedData+" but stored="+storedHV.getData());
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
	
	SimpleTextData hv=createSaveData(saveName);
	String storedData=hv.getCdate()+","+hv.getData();
	int selection=dataList.addData(hv.getName(), storedData);
	selectedId=selection;
	
	//validate
	SimpleTextData storedHV=dataList.getDataValue(selectedId);
	if(!(storedHV.getCdate()+","+storedHV.getData()).equals(storedData)){
		Window.alert("faild to save");
		throw new RuntimeException("faild to save");
	}
	
	
	updateList();
	ValueChangeEvent.fire(this, hv);
	
	return true;
}


public void rename(String newName){
	
	
	 execRename(newName,true);
	
}
	
public void execRename(String newName,boolean needUndo){
	
	
	SimpleTextData current=dataList.getDataValue(selectedId);
	if(current==null){
		LogUtils.log("execRename:current selection is null.can't rename it");
		return;
	}
	//if(current)
	String oldName=current.getName()!=null?current.getName():"";
	
	//i have no idea why create new data.there already exist current data.
	SimpleTextData hv=createSaveData(newName);
	dataList.updateData(selectedId, hv.getName(), hv.getCdate()+","+hv.getData());
	
	setCurrentName(newName);//doit for avoid reload
	
	
	updateList();
	ValueChangeEvent.fire(this, hv);
	
	if(needUndo){
		undoControler.execute(new RenameCommand(this, oldName,newName));
	}
	
	
	
}
public String rename(){
	String saveName=Window.prompt("name", getCurrentName());
	if(saveName==null){
		return null;
	}
	
	execRename(saveName,true);
	
	return saveName;
}

public boolean delete(){
	boolean confirm=Window.confirm("Delete "+getCurrentName()+"?");
	if(!confirm){
		return false;
	}
	
	return execDelete(selectedId,true);
	
}

public boolean execDelete(int id,boolean needUndo){
	
	lastSaved=dataList.getDataValue(id);//
	
	dataList.clearData(id);
	selectedId=-1;
	unselect();
	ValueChangeEvent.fire(this, null);
	if(undoControler!=null){
		undoControler.execute(new RemoveCommand(this, lastSaved));
	}
	
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
		SimpleTextData hv=dataList.getDataValue(id);
	if(hv==null){
		loadData(Optional.<SimpleTextData>absent());
	}else{
		loadData(Optional.of(hv));
	}
	
	}
	
	if(id!=-1 && (backs.size()==0 || backs.peek()!=id)){
		backs.push(id);
	}
	
}



/**
 * 
 * @param name
 */
public abstract void setCurrentName(String name);

public abstract String getCurrentName();
public abstract SimpleTextData createSaveData(String fileName);
public abstract SimpleTextData createNewData(String fileName);

/**
 * Optional.absense means notselected;clear selection
 * @param hv
 */
public abstract void loadData(Optional<SimpleTextData> hv);

public abstract void exportDatas(List<SimpleTextData> list);

public abstract void updateList();

public abstract void importData();
public abstract void clearAll();

public abstract void copy(Object object);

public abstract void paste();

public abstract void recoverLastSaved(SimpleTextData hv);

private String controlerName;
public String getControlerName() {
	return controlerName;
}

public void setControlerName(String controlerName) {
	this.controlerName = controlerName;
}

public void exportAll() {
	List<SimpleTextData> list=dataList.getDataList();
	exportDatas(list);
}



public abstract void restore();

public void unselect() {
	selectedId=-1;
	updateList();
	loadData(Optional.<SimpleTextData>absent());
}

public SimpleTextData doNew(){
	return doNew("Undefined");
}
public SimpleTextData doNew(String defaultName){
	String saveName=Window.prompt("name", defaultName);
	if(saveName==null){
		return null;
	}
	
	SimpleTextData hv=createNewData(saveName);
	
	execAdd(hv,true);
	
	return hv;
}

public int execAdd(SimpleTextData data,boolean needUndo){
	int selection=dataList.addData(data.getName(), data.getCdate()+","+data.getData());
	selectedId=selection;
	data.setId(selection);
	updateList();
	ValueChangeEvent.fire(this, data);
	
	if(undoControler!=null){
		undoControler.execute(new AddCommand(this, data));
	}
	return selection;
}
//not only update,but also insert (called from undo)
public void execUpdate(SimpleTextData data,boolean needUndo){
	
	dataList.updateData(data.getId(),data.getName(), data.getCdate()+","+data.getData());
	selectedId=data.getId();
	updateList();
	ValueChangeEvent.fire(this, data);
	
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
			SimpleTextData hv=dataList.getDataValue(id);
			
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
		ValueChangeHandler<SimpleTextData> handler) {
	return bus.addHandler(ValueChangeEvent.getType(), handler);
}

public abstract void doDoubleClick(int clientX, int clientY) ;

}
