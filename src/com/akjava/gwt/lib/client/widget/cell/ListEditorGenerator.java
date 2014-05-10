package com.akjava.gwt.lib.client.widget.cell;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Converter;
import com.google.common.base.Objects;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class ListEditorGenerator<T> {

	private EasyCellTableObjects<T> easyCells;
	public EasyCellTableObjects<T> getEasyCells() {
		return easyCells;
	}


	private Button updateButton;
	private Button newButton;

	private Button removeButton;
	private Button addButton;
	private Label infoLabel;
	public VerticalPanel generatePanel(final SimpleCellTable<T> cellTable,final Converter<List<T>,String> converter,final Editor<T> editor,@SuppressWarnings("rawtypes") final  SimpleBeanEditorDriver driver,Map<String,String> labelMaps,final ValueControler valueControler){
		//check nulls
		final SimpleBeanEditorDriver<T,Editor<? super T>> castdriver=driver;
		
		if(labelMaps==null){
			labelMaps=new HashMap<String, String>();
		}
		
		VerticalPanel panel=new VerticalPanel();
		panel.add(cellTable);
		
		easyCells = new EasyCellTableObjects<T>(cellTable
				) {
			@Override
			public void onSelect(T selection) {
				if(selection!=null){
					castdriver.edit(selection);
					//baseFormantEditor.setVisible(true);
					updateButton.setEnabled(true);
					removeButton.setEnabled(true);
					newButton.setEnabled(true);
					addButton.setEnabled(false);
					
					onEditMode();
					
				}else{
					//baseFormantEditor.setVisible(false);
					updateButton.setEnabled(false);
					removeButton.setEnabled(false);
					newButton.setEnabled(false);
					addButton.setEnabled(true);
					
					onAddMode();
				}		
			}
			
		};
		
		castdriver.initialize(editor);
		castdriver.edit(createNewData());
		
		//some case no need value store
		if(valueControler!=null){
		String baseText=valueControler.getValue();
		
		//some case no need convert
		if(converter!=null){
		easyCells.setDatas(converter.reverse().convert(baseText));
		easyCells.update(true);
		}
		}
		

		HorizontalPanel buttons=new HorizontalPanel();
		panel.add(buttons);
		
		newButton = new Button(Objects.firstNonNull(labelMaps.get("new"),"New"),new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				easyCells.unselect();
				castdriver.edit(createNewData());
				
			}
		});
		buttons.add(newButton);
		newButton.setEnabled(false);
		
		addButton = new Button(Objects.firstNonNull(labelMaps.get("add"),"Add"),new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				T data=castdriver.flush();
				easyCells.addItem(data);
				castdriver.edit(createNewData());
				
				updateValue(converter,valueControler,easyCells.getDatas());
			}
		});
		buttons.add(addButton);
		addButton.setEnabled(true);
		

		
		updateButton = new Button(Objects.firstNonNull(labelMaps.get("update"),"Update"),new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				driver.flush();
				easyCells.update(true);
				updateValue(converter,valueControler,easyCells.getDatas());
				
			}
		});
		updateButton.setEnabled(false);
		buttons.add(updateButton);
		
		removeButton = new Button(Objects.firstNonNull(labelMaps.get("remove"),"Remove"),new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(easyCells.getSelection()!=null){
					easyCells.removeItem(easyCells.getSelection());
					castdriver.edit(createNewData());
					updateValue(converter,valueControler,easyCells.getDatas());
					
				}
			}
		});
		buttons.add(removeButton);
		removeButton.setEnabled(false);
		
		
		infoLabel = new Label();
		infoLabel.setStylePrimaryName("listedit_infolabel");
		buttons.add(infoLabel);
		//panel.add(editor);
		
		//add editor by yourself
		
		
		return panel;
	}
	
	private void updateValue(final Converter<List<T>,String> converter,final ValueControler valueControler,List<T> values){
		if(converter!=null && valueControler!=null){
			valueControler.setValue(converter.convert(values));
		}
		onUpdateData();
	}
	
	/**
	 * for enable/disable outside buttons
	 */
	public void onUpdateData(){
		
	}
	
	public void onEditMode(){
		infoLabel.setText("Editing selection");
	}
	public void onAddMode(){
		infoLabel.setText("");
	}
	
	//should i move to ?
	public abstract T createNewData();
	
	
	/**
	 * need when data load or store usually use for html5 storage
	 * @author aki
	 *
	 */
	public static interface  ValueControler{
		public void setValue(String value);
		public String getValue();
		
	}
}
