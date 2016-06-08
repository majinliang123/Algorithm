package MiniSpanTree;


public class Edge implements Comparable{
	public int from,to,weight;		
	public Edge(int i, int j, int w) {
		from = i;
		to = j;
		weight = w;
	}
	
	@Override
	public int compareTo(Object arg0) {
		Edge e = (Edge)arg0;
		if(this.weight > e.weight)
			return 1;
		else if(this.weight == e.weight)
			return 0;
		else
			return -1;
	}
	
	@Override
	public String toString(){
		return "start="+from+"||end="+to+"||weight="+weight;
	}
	
}