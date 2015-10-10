package com.akjava.gwt.lib.client.experimental;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/* sample css
 
 .widgetList{
	padding:4px;
	background: #ccc;
}
.widgetList-default{
	margin:4px;
	border: 2px solid #fff;
	background: #fff;
}
.widgetList-select{
	border: 2px solid #00f;
}
 
 */
/* sample code
 
 List<TestData> tests=Lists.newArrayList(new TestData("hello"),new TestData("world"));
		WidgetList<TestData> list=new WidgetList<TestData>() {
			
			@Override
			public void onSelect(TestData data) {
				LogUtils.log(data.getName());
				
			}
			
			@Override
			public FocusPanel createWidget(TestData data) {
				FocusPanel panel=new FocusPanel();
				panel.setSize("200px", "200px");
				panel.add(new Label(data.getName()));
				return panel;
			}
		};
		RootPanel.get().add(list);
		list.setData(tests);
		
 */
public abstract class WidgetList<T> extends VerticalPanel{
public static final String STYLE_WIDGET_LIST="widgetList";
public static final String STYLE_WIDGET_LIST_DEFAULT="widgetList-default";
public static final String STYLE_WIDGET_LIST_SELECT="widgetList-select";
	private Panel container;
	private List<T> datas;
	private List<FocusPanel> widgets;
	public List<FocusPanel> getWidgets() {
		return widgets;
	}


	public WidgetList(){
		//horizontal only
		container=new HorizontalPanel();
		add(container);
		container.setStylePrimaryName(STYLE_WIDGET_LIST);
	}
	
	
	public void addData(T data){
		datas.add(data);
		updateData();
	}
	
	public void removeData(T data){
		datas.remove(data);
		updateData();
	}
	
	public void setData(List<T> datas){
		this.datas=datas;
		updateData();
	}
	public void updateData(){
		widgets=new ArrayList<FocusPanel>();
		container.clear();
		for(final T data:datas){
			FocusPanel panel=createWidget(data);
			widgets.add(panel);
			panel.setStylePrimaryName(STYLE_WIDGET_LIST_DEFAULT);
			container.add(panel);
			
			panel.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					select(data);
				}
			});
		}
	}
	private T selection;
	
	public T getSelection() {
		return selection;
	}

	private Widget getWidget(T data){
		if(data==null){
			return null;
		}
		int index=datas.indexOf(data);
		if(index!=-1 && index<widgets.size()){
			return widgets.get(index);
		}
		return null;
	}
	
	public void select(T data){
		if(selection!=null){
			Widget oldone=getWidget(selection);
			if(oldone!=null){
				oldone.removeStyleName(STYLE_WIDGET_LIST_SELECT);
			}
		}
		
		selection=data;
		if(data!=null){
			Widget newone=getWidget(data);
			if(newone!=null){
				newone.addStyleName(STYLE_WIDGET_LIST_SELECT);
			}
		}
		onSelect(data);
		
	}
	public void unselect(){
		select(null);
	}
	
	public abstract void onSelect(T data);
	public abstract FocusPanel createWidget(T data);
}
