package com.akjava.gwt.jszip.client;

import static com.google.common.base.Preconditions.checkNotNull;

import com.akjava.gwt.html5.client.download.HTML5Download;
import com.akjava.gwt.html5.client.file.Blob;
import com.akjava.gwt.html5.client.file.File;
import com.akjava.gwt.html5.client.file.FileUploadForm;
import com.akjava.gwt.html5.client.file.FileUtils;
import com.akjava.gwt.html5.client.file.FileUtils.DataArrayListener;
import com.akjava.gwt.html5.client.file.Uint8Array;
import com.akjava.gwt.lib.client.Base64Utils;
import com.akjava.gwt.lib.client.BrowserUtils;
import com.akjava.gwt.lib.client.ImageElementUtils;
import com.akjava.gwt.lib.client.LogUtils;
import com.akjava.gwt.lib.client.BrowserUtils.LoadBinaryListener;
import com.akjava.gwt.lib.client.experimental.ImageBuilder;
import com.akjava.lib.common.io.FileType;
import com.akjava.lib.common.utils.FileNames;
import com.google.common.base.Optional;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.typedarrays.shared.ArrayBuffer;
import com.google.gwt.user.client.ui.Anchor;

/*
 * somehow webpalbum has ZipUtils
 * TODO merge them
 */
public class JSZipUtils {
private JSZipUtils(){}

public static void loadZipFromFile(final String url,final ZipListener listener){
	BrowserUtils.loadBinaryFile(url,new LoadBinaryListener() {
		@Override
		public void onLoadBinaryFile(ArrayBuffer buffer) {
			listener.onLoad(url,JSZip.loadFromArrayBuffer(buffer));
		}
		
		@Override
		public void onFaild(int states, String statesText) {
			listener.onFaild(states, statesText);
		}
	});
}
public static FileUploadForm createZipFileUploadForm(final ZipListener listener){
	FileUploadForm upload=FileUtils.createSingleFileUploadForm(new DataArrayListener() {
		@Override
		public void uploaded(File file, Uint8Array array) {
			listener.onLoad(file.getFileName(), JSZip.loadFromArray(array));
		}
	});
	upload.setAccept(FileUploadForm.ACCEPT_ZIP);
	return upload;
}

public static void createImageFile(JSZip zip,String fileName,ImageElement imageElement){
	checkNotNull(fileName,"createImagheFile:need file name");
	checkNotNull(imageElement,"createImagheFile:need image element");
	String dataUrl=ImageBuilder.from(imageElement).onFileName(fileName).toDataUrl();
	zip.base64UrlFile(fileName, dataUrl);
}

public static Optional<ImageElement> getImagheFile(JSZip zip,String fileName){
	checkNotNull(fileName,"getImagheFilee:need file name");
	JSFile jsFile=zip.getFile(fileName);
	if(jsFile==null){
		return Optional.absent();
	}
	String extension=FileNames.getExtension(fileName);
	FileType type=FileType.getFileTypeByExtension(extension);
	String dataUrl=Base64Utils.toDataUrl(type.getMimeType(),jsFile.asUint8Array());
	return Optional.of(ImageElementUtils.create(dataUrl));
}


public static Anchor createDownloadAnchor(JSZip zip,String fileName,String downloadLabel,boolean removeOnClick){
	Blob blob=zip.generateBlob(null);
	Anchor a=new HTML5Download().generateDownloadLink(blob,"application/zip",fileName,downloadLabel,removeOnClick);
	return a;
}

public static interface ZipListener{
	public void onLoad(String name,JSZip zip);
	public void onFaild(int states,String statesText);
}
}
