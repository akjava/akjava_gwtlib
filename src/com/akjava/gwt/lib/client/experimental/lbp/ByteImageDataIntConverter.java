package com.akjava.gwt.lib.client.experimental.lbp;

import com.akjava.lib.common.utils.ColorUtils;
import com.google.common.base.Converter;
import com.google.common.base.Function;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.ImageData;

public class ByteImageDataIntConverter extends Converter<ImageData,int[][]>{
	private boolean color=true;
	private Context2d context2d;

	public ByteImageDataIntConverter(Context2d context,boolean imageIsColor){
		this.context2d=context;
		this.color=imageIsColor;
	}
	@Override
	protected int[][] doForward(ImageData data) {
		int[][] bytes=new int[data.getWidth()][data.getHeight()];
		
		for(int x=0;x<data.getWidth();x++){
			for(int y=0;y<data.getHeight();y++){
				int value=0;
				if(color){
					//value=(int) (0.299*data.getRedAt(x, y) + 0.587*data.getGreenAt(x, y) + 0.114*data.getBlueAt(x, y));
					value=ColorUtils.toColor(data.getRedAt(x, y), data.getGreenAt(x, y), data.getBlueAt(x, y));
				}else{
					throw new RuntimeException("not support yet");
					//value=data.getRedAt(x, y);//from grayscale imagedata
				}
				bytes[x][y]=value;
				
				
					
			}
		}
		
		
		return bytes;
	}

	@Override
	protected ImageData doBackward(int[][] b) {
		ImageData data=context2d.createImageData(b.length, b[0].length);
		
		for(int x=0;x<data.getWidth();x++){
			for(int y=0;y<data.getHeight();y++){
			
				if(b[x][y]<0){
					//LogUtils.log("not zero value:"+b[x][y]);
					}
				
				if(b[x][y]!=0){
				//	LogUtils.log("not zero value:"+b[x][y]);
				}
				
				int value=(int)b[x][y];
				
				value=Math.min(255, value);//somehow range is 1-16
				
				data.setAlphaAt(255, x, y);
				data.setRedAt(value, x, y);
				data.setGreenAt(value, x, y);
				data.setBlueAt(value, x, y);
			}
		}
		return data;
	}
	
	public static class ImageDataToByteFunction implements Function<ImageData,int[][]>{
		private boolean color;
		
		public ImageDataToByteFunction(boolean color) {
			super();
			this.color = color;
		}

		@Override
		public int[][] apply(ImageData data) {
			int[][] bytes=new int[data.getWidth()][data.getHeight()];
			
			for(int x=0;x<data.getWidth();x++){
				for(int y=0;y<data.getHeight();y++){
					int value=0;
					if(color){
						//value=(int) (0.299*data.getRedAt(x, y) + 0.587*data.getGreenAt(x, y) + 0.114*data.getBlueAt(x, y));
						value=ColorUtils.toColor(data.getRedAt(x, y), data.getGreenAt(x, y), data.getBlueAt(x, y));
					}else{
						//throw new RuntimeException("not support yet");
						value=data.getRedAt(x, y);//from grayscale imagedata
					}
					bytes[x][y]=value;
					
					
						
				}
			}
			return bytes;
		}
		
	}
}