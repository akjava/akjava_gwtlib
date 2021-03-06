package com.akjava.gwt.lib.client.datalist;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.akjava.gwt.html5.client.download.HTML5Download;
import com.akjava.gwt.html5.client.file.File;
import com.akjava.gwt.html5.client.file.FileUploadForm;
import com.akjava.gwt.html5.client.file.FileUtils;
import com.akjava.gwt.html5.client.file.FileUtils.DataURLListener;
import com.akjava.gwt.lib.client.LogUtils;
import com.akjava.gwt.lib.client.StorageDataList;
import com.akjava.gwt.lib.client.datalist.functions.CsvArrayToHeadAndValueFunction;
import com.akjava.gwt.lib.client.datalist.functions.HeadAndValueToCsvFunction;
import com.akjava.gwt.lib.client.widget.TabInputableTextArea;
import com.akjava.lib.common.csv.CSVReader;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.TextArea;

/**
 * 
 * how to use
 * 
 * 
 * 
 * @author aki
 *
 */
public class TextAreaBasedDataList extends SimpleDataListItemControler{
private TextArea textArea;
private SimpleTextData copiedValue;
private HorizontalPanel uploadPanel;
private FileUploadForm uploadForm;

public static interface TextAreaBasedDataListListener{
	public void onLoad(Optional<SimpleTextData> hv);
}

/**
 * if you need call addkeyUpHandler() for update modified
 * @param textArea
 */
	public void setTextArea(TextArea textArea) {
	this.textArea = textArea;
}

	public TextArea getTextArea() {
	return textArea;
	}

	private void textModified(){
		getSimpleDataListWidget().setModified(true);//simple modified
	}
	
	public TextAreaBasedDataList(StorageDataList dataList) {
		this(dataList,null);
	}
	public TextAreaBasedDataList(StorageDataList dataList,SimpleTextData defaultOnEmptyData) {
		super(dataList);
		this.defaultOnEmptyData=defaultOnEmptyData;
		
		textArea=new TabInputableTextArea();
		
		
		addKeyHandler(textArea);
		 
		 
		 getSimpleDataListWidget().setCellContextMenu(new TestContextMenu());
		 
		 unselect();
		 
		 if(getDataList().getDataList().isEmpty()){
			
			 for(SimpleTextData data:getDefaultOnEmptyData().asSet()){
				
				 for(Integer id:add(data).asSet()){
					
					select(id); 
				 }
			 }
		 }
		 
	}
	private SimpleTextData defaultOnEmptyData;
	


	private Optional<SimpleTextData> getDefaultOnEmptyData(){
		return Optional.fromNullable(defaultOnEmptyData);
	}
	
