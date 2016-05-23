package bintree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

/**
 * �Զ������Ĳ���
 * 1.������������������
 * 2.ǰ�����򣬺������������
 * 3.����������нڵ�ĸ���
 * 4.����������ĸ߶�
 * 5.����ǰ���������������������
 * 6.�Թ�������ʽ���������
 * 7.����ջ��ǰ��������Ƿǵݹ��㷨
 * 8.��α���������
 * 9.�����������Ŀ¼�ṹ����ʽ��ʾ
 * 10.����ǰ��������������������п�����#��ȫ
 * @author majinliang
 *
 */
public class BinTree {

	/*
	 *���нڵ�Ķ���
	 */
	public class BinTreeNode{
		private BinTreeNode leftChild;
		private BinTreeNode rightChild;
		private String data;
		
		public BinTreeNode(String d){
			data = d;
		}
		
		public BinTreeNode getLeftChile(){
			return leftChild;
		}
		
		public BinTreeNode getRightChild(){
			return rightChild;
		}
		
		public String getData(){
			return data;
		}
	}
	
	
	/*
	 * �Զ������Ķ��忪ʼ
	 */
	private BinTreeNode root;
	public BinTree(){
		root = null;
	}
	
	public BinTreeNode getRoot(){
		return root;
	}
	
	/*
	 * ��������Ĺ������������
	 * ���磺a(b(d,e(g,)),c(,f))
	 */
	public BinTreeNode createTreeByGeneralizedList(String str){
		int type = 0;//��������ڵ㻹���ֽڵ㣬1������ڵ㣬2�����ҽڵ�
		Stack<BinTreeNode> stack = new Stack<BinTreeNode>();//�洢�ڵ�
		String currentString;
		BinTreeNode currentNode = null;
		for(int i = 0; i < str.length(); i++){
			currentString = str.substring(i, i+1);
			
			switch(currentString){
				case "(":
					stack.push(currentNode);
					type = 1;
					break;
				case ")":
					stack.pop();
					break;
				case ",":
					type = 2;
					break;
				default:
					currentNode = new BinTreeNode(currentString);
					if(root == null){
						root = currentNode;
					}else{
						
						if(type == 1){
							
							((BinTreeNode) stack.peek()).leftChild = currentNode;
						}else{
							((BinTreeNode) stack.peek()).rightChild = currentNode;
						}		
					}
					break;
			}
		}	
		return root;
	}
	
	/*
	 * ǰ�����������
	 */
	public void preOrder(BinTreeNode root){
		System.out.println(root.getData());
		if(root.leftChild != null){
			preOrder(root.leftChild);
		}
		if(root.rightChild != null){
			preOrder(root.rightChild);
		}
	}
	
	/*
	 * �������������
	 */
	public void inorder(BinTreeNode root){		
		if(root.leftChild != null){
			inorder(root.leftChild);
		}
		
		System.out.println(root.getData());
		
		if(root.rightChild != null){
			inorder(root.rightChild);
		}
	}
	
	/*
	 * �������������
	 */
	public void postOrder(BinTreeNode root){
		if(root.leftChild != null){
			postOrder(root.leftChild);
		}					
		if(root.rightChild != null){
			postOrder(root.rightChild);
		}
		System.out.println(root.getData());
	}
	
	/*
	 *��ʾ�ڵ�ĸ��� 
	 */
	public int size(BinTreeNode root){
		if(root == null)
			return 0;
		else{
			return 1 + size(root.leftChild) + size(root.rightChild);
		}
	}
	
	/*
	 * ��ʾ���ĸ߶�
	 */
	public int height(BinTreeNode root){
		if(root == null)
			return 0;
		else{
			int i = height(root.leftChild);
			int j = height(root.rightChild);
			return (i < j) ? j + 1 : i + 1;
		}
	}
	
	/*
	 * ����ǰ���������������
	 * ���Ĳ���ʹ��#��ʾ
	 * ���磺abc##de#g##f###
	 */
	public BinTreeNode createBinTreeByPreOrder(Vector<String> vector){
		String current = vector.elementAt(0);
		vector.remove(0);
		BinTreeNode tree = null;
		if(!vector.isEmpty()){
			if(current.equals("#")){
				tree = null;
			}else{
				tree = new BinTreeNode(current);			
				tree.leftChild = createBinTreeByPreOrder(vector);
				tree.rightChild = createBinTreeByPreOrder(vector);
			}
		}	
		return tree;
	}
	
	/*
	 * �Թ�������ʽ���������
	 */
	public void printBinTreeByGeneralizedList(BinTreeNode tree){
		if(tree != null){
			System.out.print(tree.data);
			if(tree.leftChild != null || tree.rightChild != null){
				System.out.print("(");
				printBinTreeByGeneralizedList(tree.leftChild);
				System.out.print(",");
				printBinTreeByGeneralizedList(tree.rightChild);
				System.out.print(")");
			}
		}
	}
	
	/*
	 * ��α���������
	 * ��Ҫʹ����һ������
	 * �����õݹ�
	 */
	public void levelOrder(){
		Queue<BinTreeNode> queue = new LinkedList<BinTreeNode>();
		queue.offer(root);
		while(!queue.isEmpty()){
			BinTreeNode head = queue.poll();
			System.out.println(head.data);
			if(head.leftChild != null)
				queue.offer(head.leftChild);
			if(head.rightChild != null)
				queue.offer(head.rightChild);
		}
	}
	
	/*
	 * �����������Ŀ¼�ṹ����ʽ��ʾ
	 * ʹ���˵ݹ����
	 */
	public void printByCatalog(BinTreeNode tree,int blk){
		for(int i=0;i<blk;i++){
			System.out.print("    ");
		}
		if(tree != null){
			System.out.println("|--<"+tree.data+">");
			if(tree.leftChild != null)
				printByCatalog(tree.leftChild, blk + 1);
			if(tree.rightChild != null)
				printByCatalog(tree.rightChild, blk + 1);		
		}
	}
	/*
	 * ����ǰ���������������������
	 */
	public BinTreeNode createBinTreeByPreInOrder(String preorder, String inorder, int n){
		if(n == 0)
			return null;
		int i = 0 ;	
		while(!preorder.substring(0, 1).equals(inorder.substring(i, i + 1)))
			i++;
		BinTreeNode tree = new BinTreeNode(preorder.substring(0, 1));
		tree.leftChild = createBinTreeByPreInOrder(preorder.substring(1), inorder, i);
		tree.rightChild = createBinTreeByPreInOrder(preorder.substring(i + 1), inorder.substring(i + 1), n - i -1);
		return tree;
	}
	
	/*
	 * �����������ķǵݹ��㷨
	 */
	public void preOrderNoRecurve(){
		Stack<BinTreeNode> stack = new Stack<>();
		BinTreeNode tree = root;
		stack.push(null);
		while(tree != null){
			System.out.println(tree.data);
			if(tree.rightChild != null)
				stack.push(tree.rightChild);
			if(tree.leftChild != null){
				tree = tree.leftChild;
			}else{
				tree = stack.pop();
			}
		}
	}
	
}
