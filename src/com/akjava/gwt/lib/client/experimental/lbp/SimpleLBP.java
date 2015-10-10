package com.akjava.gwt.lib.client.experimental.lbp;

import static com.google.common.base.Preconditions.checkState;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;


public class SimpleLBP {

	public static final int NORTH_WEST=0;
	public static final int NORTH=1;
	public static final int NORTH_EAST=2;
	public static final int EAST=3;
	public static final int SOUTH_EAST=4;
	public static final int SOUTH=5;
	public static final int SOUTH_WEST=6;
	public static final int WEST=7;
	
	private static final String[] direction={"NW","N","NE","E","SE","S","SW","W"};
	private static int[] atx={-1,0,1,  1,1,0, -1,-1};
	private static int[] aty={-1,-1,-1,0,1,1, 1,0};
	/**
	 * maybe 8samples 1 neighbors
	 * 
	 * 1 pixel edges should be ignore
	 * @param useImprovedLBP
	 */
	
	public SimpleLBP(){
		this(true,1,false);
	}
	
	public SimpleLBP(boolean improved){
		this(improved,1,false);
	}
	public SimpleLBP(boolean improved,int neighbor){
		this(improved,neighbor,false);
	}
	public SimpleLBP(boolean improved,boolean useNumber){
		this(improved,1,useNumber);
	}
	
	/**
	 * 
	 * @param improved return average value as center
	 * @param useNumber return 0-9 value instead of 0-255(I believe it's better to use small range to machine learning)
	 */
	public SimpleLBP(boolean improved,int neighbor,boolean useNumber){
		this.useImprovedLBP=improved;
		this.neighbor=neighbor;
		this.useNumber=useNumber;
		container=new int[3][3];
	}
	private int neighbor;
	private int[][] container;
	boolean useImprovedLBP=true; //use center value is around average
	boolean useNumber;//return 0-9 value.
	//i think default 0-256 color is for human eye
	
	//x,y watch out
	public int[][] convertXY(int[][] arrays){
		int result[][]=new int[arrays.length][arrays[0].length];
		for(int x=0;x<arrays.length;x++){
			for(int y=0;y<arrays[0].length;y++){
				String value="";
				int number=0;
				int[][] centers=getAroundValuesXY(x,y,arrays);
				int center=getCenterValue(centers);
				//check
				for(int i=0;i<atx.length;i++){
					int offx=1+atx[i];
					int offy=1+aty[i];
					int otherValue=0;
					if(neighbor==1){
						otherValue=centers[offx][offy];
					}else{
						otherValue=getOtherValue(arrays, x+atx[i]*neighbor,y+ aty[i]*neighbor);
					}
					if(otherValue>center){
						//System.out.println("at "+x+","+y+","+otherValue+">"+center+" add "+direction[i]);
						value+="1";
						number++;
					}else{
						value+="0";
					}
				}
				//LogUtils.log(x+","+y+","+value);
				if(useNumber){
					result[x][y]=number;
				}else{
					result[x][y]=Integer.parseInt(value, 2);
				}
			}
		}
		return result;
	}
	/**
	 * 
	 * @param arrays arrays must be [y][x], this is easy to debug
	 * @return [y][x] ints
	 */
	public int[][] convert(int[][] arrays){
		int result[][]=new int[arrays.length][arrays[0].length];
		for(int y=0;y<arrays.length;y++){
		for(int x=0;x<arrays[0].length;x++){
				String value="";
				int number=0;
				int[][] centers=getAroundValues(x,y,arrays);
				int center=getCenterValue(centers);
				//check
				for(int i=0;i<atx.length;i++){
					int offx=1+atx[i];
					int offy=1+aty[i];
					int otherValue=0;
					if(neighbor==1){
						otherValue=centers[offy][offx];
					}else{
						otherValue=getOtherValue(arrays, x+atx[i]*neighbor,y+ aty[i]*neighbor);
					}
					if(otherValue>center){
						//System.out.println("at "+x+","+y+","+otherValue+">"+center+" add "+direction[i]);
						//value+="1";
						value="1"+value;
						number++;
					}else{
						//value+="0";
						value="0"+value;
					}
				}
				//LogUtils.log(x+","+y+","+value);
				if(useNumber){
					result[y][x]=number;
				}else{
					result[y][x]=Integer.parseInt(value, 2);
				}
			}
		}
		return result;
	}
	
