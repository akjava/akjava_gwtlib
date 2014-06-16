package com.akjava.gwt.lib.client.widget.cell;

import com.akjava.gwt.lib.client.datalist.CellContextMenu;
import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.ContextMenuHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.PopupPanel;

public class SimpleContextMenu implements ContextMenuHandler,CellContextMenu{
		private PopupPanel contextMenu;
		private MenuBar menu=new MenuBar(true);
		public PopupPanel getContextMenu() {
			return contextMenu;
		}
		public MenuBar getMenu() {
			return menu;
		}
		public SimpleContextMenu(){
			this.contextMenu = new PopupPanel(true);
			contextMenu.add(menu);
			this.contextMenu.hide();
		}
		public void addCommand(String label,Command command){
			 menu.addItem(new MenuItem(label, true, command));	 
		}
		
		public void addCommand(MenuBar parent,String label,Command command){
			parent.addItem(new MenuItem(label, true, command));
		}
		
		public MenuBar makeSubMenu(String label){
			MenuBar menuBar=new MenuBar(true);
			
			
			 menu.addItem(label,menuBar);	 
			
			 return menuBar;
		}
		
		public void addSeparator(){
			menu.addSeparator();
		}
		public void hide(){
			this.contextMenu.hide();
		}
		@Override
		public void onContextMenu(ContextMenuEvent event) {
			if(!isAvaiableContextMenu()){
				return;
			}
				event.preventDefault();
			    event.stopPropagation();

			    onContextMenu(event.getNativeEvent().getClientX(), event.getNativeEvent().getClientY());
		}
		
		public boolean isAvaiableContextMenu(){
			return true;
		}
		
		private int clientX;
		public int getClientX() {
			return clientX;
		}
		public int getClientY() {
			return clientY;
		}
		private int clientY;
		@Override
		public void onContextMenu(int clientX, int clientY) {
			if(!isAvaiableContextMenu()){
				return;
			}
			this.clientX=clientX;
			this.clientY=clientY;
			// TODO Auto-generated method stub
			this.contextMenu.setPopupPosition(clientX, clientY);
		    this.contextMenu.show();
		}
}
