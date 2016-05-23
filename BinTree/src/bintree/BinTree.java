package bintree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

/**
 * 对二叉树的操作
 * 1.输入广义表，建立二叉树
 * 2.前序，中序，后序遍历二叉树
 * 3.计算二叉树中节点的个数
 * 4.计算二叉树的高度
 * 5.利用前序，中序遍历，建立二叉树
 * 6.以广义表的形式输出二叉树
 * 7.利用栈的前序遍历，是非递归算法
 * 8.层次遍历二叉树
 * 9.输出二叉树以目录结构的形式表示
 * 10.利用前序遍历建立二叉树，其中空树以#补全
 * @author majinliang
 *
 */
public class BinTree {

	/*
	 *树中节点的定义
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
	 * 对二叉树的定义开始
	 */
	private BinTreeNode root;
	public BinTree(){
		root = null;
	}
	
	public BinTreeNode getRoot(){
		return root;
	}
	
	/*
	 * 利用输入的广义表建立二叉树
	 * 例如：a(b(d,e(g,)),c(,f))
	 */
	public BinTreeNode createTreeByGeneralizedList(String str){
		int type = 0;//标明是左节点还是又节点，1代表左节点，2代表右节点
		Stack<BinTreeNode> stack = new Stack<BinTreeNode>();//存储节点
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
	 * 前序遍历二叉树
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
	 * 中序遍历二叉树
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
	 * 后序遍历二叉树
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
	 *显示节点的个数 
	 */
	public int size(BinTreeNode root){
		if(root == null)
			return 0;
		else{
			return 1 + size(root.leftChild) + size(root.rightChild);
		}
	}
	
	/*
	 * 显示树的高度
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
	 * 利用前序遍历建立二叉树
	 * 填充的部分使用#表示
	 * 例如：abc##de#g##f###
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
	 * 以广义表的形式输出二叉树
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
	 * 层次遍历二叉树
	 * 主要使用了一个队列
	 * 不适用递归
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
	 * 输出二叉树以目录结构的形式表示
	 * 使用了递归调用
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
	 * 利用前序，中序遍历，建立二叉树
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
	 * 二叉树遍历的非递归算法
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
