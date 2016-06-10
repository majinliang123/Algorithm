package RadixSort;


/**
 * LSD基数排序,分配排序的一种
 * LSD(least significant digit first)最低位优先
 * @author majinliang
 *
 */
public class RadixSort {

	final int radix = 10;//基数
	
	/*
	 * 获取x这个数的d位数上的数字
	 * 比如获取123的1位数，结果返回3
	 */
	public int getDigit(int x, int d){
		 int a[] = {1, 1, 10, 100}; 
	     return ((x / a[d]) % 10);
	}
	/*
	 * 从高位到低位对序列划分，实现排序。
	 * d指的是第几位数
	 * left和right是待排序语速子序列的始端和尾端
	 */
	public void radix(int[] unsort, int digit){
		int left = 0;
		int right = unsort.length - 1;
		int[] count = new int[radix];//存放各个桶中数据的个数
		int[] bucket = new int[right - left + 1];
		for(int d = 0; d <= digit; d++){
			
			//对各个桶中所含的数据进行初始化
			for(int i = 0; i < radix; i++){
				count[i] = 0;
			}
			
			// 统计各个桶将要装入的数据个数
			for(int i = left; i <= right; i++){
				count[getDigit(unsort[i], d)]++;
			}
			
			// count[i]表示第i个桶的右边界索引
			for (int i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }

			// 将数据依次装入桶中
            // 这里要从右向左扫描，保证排序稳定性
            for (int i = right; i >= left; i--) {
                int j = getDigit(unsort[i], d); // 求出关键码的第k位的数字， 例如：576的第3位是5
                bucket[count[j] - 1] = unsort[i]; // 放入对应的桶中，count[j]-1是第j个桶的右边界索引
                count[j]--; // 对应桶的装入数据索引减一
            }
 
            // 将已分配好的桶中数据再倒出来，此时已是对应当前位数有序的表
            for (int i = left, j = 0; i <= right; i++, j++) {
            	unsort[i] = bucket[j];
            }
		}
		
	
	}
	
	public static void main(String[] args) {
		RadixSort r = new RadixSort();
		int[] unsort = {
                50, 123, 543, 187, 49, 30, 0, 2, 11, 100
        };
		r.radix(unsort, 3);
		for (int value : unsort) {
            System.out.print(value + "\t");
        }
		//System.out.println(r.getDigit(123, 3));
	}

}
