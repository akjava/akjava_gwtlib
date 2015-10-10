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
		containerPanel.setWidth("100%");
	}
	public PreviewHtmlPanelControler(){
		this(230,800);
	}
	
	public void show(){
		if(popup==null){
			createPreviewPanel();
		}
		moveToAroundRightTop(popup);
		popup.show();
	}
	public void hide(){
		if(popup!=null){
			popup.hide();
		}
	}
	
	public void setPreviewHtml(SafeHtml html){
		
		previewHTML.setHTML(html);
		show();
	}
	
	private void createPreviewPanel() {
		popup = new PopupPanel();
		mainPanel = new VerticalPanel();//need on initial
		popup.add(mainPanel);
		previewHTML = new HTML();
		previewHTML.setSize(w+"px", h+"px");
		mainPanel.add(previewHTML);
		
		mainPanel.add(containerPanel);
		
		//popup.show();
		//moveToAroundRightTop(popup);
		
		
	}
	
	public VerticalPanel getContainer() {
		return containerPanel;
	}
	
	private void moveToAroundRightTop(PopupPanel dialog){
		int clientWidth=Window.getClientWidth();
		int scrollTopPos=Window.getScrollTop();
		int dw=dialog.getOffsetWidth();
		
		
		
		//LogUtils.log(clientWidth+","+scrollTopPos+","+dw);
		if(dw==0){
			//this is bug,some how first time return 0;maybe set html is async?
			dw=w+12;
		}
		
		dialog.setPopupPosition(clientWidth-dw, scrollTopPos+marginTop);
	}
	@Override
	public void setData(T data) {
		// TODO Auto-generated method stub
		
	}
	
}
