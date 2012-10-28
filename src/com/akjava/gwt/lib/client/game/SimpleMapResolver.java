package com.akjava.gwt.lib.client.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.akjava.lib.common.repackaged.org.apache.xmlgraphics.util.DefaultEdgeDirectory;
import com.akjava.lib.common.repackaged.org.apache.xmlgraphics.util.DijkstraAlgorithm;





public class SimpleMapResolver {
	private int[][] map;
	private List<Integer> wallNumbers;
	
	public SimpleMapResolver(int[][] mapDataYX,List<Integer> wallNumbers){
		this.map=mapDataYX;
		this.wallNumbers=wallNumbers;
	}
	/**
	 * 
	 * @param start
	 * @param goal
	 * @return route from start to goal,warn start included but goal not contain.
	 */
	public ArrayList<PtVertex> resolveMap(PtVertex start,PtVertex goal){
		HashMap<String,PtVertex> hmap=new HashMap<String,PtVertex>();
		hmap.put(start.toString(), start);
		hmap.put(goal.toString(), goal);
		DefaultEdgeDirectory directory=mapToDirectory(hmap,map,wallNumbers);
		ArrayList<PtVertex> route=new ArrayList<PtVertex>();
		DijkstraAlgorithm di=new DijkstraAlgorithm(directory);
		di.execute(start, goal);
		PtVertex v=goal;
		//System.out.println("goal:"+v);
		while(v!=null){
		v=(PtVertex) di.getPredecessor(v);
		if(v!=null){
			route.add(0,v);
			 //route.add(route.size(),v);
			}
		}
		
		return route;
	}
	
	
public static DefaultEdgeDirectory mapToDirectory(HashMap<String,PtVertex> pts,int[][] map,List<Integer> wallNumbers){
		
		DefaultEdgeDirectory directory=new DefaultEdgeDirectory();
		
		for(int y=0;y<map.length;y++){
			for(int x=0;x<map[y].length;x++){
				if(map[y][x]==0){ // i have no idea.
					System.out.println("maybe invalid 0 on map mode:"+x+","+y);
				}
				String label=x+"x"+y;
				PtVertex pt=pts.get(label);
				int ptPenalty=map[y][x];
				if(pt==null){
					pt=new PtVertex(x,y);
					pts.put(label, pt);
				}
				//get right - left
				LOOP:if(x+1<map[y].length){
					if(wallNumbers.contains(map[y][x+1])){
						break LOOP;
					}	
				String label2=(x+1)+"x"+y;
				int pt2Penalty=map[y][x+1];
				PtVertex pt2=pts.get(label2);
				if(pt2==null){
					pt2=new PtVertex(x+1,y);
					pts.put(label2, pt2);
				}
				//each eay
				directory.addEdge(new PtEdge(pt, pt2,pt2Penalty));
				directory.addEdge(new PtEdge(pt2, pt,ptPenalty));
				//System.out.println("add:"+pt+","+pt2);
				}
				
				//get up - down
				LOOP:if(y+1<map.length){
					if(wallNumbers.contains(map[y+1][x])){
						break LOOP;
					}	
					String label2=x+"x"+(y+1);
					int pt2Penalty=map[y+1][x];
					PtVertex pt2=pts.get(label2);
					if(pt2==null){
						pt2=new PtVertex(x,y+1);
						pts.put(label2, pt2);
					}
					directory.addEdge(new PtEdge(pt, pt2,pt2Penalty));
					directory.addEdge(new PtEdge(pt2, pt,ptPenalty));
					//System.out.println("add:"+pt+","+pt2);
					}
			}
		}
		
		return directory;
	}
	
}
