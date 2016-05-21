package yanghvi;
/**
 * 实现队列的操作
 * 进队列操作
 * 出队列操作
 * @author majinliang
 *
 */
public class Queue {

	/*
	 * 定义类中节点
	 */
	public class Node{
		private int data;
		private Node next;
		
		public Node(int d, Node n){
			data = d;
			next = n;
		}
	}
	
	//开始对Queue的定义
	private Node head;
	private Node rear;
	private int size;
	
	public Queue(){
		head = rear = null;
		size = 0;
	}
	
	public void EnQueue(int d){
		if(head == null && rear == null){
			size++;
			head = rear = new Node( d, null);
		}else{
			size++;
			Node node = new Node(d,null);
			rear.next = node;
			rear = node;
		}
	}
	
	public int DeQueue(){
		int temp= head.data;
		head = head.next;
		size--;
		return temp;
	}
	
	public int getSize(){
		return size;
	}
}
