package Dijkstra;
/**
 * Dijkstra�㷨
 * �㷨˼��
 * Dijkstra�������������Դ��v���·�����ȵĵ�������
 * ���ɵ�����������·�����㷨��
 * �������������̵�һ�����·����
 * �ٲ�����������ȴζ̵�һ�����·����
 * �������ƣ�ֱ����Դ��v ����������������·��ȫ�����Ϊֹ��
 * 
 * ����ʵ����Ҫ�����ڽӾ���
 * ���ǻ���ͼ���ڽӱ�
 * @author majinliang
 *
 */
public class Dijkstra {

	public int[] dijkstra(int[][] weight, int start) {
		int n = weight.length;//�涨����ĸ���
		int[] shortPath = new int[n];//����start��������������·��
		String[] path = new String[n];//����start��������������·�����ַ�����ʾ
		int[] visited = new int[n];//��ǵ�ǰ�ö�������·���Ƿ��Ѿ����,1��ʾ�����
		for(int i = 0; i < n; i++)//��ʼ��·��
			path[i] = new String(start + "-->" + i);
		//�Ե�һ��������й涨,start����
		shortPath[start] = 0;
		visited[start] = 1;
		
		for(int count = 1; count < n; count++){
			int k = -1; //��¼��start��������Ķ����λ��
			int temp = Integer.MAX_VALUE;//����start��������Ķ������
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
			  System.out.println("��"+start+"������"+i+"�����·��Ϊ��"+path[i]);
		System.out.println("====================================="); 
		return shortPath;
	}
}
