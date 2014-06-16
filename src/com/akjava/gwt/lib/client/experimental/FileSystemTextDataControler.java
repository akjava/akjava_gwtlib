package com.akjava.gwt.lib.client.experimental;

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
		if(rootDirectory.endsWith("/")){
			rootDirectory=StringUtils.chomp(rootDirectory);//
		}
		this.rootDirectory=rootDirectory;
	}
	
	public void initialize(MakeDirectoryCallback callback){
		FileIOUtils.makeDirectory(true, rootDirectory, callback);
	}
	
	public void addData(String text,WriteCallback callback){
		long t=System.currentTimeMillis();
		FileIOUtils.writeFile(true, rootDirectory+"/"+t, text, callback,false);
	}
	
	public void updateData(long time,String text,WriteCallback callback){
		FileIOUtils.writeFile(true, rootDirectory+"/"+time, text, callback,false);
	}
	
	public void removeData(long time,RemoveCallback callback){
		FileIOUtils.removeFile(true, rootDirectory+"/"+time, callback);
	}
	
	public String getFilePath(String path){
		return rootDirectory+"/"+FileNames.removeStartWithSeparator(path, '/');
	}
	public void readText(String fileName,ReadStringCallback callback){
		FileIOUtils.readFileAsString(true, rootDirectory+"/"+FileNames.removeStartWithSeparator(fileName, '/'), callback);
	}
	
	public void writeText(String fileName,String text,WriteCallback callback){
		FileIOUtils.writeFile(true, rootDirectory+"/"+FileNames.removeStartWithSeparator(fileName, '/'), text, callback,false);
	}
}
