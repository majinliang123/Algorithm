package minheap;
/**
 * 堆：
 * 是一种高效率的优先级队列
 * 是以二叉树的形式显示的
 * 以下是实现的最小堆
 * 最上面的元素是所有元素中最小的
 * 使用的是数组
 * 利用完全二叉树父节点和子女节点之间的关系
 * 如果 i = 0，节点 i 是根节点，无父节点，否则节点 i 的父节点为 (i-1)/2的向下取整
 * 如果 2i + 1 > n - 1,则节点i无左子女，否则左子女节点为 2i + 1
 * 如果 2i + 2 > n - 1,则节点i无右子女，否则左子女节点为 2i + 2
 * @author majinliang
 *
 */
public class MinHeap {

	private int maxSize;
	private int currentSize;
	private int[] heap;
	
	public MinHeap(int max, int[] arr){
		maxSize = max;
		currentSize = arr.length;
		heap = new int[maxSize];
		for(int i = 0; i < currentSize; i++ ){
			heap[i] = arr[i];
		}
		int currentPosition = (currentSize - 2)/2;
		while(currentPosition >= 0){
			siftDown(currentPosition, currentSize - 1);
			currentPosition--;
		}
	}
	
	/*
	 * 最小堆得下滑调整算法
	 */
	private void siftDown(int start, int size){
		int i = start;
		int j = start * 2 + 1;
		int temp = heap[i];
		while(j <= size){
			if( j < size && heap[j] > heap[j+1] ) 
				j++;
			if(heap[i] < heap[j])
				break;
			else{
				heap[i] = heap[j];
				i = j;
				j =  2 * j + 1;
			}
			heap[i] = temp;
		}
	}
	
	/*
	 * 堆的上滑调整算法
	 */
	private void siftUp(int start){
		int j = start;
		int i = (j-1)/2;
		int temp = heap[j];
		while(j > 0){
			if(heap[i] <= temp)
				break;
			else{
				heap[j] = heap[i];
				j = i;
				i = (i-1)/2;
			}
			heap[i] = temp;
		}
	}
	
	public boolean insert(int a){
		if(currentSize == maxSize)
			return false;
		heap[currentSize] = a;
		currentSize++;
		siftUp(currentSize-1);
		return true;
	}
	
	public int remove(){
		int temp;
		if(currentSize != 0 ){
			temp = heap[0];
			heap[0] = heap[currentSize-1];
			currentSize--;
			siftDown(0, currentSize);
			return temp;
		}
		return 0;
	}
}
