package com.akjava.gwt.lib.client.datalist;

import com.google.common.base.Converter;

/**
 * only convert data&cdate,you have to care about id & header(usually file name)
 * 
 * this is for store key&value local storage
 * @author aki
 *
 */
public class SimpleTextDataDataConverter extends Converter<SimpleTextData,String> {

	@Override
	protected String doForward(SimpleTextData data) {
		return data.getCdate()+","+data.getData();
	}

	@Override
	protected SimpleTextData doBackward(String text) {
		String[] cdate_data=splitCdateAndData(text);
		SimpleTextData data=new SimpleTextData(-1,null,cdate_data[0],cdate_data[1]);
		return data;
	}
	
	//this support only has time and not have time
	private String[] splitCdateAndData(String line){
		String[] result=new String[2];

		for(int i=0;i<line.length();i++){
			if(Character.isDigit(line.charAt(i))){
				continue;
			}else if(line.charAt(i)==','){
				//find split
				result[0]=line.substring(i+1);
				result[1]=line.substring(0,i);
				return result;
			}else{
				//not found
				result[0]=line;
				result[1]="";
				return result;
			}
		}
		//just number 
		if(result[0]==null){
			result[0]="";
			result[1]=line;
		}
		
		return result;
	}

}
