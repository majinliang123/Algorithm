/*
 * ð���㷨
 * �㷨˼�룺
 * ����λ���ҵ���Ҫ������
 * �ȿ���һ��λ���ϵ���������к�������������С
 * �ͽ��н�����ֱ������β
 * �ٿ��ڶ���λ��
 * һ������
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
		// TODO �Զ����ɵķ������
		 int[] x = { 6, 2, 4, 1, 5, 9 };
		 BubbleSortMethod b = new BubbleSortMethod();
         b.bubble_sort(x);
		
	}
}
