package com.akjava.gwt.lib.client.widget.cell;

import com.google.gwt.user.cellview.client.SimplePager;

public class NormalPager extends SimplePager{

	  protected String createText() {
		  if(getDisplay().getRowCount()==0){
		  		return "0 of 0";
		  	}else{
		  		return super.createText();
		  	}
		  }	  
}
