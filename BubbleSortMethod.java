/*
 * 冒泡算法
 * 算法思想：
 * 根据位置找到想要的数据
 * 先看第一个位置上的数，如果有后面的数比这个数小
 * 就进行交换，直到到队尾
 * 再看第二个位置
 * 一次类推
 */

public class BubbleSortMethod {
	public void bubble_sort(int[] unsorted)
    {
        for (int i = 0; i < unsorted.length; i++)
        {
            for (int j = i; j < unsorted.length; j++)
            {
                if (unsorted[i] > unsorted[j])
                {
                    int temp = unsorted[i];
                    unsorted[i] = unsorted[j];
                    unsorted[j] = temp;
                }
            }
        }
        for(int i = 0; i < unsorted.length; i++){
        	System.out.println(unsorted[i]);
        }
    }
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		 int[] x = { 6, 2, 4, 1, 5, 9 };
		 BubbleSortMethod b = new BubbleSortMethod();
         b.bubble_sort(x);
		
	}
}
