package bst;
/**
 * ����������
 * ʵ�ַ���
 * ����
 * ����
 * �������ʹ�õݹ�ʵ�ָ��ֲ��������Ƿ��ֵݹ�ʵ���ɺܶ�����
 * ��Ϊjavaʹ�õ���ֵ���ݣ������ͻᵼ�ºܶ಻����Ĳ���
 * ���ʹ�õ������ͻ������Щ����ĳ���
 * @author majinliang
 *
 */
public class BST {

	public class BSTNode{
		private int data;
		private BSTNode leftChild;
		private BSTNode rightChild;
		
		public BSTNode(int d){
			data = d;
			leftChild = null;
			rightChild = null;
		}
	}
	
	private BSTNode root;
	public BST(){
		root = null;
	}
	
	public  BSTNode getRoot(){
		return root;		
	}
	
	//����������û������ڵ�
	public BSTNode search(int d){
		BSTNode currentNode  = root;
		while(currentNode != null && currentNode.data != d){
			if(currentNode.data > d){
				currentNode = currentNode.leftChild;
			}else{
				currentNode = currentNode.rightChild;
			}
		}
		return currentNode;
	}
	
	//����
	public void insert(int d){
		if(search(d) != null){
			return ;
		}else{
			BSTNode currentNode  = root;
			if(root == null){
				root = new BSTNode(d);
			}
			while(currentNode != null){
				if(currentNode.data > d){
					if(currentNode.leftChild != null){
						currentNode = currentNode.leftChild;
					}else{
						currentNode.leftChild = new BSTNode(d);
						break;
					}
					
				}else{
					if(currentNode.rightChild != null){
						currentNode = currentNode.rightChild;
					}else{
						currentNode.rightChild = new BSTNode(d);
						break;
					}
				}
			}
		}
	}
}
