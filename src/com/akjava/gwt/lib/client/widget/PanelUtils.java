package com.akjava.gwt.lib.client.widget;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PanelUtils {

	public VerticalPanel createScrolledTabInVerticalPanel(TabLayoutPanel tab,String label){
		ScrollPanel scroll=new ScrollPanel();
		scroll.setSize("98%", "98%");
		tab.add(scroll,label);
		VerticalPanel panel=new VerticalPanel();
		scroll.add(panel);
		return panel;
	}
	
	public HorizontalPanel createScrolledTabInHorizontalPanel(TabLayoutPanel tab,String label){
		ScrollPanel scroll=new ScrollPanel();
		scroll.setSize("98%", "98%");
		tab.add(scroll,label);
		HorizontalPanel panel=new HorizontalPanel();
		scroll.add(panel);
		return panel;
	}
}
