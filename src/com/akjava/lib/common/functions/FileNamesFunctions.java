package com.akjava.lib.common.functions;

import com.akjava.lib.common.utils.FileNames;
import com.google.common.base.Function;

public class FileNamesFunctions {

	/**
	 * TODO
	 * @author aki
	 *
	 *filter extension
	 *file only
	 *
	 *path to parentfolders for zip?
	 */
	public class PathToDirectoryNameFunction implements Function<String,String>{
		private boolean isHandleNoExtensionFileAsDir;
		private FileNames fileNames;
		public PathToDirectoryNameFunction(char fileSeparator,boolean isHandleNoExtensionFileAsDir){
			fileNames=FileNames.as(fileSeparator);
			this.isHandleNoExtensionFileAsDir=isHandleNoExtensionFileAsDir;
		}
		@Override
		public String apply(String input) {
			return fileNames.getDirectoryPath(input,isHandleNoExtensionFileAsDir);
		}
	}
	
	/**
	 * not replace when no extension like ends with file separator or has no extension
	 * @author aki
	 *
	 */
	public class ChangeExtensionFunction implements Function<String,String>{
		private String extension;
		private FileNames fileNames;
		public ChangeExtensionFunction(char fileSeparator,String extension){
			fileNames=FileNames.as(fileSeparator);
			this.extension=extension;
		}
		@Override
		public String apply(String input) {
			return fileNames.getChangedExtensionName(input,extension);
		}
	}
	
	/**
	 * replace no extension files too,but not replace a file must be folder(ends with fileSeparator)
	 * @author aki
	 *
	 */
	public class ChangeOrAddExtensionFunction implements Function<String,String>{
		private String extension;
		private FileNames fileNames;
		public ChangeOrAddExtensionFunction(char fileSeparator,String extension){
			fileNames=FileNames.as(fileSeparator);
			this.extension=extension;
		}
		@Override
		public String apply(String input) {
			
			if(fileNames.isEndsWithFileSeparator(input)){
				return input;
			}
			
			if(fileNames.hasExtension(input)){
			return fileNames.getChangedExtensionName(input,extension);
			}else{
			return input+"."+extension;	
			}
		}
	}

}
