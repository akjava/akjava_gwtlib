package com.akjava.gwt.lib.client.widget.cell;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.SimplePager;

public class NaturalPager extends SimplePager{
public NaturalPager(){
	//it is natural that there are firstPage.
	super(TextLocation.CENTER,getDefaultResources(),false,1000,true);
}
private static Resources DEFAULT_RESOURCES;

private static Resources getDefaultResources() {
  if (DEFAULT_RESOURCES == null) {
    DEFAULT_RESOURCES = GWT.create(Resources.class);
  }
  return DEFAULT_RESOURCES;
}

@Override
public void nextPage() {
  super.setPage(getPage()+1);
}

@Override
public void previousPage() {
	super.setPage(getPage()-1);
}
}
