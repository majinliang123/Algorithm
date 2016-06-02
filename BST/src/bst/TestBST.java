package bst;


public class TestBST {

	public static void main(String[] args) {

		BST b = new BST();
		System.out.println(b.search(3));
		b.insert(3);
		b.insert(4);
		System.out.println(b.search(4));
	}

}
