/*
 * ��������
 * �㷨˼�룺
 * ����λ���ҵ���Ҫ������
 * �ȿ���һ��λ���ϵ���������к�������������С
 * �ͽ��н�����ֱ������β
 * �ٿ��ڶ���λ��
 * һ������
 */

public class SelectSort {
	public void bubble_sort(int[] unsorted)
    {
      for(int a = 0; a < unsorted.length; a++){
    	  for(int b = a; b < unsorted.length; b++){
    		  if(unsorted[a]>unsorted[b]){
    			  int temp = unsorted[a];
    			  unsorted[a] = unsorted[b];
    			  unsorted[b] = temp;
    		  }
    	  }
      }
    }
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		 int[] x = { 6, 2, 4, 1, 5, 9 };
		 SelectSort b = new SelectSort();
         b.bubble_sort(x);
         for(int i = 0; i < x.length; i++){
	        	System.out.println(x[i]);
	      }
		
	}
}
