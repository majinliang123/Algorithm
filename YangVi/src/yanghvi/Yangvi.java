package yanghvi;
/**
 * ʵ��������ǵĲ���
 * @author majinliang
 *
 */
public class Yangvi {
	public static void main(String[] args) {
		
		Queue q = new Queue();
		
		int n = 6;//�涨�����յĲ���
		
		int i = 1;
		int j,temp;
		int s =0;
		
		q.EnQueue(i);
		q.EnQueue(i);
		
		for(i = 1; i < n; i++){
			s = 0;
			q.EnQueue(0);
			for(j = 1; j <= i + 2; j++){
				temp = q.DeQueue();
				q.EnQueue(s + temp);
				s = temp;
			}
		}
		int size = q.getSize();
		for(int k = 0;k < size; k++){
			System.out.println(q.DeQueue());
		}
	}
}
