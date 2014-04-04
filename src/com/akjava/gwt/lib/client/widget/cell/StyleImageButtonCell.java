package com.akjava.gwt.lib.client.widget.cell;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
 public  class StyleImageButtonCell extends AbstractCell<String> {
		 private String styleName;
		 public StyleImageButtonCell(String styleName){
			 super("click", "keydown");
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
	    
	    @Override
	    public void onBrowserEvent(Context context, Element parent, String value,
	        NativeEvent event, ValueUpdater<String> valueUpdater) {
	      super.onBrowserEvent(context, parent, value, event, valueUpdater);
	      if ("click".equals(event.getType())) {
	        EventTarget eventTarget = event.getEventTarget();
	        if (!Element.is(eventTarget)) {
	          return;
	        }
	        if (parent.getFirstChildElement().isOrHasChild(Element.as(eventTarget))) {
	          // Ignore clicks that occur outside of the main element.
	          onEnterKeyDown(context, parent, value, event, valueUpdater);
	        }
	      }
	    }

	  

	    @Override
	    protected void onEnterKeyDown(Context context, Element parent, String value,
	        NativeEvent event, ValueUpdater<String> valueUpdater) {
	      if (valueUpdater != null) {
	        valueUpdater.update(value);
	      }
	    }
	  }