	private boolean saved;
	public void addKeyHandler(TextArea text){
		text.addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				if(event.isControlKeyDown()){
					if(event.getNativeKeyCode()==83){//ignore save
						event.preventDefault();
						saved=true;
						return;
					}
				}
				saved=false;
			}
		})
		;
		 text.addKeyUpHandler(new KeyUpHandler() {
				@Override
				public void onKeyUp(KeyUpEvent event) {
					if(saved){//ignore save key
						return;
					}
					//ignore single ctrl,this happen after save
					if(event.getNativeKeyCode()==KeyCodes.KEY_CTRL){
						return;
					}
					
					//LogUtils.log("key-up:"+event.getNativeKeyCode());
					if(event.getNativeKeyCode()==KeyCodes.KEY_ENTER){
						textModified();
					}
					else if(event.isControlKeyDown()){
						
						
						if(event.getNativeKeyCode()==83){//S(save)
							event.preventDefault();
							return;
						}
						
						if(event.getNativeKeyCode()!=65 && event.getNativeKeyCode()!=67){//ignore A(select all) && C(copy)
							textModified();
						}
						
					}
					else{
						textModified();
					}
				}
			});
	}

	/**
	 * This context menu is very primitive,so can't modified after create.used globally
	 * TODO 
	 * @author aki
	 *
	 */
	public class TestContextMenu extends AbstractContextMenu{
		@Override
		public void createMenus(MenuBar menuBar) {
			Command test1Command = new Command() {
				  public void execute() {
					SimpleTextData data=getSimpleDataListWidget().getSelection().getData();
					String currentDate=getSimpleDataListWidget().dateFormat.format(new Date(data.getCdate()));
					hide(); 
					String value=Window.prompt("New Date", currentDate);
				    if(value!=null){
				    	Date d=getSimpleDataListWidget().dateFormat.parse(value);
				    	data.setCdate(d.getTime());
				    	getSimpleDataListWidget().save();//it's work?
				    	getSimpleDataListWidget().redraw();
				    }
				  }
			};
			menuBar.addItem("Modify create date", test1Command);
			
		}
	}
	
	@Override
	public SimpleTextData createSaveData(String fileName) {
		String text=textArea.getText();
		SimpleTextData current=getSimpleDataListWidget().getSelection().getData();
		if(current==null){
		return new SimpleTextData(-1,fileName,text);
		}else{
			return new SimpleTextData(-1,fileName,text,current.getCdate());	//copy cdate
		}
	}

	@Override
	public SimpleTextData createNewData(String fileName) {
		String text="";
		return new SimpleTextData(-1,fileName,text);
	}

	protected String unselectedText="CREATE NEW or SELECT";
	
	private TextAreaBasedDataListListener textAreaBasedDataListListener;
	public TextAreaBasedDataListListener getTextAreaBasedDataListListener() {
		return textAreaBasedDataListListener;
	}

	public void setTextAreaBasedDataListListener(
			TextAreaBasedDataListListener textAreaBasedDataListListener) {
		this.textAreaBasedDataListListener = textAreaBasedDataListListener;
	}

	@Override
	public void loadData(Optional<SimpleTextData> hv) {
		if(hv.isPresent()){
			textArea.setReadOnly(false);
			textArea.setText(hv.get().getData());		
		}else{//unselected and clear
			textArea.setReadOnly(true);
			textArea.setText(unselectedText);
		}
		
		if(textAreaBasedDataListListener!=null){
			textAreaBasedDataListListener.onLoad(hv);
		}
		
		onLoad();
	}
	
	public void onLoad(){}
	
	/**
	 * used for export file name
	 * @return
	 */
	protected String getKeyName(){
		return "datas";
	}

	@Override
	public void exportDatas(List<SimpleTextData> list) {
		String exportText=generateExportText(list);
		Anchor anchor=new HTML5Download().generateTextDownloadLink(exportText,getKeyName()+".csv","Download data",true);
		getSimpleDataListWidget().add(anchor);
	}
	
	private String generateExportText(List<SimpleTextData> list){
		List<String> lines=FluentIterable.from(list).transform(new HeadAndValueToCsvFunction()).toList();
		String exportText=Joiner.on("\r\n").join(lines);
		return exportText;
	}
	
	public String generateExportText(){
		List<SimpleTextData> list=this.getDataList().getDataList();
		return generateExportText(list);
	}

	@Override
	public void importData() {
		if(getDataList().getDataList().size()>0){
		boolean confirm=Window.confirm("Import datas:add current datas with ignoring id,if you keep id use restore and replace all data.");
		if(!confirm){
			return;
		}
		}
		
		uploadForm = FileUtils.createSingleTextFileUploadForm(new DataURLListener() {
			@Override
			public void uploaded(File file, String value) {
				List<SimpleTextData> list=textToSimpleTextData(value);
				for(SimpleTextData hv:list){
					getDataList().addData(hv.getName(), hv.getData());
				}
				
				uploadPanel.removeFromParent();
				updateList();
			}
		}, false);
		
		
		createFormPanel("Import Datas:",uploadForm);
		getSimpleDataListWidget().add(uploadPanel);
	}

	@Override
	public void clearAll() {
		boolean confirm=Window.confirm("Clear all data?this operation can not undo\nyou should ExportAll first.");
		if(!confirm){
			return;
		}
		List<SimpleTextData> hvs= getDataList().getDataList();
		for(SimpleTextData hv:hvs){
			getDataList().clearData(hv.getId());
		}
		getDataList().setCurrentId(0);//restart id
		
		getSimpleDataListWidget().unselect();
		updateList();
		
	}
	

	public Optional<Integer> add(SimpleTextData data) {
		if(data!=null){
		int id=getDataList().addData(data.getName(), data.getCdate()+","+data.getData());
		updateList();
		return Optional.of(id);
		}
		return Optional.absent();
	}

	@Override
	public void copy(Object object) {
		copiedValue=(SimpleTextData) object;
	}

	@Override
	public void paste() {
		if(copiedValue!=null){
			SimpleTextData copy=copiedValue.copy();
			copy.setName(copy.getName()+" copy");
			execAdd(copy,true);
		//modified
		//getDataList().addData(copiedValue.getName()+" copy", copiedValue.getData());
		//updateList();
		}
	}

	@Override
	public void recoverLastSaved(SimpleTextData hv) {
		copy(hv);
		paste();
	}

	/**
	 * restore keep id
	 */
	@Override
	public void restore() {
		if(getDataList().getDataList().size()>0){
			boolean confirm=Window.confirm("Restore datas:clear current datas and restore csv datas.");
			if(!confirm){
				return;
			}
			}
		
		uploadForm = FileUtils.createSingleTextFileUploadForm(new DataURLListener() {
			@Override
			public void uploaded(File file, String value) {
				execRestore(value);
			}
		}, false);
		
		
		createFormPanel("Restore layers:",uploadForm);
		getSimpleDataListWidget().add(uploadPanel);
		
	}
	public void execRestore(String value){
		//clear first
		List<SimpleTextData> hvs= getDataList().getDataList();
		for(SimpleTextData hv:hvs){
			getDataList().clearData(hv.getId());
		}
		//getDataList().setCurrentId(0);
		
		int max=0;
		
		List<SimpleTextData> list=textToSimpleTextData(value);
		
		//do offset
		//LogUtils.log("force offset");
		//doOffset(parts.getKey(),list);
		
		GWT.log("upload-size:"+list.size());
		for(SimpleTextData hv:list){
			if(hv.getId()>max){
				max=hv.getId();
			}
			getDataList().updateData(hv.getId(),hv.getName(), hv.getData());
		}
		
		getDataList().setCurrentId(max+1);
		
		if(uploadPanel!=null){
			uploadPanel.removeFromParent();
		}
		updateList();
	}
	
	public void execRestore(List<SimpleTextData> list){
		//clear first
		List<SimpleTextData> hvs= getDataList().getDataList();
		for(SimpleTextData hv:hvs){
			getDataList().clearData(hv.getId());
		}
		//getDataList().setCurrentId(0);
		
		int maxId=0;
		
		
		//do offset
		//LogUtils.log("force offset");
		//doOffset(parts.getKey(),list);
		
		//LogUtils.log("upload-size:"+list.size());
		for(SimpleTextData hv:list){
			if(hv.getId()>maxId){
				maxId=hv.getId();
			}
			//LogUtils.log("update:"+hv.getId());
			getDataList().updateData(hv.getId(),hv.getName(), hv.getData());
		}
		
		getDataList().setCurrentId(maxId+1);
		
		//LogUtils.log("current-id:"+getDataList().getCurrentId());
		
		if(uploadPanel!=null){
			uploadPanel.removeFromParent();
		}
		selectedId=-1;
		updateList();
	}


	@Override
	public void doDoubleClick(int clientX, int clientY) {
		LogUtils.log("double-click:"+clientX+","+clientY);
	}
	
	private void createFormPanel(String text,FileUploadForm form){
		if(uploadPanel==null){
		uploadPanel=new HorizontalPanel();
		uploadPanel.add(new Label(text));
		uploadPanel.add(form);
		Button bt=new Button("close",new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				uploadPanel.setVisible(false);
			}
		});
		uploadPanel.add(bt);
		}else{
			uploadPanel.setVisible(true);
		}
	}

	//TODO move somewhere
	public static List<SimpleTextData> textToSimpleTextData(String text){
		//List<SimpleTextData> list=new ArrayList<SimpleTextData>();
		CSVReader reader=new CSVReader(text,'\t','"',true);
		try {
			List<String[]> csvs=reader.readAll();
			return FluentIterable.from(csvs).transform(new CsvArrayToHeadAndValueFunction()).toList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Window.alert(e.getMessage());
		}
		return null;
	}
}
