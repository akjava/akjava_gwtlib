package com.akjava.lib.common.io;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.gwt.logging.client.DefaultLevel.Warning;

/**
 * has extension
 * @author aki
 *
 */
public class FileType {
	String type;
	String subtype;
	String extension;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSubtype() {
		return subtype;
	}
	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}
	public String getExtension() {
		return extension;
	}
	public FileType(String type, String subtype) {
		this(type,subtype,subtype);
	}
	
	public String getMimeType(){
		return getType()+"/"+getSubtype();
	}
	public FileType(String type, String subtype, String extension) {
		super();
		this.type = type;
		this.subtype = subtype;
		this.extension = extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public static final FileType WEBP=new FileType("image","webp");
	public static final FileType PNG=new FileType("image","png");
	public static final FileType JPEG=new FileType("image","jpeg","jpg");
	public static final FileType GIF=new FileType("image","gif");
	public static final FileType BMP=new FileType("image","bmp");
	
	public static final List<FileType> fileTypes=Lists.newArrayList(WEBP,PNG,JPEG,GIF,BMP);
	
	/**
	 * 
	 * @param extension
	 * @return Warning possible null if not exist ,right now only some image file type supported
	 */
	public static FileType getFileTypeByExtension(String extension){
		extension=extension.toLowerCase();
		for(FileType fileType:fileTypes){
			if(fileType.getExtension().equals(extension)){
				return fileType;
			}
		}
		return null;
	}
	
	public String toString(){
		return subtype;//for valuelistbox
	}
}
