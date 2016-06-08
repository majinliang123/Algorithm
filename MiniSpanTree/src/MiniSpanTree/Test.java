package MiniSpanTree;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [][] tree={  
                {-1,6,1,5,-1,-1},  
                {6,-1,5,-1,3,-1},  
                {1,5,-1,5,6,4},  
                {5,-1,5,-1,-1,2},  
                {-1,3,6,-1,-1,6},  
                {-1,-1,4,2,6,-1}              
        };  
        MiniSpanTree m = new MiniSpanTree(); 
        m.prim(tree, 0, 6);
        
        System.out.println("+++++++++++++++++++++++++++++++++");  
        
        int [] V={1,2,3,4,5,6};  
        Edge [] E=new Edge[10];  
        E[0]=new Edge(1,2,6);  
        E[1]=new Edge(1,3,1);  
        E[2]=new Edge(1,4,5);  
        E[3]=new Edge(2,3,5);  
        E[4]=new Edge(2,5,3);  
        E[5]=new Edge(3,4,5);  
        E[6]=new Edge(3,5,6);  
        E[7]=new Edge(3,6,4);  
        E[8]=new Edge(4,6,2);  
        E[9]=new Edge(5,6,6);  
        m.kruskal(V, E);
	}

}
