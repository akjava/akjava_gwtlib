/*
 * Created on 2004/10/24
 * Author aki@www.xucker.jpn.org
 * License Apache2.0 or Common Public License
 */
package com.akjava.lib.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;



/**
 * 
 *
 */
public class TagUtil {
public static String removeLineSeparator(String tag){
	tag=tag.replace("\n", "");
	 tag=tag.replace("\r", "");
	 return tag;
}

public static String getName(String text){
String result=null;
if(text.charAt(0)=='<'){
    int find=text.indexOf(" ");
    if(find!=-1){
        String tagName=text.substring(1,find);
        return tagName;
    }else{
    	int gt=text.indexOf(">");
    	if(gt!=-1){
    		String tagName=text.substring(1,gt);
    		
    		if(tagName.endsWith("/")){
    			return text.substring(1,gt-1); //case <tag/>
    		}else{
    			return tagName;
    		}
    	}
        return null;
    }    
}

return result;
}
public static String getAnother(String text){
String result=null;
if(text.charAt(0)=='<'){
    int find=text.indexOf(" ");
    if(find!=-1){
       int end=text.indexOf(">",find);
       if(end!=-1){
    	   String another=text.substring(find+1,end);
    	   if(another.endsWith("/")){
    		   return another.substring(0,another.length()-1);
    	   }else{
    		   return another;
    	   }
       }else{
    	   return null;
       }
    }else{
    	
        return null;
    }    
}

return result;
}
public static String getTag(String text){
String result=null;
if(text.charAt(0)=='<'){
	int maxSpaceAt=text.indexOf(">");
	if(maxSpaceAt==-1){
		return null;
	}
    int find=text.indexOf(" ");
    if(find!=-1 && find<maxSpaceAt){
        String tagName=text.substring(1,find);
        int last=text.indexOf(tagName+"/>");
        if(last!=-1){
            result=text.substring(0,tagName.length()+last+2);
           return result;
        }
        last=text.indexOf("</"+tagName+">");
        if(last!=-1){
            return text.substring(0,tagName.length()+last+3);
        }
        
       last=text.indexOf("/>");
        if(last!=-1){
            result=text.substring(0,last+2);
           return result;
        }
    }else{
    	int gt=text.indexOf(">");
    	if(gt!=-1){
    		String tagName=text.substring(1,gt);
    		
    		if(tagName.endsWith("/")){
    			return text.substring(0,gt+1); //case <tag/>
    		}else{
    			String endTag="</"+tagName+">";
    			int endMutch=text.indexOf(endTag,gt);
    			if(endMutch!=-1){
    				return text.substring(0,endMutch+endTag.length());
    			}
    		}
    	}
        return null;
    }
    
}
return result;
}

public static List<String> getTagByName(String text,String name){
	//not support />
    List<String> list=new ArrayList<String>();
    int index=text.toLowerCase().indexOf("<"+name.toLowerCase());
    
    while(index!=-1){
    	text=text.substring(index);
    	String tag=getTag(text);
    	list.add(tag);
    	text=text.substring(tag.length());
    	index=text.toLowerCase().indexOf("<"+name.toLowerCase());
    }
    /*
    int start=0;
    while(true){
        int index=text.toLowerCase().indexOf("<"+name.toLowerCase(),start);
        if(index==-1){
            break;
        }else{
            int end=text.toLowerCase().indexOf("</"+name.toLowerCase()+">",start+name.length()+2);
            if(end!=-1){
              // System.out.println("text:"+text.substring(index,end+name.length()+3));
                list.add(text.substring(index,end+name.length()+3));
                start=end+name.length()+3;
            }
        }
    }
    */
    return list;
}

public static String getContain(String tag){
    if(tag.startsWith("<")){
        int index=tag.indexOf(">");
        if(index!=-1){
            int last=tag.indexOf("<",index);
            if(last!=-1){
                return tag.substring(index+1,last);
            }
        }
        return null;
    }else{
        return null;
    }
}

/*
public static int  pmode=0;
public static Map<String,String> getAttribute(String tag){
    Map<String,String> map=new HashMap<String,String>();
    String another=getAnother(tag);
    String[] ps=another.split("\\s+");
    String key;
    String value;
    for(String v:ps){
    	if(!v.isEmpty()){
    		
    	}
    }
    return map;
}
private static boolean typeEquals(String t){
	return t.equals("=");
}
*/
public static int SINGLE_QUOTE=3;
public static int QUOTE=1;
public static int KEY=0;
public static int VALUE=2;
//BUGS cant get multiply single value
public static Map<String,String> getAttribute(String tag){
    Map<String,String> map=new HashMap<String,String>();
    if(tag.startsWith("<")){
        int start=tag.indexOf(" ");
        if(start==-1)
            return map;
        int end=tag.indexOf(">",start+1);
        if(end==-1)
            return map;
        
        String atrs=tag.substring(start+1,end);
        
        int mode=KEY;
        String quote=null;
        String key="";
        String value="";
        for(int i=0;i<atrs.length();i++){
            if(mode==KEY){
            if(isWhitespace(atrs.charAt(i))){
                
            }else{
                if(atrs.charAt(i)=='='){
                    mode=QUOTE;
                   
                }else{
                    if( atrs.charAt(i)=='/'){
                        break;
                    }
                key+=atrs.charAt(i);
                }
            }
            }else if(mode==QUOTE){
                if(isWhitespace(atrs.charAt(i))){
                    
                }else{
                    if(atrs.charAt(i)=='\''){
                        quote="'";
                        mode=VALUE;
                    }else if(atrs.charAt(i)=='"'){
                        quote="\"";
                        mode=VALUE;
                    }else{
                        quote="";
                        mode=VALUE;
                        value+=atrs.charAt(i);
                    }
                    
                }
            }else if(mode==VALUE){
                if(quote.equals(""+atrs.charAt(i))){
                    
                    map.put(key,value);
                    
                    key="";
                    value="";
                    mode=KEY;
                }else{
                    if(quote.equals("") && (isWhitespace(atrs.charAt(i)) || atrs.charAt(i)=='/')){
                        map.put(key,value);
                        
                        key="";
                        value="";
                        mode=KEY;
                    }else{
                    value+=atrs.charAt(i);
                    }
                }
            }
        }
        
        if(value.length()>0){
            if(key.length()>0){
                map.put(key,value);
            }
        }else if(key.length()>0){
            map.put(key,"true");
        }
        return map;
    }else{
        return map;
    }
}

public static boolean isWhitespace(char ch){
	if(ch=='\t'){
		return true;
	}
	if(ch==' '){
		return true;
	}
	if(ch=='\r'){
		return true;
	}
	if(ch=='\n'){
		return true;
	}
	
	return false;
}
	
}
