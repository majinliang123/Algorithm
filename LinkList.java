package linkList;

/**
 * 单链表的使用
 * 主要实现了添加数据和删除数据
 * 打印链表中所有的节点
 * @author majinliang
 *
 */
public class LinkList {
	
	private LinkNode head;
	private LinkNode current;
	private int size;
	
	//节点的类
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
	
	//构造函数
	public  LinkList(){
		head = current = null;
		size = 0;
	}

	/*
	 * i 表示位置, 表示在第 i个元素前插入数据
	 * 注意当 i 为1时，即为list中的第二个元素， i 为0时，是一个元素 
	 * x 表示想插入的数值
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
	
	//删除位置 i 上的元素
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
		// TODO 自动生成的方法存根
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
