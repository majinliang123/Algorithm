package bst;
/**
 * 二叉搜索树
 * 实现方法
 * 搜索
 * 插入
 * 起初打算使用递归实现各种操作，但是发现递归实现由很多问题
 * 因为java使用的是值传递，这样就会导致很多不方便的操作
 * 如果使用迭代，就会避免这些问题的出现
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
	
	//搜索树中有没有这个节点
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
	
	//插入
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
