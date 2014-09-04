package com.akjava.lib.common.functions;

import com.akjava.lib.common.graphics.RGBA;
import com.akjava.lib.common.param.Parameter;
import com.akjava.lib.common.param.ParameterUtils;
import com.akjava.lib.common.utils.ValuesUtils;
import com.google.common.base.Function;

public class ColorFunctions {
private ColorFunctions(){}

public static RGBAToStringFunction getRGBAToStringFunction(){
	return RGBAToStringFunction.INSTANCE;
}
public enum  RGBAToStringFunction implements Function<RGBA,String >{
	INSTANCE;
	@Override
	public String apply(RGBA value) {
		return value.toString();
	}
}


public static StringToRGBAFunction getStringToRGBAFunction(){
	return StringToRGBAFunction.INSTANCE;
}

public enum  StringToRGBAFunction implements Function<String,RGBA >{
	INSTANCE;
	@Override
	public RGBA apply(String value) {
		
		if(value.startsWith("#")){
			String hex=value.substring(1);
			if(hex.length()==3){
				StringBuilder builder=new StringBuilder();
				builder.append(hex.charAt(0));
				builder.append(hex.charAt(0));
				builder.append(hex.charAt(1));
				builder.append(hex.charAt(1));
				builder.append(hex.charAt(2));
				builder.append(hex.charAt(2));
				hex=builder.toString();
			}
			
			if(hex.length()==6){//only support this
				int color=Integer.parseInt(hex,16);
				return new RGBA(color>>16&0xff, color>>8&0xff, color&0xff);
			}
		}else if(value.startsWith("rgb")){
			Parameter param=ParameterUtils.parse(value,',');
			if(param.getName().equals("rgb")){
				if(param.getAttributes().size()==3){
					/*sadly GWT not support tryParse
					return new RGBA(Objects.firstNonNull(Ints.tryParse(param.get(0)),0),
							Objects.firstNonNull(Ints.tryParse(param.get(1)),0),
							Objects.firstNonNull(Ints.tryParse(param.get(2)),0)
							);
							*/
					return new RGBA(ValuesUtils.toInt(param.get(0), 0),ValuesUtils.toInt(param.get(1), 0),ValuesUtils.toInt(param.get(2), 0));
				}
			}else if(param.getName().equals("rgba")){
				if(param.getAttributes().size()==4){
					/*sadly GWT not support tryParse
					return new RGBA(Objects.firstNonNull(Ints.tryParse(param.get(0)),0),
							Objects.firstNonNull(Ints.tryParse(param.get(1)),0),
							Objects.firstNonNull(Ints.tryParse(param.get(2)),0),
							Objects.firstNonNull(Doubles.tryParse(param.get(3)),1.0)
							);*/
					return new RGBA(ValuesUtils.toInt(param.get(0), 0),ValuesUtils.toInt(param.get(1), 0),ValuesUtils.toInt(param.get(2), 0),ValuesUtils.toDouble(param.get(3), 1.0));
				}
			}
		}
		
		
		return null;//not support
	}
}



}
