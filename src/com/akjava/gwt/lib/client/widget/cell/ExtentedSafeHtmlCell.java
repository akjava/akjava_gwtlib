package com.akjava.gwt.lib.client.widget.cell;

import com.akjava.gwt.lib.client.datalist.AbstractContextCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * 
 * the safehtmlcell support double-click & contextmenu
 * @author aki
 *
 */

public abstract class ExtentedSafeHtmlCell extends AbstractContextCell<SafeHtml>{

	
	public abstract void onDoubleClick(int clientX, int clientY);

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			SafeHtml value, SafeHtmlBuilder sb) {
	    if (value != null) {
	        sb.append(value);
	      }
	}

}
