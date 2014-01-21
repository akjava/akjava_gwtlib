package com.akjava.lib.common.utils;



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
		return path.charAt(path.length()-1)==fileSeparator;
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
	

	
}
