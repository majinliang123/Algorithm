package Dijkstra;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dijkstra d = new Dijkstra();
		int M = 10000;
		int start=0; 
		int[][] weight = {//邻接矩阵 
				 {0,10,M,30,100}, 
			        {M,0,50,M,M}, 
			        {M,M,0,M,10}, 
			        {M,M,20,0,60}, 
			        {M,M,M,M,0} 
		    }; 
		 int[] shortPath = d.dijkstra(weight,start); 
		 for(int i = 0;i < shortPath.length;i++) 
		     System.out.println("从"+start+"出发到"+i+"的最短距离为："+shortPath[i]); 
	}

}