	public int[][] convertAverageValueForImprovedLBPTest(int[][] arrays){
		int result[][]=new int[arrays.length][arrays[0].length];
		for(int y=0;y<arrays.length;y++){
		for(int x=0;x<arrays[0].length;x++){
			
				int[][] centers=getAroundValues(x,y,arrays);
				int center=getCenterValue(centers);
				result[y][x]=center;
			}
		}
		return result;
	}
	
	public static String toBinaryPatternToDebug(int[] pattern,int splitX,int splitY){
		List<String> lines=Lists.newArrayList();
		checkState(pattern.length==splitX*splitY*8);
		
			int index=1;
			for(int y=0;y<splitY;y++){
				for(int x=0;x<splitX;x++){
				List<String> result=Lists.newArrayList();
				int offset=(x+y*splitX)*8;
				for(int i=0;i<8;i++){
					int v=pattern[offset+i];
					if(v>0){
						result.add(direction[i]+"="+v);
					}
				}
				lines.add(index+" block:"+x+"x"+y+" "+Joiner.on(",").join(result));
				index++;
			}
		}
		return Joiner.on("\n").join(lines);
	}
	public static String toDirectionLabelForDebug(int value){
		List<String> result=Lists.newArrayList();
		String v=Strings.padStart(Integer.toBinaryString(value),8,'0');
	
		for(int i=8-1;i>=0;i--){
			char ch=v.charAt(i);
			if(ch=='1'){
				result.add(Strings.padStart(direction[7-i],2,' '));
			}
		}
		
		if(result.isEmpty()){
			result.add(" 0");
		}
		
		return Joiner.on(":").join(result);
	}
	
	/*
	 * basically simple-lbp support only 8 samples 
	 * right now 8 bit fixed
	 */
	public static int[] flipHorizontal(int[] binaryPattern,int splitW,int splitH){
		checkState(binaryPattern.length==8*splitW*splitH);
		
		int[] converted=new int[binaryPattern.length];
		
		for(int y=0;y<splitH;y++){
			for(int x=0;x<splitW;x++){
				int srcOffset=(y*splitW+x)*8;
				int destOffset=(y*splitW+(splitW-1-x))*8;
				for(int i=0;i<8;i++){
					int nindex=0;
					switch(i){
					case SimpleLBP.NORTH_WEST:
						nindex=SimpleLBP.NORTH_EAST;
						break;
					case SimpleLBP.NORTH:
						nindex=SimpleLBP.NORTH;
						break;
					case SimpleLBP.NORTH_EAST:
						nindex=SimpleLBP.NORTH_WEST;
						break;
					case SimpleLBP.EAST:
						nindex=SimpleLBP.WEST;
						break;
					case SimpleLBP.SOUTH_EAST:
						nindex=SimpleLBP.SOUTH_WEST;
						break;
					case SimpleLBP.SOUTH:
						nindex=SimpleLBP.SOUTH;
						break;
					case SimpleLBP.SOUTH_WEST:
						nindex=SimpleLBP.SOUTH_EAST;
						break;
					case SimpleLBP.WEST:
						nindex=SimpleLBP.EAST;
						break;
					}
				converted[destOffset+nindex]=binaryPattern[srcOffset+i];		
				}
			}
		}
		
		
		return converted;
	}
	
