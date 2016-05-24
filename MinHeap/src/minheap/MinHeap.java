package minheap;
/**
 * �ѣ�
 * ��һ�ָ�Ч�ʵ����ȼ�����
 * ���Զ���������ʽ��ʾ��
 * ������ʵ�ֵ���С��
 * �������Ԫ��������Ԫ������С��
 * ʹ�õ�������
 * ������ȫ���������ڵ����Ů�ڵ�֮��Ĺ�ϵ
 * ��� i = 0���ڵ� i �Ǹ��ڵ㣬�޸��ڵ㣬����ڵ� i �ĸ��ڵ�Ϊ (i-1)/2������ȡ��
 * ��� 2i + 1 > n - 1,��ڵ�i������Ů����������Ů�ڵ�Ϊ 2i + 1
 * ��� 2i + 2 > n - 1,��ڵ�i������Ů����������Ů�ڵ�Ϊ 2i + 2
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
	 * ��С�ѵ��»������㷨
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
	 * �ѵ��ϻ������㷨
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
