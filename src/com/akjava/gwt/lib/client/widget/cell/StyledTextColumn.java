package com.akjava.gwt.lib.client.widget.cell;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.Column;

public abstract class StyledTextColumn<T> extends Column<T,SafeHtml>{
	public static class StyleAndLabel{
		public StyleAndLabel(String style,String label){
			if(style==null){
				style="";
			}
			this.style=style;
			if(label==null){
				label="";
			}
			this.label=label;
		}
		private String style;
		private String label;
		public String getStyle() {
			return style;
		}
		public void setStyle(String style) {
			this.style = style;
		}
		public String getLabel() {
			return label;
		}
		public void setLabel(String label) {
			this.label = label;
		}
	}
	
	public StyledTextColumn(AbstractCell<SafeHtml> cell) {
		super(cell);
	}
	
	public StyledTextColumn() {
		this(new SafeHtmlCell());
	}

	@Override
	public SafeHtml getValue(T object) {
		StyleAndLabel styleAndLabel=getStyleAndLabel(object);
		 SafeHtmlBuilder sb = new SafeHtmlBuilder();
    	 sb.appendHtmlConstant("<span class=\"" + styleAndLabel.getStyle()
   	          + "\">");
   	      sb.appendEscaped(styleAndLabel.getLabel());
   	      sb.appendHtmlConstant("</span>");
    	 return sb.toSafeHtml();
	}
	
	public abstract StyleAndLabel getStyleAndLabel(T object);

}
