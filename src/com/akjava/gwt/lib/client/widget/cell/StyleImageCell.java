package com.akjava.gwt.lib.client.widget.cell;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
 public  class StyleImageCell extends AbstractCell<String> {
		 private String styleName;
		 public StyleImageCell(String styleName){
			 this.styleName=styleName;
		 }
		  
	    @Override
	    public void render(Context context,String value, SafeHtmlBuilder sb) {

	      // Always do a null check on the value. Cell widgets can pass null to cells
	      // if the underlying data contains a null, or if the data arrives out of order.
	      if (value == null) {
	        return;
	      }

	      // If the value comes from the user, we escape it to avoid XSS attacks.
	      SafeHtml safeValue = SafeHtmlUtils.fromString(value);

	      // Append some HTML that sets the text color.
	      sb.appendHtmlConstant("<img class=\"" + styleName
	          + "\" src=\""+value+"\">");
	      //sb.append(safeValue);
	      sb.appendHtmlConstant("</img>");
	    }
	  }