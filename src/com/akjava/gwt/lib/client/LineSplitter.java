package com.akjava.gwt.lib.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.Scheduler.RepeatingCommand;



public class LineSplitter implements RepeatingCommand{
private List<String> lines=new ArrayList<String>();
private int consumeTime;
private String text;
private int cindex;
private SplitterListener listener;
public LineSplitter(String text,int consumeTime,SplitterListener listener){
	this.text=text;
	this.consumeTime=consumeTime;
	this.listener=listener;
}
	@Override
	public boolean execute() {
		int splitted=0;
		long s=System.currentTimeMillis();
		while(System.currentTimeMillis()<s+consumeTime){
		int find=text.indexOf("\n", cindex);
		if(find!=-1){
			lines.add(text.substring(cindex,find));
			cindex=find+1;
		}else{
			lines.add(text.substring(cindex));
			listener.onSuccess(lines);
			return false;
		}
		splitted++;
		}
		//LogUtils.log("parsed:"+index);
		return true;
	}

	public static interface SplitterListener {
		public void onSuccess(List<String> lines);
	}
	
	public static String[] splitLineAsArray(String text){
		List<String> values=splitLines(text);
		return values.toArray(new String[values.size()]);
	}
	public static List<String> splitLines(String text){
		text=text.replace("\r", "");
		List<String> result=new ArrayList<String>();
		int find=0;
		int cindex=0;
		while(find!=-1){
			find=text.indexOf("\n", cindex);
			if(find!=-1){
				result.add(text.substring(cindex,find));
				cindex=find+1;
			}else{
				result.add(text.substring(cindex));
			}
		}
		return result;
	}
	

}
