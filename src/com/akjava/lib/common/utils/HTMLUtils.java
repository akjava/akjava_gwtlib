package com.akjava.lib.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;

public class HTMLUtils {

	//SHOULD i use commons lang?
	 public static String[] sanitizeSrc={"&","<",">","\"","'"};
	 public static String[] sanitizeDesc={"&amp;","&lt;","&gt;","&quot;","&#39;"};
	
	 
	  public static String sanitize(String html) {
	      if(html==null || html.isEmpty()){
	    	  return html;
	      }
	      
	      for(int i=0;i<sanitizeSrc.length;i++){
	    	  html=html.replace(sanitizeSrc[i], sanitizeDesc[i]);
	      }
	      
	      return html;
	   }

	   public static String unsanitize(String html) {
	      if(html==null || html.isEmpty()){
	    	  return html;
	      }
	      for(int i=0;i<sanitizeSrc.length;i++){
	    	  html=html.replace(sanitizeDesc[i],sanitizeSrc[i]);
	      }
	      
	      return html;
	   }
	   public static boolean hasSanitize(String html){
		   for(int i=0;i<sanitizeSrc.length;i++){
			   if(sanitizeSrc[i].equals("&")){
				   continue;//TODO check it
			   }
		    	  if(html.indexOf(sanitizeSrc[i])!=-1){
		    		  return true;
		    	  }
		      }
		   
		   return false;
	   }
	   
	   public static String createHiddenInput(String name,String value){
		   return "<input type='hidden' name='"+name+"' value='"+value+"'/>";
	   }
	   public static List<String> getLinks(String html){
		  List<String> links=new ArrayList<String>();
		   List<String> alinks=TagUtil.getTagByName(html, "a");
		   GWT.log("alink:"+alinks);
		   for(int i=0;i<alinks.size();i++){
			  // GWT.log(""+alinks.get(i));
		   }
		  for(String link:alinks){
			  Map<String,String> attr=TagUtil.getAttribute(link);
			  if(attr.get("href")!=null){
				  links.add(attr.get("href"));
			  }
			  
		  }
		   return links;
	   }
}
