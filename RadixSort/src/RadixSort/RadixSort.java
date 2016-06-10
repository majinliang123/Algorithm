package RadixSort;


/**
 * LSD��������,���������һ��
 * LSD(least significant digit first)���λ����
 * @author majinliang
 *
 */
public class RadixSort {

	final int radix = 10;//����
	
	/*
	 * ��ȡx�������dλ���ϵ�����
	 * �����ȡ123��1λ�����������3
	 */
	public int getDigit(int x, int d){
		 int a[] = {1, 1, 10, 100}; 
	     return ((x / a[d]) % 10);
	}
	/*
	 * �Ӹ�λ����λ�����л��֣�ʵ������
	 * dָ���ǵڼ�λ��
	 * left��right�Ǵ��������������е�ʼ�˺�β��
	 */
	public void radix(int[] unsort, int digit){
		int left = 0;
		int right = unsort.length - 1;
		int[] count = new int[radix];//��Ÿ���Ͱ�����ݵĸ���
		int[] bucket = new int[right - left + 1];
		for(int d = 0; d <= digit; d++){
			
			//�Ը���Ͱ�����������ݽ��г�ʼ��
			for(int i = 0; i < radix; i++){
				count[i] = 0;
			}
			
			// ͳ�Ƹ���Ͱ��Ҫװ������ݸ���
			for(int i = left; i <= right; i++){
				count[getDigit(unsort[i], d)]++;
			}
			
			// count[i]��ʾ��i��Ͱ���ұ߽�����
			for (int i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }

			// ����������װ��Ͱ��
            // ����Ҫ��������ɨ�裬��֤�����ȶ���
            for (int i = right; i >= left; i--) {
                int j = getDigit(unsort[i], d); // ����ؼ���ĵ�kλ�����֣� ���磺576�ĵ�3λ��5
                bucket[count[j] - 1] = unsort[i]; // �����Ӧ��Ͱ�У�count[j]-1�ǵ�j��Ͱ���ұ߽�����
                count[j]--; // ��ӦͰ��װ������������һ
            }
 
            // ���ѷ���õ�Ͱ�������ٵ���������ʱ���Ƕ�Ӧ��ǰλ������ı�
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
