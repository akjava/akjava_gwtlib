package com.akjava.gwt.lib.client.canvas;

import com.akjava.gwt.lib.client.CanvasUtils;
import com.akjava.gwt.lib.client.LogUtils;
import com.akjava.lib.common.utils.ValuesUtils;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.TextMetrics;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

public class CanvasTextUtils {

	
	public static void drawCenterInRect(Canvas canvas,String text,Rect rect){
		TextMetrics metrix=canvas.getContext2d().measureText(text);
		
		int dx=(int) ((rect.getWidth()-metrix.getWidth())/2);
		int dy=rect.getHeight()/2;
		
		int size=parseFontSize(canvas.getContext2d().getFont());
		double descentOffset=(double)size/4;
		
		canvas.getContext2d().fillText(text, rect.getX()+dx, rect.getY()+dy+descentOffset);
	}
	
	static RegExp nums=RegExp.compile("(\\d+)px");
	public static int parseFontSize(String text){
		MatchResult result=nums.exec(text);
		if(result.getGroupCount()>1){
			//LogUtils.log(result.getGroupCount()+","+result.getGroup(0)+","+result.getGroup(1));
			return ValuesUtils.toInt(result.getGroup(1), 0);
		}
		return 0;
	}
	
	public static Rect getAlignRect(Canvas canvas,int cw,int ch,String text,int align,int valign){
		TextMetrics metrix=canvas.getContext2d().measureText(text);
		
		double width=metrix.getWidth();
		
		int size=parseFontSize(canvas.getContext2d().getFont());
		double descentOffset=(double)size/4;
		int height=size-(int) (descentOffset/2);
		
		double dx=0;	//ALIGN_LEFT
		double dy=0;
		if(align==CanvasUtils.ALIGN_CENTER){
		dx=(cw-width)/2;
		}else if(align==CanvasUtils.ALIGN_RIGHT){
		dx=cw-width;
		}
		if(valign==CanvasUtils.VALIGN_MIDDLE){
		dy=(ch-height)/2;
		}else if(valign==CanvasUtils.VALIGN_BOTTOM){
		dy=ch-height;	
		}
		
		return new Rect((int)dx,(int)dy,(int)width,size);
	}
	public static Rect getAlignRect(Canvas canvas,String text,int align,int valign){
		int cw=canvas.getCoordinateSpaceWidth();
		int ch=canvas.getCoordinateSpaceHeight();
		return getAlignRect(canvas,cw,ch,text,align,valign);
	}
}