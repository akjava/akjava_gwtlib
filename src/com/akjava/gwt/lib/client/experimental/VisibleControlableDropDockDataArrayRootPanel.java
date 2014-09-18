package com.akjava.gwt.lib.client.experimental;

import com.akjava.gwt.html5.client.file.ui.DropDockDataArrayRootPanel;
import com.google.gwt.user.client.ui.Panel;

public  abstract class VisibleControlableDropDockDataArrayRootPanel extends DropDockDataArrayRootPanel{
	private Panel topPanel;
	private Panel rightPanel;
	private Panel leftPanel;
	private Panel bottomPanel;
	private Panel centerPanel;
	
	public void setCenterPanel(Panel centerPanel) {
		this.centerPanel = centerPanel;
	}
	private int topSize;
	private int rightSize;
	private int leftSize;
	private int bottomSize;
	private boolean topPanelVisible;
	private boolean bottomPanelVisible;
	private boolean leftPanelVisible;
	private boolean rightPanelVisible;
	
	public void setVisiblePanels(boolean topPanelVisible,boolean bottomPanelVisible,boolean leftPanelVisible,boolean rightPanelVisible){
		this.clear();
		this.topPanelVisible=topPanelVisible;
		this.bottomPanelVisible=bottomPanelVisible;
		this.leftPanelVisible=leftPanelVisible;
		this.rightPanelVisible=rightPanelVisible;
		
		if(topPanel!=null && topPanelVisible){
			addNorth(topPanel, topSize);
			topPanel.setSize("100%","100%");
		}
		
		if(bottomPanel!=null && bottomPanelVisible){
			addSouth(bottomPanel, bottomSize);
			topPanel.setSize("100%","100%");
		}
		
		if(leftPanel!=null && leftPanelVisible){
			addWest(leftPanel, leftSize);
			leftPanel.setSize("100%","100%");
		}
		if(rightPanel!=null && rightPanelVisible){
			addEast(rightPanel, rightSize);
			rightPanel.setSize("100%","100%");
		}
		
		add(centerPanel);//center always show
		
	}
	public void setTop(Panel topPanel,int topSize){
		this.topPanel=topPanel;
		this.topSize=topSize;
	}
	public void setBottom(Panel bottomPanel,int bottomSize){
		this.bottomPanel=bottomPanel;
		this.bottomSize=bottomSize;
	}
	public void setRight(Panel rightPanel,int rightSize){
		this.rightPanel=rightPanel;
		this.rightSize=rightSize;
	}
	public void setLeft(Panel leftPanel,int leftSize){
		this.leftPanel=leftPanel;
		this.leftSize=leftSize;
	}
	public void switchRight(){
		setVisiblePanels(topPanelVisible, bottomPanelVisible, leftPanelVisible, !rightPanelVisible);
	}
	//TODO more
	
}
