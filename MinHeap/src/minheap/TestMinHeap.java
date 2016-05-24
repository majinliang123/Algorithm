package minheap;

public class TestMinHeap {

	public static void main(String[] args) {
		int[] arr = {6,5,4,3,2,1};
		MinHeap heap = new MinHeap(6, arr);
		System.out.println(heap.remove());
		/*System.out.println(heap.remove());
		System.out.println(heap.remove());
		System.out.println(heap.remove());
		System.out.println(heap.remove());
		System.out.println(heap.remove());*/
		heap.insert(8);
		System.out.println(heap.remove());

	}

}
