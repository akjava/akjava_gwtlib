package com.akjava.gwt.lib.client.experimental;

import static com.google.common.base.Preconditions.checkNotNull;

import com.akjava.gwt.html5.client.file.FileIOUtils;
import com.akjava.gwt.html5.client.file.FileIOUtils.MakeDirectoryCallback;
import com.akjava.gwt.html5.client.file.FileIOUtils.ReadStringCallback;
import com.akjava.gwt.html5.client.file.FileIOUtils.RemoveCallback;
import com.akjava.gwt.html5.client.file.FileIOUtils.WriteCallback;
import com.akjava.lib.common.utils.FileNames;
import com.akjava.lib.common.utils.StringUtils;

public class FileSystemTextDataControler{

	private String rootDirectory;
	
	public FileSystemTextDataControler(String rootDirectory){
		checkNotNull(rootDirectory);
		if(rootDirectory.endsWith("/")){
			rootDirectory=StringUtils.chomp(rootDirectory);//
		}
		this.rootDirectory=rootDirectory;
	}
	
	public void initialize(MakeDirectoryCallback callback){
		checkNotNull(callback);
		FileIOUtils.makeDirectory(true, rootDirectory, callback);
	}
	
	public void addData(String text,WriteCallback callback){
		checkNotNull(text);
		checkNotNull(callback);
		long t=System.currentTimeMillis();
		FileIOUtils.writeFile(true, rootDirectory+"/"+t, text, callback,false);
	}
	
	public void updateData(long time,String text,WriteCallback callback){
		checkNotNull(text);
		checkNotNull(callback);
		FileIOUtils.writeFile(true, rootDirectory+"/"+time, text, callback,false);
	}
	
	public void updateData(String fileName,String text,WriteCallback callback){
		checkNotNull(text);
		checkNotNull(callback);
		checkNotNull(fileName);
		FileIOUtils.writeFile(true, rootDirectory+"/"+fileName, text, callback,false);
	}
	
	public void removeData(long time,RemoveCallback callback){
		checkNotNull(callback);
		FileIOUtils.removeFile(true, rootDirectory+"/"+time, callback);
	}
	public void removeData(String fileName,RemoveCallback callback){
		checkNotNull(callback);
		checkNotNull(fileName);
		FileIOUtils.removeFile(true, rootDirectory+"/"+fileName, callback);
	}
	
	public String getFilePath(String path){
		checkNotNull(path);
		return rootDirectory+"/"+FileNames.removeStartWithSeparator(path, '/');
	}
	public void readText(String fileName,ReadStringCallback callback){
		checkNotNull(fileName);
		checkNotNull(callback);
		FileIOUtils.readFileAsString(true, rootDirectory+"/"+FileNames.removeStartWithSeparator(fileName, '/'), callback);
	}
	
	public void writeText(String fileName,String text,WriteCallback callback){
		checkNotNull(fileName);
		checkNotNull(callback);
		FileIOUtils.writeFile(true, rootDirectory+"/"+FileNames.removeStartWithSeparator(fileName, '/'), text, callback,false);
	}
}