	public static double[] flipHorizontal(double[] binaryPattern,int splitW,int splitH){
		checkState(binaryPattern.length==8*splitW*splitH);
		
		double[] converted=new double[binaryPattern.length];
		
		for(int y=0;y<splitH;y++){
			for(int x=0;x<splitW;x++){
				int srcOffset=(y*splitW+x)*8;
				int destOffset=(y*splitW+(splitW-1-x))*8;
				for(int i=0;i<8;i++){
					int nindex=0;
					switch(i){
					case SimpleLBP.NORTH_WEST:
						nindex=SimpleLBP.NORTH_EAST;
						break;
					case SimpleLBP.NORTH:
						nindex=SimpleLBP.NORTH;
						break;
					case SimpleLBP.NORTH_EAST:
						nindex=SimpleLBP.NORTH_WEST;
						break;
					case SimpleLBP.EAST:
						nindex=SimpleLBP.WEST;
						break;
					case SimpleLBP.SOUTH_EAST:
						nindex=SimpleLBP.SOUTH_WEST;
						break;
					case SimpleLBP.SOUTH:
						nindex=SimpleLBP.SOUTH;
						break;
					case SimpleLBP.SOUTH_WEST:
						nindex=SimpleLBP.SOUTH_EAST;
						break;
					case SimpleLBP.WEST:
						nindex=SimpleLBP.EAST;
						break;
					}
				converted[destOffset+nindex]=binaryPattern[srcOffset+i];		
				}
			}
		}
		
		
		return converted;
	}
	
	public static String toBinaryForDebug(int value){
		String v=Integer.toBinaryString(value);
		while(v.length()<8){
			v="0"+v;
		}
		Strings.padStart(v, 8, '0');
		return v;
	}
	
	/**
	 * binary pattern
	 * 
	 *  has int[8] x splitX x splitY
	 *  Y first,first 8 byte is X:Y 0:0,second is 0:1,third is 1:0,forth is 1:1
	 *  
	 *  8byte[] is first one[0] is W ,last[7] is NW
	 *  
	 *  I'm not sure why this is.
	 *  
	 *  maybe should change TODO
	 *  X first ,NW to W
	 * 
	 * @param arrays
	 * @param edgeX
	 * @param edgeY
	 * @return
	 */
	
	//split is fixed;
	public  int[] dataToBinaryPattern(int[][] arrays,int edgeX,int edgeY){
		return dataToBinaryPattern(arrays,2,2,edgeX,edgeY);
	}
	
	/**
	 * 
	 * @param arrays
	 * @param splitW must be possible divide (arrays[0].length-edgeX) 
	 * @param splitH must be possible divide (arrays.length-edgeY) 
	 * @param edgeX
	 * @param edgeY
	 * @return
	 */
	public  int[] dataToBinaryPattern(int[][] arrays,int splitW,int splitH,int edgeX,int edgeY){
		//int split=2;
		
		int w=arrays[0].length;
		int h=arrays.length;
		
		int resultW=(w-edgeX)/splitW;
		int resultH=(h-edgeY)/splitH;
		
		int[] retInt=new int[8*splitW*splitH];
		
		
		
		int halfEdgeX=edgeX/2;
		int helfEdgeY=edgeY/2;
		
		
		//ignore edge
		for(int x=halfEdgeX;x<w-halfEdgeX;x++){
			for(int y=helfEdgeY;y<h-helfEdgeY;y++){
				
				int[][] centers=getAroundValues(x,y,arrays);
				int center=getCenterValue(centers);
				int retX=(x-halfEdgeX)/resultW;
				int retY=(y-helfEdgeY)/resultH;
				
				int retIndexOffset=8*(retY*splitW+retX);
				
				//System.out.println("x="+x+",y="+y+",retX="+retX+",retY="+retY);
				
				//check
				for(int i=0;i<atx.length;i++){
					int offx=1+atx[i];
					int offy=1+aty[i];
					int otherValue=0;
					if(neighbor==1){
						otherValue=centers[offy][offx];
					}else{
						otherValue=getOtherValue(arrays, x+atx[i]*neighbor,y+ aty[i]*neighbor);
					}
					
					
					
					
					
					if(otherValue>center){
						
						retInt[i+retIndexOffset]++;
					}
					
					
				}
				
			}
		}
		
		return retInt;
	}
	
	private int getOtherValue(int[][] arrays, int offx, int offy) {
		if(offy<0 || offy>=arrays.length || offx<0 || offx>=arrays[0].length){
		//if(offx<0 || offx>=arrays.length || offy<0 || offy>=arrays[0].length){
			return -1;
		}
		return  arrays[offy][offx];
	}
	
