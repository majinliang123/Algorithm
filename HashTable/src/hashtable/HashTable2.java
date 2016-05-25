package hashtable;

/**
 * �Կ�ɢ�е�hashtable
 * ʵ�ֲ���
 * ����
 * ����
 * ɾ��
 * ���������ʽ�洢
 * @author majinliang
 *
 */
public class HashTable2 {

	public class HashTableNode{
		private int data;
		private HashTableNode next;
		
		public HashTableNode(){
			next = null;
			data = -1;
		}		
	}
	
	private int size;
	private int divitor;
	private HashTableNode[] ht;
	public HashTable2(int d, int s){
		divitor = d;
		size = s;
		ht = new HashTableNode[size];
		for(int i = 0; i < size; i++){
			ht[i] = new HashTableNode();
		}
	}
	
	public HashTableNode findPos(int e){
		int j = e % divitor;
		HashTableNode node = ht[j];
		HashTableNode pre = node;
		while(node != null && node.data != e){
			pre = node;
			node = node.next;
		}
		return pre;
	}

	public boolean search(int e){
		HashTableNode node = findPos(e);
		if(node.data==e){
			return true;
		}else
			return false;
	}
	
	public boolean insert(int e){
		HashTableNode node = findPos(e);
		int j = e % divitor;
		if(node.data==e){
			System.out.println("Ԫ���Ѿ�����");
			return false;
		}else{
			ht[j].data = e;
			return true;
		}
	}
	
	public boolean remove(int e){
		HashTableNode node = findPos(e);
		if(node.data == e){
			int j = e % divitor;
			node = ht[j];
			HashTableNode pre = node;
			while(node.data != e){
				pre = node;
				node = node.next;
			}
			pre.next = node.next;
			return true;
		}else{
			System.out.println("Ԫ�ز�����");
			return false;
		}
	}
}
