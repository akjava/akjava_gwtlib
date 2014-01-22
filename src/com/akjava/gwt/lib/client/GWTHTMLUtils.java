package com.akjava.gwt.lib.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.UIObject;

public class GWTHTMLUtils {

	public static String getInputValueById(String id,String defaultValue){
		Element element=DOM.getElementById(id);
		if(element!=null){
			String v=element.hasAttribute("value")?element.getAttribute("value"):defaultValue;
			if(v==null){//some how trouble
				return defaultValue;
			}else{
				return v;
			}
		}else{
			return defaultValue;
		}
	}
	
	public static void addFloatLeftStyle(UIObject object){
		object.getElement().getStyle().setProperty("float", "left");
	}
	
	/**
	 * intension to set hidden value
	 * @param id
	 * @param value
	 */
	public static void setValueAttributeById(String id,String value){
		setAttributeById(id,"value",value);
	}
	public static void setAttributeById(String id,String attributeName,String value){
		Element element=DOM.getElementById(id);
		if(element!=null){
			element.setAttribute(attributeName, value);
		}else{
			LogUtils.log("not found element:setInputValueById"+id);
		}
	}
	
	public static void setPlaceHolder(TextBoxBase textbox,String text){
		textbox.getElement().setAttribute("placeHolder", text);
	}
	
	public static String extract(String tag){
		int s=tag.indexOf(">");
		int e=tag.lastIndexOf("<");
		if(s!=-1&&e!=-1 && e>s){
			return tag.substring(s+1,e);
		}else{
			return tag;
		}
	}
	public static void injectJavascript(String scriptSrc){
		Document doc = Document.get();
		ScriptElement script = doc.createScriptElement();
		script.setSrc(scriptSrc);
		script.setType("text/javascript");
		script.setLang("javascript");
		doc.getBody().appendChild(script);
	}


	/**
	 * 
	 * @param panel name
	 * @return
	 * return element which id is equals name if exist,otherwise return default root panel
	 * RootPanel htmlRoot=GWTHTMLUtils.getPanelIfExist("gwtapp");
	 */
	public static RootPanel getPanelIfExist(String name) {
		if(RootPanel.get(name)!=null){
			return RootPanel.get(name);
		}else{
			return RootPanel.get();
		}
	}
	/**
	 * used when only add modules if target id exists.
	 * @param name
	 * @return whether panel or input exist or not
	 *
	 */
	public static boolean isExistPanel(String name){
	return RootPanel.get(name)!=null;	
	}
	
	/*
	 * canvas never annoying selected
	 * via http://stackoverflow.com/questions/3684285/how-to-prevent-text-select-outside-html5-canvas-on-double-click
	 * canvas element
	 */
	public native final static void disableSelectionStart(Element element)/*-{
	element.onselectstart = function () { return false; }

	}-*/;
	public native final static void disableOnDragAndDrop(Element element)/*-{
		element.ondrag = function (){return false;};
	element.ondragstart = function (){return false;};
	element.ondragend = function (){return false;};
	}-*/;
}
