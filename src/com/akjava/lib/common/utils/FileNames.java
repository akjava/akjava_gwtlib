package com.akjava.lib.common.utils;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;



/**
 * TODO
 * define standards file-sets,htmls,images
 * define \
 * 
 * getLastDirectory()
 * @author aki
 *
 */
public class FileNames {
	public static final char SLASH='/';
	private char fileSeparator;
	public static FileNames asSlashFileName=new FileNames(SLASH);
	private FileNames(char fileSeparator){
		this.fileSeparator=fileSeparator;
	}
	
	public boolean isEndsWithFileSeparator(String path){
		if(Strings.isNullOrEmpty(path)){
			return false;
		}
		return path.charAt(path.length()-1)==fileSeparator;
	}
	
	public String chompIfEndsWithFileSeparator(String path){
		if(isEndsWithFileSeparator(path)){
			return path.substring(0,path.length()-1);
		}else{
			return path;
		}
	}
	
	
	public static String addEndWithSeparator(String string,char separator){
		if(Strings.isNullOrEmpty(string)){
			return string;
		}
		if(string.charAt(string.length()-1)==separator){
			return string;
		}else{
			return string+separator;
		}
	}
	
	public static String removeStartWithSeparator(String string,char separator){
		if(Strings.isNullOrEmpty(string)){
			return string;
		}
		if(string.charAt(0)==separator){
			return string.substring(1);
		}else{
			return string;
		}
	}
	
	public static FileNames asSlash(){
		return asSlashFileName;
	}
	/**
	 * i'm not sure why i choose method name "as.
	 * @param fileSeparator
	 * @return
	 */
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
	
	/**
	 * this method have bugs ignore folder have . 
	 * @param name
	 * @return
	 */
	public  static String getExtension(String name){
		checkNotNull(name,"getExtension():name must not be null");
		String ext;
		if(name.lastIndexOf(".")==-1){
			ext="";
		
		}else{
			int index=name.lastIndexOf(".");
			ext=name.substring(index+1,name.length());
		}
		return ext;
	}
	
	/**
	 * not support directory name contain .
	 * @param name
	 * @return
	 */
	public  static String getRemovedExtensionName(String name){
		checkNotNull(name,"getRemovedExtensionName():name must not be null");
		String baseName;
		if(name.lastIndexOf(".")==-1){
			baseName=name;
		
		}else{
			int index=name.lastIndexOf(".");
			baseName=name.substring(0,index);
		}
		return baseName;
	}
	
	/*
	 * try name03 > name , name1 > name
	 */
	public  static String getRemovedLastNumbers(String name){
		if(name==null || name.isEmpty()){
			return name;
		}
		int index=name.length()-1;
		for(int i=name.length()-1;i>=0;i--){
			if(!Character.isDigit(name.charAt(i))){
				index=i;
				break;
			}
		}
		
		return name.substring(0,index+1);
	}
	/*
	 * try name (03) > name , name(1) > name
	 */
	public  static String getRemovedLastDownloadNumbers(String name){
		if(name==null || name.isEmpty()){
			return name;
		}
		
		//need ends with )
		if(name.charAt(name.length()-1)!=')'){
			return name;
		}
		
		int index=name.length()-1;
		for(int i=name.length()-2;i>=0;i--){
			if(!Character.isDigit(name.charAt(i))){
				index=i;
				break;
			}
		}
		
		if(name.charAt(index)!='('){
			return name;
		}
		
		if(name.length()>index && name.charAt(index-1) ==' '){
			index--;
		}
		
		
		return name.substring(0,index);
	}
	
	//TODO support pattern?
	/*
	 * for creating uniq name
	 */
	public  static String createotExistNumberName(List<String> exists,String name,int start){
		Map<String,String> map=new HashMap<String, String>();
		for(String ex:exists){
			map.put(ex, "");
		}
		String fileName=null;
		do{
			fileName=name+start;
			start++;
		}while(map.get(fileName)!=null);
		
		return fileName;
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
	/**
	 * technically not filename,TODO make urls
	 * @param path
	 * @return
	 */
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
	public  String getDirectoryPath(String path,boolean isHandleNoExtensionFileAsDir){
		return getDirectoryPath(path,isHandleNoExtensionFileAsDir,true);
	}
	
	/**
	 * 
	 * @param path
	 * @param isHandleNoExtensionFileAsDir
	 * @param noDirContainAsDirectory
	 * the case path is "name" ,if true return "name":false return ""
	 * @return
	 */
	public  String getDirectoryPath(String path,boolean isHandleNoExtensionFileAsDir,boolean noDirContainAsDirectory){
		String extension=getExtension(path);
		if(extension.isEmpty() && (path.endsWith(""+fileSeparator) || isHandleNoExtensionFileAsDir)){
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
				if(noDirContainAsDirectory){
				return path;
				}else{
				return "";//root
				}
			}
		}
	}
	
	public static final String TYPE_PNG="png";
	public static final String TYPE_JPEG="jpeg";
	public static final String TYPE_GIF="gif";
	public static final String TYPE_UNKNOWN="*";
	public static String getImageType(String name){
	
		String ext=getExtension(name).toLowerCase();
		if(ext.equals("png")){
			return TYPE_PNG;
		}else if(ext.equals("jpg")||ext.equals("jpeg")){
			return TYPE_JPEG;
		}if(ext.equals("gif")){
			return TYPE_GIF;
		}
		return TYPE_UNKNOWN;
	}

	
}
