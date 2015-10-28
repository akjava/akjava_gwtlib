package com.akjava.gwt.jszip.client;

import static com.google.common.base.Preconditions.checkState;

import java.util.Date;

import com.akjava.gwt.html5.client.file.Blob;
import com.akjava.gwt.html5.client.file.Uint8Array;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.typedarrays.client.Uint8ArrayNative;
import com.google.gwt.typedarrays.shared.ArrayBuffer;

public class JSZip extends JavaScriptObject{
protected JSZip(){}
	private static final DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("MMMM dd, yyyy HH:mm:ss");
	public static native final boolean exists()/*-{
		if($wnd.JSZip){
			return true;
		}else{
			return false;
		}
	
	}-*/;

	public final static native JSZip newJSZip()/*-{
	return new $wnd.JSZip();
	}-*/;
	
	public final static native JSZip newJSZip(Uint8Array array)/*-{
	return new $wnd.JSZip(array);
	}-*/;
	
	public final native JSFile getFile(String fileName)/*-{
	return this.file(fileName);
	}-*/;
	
	public final native JavaScriptObject files()/*-{
	

	return this.files;
	}-*/;
	
	public final native JsArrayString getFiles()/*-{
	var ret=[];
			for(var value in this.files){
			ret.push(value);
		}
		
	return ret;
	}-*/;
	
	/**
	 * 
	 * @param fileName
	 * if fileName contain /,directory is created automatically  
	 * @param text
	 * @return
	 */
	public final native JSZip file(String fileName,String text)/*-{
	return this.file(fileName,text);
	}-*/;
	
	
	
	public final native JSZip file(String fileName,String text,String dateLabel)/*-{
	return this.file(fileName,text,{date : new $wnd.Date(dateLabel)});
	}-*/;
	
	public final  JSZip file(String fileName,String text,long datetime){
		String dateLabel=dateTimeFormat.format(new Date(datetime));
		return file(fileName,text,dateLabel);
	}
	
	/**
	 * designed for canvas-url
	 * @param fileName
	 * @param url
	 * @return
	 */
	public final  JSZip base64UrlFile(String fileName,String url){
		int index=url.indexOf("base64,");
		String imgData;
		if(index==-1){
			imgData=url;
		}else{
			imgData=url.substring(index+"base64,".length());
		}
		return file(fileName, imgData,JSFileOptions.newJSFileOptions().base64(true));
	}
	
	public final native JSZip file(String fileName,String text,JSFileOptions option)/*-{
	return this.file(fileName,text,option);
	}-*/;
	
	/**
	 * not tested
	 * @param fileName
	 * @param data
	 * @param option
	 * @return
	 */
	public final native JSZip file(String fileName,JavaScriptObject data,JSFileOptions option)/*-{
	return this.file(fileName,data,option);
	}-*/;
	
	public final native JSZip file(String fileName,Uint8Array data,JSFileOptions option)/*-{
	return this.file(fileName,data,option);
	}-*/;
	
	public final native JSZip folder(String folderName)/*-{
	return this.folder(folderName);
	}-*/;
	
	public final native JSZip load(String xhrText)/*-{
	return this.load(xhrText);
	}-*/;
	
	public final native JSZip load(Uint8Array array)/*-{
	return this.load(array);
	}-*/;
	
	public final native JSZip load(String text,JSFileOptions option)/*-{
	return this.load(text,option);
	}-*/;
	
	
	
	public final native JSZip remove(String name)/*-{
	return this.remove(name);
	}-*/;
	
	//TODO cast type
	public final native Object generate()/*-{
	return this.generate();
	}-*/;
	
	public final native JavaScriptObject generate(GenerateParameter parameter)/*-{
	return this.generate(parameter);
	}-*/;
	
	public final native Uint8ArrayNative generateUnit8Deflate(int compressionLevel)/*-{
	return this.generate({type:"uint8array",compression:"DEFLATE",compressionOptions:{level:compressionLevel}});
	}-*/;
	
	/**
	 * this is very useful but this method need html5 libs
	 
	<inherits name="com.akjava.gwt.lib.Commons"/>
	<inherits name="com.akjava.gwt.html5.Html5widget" />
	 
	 * @param compression
	 * @return
	 */
	public final native Blob generateBlob(String compression)/*-{
	var parameter={type:"blob"};
	if(compression){
		parameter.compression=compression;
	}	
	return this.generate(parameter);
	}-*/;
	
	//path must be from module
	public static final JSZip loadFile(String url){
		checkState(JSZip.exists(),"you need load jszip.js on html");
		JSZip zip=JSZip.newJSZip();
		if(!url.startsWith("/")){
			url="../"+url;//relative from module
		}
		return zip.load(getBinaryResource(url));
	}
	
	public static final JSZip loadBase64(String base64){
		checkState(JSZip.exists(),"you need load jszip.js on html");
		//TODO Base64Utils.cutHeader(text) in here
		JSZip zip=JSZip.newJSZip();
		zip.load(base64,JSFileOptions.newJSFileOptions().base64(true));
		return zip;
	}

	public static final JSZip loadFromArray(Uint8Array array){
		checkState(JSZip.exists(),"you need load jszip.js on html");
		JSZip zip=JSZip.newJSZip();
		zip.load(array);
		return zip;
	}
	
	public static final JSZip loadFromArrayBuffer(ArrayBuffer buffer){
		checkState(JSZip.exists(),"you need load jszip.js on html");
		JSZip zip=JSZip.newJSZip();
		zip.load(Uint8Array.createUint8(buffer));
		return zip;
	}
	
	
	
	//from http://stackoverflow.com/questions/6023915/gwt-http-response-gettext-as-binary
	private static native String getBinaryResource(String url) /*-{
    // ...implemented with JavaScript                 
    var req = new XMLHttpRequest();
    req.open("GET", url, false);  // The last parameter determines whether the request is asynchronous -> this case is sync.
    req.overrideMimeType('text/plain; charset=x-user-defined');
    req.send(null);
    if (req.status == 200) {                    
        return req.responseText;
    } else return null
}-*/;
	

}
