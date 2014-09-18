package com.akjava.gwt.lib.client.experimental.lbp;


public class SimpleLBP {

	
	private static int[] atx={-1,0,1,1,1,0,-1,-1};
	private static int[] aty={-1,-1,-1,0,1,1,1,0};
	/**
	 * maybe 8samples 1 neighbors
	 * 
	 * 1 pixel edges should be ignore
	 * @param improved
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
		this.improved=improved;
		this.neighbor=neighbor;
		this.useNumber=useNumber;
		container=new int[3][3];
	}
	private int neighbor;
	private int[][] container;
	boolean improved=true;
	boolean useNumber;//return 0-9 value.
	//i think default 0-256 color is for human eye
	
	//x,y
	public int[][] convert(int[][] arrays){
		int result[][]=new int[arrays.length][arrays[0].length];
		for(int x=0;x<arrays.length;x++){
			for(int y=0;y<arrays[0].length;y++){
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
						otherValue=centers[offx][offy];
					}else{
						otherValue=getOtherValue(arrays, x+atx[i]*neighbor,y+ aty[i]*neighbor);
					}
					if(otherValue>center){
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
	
	//split is fixed;
	public  int[] dataToBinaryPattern(int[][] arrays,int edgeX,int edgeY){

		int split=2;
		
		int resultW=(arrays.length-edgeX)/split;
		int resultH=(arrays[0].length-edgeY)/split;
		
		int[] retInt=new int[8*split*split];
		
		
		
		int halfEdgeX=edgeX/2;
		int helfEdgeY=edgeY/2;
		
		int w=arrays.length;
		int h=arrays[0].length;
		//ignore edge
		for(int x=halfEdgeX;x<w-halfEdgeX;x++){
			for(int y=helfEdgeY;y<h-helfEdgeY;y++){
				
				int[][] centers=getAroundValues(x,y,arrays);
				int center=getCenterValue(centers);
				int retX=(x-halfEdgeX)/resultW;
				int retY=(y-helfEdgeY)/resultH;
				
				int retIndexOffset=8*(retY*2+retX);
				
				//System.out.println("x="+x+",y="+y+",retX="+retX+",retY="+retY);
				
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
						int v=7-i;//need swap
						retInt[v+retIndexOffset]++;
					}
					
					
				}
				
			}
		}
		
		return retInt;
	}
	
	private int getOtherValue(int[][] arrays, int offx, int offy) {
		if(offx<0 || offx>=arrays.length || offy<0 || offy>=arrays[0].length){
			return -1;
		}
		return  arrays[offx][offy];
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
		if(improved){
			int total=0;
			int items=0;
			for(int x=0;x<3;x++){
				for(int y=0;y<3;y++){
					if(around[x][y]>=0){//-1 means out
					total+=around[x][y];
					items++;
					}
				}
			}
			return total/items;//improved LBP average;
		}else{
			return around[1][1];//just return center;
		}
	}
	
	private int[][] getAroundValues(int tx,int ty,int[][] arrays){
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
	
}
