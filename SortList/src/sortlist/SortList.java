package sortlist;
/**
 * 主要实现
 * 基于有序顺序表的顺序搜索和折半搜索
 * 使用从小到大的顺序
 * 实现功能：
 * 插入
 * 顺序搜索
 * 折半搜索
 * @author majinliang
 *
 */
public class SortList {

	private int[] element;
	private int maxSize;
	private int currentSize;
	
	public SortList(int size){
		maxSize = size;
		currentSize = 0;
		element = new int[maxSize];
	}
	
	
	public void insert(int e){
		if(currentSize < maxSize){
			int i = 1;
			while(i <= currentSize && element[i - 1] <= e){
				i++;
			}
			for(int j = currentSize; j >= i; j--){
				element[j] = element[j - 1];
			}
			element[i - 1] = e;
			currentSize++;
		}
	}
	
	
	//顺序搜索
	public int sequentSearch(int e){
		for(int i = 1; i <= currentSize; i++){
			if(element[ i - 1] == e)
				return i;
			if(element[ i - 1] > e)
				break;
		}
		return 0;
	}
	
	//折半搜索
	//使用的是非递归算法，也可以使用递归算法
	public int binarySearch(int e){
		int high = currentSize - 1;
		int low = 0;
		int mid;
		while(low <= high){
			mid = (low + high) / 2;
			if(e > element[mid]){
				low = mid + 1;
			}else if(e < element[mid]){
				high = mid - 1;
			}else{
				return mid + 1;
			}
		}
		return 0;
	}
}
