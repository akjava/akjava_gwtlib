package com.akjava.gwt.lib.client;

import com.google.gwt.user.client.Window;

public class ExportUtils {

	/*
	 * trying export data with tab.
	 * Html5Download is nice way
	 */
	public static native final void openTabTextChrome(String text,String wname)/*-{
	win = $wnd.open("", wname)
	win.document.body.innerText =""+text+"";
	}-*/;
	
	/*
	 * i have not idea how style set
	 */
	public static native final void openTabHtml(String text,String wname)/*-{
	win = $wnd.open("", wname);
	win.document.body.innerHTML =""+text+"";
	}-*/;
	
	/**
	 * 
	 * @param url data url or absolute url like http://*.com/*.jpg  ,use GWT.getHostPageBaseURL()
	 * @param wname
	 */
	public static native final void openTabAbsoluteURLImage(String url,String wname)/*-{
	win = $wnd.open("", wname);
	win.document.body.innerHTML ="<img style='position:absolute;top:0;left:0' src='"+url+"'>";
	}-*/;
	
	
	public static final void exportTextAsDownloadDataUrl(String text,String encode,String wname){		
		String url="data:application/octet-stream;charset="+encode+","+text;
		Window.open(url, wname, null);
	}
}
