package MiniSpanTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * 最小生成树算法
 * kruskal算法：
 * 先构造一个只含 n 个顶点，而边集为空的子图，
 * 若将该子图中各个顶点看成是各棵树上的根结点，则它是一个含有 n 棵树的一个森林。
 * 之后，从网的边集 E 中选取一条权值最小的边，若该条边的两个顶点分属不同的树，
 * 则将其加入子图，也就是说，将这两个顶点分别所在的两棵树合成一棵树；
 * 反之，若该条边的两个顶点已落在同一棵树上，则不可取，而应该取下一条权值最小的边再试之。
 * 依次类推，直至森林中只有一棵树，也即子图中含有 n-1条边为止。
 * 
 * 
 * Prim算法：
 * 从任意一顶点 v0 开始选择其最近顶点 v1 构成树 T1，
 * 再连接与 T1 最近顶点 v2 构成树 T2， 
 * 如此重复直到所有顶点均在所构成树中为止。
 * @author majinliang
 *
 */
public class MiniSpanTree {

	//边的类
	
	
	
	/*
	 * start：开始的节点
	 * n：节点的个数
	 */
	public void prim(int[][] graph, int start, int n){
		int[][] mins = new int[n][2];//用于保存图中的最小边,第一个参数是已经在最小生成树中的节点到这个节点的在最小生成树的节点
									//第二个参数此节点是否在最小生成树中,0代表已经在了，-1代表不在
		for(int i = 0; i < n; i++){//完成节点中只有start这个节点
			if(i == start){
				mins[i][0] = -1;
				mins[i][1] = 0;
			}else if(graph[start][i]!=-1){
				mins[i][0] = start;
				mins[i][1] = graph[start][i];
			}else{
				mins[i][0] = -1;
				mins[i][1] = Integer.MAX_VALUE;
			}
		}
		for(int i = 0; i < n - 1; i++){//按没有归入最小生成树的个数进行循环
			 int minNode=-1;
			 int minWeight=Integer.MAX_VALUE;  
			 for(int k = 0; k < n; k++){
				 if(minWeight > mins[k][1] && mins[k][1] != 0){
					 minWeight = mins[k][1];
					 minNode = k;
				 }
			 }
			 mins[minNode][1] = 0;
			 System.out.println("最小生成树的第"+i+"条最小边=<"+(mins[minNode][0]+1)+","+(minNode+1)+">，权重="+minWeight);  
			 for(int j=0;j<n;j++){//更新mins数组  
	                if(mins[j][1]!=0){   
	                    if( graph[minNode][j]!=-1&& graph[minNode][j]<mins[j][1]){  
	                        mins[j][0]=minNode;  
	                        mins[j][1]= graph[minNode][j];  
	                    }  
	                }  
	            }  
		}
       
	}
	
	public void kruskal(int []v, Edge[] edge){
		Arrays.sort(edge);
		ArrayList<HashSet> sets = new ArrayList<>();
		for(int i = 0; i < v.length; i++){
			HashSet set = new HashSet();
			set.add(v[i]);
			sets.add(set);
		}
		System.out.println("++++++++++++++++++++++size="+sets.size());  
		for(int i = 0; i < edge.length; i++){
			int start = edge[i].from;
			int end = edge[i].to;
			int counti = -1;
			int countj = -2;
			for(int j = 0; j < sets.size(); j++){
				HashSet set = sets.get(j);
				if(set.contains(start))
					counti = j;
				if(set.contains(end))
					countj = j;
			}            
            if(counti!=countj){  
                System.out.println("输出start="+start+"||end="+end+"||w="+edge[i].weight);  
                HashSet setj=sets.get(countj);  
                sets.remove(countj);  
                HashSet seti=sets.get(counti);  
                sets.remove(counti);  
                seti.addAll(setj);  
                sets.add(seti);  
            }else{  
                //System.out.println("他们在一棵子树中，不能输出start="+start+"||end="+end+"||w="+edge[i].weight);  
  
            }  
		}
	}
}
