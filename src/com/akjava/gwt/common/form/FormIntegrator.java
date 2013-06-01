package com.akjava.gwt.common.form;

import com.akjava.gwt.lib.client.LogUtils;
import com.akjava.gwt.lib.client.StorageControler;
import com.akjava.gwt.lib.client.StorageException;
import com.akjava.lib.common.utils.ValuesUtils;
import com.google.common.base.Strings;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * for integrate normal html form & gwt
 * however not so good work
 * @author aki
 *
 */
public class FormIntegrator {
private StorageControler storageControler=new StorageControler(false);	
private String formKey;	
public FormIntegrator(String formKey,String initialValue,String sessionId,String targetId) throws StorageException{
	this.formKey=formKey;
	if(storageControler!=null){
		lastSessionId=storageControler.getValue(formKey+"_fi_session_id", "");
	}
	
	this.sessionId=sessionId;
	this.initialValue=initialValue;
	this.targetId = targetId;
	
	
	if(storageControler!=null){
		//overwrite sessionId
		if(sessionId!=null && !sessionId.isEmpty()){
		storageControler.setValue(formKey+"_fi_session_id", sessionId);
		}
	}
}

private String initialValue;//used initial widget value & keep value for reset
public String getInitialValue() {
	return initialValue;
}

private String sessionId;//used for store modified 
private String targetId;//used for sync normal-input(text or hidden)
private String lastSessionId;

public void updateValue(String value) throws StorageException{
		//only save if sessionId exist
		if(!Strings.isNullOrEmpty(sessionId)){
			storageControler.setValue(formKey+"_fi_last_value", value);
		}
		
		//only sync value if targetId exist
		if(!Strings.isNullOrEmpty(targetId)){
			setInputValueById(targetId, value);
		}
}
private void setInputValueById(String id,String value){
	RootPanel panel=RootPanel.get(id);
	if(panel!=null){
		Element element=panel.getElement();
		element.setAttribute("value",value);
	}
}

public String getValue() throws StorageException{
	String value=null;
	if(storageControler==null){
		LogUtils.log("null storage controler");
		return initialValue;
	}
	if(Strings.isNullOrEmpty(sessionId)){//no integrate
		value= initialValue;
		LogUtils.log("no-integrate");
	}else{
		
		if(lastSessionId.equals(sessionId)){
			LogUtils.log("browser back");
			//maybe browser back
			value= storageControler.getValue(formKey+"_fi_last_value", "");
		}else{
			LogUtils.log("new-page:last="+lastSessionId+",new="+sessionId);
			//page new loaded
			storageControler.setValue(formKey+"_fi_last_value", initialValue);
			value= initialValue;
		}
	}
	
	if(!Strings.isNullOrEmpty(targetId)){
		setInputValueById(targetId, value);
	}
	
	return value;
}

}
