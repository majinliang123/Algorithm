package MiniSpanTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * ��С�������㷨
 * kruskal�㷨��
 * �ȹ���һ��ֻ�� n �����㣬���߼�Ϊ�յ���ͼ��
 * ��������ͼ�и������㿴���Ǹ������ϵĸ���㣬������һ������ n ������һ��ɭ�֡�
 * ֮�󣬴����ı߼� E ��ѡȡһ��Ȩֵ��С�ıߣ��������ߵ��������������ͬ������
 * ���������ͼ��Ҳ����˵��������������ֱ����ڵ��������ϳ�һ������
 * ��֮���������ߵ���������������ͬһ�����ϣ��򲻿�ȡ����Ӧ��ȡ��һ��Ȩֵ��С�ı�����֮��
 * �������ƣ�ֱ��ɭ����ֻ��һ������Ҳ����ͼ�к��� n-1����Ϊֹ��
 * 
 * 
 * Prim�㷨��
 * ������һ���� v0 ��ʼѡ����������� v1 ������ T1��
 * �������� T1 ������� v2 ������ T2�� 
 * ����ظ�ֱ�����ж����������������Ϊֹ��
 * @author majinliang
 *
 */
public class MiniSpanTree {

	//�ߵ���
	
	
	
	/*
	 * start����ʼ�Ľڵ�
	 * n���ڵ�ĸ���
	 */
	public void prim(int[][] graph, int start, int n){
		int[][] mins = new int[n][2];//���ڱ���ͼ�е���С��,��һ���������Ѿ�����С�������еĽڵ㵽����ڵ������С�������Ľڵ�
									//�ڶ��������˽ڵ��Ƿ�����С��������,0�����Ѿ����ˣ�-1������
		for(int i = 0; i < n; i++){//��ɽڵ���ֻ��start����ڵ�
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
		for(int i = 0; i < n - 1; i++){//��û�й�����С�������ĸ�������ѭ��
			 int minNode=-1;
			 int minWeight=Integer.MAX_VALUE;  
			 for(int k = 0; k < n; k++){
				 if(minWeight > mins[k][1] && mins[k][1] != 0){
					 minWeight = mins[k][1];
					 minNode = k;
				 }
			 }
			 mins[minNode][1] = 0;
			 System.out.println("��С�������ĵ�"+i+"����С��=<"+(mins[minNode][0]+1)+","+(minNode+1)+">��Ȩ��="+minWeight);  
			 for(int j=0;j<n;j++){//����mins����  
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
                System.out.println("���start="+start+"||end="+end+"||w="+edge[i].weight);  
                HashSet setj=sets.get(countj);  
                sets.remove(countj);  
                HashSet seti=sets.get(counti);  
                sets.remove(counti);  
                seti.addAll(setj);  
                sets.add(seti);  
            }else{  
                //System.out.println("������һ�������У��������start="+start+"||end="+end+"||w="+edge[i].weight);  
  
            }  
		}
	}
}
