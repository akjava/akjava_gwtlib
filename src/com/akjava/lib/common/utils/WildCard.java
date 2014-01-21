/*
 * Created on 2004/11/19
 * Author aki@www.xucker.jpn.org
 * License Apache2.0 or Common Public License
 */
package com.akjava.lib.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * moved use commons
 * @deprected 
 * @author aki
 *
 */
public class WildCard {
private String pattern;
public String getPattern() {
    return pattern;
}
public void setPattern(String pattern) {
    this.pattern = pattern;
    javaPattern=toJavaPattern(pattern);
}
private String javaPattern;
public WildCard(String text){
    setPattern(text);
}

public boolean match(String text){
    Pattern pattern = Pattern.compile(javaPattern,Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(text);
    return matcher.matches();
}
/*for 
 * Documents (*.txt,*.doc)
 */
public static WildCard[] parseFileFilters(String text){
  
    if(text.indexOf("(")!=-1){
       int first=text.indexOf("(");
       int last=text.indexOf(")",first+1);
       if(last!=-1){
       String substring = text.substring(first+1,last);
       if(substring.length()>0){
    String splits[]=substring.split(",");
       WildCard result[]=new WildCard[splits.length];
       
       for (int i = 0; i < splits.length; i++) {
        result[i]=new WildCard(splits[i]);
       }
       return result;
       }
       }
    }
    return new WildCard[0];
}

public static WildCard[] parseExtensions(String text){
	String extension[]=text.split(";");
	WildCard[] rets=new WildCard[extension.length];
	for (int i = 0; i < rets.length; i++) {
		rets[i]=new WildCard(extension[i]);
	}
    return rets;
}

public static String toJavaPattern(String pattern){
    String result="^";
    char metachar[]={'$','^','[',']','(',')','+','.','\\'};
    for(int i=0;i<pattern.length();i++){
        char ch=pattern.charAt(i);
        boolean isMeta=false;
        for(int j=0;j<metachar.length;j++){
            if(ch==metachar[j]){
                result+="\\"+ch;
                isMeta=true;
                break;
            }
        }
        if(!isMeta){
            if(ch=='*'){
                result+=".*";
            }else{
                result+=ch;
            }
            
        }
    }
    result+="$";
    return result;
}
}
