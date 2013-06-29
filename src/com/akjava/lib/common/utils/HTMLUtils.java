package com.akjava.lib.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.akjava.gwt.lib.client.LogUtils;
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
	   
	   public static String createSelectOptions(Map<String,String> valueAndLabel,List<String> selection){
		  String ret="";
		   for(String value:valueAndLabel.keySet()){
			   String option="<option";
			   if(selection!=null && selection.contains(value)){
				   option+=" selected=\"selected\">";
			   }else{
				   option+=">";
			   }
			   option+=valueAndLabel.get(value);
			   ret+=option+"\n";
		   }
		  
		   return ret;
	   }
	   
	   public static String createSelectInput(String name,Map<String,String> valueAndLabel,List<String> selection,boolean multiple){
		   String ret="<select name=\""+name+"\"";
		   if(multiple){
			   ret+=" multiple=\"multiple\"";
		   }
		   ret+=">\n";
		   
		   for(String value:valueAndLabel.keySet()){
			   String option="<option";
			   if(selection!=null && selection.contains(value)){
				   option+=" selected=\"selected\">";
			   }else{
				   option+=">";
			   }
			   option+=valueAndLabel.get(value);
			   ret+=option+"\n";
		   }
		   ret+="</select>";
		   return ret;
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
	   
	   public static boolean isHexColor(String text){
		   if(!text.startsWith("#")){
			   return false;
		   }
		   
		   if(text.length()!=4 && text.length()!=7){
			   return false;
		   }
		   
		   return true;
	   }
	   public static String rgbaToHex(String color){
		   if(isHexColor(color)){
			   return color;
		   }
		   String f=color.substring("rgba(".length());
		   int l=f.lastIndexOf(",");
		   if(l==-1){
			   return color;
		   }
		   String t=f.substring(0,l);
		   String[] rgb=t.split(",");
		   int r=Integer.parseInt(rgb[0], 10);
		   int g=Integer.parseInt(rgb[1], 10);
		   int b=Integer.parseInt(rgb[2], 10);
		   
		   //LogUtils.log(r+","+g+","+b);
		   String rColor=Integer.toHexString(r);
		   if(rColor.length()<2){
			   rColor="0"+r;
		   }
		   String gColor=Integer.toHexString(g);
		   if(gColor.length()<2){
			   gColor="0"+g;
		   }
		   String bColor=Integer.toHexString(b);
		   if(bColor.length()<2){
			   bColor="0"+b;
		   }
		   return "#"+rColor+gColor+bColor;
	   }
	   public static String hexColorToRGBA(String color,double start){
		   if(!isHexColor(color)){
			   return color;
		   }
		   String colorText=color.substring(1);
		   String rColor=colorText.length()==3?colorText.substring(0,1)+colorText.substring(0,1):colorText.substring(0,2);
		   String gColor=colorText.length()==3?colorText.substring(1,2)+colorText.substring(1,2):colorText.substring(2,4);
		   String bColor=colorText.length()==3?colorText.substring(2,3)+colorText.substring(2,3):colorText.substring(4,6);
		   
		   int r=Integer.parseInt(rColor, 16);
		   int g=Integer.parseInt(gColor, 16);
		   int b=Integer.parseInt(bColor, 16);
		   
		   return "rgba("+r+","+g+","+b+","+start+")";
	   }
	   
		public static String getLastDir(String url){
			int anchor=url.indexOf("#");
			if(anchor!=-1){
				url=url.substring(0,anchor);
			}
			int query=url.indexOf("?");
			if(query!=-1){
				url=url.substring(0,query);
			}
			String paths[]=url.split("/");
			return paths[paths.length-1];
		}
}
