package com.akjava.gwt.lib.client.datalist;
import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.ContextMenuHandler;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.PopupPanel;


public abstract class AbstractContextMenu implements ContextMenuHandler,CellContextMenu{

	private PopupPanel contextMenu;
	private MenuBar menuBar=new MenuBar(true);
	public AbstractContextMenu(){
		 this.contextMenu = new PopupPanel(true);
		 contextMenu.add(menuBar);
		
		 createMenus(menuBar);
		
		 this.contextMenu.hide();
	}
	public abstract void createMenus(MenuBar menuBar);
	
	@Override
	public void onContextMenu(int clientX, int clientY) {
		this.contextMenu.setPopupPosition(clientX, clientY);
	    this.contextMenu.show();
	}

	@Override
	public void onContextMenu(ContextMenuEvent event) {
		event.preventDefault();
	    event.stopPropagation();
	    
		onContextMenu(event.getNativeEvent().getClientX(),event.getNativeEvent().getClientY());
	}
	public void hide(){
		contextMenu.hide();
	}

}
