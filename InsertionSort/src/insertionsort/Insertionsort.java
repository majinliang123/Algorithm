package insertionsort;
/*
 * ��������
 * ��������������
 * 1.ֱ�Ӳ�������
 * 2.�۰��������
 * 3.ϣ������
 *
 * ����ʵ�ֵ���ֱ�Ӳ�������
 * 
 * ֱ�Ӳ��������㷨˼�룺
 * ÿ�ν�һ��������ļ�¼
 * ����ؼ��ִ�С���뵽ǰ���Ѿ��ź�����������е��ʵ�λ��
 * ֱ��ȫ����¼�������Ϊֹ
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
