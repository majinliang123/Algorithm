package bubblingSort;
/*ð������
 * �㷨˼�룺
 * ������ĵ�һ��λ�ÿ�ʼ�����Ƚ�array[index]��array[index+1]
 * ���array[index]����array[index+1]�򽻻�array[index]��array[index+1]��λ��
 * ֹ�����������
 */
public class BubblingSort {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
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
