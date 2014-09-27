package com.akjava.gwt.lib.client.experimental.lbp;

public class BinaryPattern {
	
	/**
	 * TODO support separate splitX,splitY
	 * only support 8 bit base
	 * 
	 * @param data must be [y][x] 
	 * @param split
	 * @param edgeX
	 * @param edgeY
	 * @return  8bit x split x split
	 */
	public static int[] dataToBinaryPattern(int[][] data,int split,int edgeX,int edgeY){
		return dataToBinaryPattern(data,split,split,edgeX,edgeY);
	}
		public static int[] dataToBinaryPattern(int[][] data,int splitW,int splitH,int edgeX,int edgeY){
		int volOff=0;
		
		
		//old version i have no idea why *2 ,edge contain both side
		//int w=(data.length-edgeX*2)/split;
		//int h=(data[0].length-edgeY*2)/split;
		
		//minus useless edge pixel and divided split
		int w=(data[0].length-edgeX)/splitW;
		int h=(data.length-edgeY)/splitH;
		
		int size=splitW*splitH;
		
		int[] retInt=new int[8*size];
		for(int i=0;i<size;i++){
			int histgram[]=new int[8];
			int ox=i%splitW;
			int oy=i/splitW;
			
			//i feels these is wrong
			//int offx=edgeX/2+ox;
			//int offy=edgeY/2+oy*w;
			
			int offx=edgeX/2+ox*w; //start with half-edge and divided-width x at
			int offy=edgeY/2+oy*h;
			
			for(int x=0;x<w;x++){
				for(int y=0;y<h;y++){
					
					String binary=Integer.toBinaryString(data[y+offy][x+offx]);
					int length=binary.length()-1;
					//System.out.println((x+offx)+","+(y+offy)+"="+binary);
					for(int j=0;j<8;j++){
						int at=length-j;
						if(at>=0){
							if(binary.charAt(j)=='1'){
								histgram[at]++;
							}
						}else{
							break;
						}
						
					}
				}
				
				
			}
			
			for(int j=0;j<8;j++){
				retInt[j+8*volOff]=histgram[j];
			//vol.set(1, 1, i+8*volOff, histgram[i]);
			}
			volOff++;
		}
		
		return retInt;
	}
	
	public static int[] dataToBinaryPatternXY(int[][] data,int split,int edgeX,int edgeY){

		int volOff=0;
		
		
		//old version i have no idea why *2 ,edge contain both side
		//int w=(data.length-edgeX*2)/split;
		//int h=(data[0].length-edgeY*2)/split;
		
		//minus useless edge pixel and divided split
		int w=(data.length-edgeX)/split;
		int h=(data[0].length-edgeY)/split;
		
		int[] retInt=new int[8*split*split];
		for(int i=0;i<split*split;i++){
			int histgram[]=new int[8];
			int ox=i%split;
			int oy=i/split;
			
			//i feels these is wrong
			//int offx=edgeX/2+ox;
			//int offy=edgeY/2+oy*w;
			
			int offx=edgeX/2+ox*w; //start with half-edge and divided-width x at
			int offy=edgeY/2+oy*h;
			
			for(int x=0;x<w;x++){
				for(int y=0;y<h;y++){
					
					String binary=Integer.toBinaryString(data[x+offx][y+offy]);
					int length=binary.length()-1;
					//System.out.println((x+offx)+","+(y+offy)+"="+binary);
					for(int j=0;j<8;j++){
						int at=length-j;
						if(at>=0){
							if(binary.charAt(j)=='1'){
								histgram[at]++;
							}
						}else{
							break;
						}
						
					}
				}
				
				
			}
			
			for(int j=0;j<8;j++){
				retInt[j+8*volOff]=histgram[j];
			//vol.set(1, 1, i+8*volOff, histgram[i]);
			}
			volOff++;
		}
		
		return retInt;
	}
	
}
