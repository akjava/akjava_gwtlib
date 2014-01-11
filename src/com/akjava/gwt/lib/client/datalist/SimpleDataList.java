package com.akjava.gwt.lib.client.datalist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.akjava.gwt.lib.client.LogUtils;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

public class SimpleDataList extends VerticalPanel{
//TODO comparable
	
	 private static final ProvidesKey<DataListData<SimpleTextData>> KEY_PROVIDER = new ProvidesKey<DataListData<SimpleTextData>>() {
		    public Object getKey(DataListData<SimpleTextData> item) {
		      return item.getData().getId();
		    }
		  };	

private ItemIOControler ioControler;

private CellList<DataListData<SimpleTextData>> cellList;
private SingleSelectionModel<DataListData<SimpleTextData>> selectionModel;


private Handler selectionChangeHandler;

public void setModified(boolean modified) {
	if(getSelection()==null){
		return;
	}
	
	if(getSelection().isModified()!=modified){
		getSelection().setModified(modified);
		cellList.redraw();
	}
}

public List<DataListData<SimpleTextData>> getCellData(){
	return dataListDatas;
}

/**
 * 
 * @param io
 * @param selection somehow not used yet
 */
public SimpleDataList(ItemIOControler io,int selection){
this.ioControler=io;

HorizontalPanel buttons=new HorizontalPanel();
add(buttons);
HorizontalPanel buttons2=new HorizontalPanel();
add(buttons2);

VerticalPanel opVerticalPanel=new VerticalPanel();
add(opVerticalPanel);
final HorizontalPanel expandButtons=new HorizontalPanel();
add(expandButtons);
expandButtons.setVisible(false);


saveBt = new Button("Save");
saveBt.addClickHandler(new ClickHandler() {
	@Override
	public void onClick(ClickEvent event) {
		save();
		
	}
});
saveBt.setEnabled(false);
buttons.add(saveBt);

saveAsBt = new Button("SaveAs",new ClickHandler() {
	@Override
	public void onClick(ClickEvent event) {
		boolean done=ioControler.saveAs();
		if(done){
			setModified(false);
		}
	}
});
buttons.add(saveAsBt);
saveAsBt.setEnabled(false);

renameBt = new Button("Rename",new ClickHandler() {
	@Override
	public void onClick(ClickEvent event) {
		String newName=ioControler.rename();
		if(newName!=null){
		currentSelection.getData().setName(newName);
		cellList.redraw();
		}
	}
});
buttons.add(renameBt);

deleteBt = new Button("Delete",new ClickHandler() {
	@Override
	public void onClick(ClickEvent event) {
		boolean done=ioControler.delete();
		
		if(done){
			currentSelection=null;
			setModified(false);
		}
	}
});
buttons.add(deleteBt);



copyBt = new Button("Copy",new ClickHandler() {
	@Override
	public void onClick(ClickEvent event) {
		//copy before save data.
		ioControler.copy(currentSelection.getData());
	}
});
buttons.add(copyBt);

Button paste=new Button("Paste",new ClickHandler() {
	@Override
	public void onClick(ClickEvent event) {
		ioControler.paste();
	}
});
buttons.add(paste);

Button exportAll=new Button("ExportAll",new ClickHandler() {
	@Override
	public void onClick(ClickEvent event) {
		ioControler.exportAll();
	}
});
expandButtons.add(exportAll);

Button importBt=new Button("Import",new ClickHandler() {
	@Override
	public void onClick(ClickEvent event) {
		ioControler.importData();
	}
});
expandButtons.add(importBt);

Button restore=new Button("Restore",new ClickHandler() {
	@Override
	public void onClick(ClickEvent event) {
		ioControler.restore();
	}
});
expandButtons.add(restore);

Button clearAll=new Button("ClearAll",new ClickHandler() {
	@Override
	public void onClick(ClickEvent event) {
		ioControler.clearAll();
	}
});
expandButtons.add(clearAll);

Button recover=new Button("Recover",new ClickHandler() {
	@Override
	public void onClick(ClickEvent event) {
		ioControler.recoverLastSaved(ioControler.getLastSaved());
	}
});
expandButtons.add(recover);
recover.setTitle("recover last overwrited(saved) data");


Button doNew=new Button("New",new ClickHandler() {
	@Override
	public void onClick(ClickEvent event) {
		boolean isContinue=askContinueAction();
		if(!isContinue){
			return;
		}
		ioControler.doNew();
	}
});
buttons2.add(doNew);

reloadBt = new Button("Reload",new ClickHandler() {
	@Override
	public void onClick(ClickEvent event) {
		boolean isContinue=askContinueAction();
		if(!isContinue){
			return;
		}
		if(currentSelection!=null){
			int index=getSelectedIndex();
			ioControler.unselect();
			select(index);
		}
	}
});
buttons2.add(reloadBt);


unselectBt = new Button("Unselect",new ClickHandler() {
	@Override
	public void onClick(ClickEvent event) {
		unselect();
	}
});
buttons2.add(unselectBt);



expandButton = new Button("Expand>>",new ClickHandler() {
	@Override
	public void onClick(ClickEvent event) {
		expandButtons.setVisible(!expandButtons.isVisible());
		if(expandButtons.isVisible()){
			expandButton.setText("Collapse<<");
		}else{
			expandButton.setText("Expand>>");
		}
	}
});
buttons2.add(expandButton);

ScrollPanel scroll=new ScrollPanel();
scroll.setHeight("300px");
add(scroll);


cell = new DataListDataCell();
cellList = new CellList<DataListData<SimpleTextData>>(cell,KEY_PROVIDER);
cellList.setStylePrimaryName("table");

scroll.setWidget(cellList);

selectionModel = new SingleSelectionModel<DataListData<SimpleTextData>>(KEY_PROVIDER);
cellList.setSelectionModel(selectionModel);
selectionChangeHandler = new Handler() {
	@Override
	public void onSelectionChange(SelectionChangeEvent event) {
		//LogUtils.log("selection change called");
		if(ignoreSelectionChange){
			//LogUtils.log("ignore by flag");
			ignoreSelectionChange=false;
			return;
		}
		boolean isContinue=askContinueAction();
		if(!isContinue){
			backselect(currentSelection);
			//LogUtils.log("backselected");
			return;
		}
		DataListData<SimpleTextData> data=selectionModel.getSelectedObject();
		if(data!=null){
			//LogUtils.log("select and call load:"+data.getData().getHeader()+","+data.getData().getId());	
		selectFromSelectionModel(data);
		}else{
			//LogUtils.log("null-selection-data");
			//need for clear selection label
			//maybe called selectionModel.clear() in updateList
			//LogUtils.log("null selection called: in selectionModel");
			//throw new RuntimeException();//need ir or loop & crash
		}
	}
};
selectionModel.addSelectionChangeHandler(selectionChangeHandler);




HorizontalPanel downButtons=new HorizontalPanel();
add(downButtons);
downButtons2 = new HorizontalPanel();
add(downButtons2);

Button back=new Button("Back",new ClickHandler() {
	@Override
	public void onClick(ClickEvent event) {
		ioControler.back();
	}
});
downButtons.add(back);

Button prev=new Button("Prev",new ClickHandler() {
	@Override
	public void onClick(ClickEvent event) {
		boolean isContinue=askContinueAction();
		if(!isContinue){
			return;
		}
		prev();
	}
});
downButtons.add(prev);


Button next=new Button("Next",new ClickHandler() {
	@Override
	public void onClick(ClickEvent event) {
		boolean isContinue=askContinueAction();
		if(!isContinue){
			return;
		}
		next();
	}
});
downButtons.add(next);

final ListBox orderList=new ListBox();
orderList.addItem("id");
orderList.addItem("id desc");
orderList.addItem("a-z");
orderList.setSelectedIndex(1);
orderList.addChangeHandler(new ChangeHandler() {
	
	@Override
	public void onChange(ChangeEvent event) {
		order=orderList.getSelectedIndex();
		ioControler.updateList();
		
	}
});
downButtons.add(orderList);


//cellContextMenu=new TestContextMenu();//for test

update();
}

/*
private class TestContextMenu implements CellContextMenu{
	private PopupPanel contextMenu;
	private MenuBar menu=new MenuBar(true);
	public TestContextMenu(){
		 this.contextMenu = new PopupPanel(true);
		 contextMenu.add(menu);
		
		 Command testCommand = new Command() {
			  public void execute() {
				
				Window.alert("hello");
				
			    contextMenu.hide(); 
			  }
		};
		
		 menu.addItem(new MenuItem("Top", true, testCommand));
		 this.contextMenu.hide();
	}
	@Override
	public void onContextMenu(int clientX, int clientY) {
		this.contextMenu.setPopupPosition(clientX, clientY);
	    this.contextMenu.show();
	}
	
}*/

public void save() {
	//save.setEnabled(false);
			saveBt.setText("Saving");
			boolean done=false;
			try{
			done=ioControler.save();
			}catch(Exception e){
				e.getMessage();
				LogUtils.log(e.getMessage());
			}
			saveBt.setText("Save");
			//save.setEnabled(true);
			if(done){
				setModified(false);
			}
}

public void unselect() {
	ioControler.unselect();
	currentSelection=null;
	setModified(false);
	
	setSelectionStatus(false);
}

/**
 * this called from direct selection and buttons
 * TODO bugs called multiple
 * @param selection
 */
public void setSelectionStatus(boolean selection){
	//LogUtils.log("selection:"+selection);
	saveBt.setEnabled(selection);
	saveAsBt.setEnabled(selection);
	
	renameBt.setEnabled(selection);
	deleteBt.setEnabled(selection);
	copyBt.setEnabled(selection);
	reloadBt.setEnabled(selection);
	unselectBt.setEnabled(selection);
}


public void next(){
	int index=getSelectedIndex();
	if(index<getItemCount()-1){
		index++;
	}else{
		index=0;
	}
	select(index);
}
public void prev(){
	int index=getSelectedIndex();
	if(index>0){
		index--;
	}else{
		index=getItemCount()-1;
	}
	select(index);
}

private boolean askContinueAction(){
	if(currentSelection!=null){
	if(currentSelection.isModified()){
		boolean confirm=Window.confirm(getConfirmMessage());
		if(!confirm){
			return false;
		}
		currentSelection.setModified(false);//aband
	}
	}
	return true;
}

private List<DataListData<SimpleTextData>> dataListDatas=new ArrayList<DataListData<SimpleTextData>>();
private DataListData<SimpleTextData> currentSelection;
public static final int ORDER_ID=0;
public static final int ORDER_ID_DESC=1;
public static final int ORDER_AZ=2;
private int order=1;
private SimpleTextDataComparator comparator=new SimpleTextDataComparator();
private String getConfirmMessage(){
	return "last selection is modified?disband this and select another?";
}
public void update(){
	try{
	dataListDatas.clear();
	List<SimpleTextData> hvs=ioControler.getDataList().getDataList();
	
	comparator.setOrder(order);
	Collections.sort(hvs, comparator);
	
	for(SimpleTextData hv:hvs){
		dataListDatas.add(new DataListData<SimpleTextData>(hv));
	}
	 
	 selectionModel.clear();
	/*
	 selectionModel=new SingleSelectionModel<DataListData<SimpleTextData>>();
	 selectionModel.addSelectionChangeHandler(selectionChangeHandler);
	 cellList.setSelectionModel(selectionModel);
	*/
	 cellList.setRowCount(dataListDatas.size());
	 cellList.setVisibleRange(0, dataListDatas.size());
	 cellList.setRowData(0, dataListDatas);
	 //
	 //TODO refresh selectionModel?
	}catch(Exception e){
		LogUtils.log(e.getMessage());
		e.printStackTrace();
	}
}

public void redraw(){
	cellList.redraw();
}

public static class SimpleTextDataComparator implements Comparator<SimpleTextData>{
private  int order;
	public int getOrder() {
	return order;
}
public void setOrder(int order) {
	this.order = order;
}
@Override
public int compare(SimpleTextData o1, SimpleTextData o2) {
	if(order==ORDER_ID_DESC){
		return o2.getId()-o1.getId();
	}
	if(order==ORDER_AZ){
		return o1.getName().compareTo(o2.getName());
	}
	return o1.getId()-o2.getId();
}
	
	
}

boolean ignoreSelectionChange;

private Button expandButton;
private void backselect(DataListData<SimpleTextData> item){
	ignoreSelectionChange=true;
	selectionModel.setSelected(item, true);
}

private void selectFromSelectionModel(DataListData<SimpleTextData> item){
	//never call selectionModel.setSelected(item, true);
	currentSelection=item;
	ioControler.load(item.getData().getId());
	currentSelection.setModified(false);
	cellList.redraw();
	setSelectionStatus(true);
}

public DataListData<SimpleTextData> getSelection(){
	return currentSelection;
}

public int getItemCount(){
	return dataListDatas.size();
}
public int getSelectedIndex(){
	if(currentSelection==null){
		return -1;
	}
	
	DataListData<SimpleTextData> data=selectionModel.getSelectedObject();
	return dataListDatas.indexOf(data);
}





/*
public class SimpleDataListItem extends HorizontalPanel{
	private String data;	//not need?
	public String getData() {
		return data;
	}
	private String header;
	public void setHeader(String header) {
		this.header = header;
	}
	public String getHeader() {
		return header;
	}
	private int index;
	public int getId() {
		return index;
	}
	private Label label;
	public Label getLabel() {
		return label;
	}
	
	public void updateModifiedLabel(boolean modified){
		if(modified){
		label.setText("*"+header);
		}else{
		label.setText(header);
		}
	}
	
	public SimpleDataListItem(String header,String data,int index){
		this.header=header;
		this.data=data;
		this.index=index;
		
		String labelText=header;
		label=new Label(header);
		this.add(label);
		label.setWidth("100%");
	}
}
*/

public void select(int index) {
	if(index<0){
	LogUtils.log("invalid index:"+index);
	}
	DataListData<SimpleTextData> data=dataListDatas.get(index);
	select(data);
	setSelectionStatus(true);
}

private void select(final DataListData<SimpleTextData> data) {
	Scheduler.get().scheduleFinally(new ScheduledCommand() {
		@Override
		public void execute() {
			selectionModel.setSelected(data, true);//how to manual select.
		}
	});
	
}


public void selectById(int id) {
	for(DataListData<SimpleTextData> data:dataListDatas){
		if(data.getData().getId()==id){
			select(data);
			break;
		}
	}
}



private DataListDataCell cell;

private Button saveBt;

private Button saveAsBt;

private Button unselectBt;

private Button reloadBt;

private Button copyBt;

private Button deleteBt;

private Button renameBt;

private HorizontalPanel downButtons2;
private VerticalPanel optionButtons3;

/**
 * for user customize
 * @return
 */
public HorizontalPanel getOptionButtonPanel(){
	return downButtons2;
}
public VerticalPanel getVerticalOptionPanel(){
	return optionButtons3;
}


public CellContextMenu getCellContextMenu() {
	return cell.getCellContextMenu();
}


public void setCellContextMenu(CellContextMenu cellContextMenu) {
	cell.setCellContextMenu(cellContextMenu);
	
}



@SuppressWarnings("unchecked")
public class DataListDataCell  extends AbstractContextCell<DataListData<SimpleTextData>>{

	
	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			DataListData<SimpleTextData> value, SafeHtmlBuilder sb) {
		if(value==null){
			return;
		}
		
		String title="";
		title+=value.getData().getName();
		if(value.isModified()){
			title="*"+title;
		}
		sb.appendEscaped(title);
	}
	
	@Override
	public void onDoubleClick(int clientX, int clientY) {
		ioControler.doDoubleClick(clientX,clientY);
	}

	
 

}

}
