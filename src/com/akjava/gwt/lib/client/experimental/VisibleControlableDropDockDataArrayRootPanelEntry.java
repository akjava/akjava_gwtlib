package com.akjava.gwt.lib.client.experimental;

import java.util.List;

import com.akjava.gwt.html5.client.file.File;
import com.akjava.gwt.html5.client.file.Uint8Array;
import com.google.common.base.Optional;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.DeckLayoutPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public abstract class VisibleControlableDropDockDataArrayRootPanelEntry implements EntryPoint {
	protected DeckLayoutPanel rootDeck;
	protected VisibleControlableDropDockDataArrayRootPanel mainPanel;
	public void onModuleLoad() {
		
		initialize();
		
		rootDeck = new DeckLayoutPanel();
		RootLayoutPanel.get().add(rootDeck);
		
		rootDeck.add(createMainPanel());
		//TODO make setting-panel
		
		if(createExtraPanels()!=null){
		for(Panel panel:createExtraPanels()){
			rootDeck.add(panel);
		}
		}
		
		doBeforeShowMainPanel();
		
		showMainPanel();
	}
	
	private Panel createMainPanel(){
		mainPanel = new VisibleControlableDropDockDataArrayRootPanel() {

			
			
			@Override
			public void loadFile(String pareht, Optional<File> optional, Uint8Array array) {
				for(File file:optional.asSet()){
					doUploadFile(file,array);
				}
			}
			
		};
		
			final Panel top=createTopPanel();
			final Panel rightPanel=createRightPanel();
			final Panel centerPanel=createCenterPanel();
			//TODO support left and bottom
			
			
			mainPanel.setTop(top,getTopSize());
			mainPanel.setRight(rightPanel, getRightSize());
			mainPanel.setCenterPanel(centerPanel);
			
			mainPanel.setVisiblePanels(true, true, true, true);
			
			return mainPanel;
	}
	public abstract void doUploadFile(File file, Uint8Array array);
	public abstract Panel createTopPanel();
	/**
	 * use mainPanel.switchRight(); to show/hide right
	 * @return
	 */
	public abstract Panel createRightPanel();
	public abstract Panel createCenterPanel();
	public abstract int getTopSize();
	public abstract int getRightSize();
	
	public abstract void initialize();
	public abstract void doBeforeShowMainPanel();
	public abstract List<Panel> createExtraPanels();
	public void showMainPanel(){
		rootDeck.showWidget(0);
	}
}
