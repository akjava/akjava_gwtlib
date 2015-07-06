package com.akjava.gwt.lib.client.widget;

import com.akjava.gwt.lib.client.LogUtils;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.TextArea;
/**
 * only work on Chrome(web-kit)
 * to catch new value add addValueChangeHandler
 * 
 * 	addValueChangeHandler(new ValueChangeHandler<String>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				GWT.log("changed:"+event.getValue());
			}
		});
 * ref
 * https://groups.google.com/forum/?fromgroups#!topic/google-web-toolkit/CaNSdwfSK-A
 * @author aki
 *
 */
public class PasteValueReceiveArea extends TextArea{
		public PasteValueReceiveArea(){
			super();
			sinkEvents(Event.ONPASTE);
			
			setReadOnly(true);
		}
		public void onBrowserEvent(Event event) {
		    super.onBrowserEvent(event);
		    switch (event.getTypeInt()) {
		        case Event.ONPASTE: {
		        	ValueChangeEvent.fire(this, getPastedText(event));
		            break;
		        }
		    }
		}
	
		/**
		 ref
		 * https://groups.google.com/forum/?fromgroups#!topic/google-web-toolkit/CaNSdwfSK-A
		 */
		
	public static native String getPastedText(Event event)
    /*-{

        var text = "";

        if (event.clipboardData) // WebKit/Chrome/Safari
        {
            try
            {
                text = event.clipboardData.getData("Text");
                return text;
            }
            catch (e)
            {
                
            }
        }
        return text;
        }-*/;

}