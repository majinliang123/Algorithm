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
