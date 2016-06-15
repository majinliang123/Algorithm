package BTree;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BTree  b = new BTree(2);
		b.insert(53);
		b.insert(75);
		b.insert(139);
		b.insert(49);	
		b.insert(3);
		b.print();
		b.delete(3);
		b.delete(49);
		b.print();
	}

}
