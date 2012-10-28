package com.akjava.gwt.lib.client.game;

import com.akjava.lib.common.repackaged.org.apache.xmlgraphics.util.Edge;
import com.akjava.lib.common.repackaged.org.apache.xmlgraphics.util.Vertex;

public  class PtEdge implements Edge{
	PtVertex start;
	PtVertex end;
	private int penalty;
	public PtEdge(PtVertex start,PtVertex end){
		this.start=start;
		this.end=end;
	}
	public PtEdge(PtVertex start,PtVertex end,int penalty){
		this(start,end);
		this.penalty=penalty;
	}
	@Override
	public Vertex getEnd() {
		
		return end;
	}

	@Override
	public int getPenalty() {
		return penalty;
	}

	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}
	@Override
	public Vertex getStart() {
		
		return start;
	}
	@Override
	public String toString(){
		return start+" - "+end+","+penalty;
	}
}
