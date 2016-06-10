/*
 * 快速排序
 * 算法思想：
 * 找出一个元素作为基准
 * 然后对数组进行分区操作
 * 使基准左边元素的值都不大于基准值
 * 基准右边的元素值 都不小于基准值
 * 如此作为基准的元素调整到排序后的正确位置
 * 进行这种调度的方法：
 * 自己想。。。
 */
public class Quicksort {
	void quicksort(int[] v, int left, int right){
       int high = right;
       int low = left;
       int key = v[low];
       if(low < high){
    	     while(low < high){
    	    	   while(low < high && v[high] > key){
    	    		   high--;
    	    	   }
    	    	   v[low] = v[high];
    	    	   while(low < high && v[low] < key){
    	    		   low++;
    	    	   }
    	    	   v[high] = v[low];
    	       }
    	       v[low] = key;
    	       quicksort(v, left, low-1);
    	       quicksort(v, low+1, right);
       }
  
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		 int[] x = { 6, 2, 4, 1,0, 5, 9 ,12};
		 Quicksort q = new Quicksort();
		 q.quicksort(x, 0, 5);
		 for(int i = 0; i < x.length; i++){
	        	System.out.println(x[i]);
	      }
	}

}
