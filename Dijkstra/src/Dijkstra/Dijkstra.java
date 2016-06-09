package Dijkstra;
/**
 * Dijkstra算法
 * 算法思想
 * Dijkstra提出按各顶点与源点v间的路径长度的递增次序，
 * 生成到各顶点的最短路径的算法。
 * 即先求出长度最短的一条最短路径，
 * 再参照它求出长度次短的一条最短路径，
 * 依次类推，直到从源点v 到其它各顶点的最短路径全部求出为止。
 * 
 * 这里实现主要基于邻接矩阵
 * 不是基于图的邻接表
 * @author majinliang
 *
 */
public class Dijkstra {

	public int[] dijkstra(int[][] weight, int start) {
		int n = weight.length;//规定顶点的个数
		int[] shortPath = new int[n];//保存start到其他顶点的最短路径
		String[] path = new String[n];//保存start到其他顶点的最短路径的字符串表示
		int[] visited = new int[n];//标记当前该顶点的最短路径是否已经求出,1表示已求出
		for(int i = 0; i < n; i++)//初始化路径
			path[i] = new String(start + "-->" + i);
		//对第一个顶点进行规定,start顶点
		shortPath[start] = 0;
		visited[start] = 1;
		
		for(int count = 1; count < n; count++){
			int k = -1; //记录离start顶点最近的顶点的位置
			int temp = Integer.MAX_VALUE;//距离start顶点最近的顶点距离
			for(int i = 0; i < n; i++){
				if(weight[start][i] < temp && visited[i] == 0){
					temp = weight[start][i];
					k = i;
				}		
			}
			visited[k] = 1;
			shortPath[k] = temp;
			
			for(int i = 0; i < n; i++){
				if(visited[i] == 0 && weight[start][i] > weight[start][k] + weight[k][i]){
					weight[start][i] = weight[k][i] + weight[start][k];
					path[i] = path[k] + "-->" + i; 
				}
			}
			
		}
		for(int i = 0; i < n; i++) 
			  System.out.println("从"+start+"出发到"+i+"的最短路径为："+path[i]);
		System.out.println("====================================="); 
		return shortPath;
	}
}
