package dictionary;
/**
 * 字典
 * 实现的方法：
 * 搜索
 * 插入
 * 删除
 * 我使用链表进行实现
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
	 *插入数据
	 *如果已经有了
	 *就不能插入
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
					System.out.println("存在，不能插入");
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
	 * 进行搜索操作
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
			System.out.println("字典中没有这项数据");
		}
	}
}
