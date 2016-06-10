package insertionsort;
/*
 * 插入排序
 * 插入排序有三种
 * 1.直接插入排序
 * 2.折半插入排序
 * 3.希尔排序
 *
 * 这里实现的是直接插入排序
 * 
 * 直接插入排序算法思想：
 * 每次将一个待排序的记录
 * 按其关键字大小插入到前面已经排好序的子序列中的适当位置
 * 直到全部记录插入完成为止
 * 
 * 希尔排序算法思想
 *  先取一个小于n的整数d1作为第一个增量，把文件的全部记录分成d1个组。
 *  所有距离为dl的倍数的记录放在同一个组中。先在各组内进行直接插人排序；
 *  然后，取第二个增量d2<d1重复上述的分组和排序，直至所取的增量dt=1(dt<dt-l<…<d2<d1)，
 *  即所有记录放在同一组中进行直接插入排序为止。
    　该方法实质上是一种分组插入方法。
    
    折半插入排序其实就是在插入搜索比自己小的元素的时候使用折半搜索的方法，其他的和直接插入排序一样
 */
public class Insertionsort {
	
	void sort(int[] x){
		for(int i = 1; i < x.length; i++){
			if(x[i] < x[i - 1]){
				int temp = x[i];
				int a = i;
				for(a = i; a > 0 && temp < x[a - 1]; a--){
					x[a] = x[a -1];
				}
				x[a] = temp;
			}
				
		}
	}

	public static void main(String[] args) {
		int[] x = { 6, 2, 4, 1, 5, 9 };
		Insertionsort b = new Insertionsort();
		b.sort(x);
		for(int i = 0; i < x.length; i++){
			System.out.println(x[i]);
		}
	}

}
