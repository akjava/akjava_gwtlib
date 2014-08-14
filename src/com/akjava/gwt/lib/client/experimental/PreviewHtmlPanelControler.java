package com.akjava.gwt.lib.client.experimental;

import com.akjava.gwt.lib.client.experimental.CellBaseEntryPoint.PreviewControler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PreviewHtmlPanelControler<T> implements PreviewControler<T>{

	private PopupPanel popup;
	private HTML previewHTML;
	
	
	int w;
	int h;
	private int marginTop=48;
	private VerticalPanel mainPanel;
	private VerticalPanel containerPanel;
	public PreviewHtmlPanelControler(int w,int h){
		this.w=w;
		this.h=h;
		containerPanel = new VerticalPanel();
	}
	public PreviewHtmlPanelControler(){
		this(230,800);
	}
	
	public void show(){
		if(popup==null){
			createPreviewPanel();
		}
		popup.show();
	}
	public void hide(){
		if(popup!=null){
			popup.hide();
		}
	}
	
	public void setPreviewHtml(SafeHtml html){
		show();
		previewHTML.setHTML(html);
		
	}
	
	private void createPreviewPanel() {
		popup = new PopupPanel();
		mainPanel = new VerticalPanel();//need on initial
		popup.add(mainPanel);
		previewHTML = new HTML();
		previewHTML.setSize(w+"px", h+"px");
		mainPanel.add(previewHTML);
		
		mainPanel.add(containerPanel);
		popup.show();
		moveToAroundRightTop(popup);
		
		
	}
	
	public VerticalPanel getContainer() {
		return containerPanel;
	}
	private void moveToAroundRightTop(PopupPanel dialog){
		int w=Window.getClientWidth();
		int h=Window.getScrollTop();
		int dw=dialog.getOffsetWidth();
		
		dialog.setPopupPosition(w-dw, h+marginTop);
	}
	@Override
	public void setData(T data) {
		// TODO Auto-generated method stub
		
	}
	
}
