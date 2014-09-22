package com.akjava.gwt.lib.client.experimental;

import java.util.List;

import com.akjava.gwt.html5.client.file.File;
import com.akjava.gwt.html5.client.file.Uint8Array;
import com.google.common.base.Optional;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.DeckLayoutPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

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
			final Widget rightPanel=createRightPanel();
			final Panel centerPanel=createCenterPanel();
			//TODO support left and bottom
			
			
			mainPanel.setTop(top,getTopSize());
			mainPanel.setRight(rightPanel, getRightSize());
			mainPanel.setCenterPanel(centerPanel);
			
			mainPanel.setVisiblePanels(true, true, true, true);
			
			return mainPanel;
	}
	public abstract void doUploadFile(File file, Uint8Array array);
	protected abstract Panel createTopPanel();
	/**
	 * use mainPanel.switchRight(); to show/hide right
	 * @return
	 */
	protected abstract Widget createRightPanel();
	protected abstract Panel createCenterPanel();
	protected abstract int getTopSize();
	protected abstract int getRightSize();
	
	protected abstract void initialize();
	protected abstract void doBeforeShowMainPanel();
	protected abstract List<Panel> createExtraPanels();
	public void showMainPanel(){
		rootDeck.showWidget(0);
	}
}
