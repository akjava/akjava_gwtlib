package com.akjava.gwt.lib.client.widget.cell.util;

import com.google.gwt.user.client.ui.CellPanel;

public class WidgetUtils {
	public static  int calculateScrollY(CellPanel widget,int index){
		int scroll=0;
		for(int i=0;i<index;i++){
			scroll+=widget.getWidget(i).getOffsetHeight();
			scroll+=widget.getSpacing();
		}
		return scroll;
	}
}