	public int[] count(int[][] arrays){
		int result[]=new int[8];
		for(int x=0;x<arrays.length;x++){
			for(int y=0;y<arrays.length;y++){
				
				int[][] centers=getAroundValues(x,y,arrays);
				int center=getCenterValue(centers);
				//check
				for(int i=0;i<atx.length;i++){
					int offx=1+atx[i];
					int offy=1+aty[i];
					int otherValue=centers[offx][offy];
					
					if(x!=0 && y!=0&& x!=arrays.length-1 && y!=arrays[0].length-1){//ignore edge.no means?
					
					if(otherValue>center){
						result[i]++;
					}else{
						
					}
					}
				}
				
			}
		}
		return result;
	}
	
	private int getCenterValue(int[][] around){
		if(useImprovedLBP){
			int total=0;
			int exists=0;
			for(int x=0;x<3;x++){
				for(int y=0;y<3;y++){
					if(around[y][x]>=0){//-1 means out
					total+=around[y][x];
					exists++;
					}
				}
			}
			return total/exists;//improved LBP average;
		}else{
			return around[1][1];//just return center;
		}
	}
	
	/**
	 * 
	 * @param tx
	 * @param ty
	 * @param arrays MUST be [y][x] value for easy debug
	 * @return
	 */
	private int[][] getAroundValues(int tx,int ty,int[][] arrays){
		for(int i=0;i<atx.length;i++){
			container[1][1]=arrays[ty][tx];
			int offx=tx+atx[i];
			int offy=ty+aty[i];
			if(offx>=0 && offy>=0 && offx<arrays[0].length&&offy<arrays.length){
				container[1+aty[i]][1+atx[i]]=arrays[offy][offx];
			}else{
				container[1+aty[i]][1+atx[i]]=-1;//null
			}
		}
		return container;
		
	}
	

	/*
	 * return [X][Y] ints
	 */
	private int[][] getAroundValuesXY(int tx,int ty,int[][] arrays){
		for(int i=0;i<atx.length;i++){
			container[1][1]=arrays[tx][ty];
			int offx=tx+atx[i];
			int offy=ty+aty[i];
			if(offx>=0 && offy>=0 && offx<arrays.length&&offy<arrays[0].length){
				container[1+atx[i]][1+aty[i]]=arrays[offx][offy];
			}else{
				container[1+atx[i]][1+aty[i]]=-1;//
			}
		}
		return container;
		
	}
	//TODO make map for reduce calcurate time
	private static Integer[][][] turnoffsets=new Integer[3][3][8];//caching last values
	private static int findNewTurnOffset(final int sx,final int sy,final int move){
		
		if(turnoffsets[sx][sy][move]!=null){
			return turnoffsets[sx][sy][move];
		}
		int x=sx;
		int y=sy;
		//System.out.println("input "+x+"x"+y);
		if(x!=1 || y!=1){// 1x1 is center & no need move
			for(int i=0;i<move;i++){
				if(x==0){
					if(y>0){
						y--;
					}else{
						x++;
					}
				}else if(x==1){
					if(y==0){
						x++;
					}else{
						x--;
					}
				}else if(x==2){
					if(y<2){
						y++;
					}else{
						x--;
					}
				}
				//System.out.println(i+" "+x+"x"+y);
			}
		}
		int v=(y*3+x)*8;
		turnoffsets[sx][sy][move]=v;
		return v;
	}
	
	/*
	 * angle must be 0-360;
	 * 8 samples LBP only 45 base
	 */
	public static int[] turn3x3(int[] binaryPattern,int angle){
		if(binaryPattern.length!=72){//for 3x3 8samples
			return null;
		}
		int[] result=new int[72];
		
		if(angle<0){
			angle=360+angle;
		}
		
		int move=angle/45; //turn based 45 ,0-7
		
		
		for(int y=0;y<3;y++){
			for(int x=0;x<3;x++){
				int srcOffset=(y*3+x)*8;
				int destOffset=findNewTurnOffset(x,y,move);
				for(int i=0;i<8;i++){
					int newIndex=i+move;
					if(newIndex>=8){
						newIndex-=8;
					}
					result[destOffset+newIndex]=binaryPattern[srcOffset+i];
				}
			}
		}
		
		
		
		
		
		
		
		return result;
	}
	
	
}
