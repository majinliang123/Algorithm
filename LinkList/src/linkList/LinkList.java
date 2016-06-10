package linkList;

/**
 * �������ʹ��
 * ��Ҫʵ����������ݺ�ɾ������
 * ��ӡ���������еĽڵ�
 * @author majinliang
 *
 */
public class LinkList {
	
	private LinkNode head;
	private LinkNode current;
	private int size;
	
	//�ڵ����
	public class LinkNode{		
		public int data;
		public LinkNode next;
		public LinkNode(int a,LinkNode n){
			next = n;
			data = a;
		}
		
		 public int getData(){
			 return data;
		 }
		 
		 public LinkNode getNext(){
			 return next;
		 }
	}
	
	//���캯��
	public  LinkList(){
		head = current = null;
		size = 0;
	}

	/*
	 * i ��ʾλ��, ��ʾ�ڵ� i��Ԫ��ǰ��������
	 * ע�⵱ i Ϊ1ʱ����Ϊlist�еĵڶ���Ԫ�أ� i Ϊ0ʱ����һ��Ԫ�� 
	 * x ��ʾ��������ֵ
	 */
	public boolean insert(int i, int x){
		current = head;
		if(head == null || size == 0){
			if(i == 0){
				LinkNode node = new LinkNode(x, null);
				head = node;
				size++;
			}else{
				return false;
			}		
		}else{
			for(int k = 1; k < i; k++){
				if(current == null){
					return false;
				}else{
					current = current.next;
				}		
			}
			if(current == null){
				return false;
			}else{
				LinkNode n = new LinkNode(x, current.next);
				current.next = n;
				size++;
			}
		}
		return true;
	}
	
	//ɾ��λ�� i �ϵ�Ԫ��
	public boolean delete(int i){
		current = head;
		if(head == null || size == 0){
			return false;
		}else{		
			if(i == 0){
				head = head.next;
				size--;
			}else{
				for(int k = 0; k < i; k++){
					if(current == null){
						return false;
					}else{
						current.next = current.next.next;
					}
				}
			}
		}
		return true;
	}
	
	 public int getSize(){
		 return size;
	 }
	 
	 public LinkNode getHead(){
		 return head;
	 }
	 
	public void printList(){
		current = head;
		while(current != null){
			System.out.println(current.data);
			current = current.next;
		}
		
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		LinkList list = new LinkList();
		list.insert(0, 2);
		list.insert(1, 3);
		list.insert(1, 4);
		list.insert(2, 5);
		int size = list.getSize();
		list.printList();
		
		list.delete(1);
		list.printList();
	}
	

}
