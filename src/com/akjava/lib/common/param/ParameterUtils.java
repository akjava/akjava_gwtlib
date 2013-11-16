package com.akjava.lib.common.param;

public class ParameterUtils {

	public static Parameter parse(String line){
	return parse(line,':');	
	}
	/**
	 * easy way to detect is really param
	 * @param param
	 * @return
	 */
	public static boolean isClosedAndHaveParameter(Parameter param){
		return param.getAttributes().size()>0 && param.isClosed();
	}
	public static Parameter parse(String line,char separator){
		int start=line.indexOf("(");
		
		if(start==-1){
			return new Parameter(line);
		}else{
			String remain=null;
			String name=line.substring(0,start);
			int end=line.lastIndexOf(")");
			String inside;
			Parameter p=new Parameter(name);
			if(end==-1){
				inside=line.substring(start+1);
			}else{
				p.setClosed(true);
				inside=line.substring(start+1,end);
				if(end+1<line.length()-1){
					remain=line.substring(end+1);
				}
			}
			
			if(remain!=null){
				p.setRemain(remain);
			}
			if(inside.isEmpty()){
				return p;
			}
			String[] atts=inside.split(""+separator);
			
			
			for(String at:atts){
				p.add(at);
			}
			return p;
		}
	}
}
