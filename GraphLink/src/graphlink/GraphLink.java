package graphlink;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;
/**
 * 图有两种表示方式
 * 图的邻接矩阵表示
 * 图的邻接表表示
 * 由于当数据很少是，邻接矩阵是身份浪费空间的
 * 所以一下采用邻接表表示图
 * 这里用的是有方向的邻接图
 * <注>没有实现书中所写的删除节点和边的操作
 * @author majinliang
 *
 */
public class GraphLink {
/*
	 * 在边中包含着这条边右边的顶点
	 */
	public class Edge{
		private int dest;//边的另一定点的位置,是表前面的序号
		private int cost;//边上的权值
		private Edge next;//下一个顶点
	}
	
	public class Vertex{
		private int key;
		private Edge adj;
	}
	
	private Vector<Vertex> vector;
	public GraphLink(){
		vector = new Vector<>();
	}
	
	//n指的是第几个顶点，
	//获取这个顶点的第一个相邻的顶点
	public int getFirstNeighbor(int n){
		if(n != -1){
			Edge e = vector.get(n).adj;
			if(e != null){
				return e.dest;
			}
		}
		return -1;
	}
	
	//给出顶点v的邻接顶点w的下一个邻接顶点的位置,
    //若没有下一个邻接顶点, 则函数返回-1
	public int getNextNeighbor(int v, int w){
		if(v != -1){
			Edge e = vector.get(v).adj;
			while(e != null && e.dest != w)
				e = e.next;
			if(e != null && e.next != null){
				return e.next.dest;
			}
		}
		return -1;
	}
	
	//获取边的权值，连个参数分别是边的两个顶点
	public int getWeight(int v, int w){
		if(v != -1 && w != -2){
			Edge e = vector.get(v).adj;
			while(e != null && e.dest != w)
				e = e.next;
			if(e != null)
				return e.cost;				
		}
		return 0;
	}
	
	//添加端点
	public void insertVertex(int key){
		Vertex v = new Vertex();
		v.key = key;
		vector.addElement(v);
	}
	
	
	
	//插入边，规定边的权值
	public void insert(int v1, int v2, int weight){
		if(v1 >= 0 && v1 < vector.size() && v2 >= 0 && v2 < vector.size()){
			Edge e = vector.get(v1).adj;
			Edge d = new Edge();
			d.cost = weight;
			d.dest = v2;
			d.next = null;
			if(e == null){
				vector.get(v1).adj = d;
			}else{
				while(e.next != null){
					e = e.next;
				}
				e.next = d;
			}
		}		
	}
	
	//对图的简单打印
	public void print(){
		for(int i = 0; i < vector.size(); i++){
			System.out.println(i);
			Edge e = vector.get(i).adj;
			while(e != null){
				System.out.println(e.dest);
				e = e.next;
			}
		}
	}
	
	//对图进行深度优先搜索,下面两个方法都是
	public void DFS(){
		int loc = 0;
		int n = vector.size();
		boolean[] visited = new boolean[n];
		for(int i = 0; i < n; i++){
			visited[i] = false;
		}
		DFS(loc, visited);
		
		
	}
	public void DFS(int k, boolean[] visited){
		System.out.println(vector.get(k).key);
		visited[k] = true;
		Edge e = vector.get(k).adj;
		while(e != null){
			if(!visited[e.dest])
				DFS(e.dest, visited);
			e = e.next;
		}
	}
	
	//对图进行广度优先搜索,其中使用了队列
	public void BFS(){
		int n = vector.size();
		int loc = 0;
		int w;
		boolean[] visited = new boolean[n];
		for(int i = 0; i < n; i++){
			visited[i] = false;
		}
		System.out.println(vector.get(loc).key);
		visited[loc] = true;
		Queue<Integer> queue = new LinkedList<Integer>(); 
		queue.offer(loc);
		while(!queue.isEmpty()){
			loc = queue.poll();
			w = getFirstNeighbor(loc);
			while(w != -1){
				if(visited[w] == false){
					System.out.println(vector.get(w).key);
					visited[w] = true;
					queue.offer(w);
				}
				w = getNextNeighbor(loc, w);
			}
		}
	}
	
}
