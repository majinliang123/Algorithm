package graphlink;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GraphLink g = new GraphLink();
		g.insertVertex(0);
		g.insertVertex(1);
		g.insertVertex(2);
		g.insertVertex(3);
		g.insertVertex(4);
		g.insert(0, 2, 4);
		g.insert(0, 1, 3);
		g.insert(2, 1, 3);
		g.insert(2, 3, 3);
		g.insert(1, 4, 3);
		//g.print();
		//System.out.println(g.getFirstNeighbor(0));
		//System.out.println(g.getNextNeighbor(0, 2));
		//System.out.println(g.getWeight(0, 2));
		//g.print();
		//g.DFS();
		g.BFS();
	}

}
