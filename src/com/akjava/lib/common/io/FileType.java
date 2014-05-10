package com.akjava.lib.common.io;

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
	
	public String toString(){
		return subtype;//for valuelistbox
	}
}
