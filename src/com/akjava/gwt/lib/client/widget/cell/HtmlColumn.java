package com.akjava.gwt.lib.client.widget.cell;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.Column;

public abstract class HtmlColumn<T> extends Column<T,SafeHtml>{

	
	public HtmlColumn() {
		this(new SafeHtmlCell());
	}
	
	public HtmlColumn(Cell<SafeHtml> cell) {
		super(cell);
	}

	public abstract String toHtml(T object);
	
	@Override
	public SafeHtml getValue(T object) {
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
    	 sb.appendHtmlConstant(toHtml(object));
    	 return sb.toSafeHtml();
	}
	
}