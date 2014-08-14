package com.akjava.gwt.lib.client.experimental.customcell;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellList.Style;


import com.google.gwt.resources.client.ImageResource;

public interface SimpleCellListResources extends CellList.Resources{
	 @Source({"clear.gif"})
	public ImageResource clearGif();
	 
     @Source({"simpleCellList.css"})
     @Override
     public Style cellListStyle();
}