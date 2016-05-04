/*
 * 快速排序
 * 算法思想：
 * 根据位置找到想要的数据
 * 先看第一个位置上的数，如果有后面的数比这个数小
 * 就进行交换，直到到队尾
 * 再看第二个位置
 * 一次类推
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
		// TODO 自动生成的方法存根
		 int[] x = { 6, 2, 4, 1, 5, 9 };
		 SelectSort b = new SelectSort();
         b.bubble_sort(x);
         for(int i = 0; i < x.length; i++){
	        	System.out.println(x[i]);
	      }
		
	}
}
