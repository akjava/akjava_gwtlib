package com.akjava.gwt.lib.client.datalist;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * used for  keyword links
 * @author aki
 *
 */
public class RichTitle {



@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((subTitles == null) ? 0 : subTitles.hashCode());
		result = prime * result + ((titles == null) ? 0 : titles.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RichTitle other = (RichTitle) obj;
		if (subTitles == null) {
			if (other.subTitles != null)
				return false;
		} else if (!subTitles.equals(other.subTitles))
			return false;
		if (titles == null) {
			if (other.titles != null)
				return false;
		} else if (!titles.equals(other.titles))
			return false;
		return true;
	}

private static Joiner joiner=Joiner.on("/");
private List<String> titles=new ArrayList<String>();
public List<String> getTitles() {
	return titles;
}

public List<String> getSubTitles() {
	return subTitles;
}

private List<String> subTitles=new ArrayList<String>();

public RichTitle(String line){
	int index=line.indexOf("(");
	String titleText="";
	String subText="";
	if(index==-1){
		titleText=line;
	}else{
		int end=line.indexOf(")");
		if(end==-1){
			//something invalid
			titleText=line;
		}else{
			titleText=line.substring(0,index);
			subText=line.substring(index+1,end);
		}
	}
	String[] ts=titleText.split("/");
	for(String t:ts){
		if(!t.isEmpty()){
			titles.add(t);
		}
	}
	
	String[] ss=subText.split("/");
	for(String s:ss){
		if(!s.isEmpty()){
			subTitles.add(s);
		}
		
	}
}
public List<String> getBothTitles(){
	List<String> titles=Lists.newArrayList(this.getTitles());
	Iterable<String> bothTitles=Iterables.concat(titles,this.getSubTitles());
	return Lists.newArrayList(bothTitles);
}

@Override
public String toString(){
	String result=joiner.join(titles);
	if(subTitles.size()>0){
		result+="("+joiner.join(subTitles)+")";
	}
	return result;
}

}
