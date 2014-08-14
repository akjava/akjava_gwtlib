package com.akjava.gwt.lib.client.experimental;

import java.util.List;

import com.akjava.gwt.html5.client.file.File;
import com.akjava.gwt.html5.client.file.ui.DropDockDataUrlRootPanel;
import com.akjava.gwt.html5.client.file.webkit.FileEntry;
import com.akjava.gwt.lib.client.widget.PanelUtils;
import com.google.common.base.Optional;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DeckLayoutPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class CellBaseEntryPoint<T> implements EntryPoint{

	protected DeckLayoutPanel rootDeck;
	protected PreviewControler<T> previewControler;
	@Override
	public void onModuleLoad() {
		rootDeck = new DeckLayoutPanel();
		RootLayoutPanel.get().add(rootDeck);
		
		initialize();
		
		previewControler=createPreviewControler();
		
		DockLayoutPanel mainRoot=new DropDockDataUrlRootPanel() {
			
			@Override
			public void loadFile(String pareht, Optional<File> optional, String dataUrl) {
				//never called.onDropFiles overwrite but need implement
			}
			
			@Override
			public  void onDropFiles(List<FileEntry> files){
				doDropFiles(files);
			}
		};
		rootDeck.add(mainRoot);
		
		VerticalPanel topPanel=new VerticalPanel();
		topPanel.setSpacing(16);
		mainRoot.addNorth(topPanel, 48);
		topPanel.setSize("100%", "100%");
		topPanel.getElement().getStyle().setBackgroundColor("#607d8b");
		
		rootDeck.showWidget(0);
		
		
		HorizontalPanel panel=new HorizontalPanel();
		panel.setWidth("100%");
		topPanel.add(panel);
		
		Label appLabel=new Label(getAppName()+" Ver"+getAppVersion());
		appLabel.getElement().getStyle().setColor("#fff");
		panel.add(appLabel);
		
		HorizontalPanel rightPanel=new HorizontalPanel();
		panel.add(rightPanel);
		rightPanel.setWidth("100%");
		rightPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
		
		Anchor setting=new Anchor("Settings");
		setting.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				showSettingWidget();
			}
		});
		rightPanel.add(setting);
		
		VerticalPanel mainPanel=PanelUtils.createScrolledVerticalPanel(mainRoot,99);
		mainPanel.setWidth("100%");
		
		
		
		mainPanel.getElement().getStyle().setBackgroundColor("#e8e8e8");
		mainPanel.setSpacing(16);
    	
		mainPanel.add(createInputPanel());
		mainPanel.add(createMainCell());
		
		
		//create settings
		DockLayoutPanel settingPanel=new DockLayoutPanel(Unit.PX);
		rootDeck.add(settingPanel);
		
		VerticalPanel settingTopPanel=new VerticalPanel();
		settingTopPanel.setSpacing(16);
		
		settingTopPanel.setSize("100%", "100%");
		settingTopPanel.getElement().getStyle().setBackgroundColor("#607d8b");
		settingPanel.addNorth(settingTopPanel, 48);
		
		Label settingTitleLabel=new Label(getAppName()+" >> "+"Settings");
		settingTitleLabel.getElement().getStyle().setColor("#fff");
		settingTopPanel.add(settingTitleLabel);
		
		DockLayoutPanel closePanel=new DockLayoutPanel(Unit.PX);
		settingPanel.add(closePanel);
		
		Button closeBt=new Button("Close",new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				onCloseSettings();
				showMainWidget();
			}
		});
		closePanel.addNorth(closeBt, 48);
		
		ScrollPanel scroll=new ScrollPanel();
		closePanel.add(scroll);
		scroll.add(createSettingMainPanel());
		
		
		//create editor
		createEditor(rootDeck);
		
		createOtherWidget();
		}
	
	public abstract void initialize();
	public abstract void createOtherWidget();
	/*
	 * please add by yourself
	 */
	public abstract void createEditor(DeckLayoutPanel container);
	
	public abstract void onCloseSettings();
	public abstract String getAppName();
	public abstract String getAppVersion();
	
	public abstract CellList<T> createMainCell();
	public abstract Panel createInputPanel();
	public abstract Panel createSettingMainPanel();
	public void showMainWidget(){
		rootDeck.showWidget(0);
	}
	public void showSettingWidget(){
		rootDeck.showWidget(1);
	}
	
	public void showEditorgWidget(){
		rootDeck.showWidget(2);
	}
	
	public abstract void doDropFiles(List<FileEntry> files);
	public abstract PreviewControler<T> createPreviewControler();

	public static interface PreviewControler<T>{
		public void show();
		public void hide();
		public void setData(T data);
		public void setPreviewHtml(SafeHtml html);
	}

}
