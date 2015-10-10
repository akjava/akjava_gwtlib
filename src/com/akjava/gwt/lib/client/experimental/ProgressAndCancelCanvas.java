package com.akjava.gwt.lib.client.experimental;

import com.google.gwt.user.client.ui.HorizontalPanel;

public abstract class ProgressAndCancelCanvas extends ProgressCanvas {

	public ProgressAndCancelCanvas(String title, int maxStep) {
		super(title, maxStep);
		HorizontalPanel h=new HorizontalPanel();
		mainPanel.add(h);
		h.setWidth("100%");
		h.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		ExecuteButton cancelBt=new ExecuteButton("Cancel",false) {
			
			@Override
			public void executeOnClick() {
				onCancel();
			}
		};
		
		h.add(cancelBt);
	}

	public abstract void onCancel();
}
