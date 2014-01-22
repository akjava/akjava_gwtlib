package com.akjava.gwt.lib.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;

public class RSSUtils {

	/*
	 * need
	 * <inherits name="com.google.gwt.xml.XML"/>
	 * 
	 * TODO create rss class
	 */
	public static List<Map<String,String>> parseRss(String xml){
		List<Map<String,String>> result=new ArrayList<Map<String,String>>();
		Document doc=XMLParser.parse(xml);
		NodeList list=doc.getElementsByTagName("item");
		for(int i=0;i<list.getLength()&&i<3;i++){
			Node node=list.item(i);
			NodeList child=node.getChildNodes();
			for(int j=0;j<child.getLength();j++){
				Node ch=child.item(j);
				Map<String,String> map=new HashMap<String, String>();
				String text="";
				NodeList c=ch.getChildNodes();
				for(int k=0;k<c.getLength();k++){
					if(c.item(k).getNodeType()==Node.TEXT_NODE){
					text+=c.item(k).toString();
					}
				}
				map.put(ch.getNodeName(), text);
				result.add(map);
			}
		}
		return result;
	}
}
