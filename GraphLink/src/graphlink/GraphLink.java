package graphlink;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;
/**
 * ͼ�����ֱ�ʾ��ʽ
 * ͼ���ڽӾ����ʾ
 * ͼ���ڽӱ��ʾ
 * ���ڵ����ݺ����ǣ��ڽӾ���������˷ѿռ��
 * ����һ�²����ڽӱ��ʾͼ
 * �����õ����з�����ڽ�ͼ
 * <ע>û��ʵ��������д��ɾ���ڵ�ͱߵĲ���
 * @author majinliang
 *
 */
public class GraphLink {
/*
	 * �ڱ��а������������ұߵĶ���
	 */
	public class Edge{
		private int dest;//�ߵ���һ�����λ��,�Ǳ�ǰ������
		private int cost;//���ϵ�Ȩֵ
		private Edge next;//��һ������
	}
	
	public class Vertex{
		private int key;
		private Edge adj;
	}
	
	private Vector<Vertex> vector;
	public GraphLink(){
		vector = new Vector<>();
	}
	
	//nָ���ǵڼ������㣬
	//��ȡ�������ĵ�һ�����ڵĶ���
	public int getFirstNeighbor(int n){
		if(n != -1){
			Edge e = vector.get(n).adj;
			if(e != null){
				return e.dest;
			}
		}
		return -1;
	}
	
	//��������v���ڽӶ���w����һ���ڽӶ����λ��,
    //��û����һ���ڽӶ���, ��������-1
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
	
	//��ȡ�ߵ�Ȩֵ�����������ֱ��Ǳߵ���������
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
	
	//��Ӷ˵�
	public void insertVertex(int key){
		Vertex v = new Vertex();
		v.key = key;
		vector.addElement(v);
	}
	
	
	
	//����ߣ��涨�ߵ�Ȩֵ
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
	
	//��ͼ�ļ򵥴�ӡ
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
	
	//��ͼ���������������,����������������
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
	
	//��ͼ���й����������,����ʹ���˶���
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
