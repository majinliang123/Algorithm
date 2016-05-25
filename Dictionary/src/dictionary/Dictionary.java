package dictionary;
/**
 * �ֵ�
 * ʵ�ֵķ�����
 * ����
 * ����
 * ɾ��
 * ��ʹ���������ʵ��
 * @author majinliang
 *
 */
public class Dictionary {

	public class DictionaryNode{
		private int data;
		private DictionaryNode next;
		
		public DictionaryNode(){
			next = null;
		}	
	}
	
	private DictionaryNode head;
	public Dictionary(){
		head = null;
	}
	
	/*
	 *��������
	 *����Ѿ�����
	 *�Ͳ��ܲ���
	 */
	public void insert(int d){
		if(head == null){
			head = new DictionaryNode();
			head.data = d;
			head.next = null;
		}else{
			DictionaryNode current = head;
			
			while(current != null){
				if(current.data <= d){
					System.out.println("���ڣ����ܲ���");
					return ;
				}			
				current = current.next;
			}
			current = head;
			if(d < head.data){
				DictionaryNode temp = head;
				head = new DictionaryNode();
				head.data = d;
				head.next = temp;
			}else{
				DictionaryNode pre = head;
				current = head.next;
				while(current != null){
					if(current.data > d && pre.data < d){
						DictionaryNode temp = new DictionaryNode();
						temp.data = d;
						pre.next = temp;
						temp.next = current;
					}
					pre = current;
					current = current.next;
				}			
			}		
		}
	}
	
	/*
	 * ������������
	 */
	public boolean search(int d){
		DictionaryNode current = head;
		while(current != null){
			if(current.data == d)
				return true;
			current = current.next;
		}
		return false;
	}
	
	public void remove(int d){
		if(search(d)){
			DictionaryNode current = head;
			DictionaryNode pre = head;
			if(head.data == d){
				head = head.next;
			}else{
				while(current !=null){
					if(current.data == d){
						pre.next = current.next;
					}
					current = current.next;
				}
			}			
		}else{
			System.out.println("�ֵ���û����������");
		}
	}
}
