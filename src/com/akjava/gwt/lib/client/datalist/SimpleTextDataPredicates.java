package com.akjava.gwt.lib.client.datalist;

import com.akjava.gwt.lib.client.LogUtils;
import com.akjava.lib.common.utils.FileNames;
import com.google.common.base.Predicate;

public class SimpleTextDataPredicates {
	/**
	 * for generating indexes
	 * @author aki
	 *
	 */
	public static class SameDirectoryOnly implements Predicate<SimpleTextData>{
		private String myFile;
		private boolean includeMyself;
		private String targetDirectory;
		FileNames fileNames=FileNames.asSlash();
		public SameDirectoryOnly(String myFile,boolean includeMyself){
			this.myFile=myFile;
			this.includeMyself=includeMyself;
			targetDirectory=fileNames.getDirectoryPath(myFile, false,false);
		}
		@Override
		public boolean apply(SimpleTextData input) {
			if(input.getName().equals(myFile)){
				if(includeMyself){
					return true;
				}
			}else{
				String directory=fileNames.getDirectoryPath(input.getName(), false,false);
				LogUtils.log(targetDirectory+","+directory);
				return targetDirectory.equals(directory);
			}
			return false;
		}
		
	}
}
