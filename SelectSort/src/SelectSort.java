/*
 * 选择排序
 * 算法思想：
 * 每一趟在后面的 n - i 个待排序元素中选出排序码最小的元素
 * 作为有序元素序列的第 i 个元素。
 * 带到第 n - 2 趟做完，待排序元素只剩下 1 个，
 * 就不用再选了。
 * 
 * 选则排序的种类
 * 1.直接选则排序
 * 2.锦标赛排序
 * 3.堆排序
 * 
 * 以下实现的是直接选则排序
 * 
 * 直接选择排序
 * 算法思想：
 * 根据位置找到想要的数据
 * 先看第一个位置上的数，如果有后面的数比这个数小
 * 就进行交换，直到到队尾
 * 再看第二个位置
 * 一次类推
 * 
 * 锦标赛排序
 * 算法思想
 * 主要使用了胜者树
 * 利用胜者树来选则当前情况下的最小元素
 * 主要克服了直接选则排序过程中：在后一趟比较选则时，往往把前一趟已经做过的比较右重复了一次
 * 没有把把前一趟比较的结果保留下来
 * 
 * 堆排序
 * 算法思想：
 * 主要使用了前面所讲的最小堆和最大堆的过程
 * 假设是最大堆
 * 每次取出堆中的堆顶，放在堆的末尾
 * 然后对其他元素进行重新的堆排序
 * 完成全部的排序后
 * 最大的那个元素就浮到的堆顶
 * 
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
