package com.akjava.lib.common.utils;




public class FileNames {
	public static final char SLAST='/';
	private char fileSeparator;
	private FileNames(char fileSeparator){
		this.fileSeparator=fileSeparator;
	}
	
	public static FileNames as(char fileSeparator){
		return new FileNames(fileSeparator);
	}
	
	public   boolean hasExtension(String path){
	String ext=getFileName(path);
	return ext.indexOf(".")!=-1;
	}
	
	public   String getFileName(String path){
	int last=path.lastIndexOf(fileSeparator);
	if(last!=-1){
		return path.substring(last+1);
	}else{
		return path;
	}
	}
	
	public  static String getExtension(String name){
		String ext;
		if(name.lastIndexOf(".")==-1){
			ext="";
		
		}else{
			int index=name.lastIndexOf(".");
			ext=name.substring(index+1,name.length());
		}
		return ext;
	}
	
	public  static String getRemovedExtensionName(String name){
		String baseName;
		if(name.lastIndexOf(".")==-1){
			baseName=name;
		
		}else{
			int index=name.lastIndexOf(".");
			baseName=name.substring(0,index);
		}
		return baseName;
	}
	
	public   String getChangedExtensionName(String path,String extension){
		if(hasExtension(path)){
			String removed=getRemovedExtensionName(path);
			return removed+"."+extension;
		}else{
			return path;
		}
		
	}
	
	public  String getIndexedPath(String path,String indexName){
		String extension=getExtension(path);
		if(extension.isEmpty()){
			if(!path.endsWith(""+fileSeparator)){
				path+=fileSeparator;
			}
			return path+indexName;
		}
		return path;
	}
	public String getRemovedDomainName(String path){
		int s=path.indexOf("://");
		if(s!=-1){
			int n=path.indexOf("/",s+"://".length());
			if(n==-1){
				return "";
			}else{
				return path.substring(n);
			}
		}
		return path;
	}
	
	/**
	 * 
	 * @param path
	 * @param isNoExtensionIsDir  recognie  filename which has no extension as folder 
	 * @return
	 */
	public  String getDirectoryPath(String path,boolean isNoExtensionIsDir){
		String extension=getExtension(path);
		if(extension.isEmpty() && (path.endsWith(""+fileSeparator) || isNoExtensionIsDir)){
			if(path.endsWith(""+fileSeparator)){
				return path;
			}else{
				return path+fileSeparator;
			}
		}else{
			int last=path.lastIndexOf(fileSeparator);
			if(last!=-1){
				return path.substring(0,last+1);
			}else{
				return path;
			}
		}
	}
	
}
