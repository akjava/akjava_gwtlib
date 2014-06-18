package com.akjava.gwt.lib.client.experimental;

import com.akjava.gwt.lib.client.LogUtils;
import com.google.common.base.Strings;
import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.Column;

/**
 *
 * @author aki
 *
 * @param <T>
 * 
 * sample
 MaxSizeImageColumn<MarkdownKeepData> imageColumn=new MaxSizeImageColumn<MarkdownKeepData>(200,40) {
					@Override
					public String getImageSrc(MarkdownKeepData object) {
						if(object.getImageDataUrl()==null){
							return "";
						}
						return object.getImageDataUrl();
					}
				
					
				
				};
				table.addColumn(imageColumn,"Image");
 */
public abstract class MaxSizeImageColumn<T> extends Column<T,SafeHtml>{
private int maxWidth;
private int maxHeight;
	
	public MaxSizeImageColumn(int maxWidth,int maxHeight) {
		super(new SafeHtmlCell());
		this.maxWidth=maxWidth;
		this.maxHeight=maxHeight;
	}
	
	

	public abstract String getImageSrc(T object);
	
	@Override
	public SafeHtml getValue(T object) {
		 SafeHtmlBuilder sb = new SafeHtmlBuilder();
		 if(!Strings.isNullOrEmpty(getImageSrc(object))){
			 String html="<img src='"+getImageSrc(object)+"' style='max-width:"+maxWidth+"px;max-height:"+maxHeight+"px'>";
			 LogUtils.log(html);
    	 	sb.appendHtmlConstant(html);
		 }
    	 return sb.toSafeHtml();
	}
	
}