package com.akjava.gwt.lib.client.datalist;

import java.util.List;

import com.akjava.gwt.html5.client.download.HTML5Download;
import com.akjava.gwt.lib.client.StorageDataList;
import com.google.gwt.user.client.ui.Anchor;

public class SimpleTextDataUtils {
private SimpleTextDataUtils(){}

public Anchor createDumpDownloadLink(List<SimpleTextData> datas,String fileName,String label){
	String text=new SimpleTextDatasCsvConverter().convert(datas);
	Anchor anchor=HTML5Download.get().generateTextDownloadLink(text, fileName, label,true);
	return anchor;
}

public static void execClear(StorageDataList dataList){
	List<SimpleTextData> hvs= dataList.getDataList();
	for(SimpleTextData hv:hvs){
		dataList.clearData(hv.getId());
	}
}
public static void execRestore(StorageDataList dataList,String csvText){
	//clear first
	execClear(dataList);
	
	int max=0;
	
	List<SimpleTextData> list=new SimpleTextDatasCsvConverter().reverse().convert(csvText);
	
	for(SimpleTextData hv:list){
		if(hv.getId()>max){
			max=hv.getId();
		}
		dataList.updateData(hv.getId(),hv.getName(), hv.getData());
	}
	
	dataList.setCurrentId(max+1);
}
}
