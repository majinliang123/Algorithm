/*
 * ��������
 * �㷨˼�룺
 * �ҳ�һ��Ԫ����Ϊ��׼
 * Ȼ���������з�������
 * ʹ��׼���Ԫ�ص�ֵ�������ڻ�׼ֵ
 * ��׼�ұߵ�Ԫ��ֵ ����С�ڻ�׼ֵ
 * �����Ϊ��׼��Ԫ�ص�������������ȷλ��
 * �������ֵ��ȵķ�����
 * �Լ��롣����
 */
public class Quicksort {
	void quicksort(int[] v, int left, int right){
       int high = right;
       int low = left;
       int key = v[low];
       if(low < high){
    	     while(low < high){
    	    	   while(low < high && v[high] > key){
    	    		   high--;
    	    	   }
    	    	   v[low] = v[high];
    	    	   while(low < high && v[low] < key){
    	    		   low++;
    	    	   }
    	    	   v[high] = v[low];
    	       }
    	       v[low] = key;
    	       quicksort(v, left, low-1);
    	       quicksort(v, low+1, right);
       }
  
	}
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		 int[] x = { 6, 2, 4, 1,0, 5, 9 ,12};
		 Quicksort q = new Quicksort();
		 q.quicksort(x, 0, 5);
		 for(int i = 0; i < x.length; i++){
	        	System.out.println(x[i]);
	      }
	}

}
