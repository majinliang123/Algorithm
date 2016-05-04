package bubblingSort;
/*冒泡排序
 * 算法思想：
 * 从数组的第一个位置开始两两比较array[index]和array[index+1]
 * 如果array[index]大于array[index+1]则交换array[index]和array[index+1]的位置
 * 止到数组结束；
 */
public class BubblingSort {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		int[] x = { 6, 2, 4, 1, 5, 9 };
		BubblingSort b = new BubblingSort();
        b.bubble_sort(x);
        for(int i = 0; i < x.length; i++){
	        	System.out.println(x[i]);
	      }
	}

	void bubble_sort(int[] unsorted){
		for(int a = 0; a < unsorted.length - 1; a++){
			for(int b = 0; b < unsorted.length - 1; b++){
				if(unsorted[b] > unsorted[b+1]){
					int temp = unsorted[b];
					unsorted[b] = unsorted[b+1];
					unsorted[b+1] = temp;
				}
			}
		}
	}
}
