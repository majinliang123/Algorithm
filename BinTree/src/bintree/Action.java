package bintree;

import java.util.Vector;
import bintree.BinTree.BinTreeNode;

public class Action {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinTree tree = new BinTree();
		//BinTreeNode root  = tree.createTreeByGeneralizedList("a(b(d,e(g,)),c(,f))");
		//tree.preOrder(root);
		//tree.inorder(root);
		//tree.postOrder(root);
		//System.out.println(tree.size(root));
		//System.out.println(tree.height(root));
		
		/*主要测试createBinTreeByPreOrder方法
		String str = "abc##de#g##f###";
		Vector<String> vector = new Vector<String>();
		for(int i = 0; i < str.length(); i++ ){
			vector.add(str.substring(i, i+1));
		}
		tree.inorder(tree.createBinTreeByPreOrder(vector));
		*/
		//tree.printBinTreeByGeneralizedList(root);
		//tree.levelOrder();
		//tree.printByCatalog(root, 0);
		//tree.preOrder(tree.createBinTreeByPreInOrder("abdce", "bdaec", 5));
		//tree.preOrderNoRecurve();
	}

}
